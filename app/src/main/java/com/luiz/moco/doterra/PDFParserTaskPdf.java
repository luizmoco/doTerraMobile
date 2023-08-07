package com.luiz.moco.doterra;

import android.content.Context;
import android.os.AsyncTask;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PDFParserTaskPdf extends AsyncTask<InputStream, Void, List<Produto>> {

    private Context context;
    private OnParsingCompleteListener listener;

    public PDFParserTaskPdf(Context context, OnParsingCompleteListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Produto> doInBackground(InputStream... params) {
        List<Produto> produtos = new ArrayList<>();

        try {
            InputStream inputStream = params[0];
            PdfReader reader = new PdfReader(inputStream);

            int numPages = reader.getNumberOfPages();

            for (int page = 1; page <= numPages; page++) {
                String pdfText = PdfTextExtractor.getTextFromPage(reader, page);

                String[] linhas = pdfText.split("\n");
                produtos.addAll(parse(linhas));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        super.onPostExecute(produtos);
        if (listener != null) {
            listener.onParsingComplete(produtos);
        }
    }

    public interface OnParsingCompleteListener {
        void onParsingComplete(List<Produto> produtos);
    }

    public static List<Produto> parse(String[] linhaArquivo) throws IOException {
        String[] products = manipulaProdutos(linhaArquivo);
        List<Produto> produtos = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            String linha = products[i];
            if (linha == null) {
                return produtos;
            }
            if (linha.length() >= 5 && inicioECodigo(linha)) {
                int posicaoPrecoRegular = linha.indexOf("$");
                int posicaoPrecoMembro = posicaoPrecoRegular;
                posicaoPrecoMembro = linha.indexOf("$", posicaoPrecoRegular + 1);
                if (posicaoPrecoRegular > 0 && posicaoPrecoMembro > 0) {
                    Produto p = getProduto(linha);
                    produtos.add(p);
                } else {
                    int count = 0;
                    while ((posicaoPrecoRegular <= 0 && posicaoPrecoMembro <= 0) || count == 10) {
                        i++;
                        linha = linha.concat(products[i]);
                        posicaoPrecoRegular = linha.indexOf("$");
                        posicaoPrecoMembro = 0;
                        posicaoPrecoMembro = linha.indexOf("$", posicaoPrecoRegular + 1);
                        count++;
                    }
                    if (count == 10) {
                        System.out.println(linha);
                    } else {

                        Produto p = getProduto(linha);
                        produtos.add(p);
                    }

                }
            }
        }

        for (Produto prd : produtos) {
            System.out.println(prd.toString());
        }

        return produtos;
    }

    private static String[] manipulaProdutos(String[] linhas) {
        String[] retorno = new String[1000];
        int j = 0;
        for(int i = 0; i < linhas.length; i++) {
            String linha = linhas[i];
            if (checkStringForRegex(linha, "\\b\\d{8}\\b")) {
                String[] split = linha.split("\\s(?=\\d{8}\\b)");
                if (split.length > 1) {
                    for (int k = 0; k < split.length; k++) {
                        retorno[j] = split[k];
                        j++;
                    }
                } else if (split.length == 1) {
                    retorno[j] = split[0];
                    j++;
                }
            }
        }
        return retorno;
    }

    private static boolean inicioECodigo(String linha) {
        try {
            String inicio = linha.substring(0, 5);
            Integer.parseInt(inicio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Produto getProduto(String linha) {
        String linhaOriginal = linha;
        try {
            if (linha.indexOf("R$") < 0) {
                Integer codigo = getCodigo(linha);
                linha = linha.replace(codigo.toString(), "");
                int posicaoPrecoRegular = linha.indexOf("$");
                int posicaoPrecoMembro = posicaoPrecoRegular;
                posicaoPrecoMembro = linha.indexOf("$", posicaoPrecoRegular + 1);

                String pRegular = linha.substring(posicaoPrecoRegular, posicaoPrecoMembro);

                linha = linha.replace(pRegular, "");

                String pMembro;
                BigDecimal precoMembro;
                posicaoPrecoMembro = linha.indexOf("$");
                int posicaoPrecoMembroFim = linha.indexOf(",00");
                if (posicaoPrecoMembroFim <= 0) {
                    posicaoPrecoMembroFim = linha.indexOf(", 00") + 1;
                    pMembro = linha.substring(posicaoPrecoMembro, posicaoPrecoMembroFim + 3);
                    precoMembro = getBigDecimal(pMembro);
                } else {
                    pMembro = linha.substring(posicaoPrecoMembro, posicaoPrecoMembroFim + 3);
                    precoMembro = getBigDecimal(pMembro);
                }


                linha = linha.replace(pMembro, "");

                String pvStr = linha.substring(linha.length() - 3);

                linha = linha.replace(pvStr, "");

                String produto = linha.trim();
                BigDecimal precoRegular = getBigDecimal(pRegular);
                BigDecimal pv = new BigDecimal(pvStr.trim());

                Produto p = new Produto();
                p.setCodigo(codigo);
                p.setProduto(produto);
                p.setPrecoRegular(precoRegular);
                p.setPrecoMembro(precoMembro);
                p.setPv(pv);

                return p;
            } else {

                Integer codigo = getCodigo(linha);
                linha = linha.replace(codigo.toString(), "");

                String pv = getPV(linha);
                linha = linha.replace(pv, "");

                int posicaoPrecoRegular = linha.indexOf("R$");
                int posicaoPrecoMembro = linha.indexOf("R$", posicaoPrecoRegular + 1);
                String precoRegular = linha.substring(posicaoPrecoRegular, posicaoPrecoMembro-1);
                linha = linha.replace(precoRegular, "");

                posicaoPrecoMembro = linha.indexOf("R$");
                String precoMembro = linha.substring(posicaoPrecoMembro);
                linha = linha.replace(precoMembro, "");

                Produto p = new Produto();
                p.setCodigo(codigo);
                p.setProduto(linha.trim());
                p.setPrecoRegular(getBigDecimal(precoRegular));
                p.setPrecoMembro(getBigDecimal(precoMembro));
                // Verificar se o retorno consiste apenas em dígitos válidos
                if (pv.matches("\\d+")) {
                    p.setPv(new BigDecimal(pv.trim()));
                } else {
                    p.setPv(BigDecimal.ZERO);
                }

                return p;
            }
        } catch (Exception e) {
            System.out.println(linhaOriginal);
            e.printStackTrace();
        }
        return null;
    }

    private static BigDecimal getBigDecimal(String value) {
        return new BigDecimal(value.replace("$  R", "").replace("R$", "").replace(".", "").replace(",", ".").trim().replace(" ", ""));
    }

    private static Integer getCodigo(String linha) {
        String[] partes = linha.split("\\s+");
        return Integer.parseInt(partes[0]);
    }

    public static boolean checkStringForRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private static String getPV(String linha) {
        String retorno = "";
        int endIndex = linha.length() - 1;
        while (endIndex >= 0 && !Character.isWhitespace(linha.charAt(endIndex)) || !retorno.matches(".*\\d+.*")) {
            retorno = String.valueOf(linha.charAt(endIndex)).concat(retorno);
            endIndex--;
        }
        // Verificar se o retorno consiste apenas em dígitos válidos
        if (retorno.matches("\\d+")) {
            return linha.substring(endIndex + 1);
        } else {
            return retorno;
        }
    }
}
