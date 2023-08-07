package com.luiz.moco.doterra;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFConvertToProduto {

    static List<Produto> produtos = new ArrayList<Produto>();

    public static void main(String[] args) {
        // URL do PDF que você deseja baixar
        // String pdfUrl = "https://media.doterra.com/br-otg/pt/flyers/price-list.pdf";

        // Realiza o download do PDF e lê o conteúdo
        // readPDFFromURL(pdfUrl);
    }

    public static List<Produto> readPDFFromURL() throws IOException {
        try {

            String pdfUrl = "https://media.doterra.com/br-otg/pt/flyers/price-list.pdf";
            URL url = new URL(pdfUrl);
            try (InputStream in = new BufferedInputStream(url.openStream());
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

                // Lê os dados do PDF e armazena-os em memória
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    byteArrayOutputStream.write(dataBuffer, 0, bytesRead);
                }

                // Carrega o PDF usando o iText
                PdfReader reader = new PdfReader(in);
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    String linha = PdfTextExtractor.getTextFromPage(reader, i);
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
                                linha = linha.concat(PdfTextExtractor.getTextFromPage(reader, i+1));
                                i++;
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return produtos;
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
                linha = linha.replace(String.valueOf(codigo), "");
                int posicaoPrecoRegular = linha.indexOf("$");
                int posicaoPrecoMembro = linha.indexOf("$", posicaoPrecoRegular + 1);

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
                linha = linha.replace(String.valueOf(codigo), "");
                int posicaoPrecoRegular = linha.indexOf("$");
                int posicaoPrecoMembro = linha.indexOf("$", posicaoPrecoRegular + 1);;

                String pRegular;
                if (linha.charAt(posicaoPrecoRegular - 1) == 'R' || linha.charAt(posicaoPrecoRegular - 1) == 'r') {
                    pRegular = linha.substring(posicaoPrecoRegular - 1, posicaoPrecoMembro);
                } else {
                    pRegular = linha.substring(posicaoPrecoRegular, posicaoPrecoMembro);
                }


                linha = linha.replace(pRegular, "");

                String pMembro;
                BigDecimal precoMembro;
                posicaoPrecoMembro = linha.indexOf("$");
                if (linha.charAt(posicaoPrecoMembro - 1) == 'R' || linha.charAt(posicaoPrecoMembro - 1) == 'r') {
                    int posicaoPrecoMembroFim = linha.indexOf(",00");
                    if (posicaoPrecoMembroFim <= 0) {
                        posicaoPrecoMembroFim = linha.indexOf(", 00") + 1;
                        pMembro = linha.substring(posicaoPrecoMembro - 1, posicaoPrecoMembroFim + 3);
                        precoMembro = getBigDecimal(pMembro);
                    } else {
                        pMembro = linha.substring(posicaoPrecoMembro - 1, posicaoPrecoMembroFim + 3);
                        precoMembro = getBigDecimal(pMembro);
                    }
                } else {
                    int posicaoPrecoMembroFim = linha.indexOf(",00");
                    if (posicaoPrecoMembroFim <= 0) {
                        posicaoPrecoMembroFim = linha.indexOf(", 00") + 1;
                        pMembro = linha.substring(posicaoPrecoMembro, posicaoPrecoMembroFim + 3);
                        precoMembro = getBigDecimal(pMembro);
                    } else {
                        pMembro = linha.substring(posicaoPrecoMembro, posicaoPrecoMembroFim + 3);
                        precoMembro = getBigDecimal(pMembro);
                    }
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
}
