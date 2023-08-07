package com.luiz.moco.doterra;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDiscount;
    private Button buttonCalculate;
    private TextView textViewResult;
    ProductAutoCompleteAdapter adapter;

    private AutoCompleteTextView autoCompleteValue;
    private List<Produto> productsList = new ArrayList<>();

    private Produto selectedProduct;

    private AlertDialog progressDialog;

    public static Context getContext() {
        return getContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoadingDialog();
        // Criar o layout principal (LinearLayout)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);
        setContentView(layout);
        List<String> productNames = new ArrayList<>();

        // Chame essa função para iniciar o download do PDF e obter o InputStream.
        PDFDownloadTask downloadTask = new PDFDownloadTask(new PDFDownloadTask.OnDownloadCompleteListener() {
            @Override
            public void onDownloadComplete(InputStream inputStream) {
                try {
                    if (inputStream != null) {
                        parsePdfData(inputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        downloadTask.execute("https://media.doterra.com/br-otg/pt/flyers/price-list.pdf");

        // Criar o AutoCompleteTextView para seleção do valor
        autoCompleteValue = new AutoCompleteTextView(this);
        autoCompleteValue.setHint("Selecione o produto");
        autoCompleteValue.setInputType(InputType.TYPE_CLASS_TEXT);
        // Crie o adaptador personalizado usando a lista de produtos productsList
        adapter = new ProductAutoCompleteAdapter(this, productsList);
        autoCompleteValue.setAdapter(adapter);

        // Definir o OnItemClickListener para capturar a seleção do usuário
        autoCompleteValue.setOnItemClickListener((parent, view, position, id) -> {
            String text = ((TextView) view).getText().toString();
            selectedProduct = Produto.getByDescricao(text, productsList);
            String description = selectedProduct != null ? selectedProduct.getProduto() : "";
            autoCompleteValue.setText(description);
            Log.d("test", "test");
        });

        layout.addView(autoCompleteValue);

        editTextDiscount = new EditText(this);
        editTextDiscount.setHint("Insira o percentual de desconto");
        editTextDiscount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(editTextDiscount);

        buttonCalculate = new Button(this);
        buttonCalculate.setText("Calcular Desconto");
        layout.addView(buttonCalculate);

        textViewResult = new TextView(this);
        textViewResult.setText("Resultado do Desconto:");
        textViewResult.setTextSize(18);
        textViewResult.setPadding(0, 16, 0, 0);
        layout.addView(textViewResult);

        // Obter o item selecionado após clicar no botão de cálculo
        buttonCalculate.setOnClickListener(v -> {
            calcularDesconto();
        });
    }

    // Chame essa função para iniciar a tarefa de leitura do PDF.
    private void parsePdfData(InputStream inputStream) {
        PDFParserTaskPdf parserTask = new PDFParserTaskPdf(this, new PDFParserTaskPdf.OnParsingCompleteListener() {
            @Override
            public void onParsingComplete(List<Produto> produtos) {
                // Preencher o AutoCompleteTextView com os nomes dos produtos do enum ProdutoEnum
                productsList = produtos;
                updateProdutoList();
                hideLoadingDialog();
            }
        });

        parserTask.execute(inputStream);
    }

    private void updateProdutoList() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.updateList(productsList);
            }
        });
    }

    private void calcularDesconto() {
        if (selectedProduct == null) {
            // Se nenhum produto foi selecionado, não faça nada
            return;
        }

        // Usar o preço de varejo do produto selecionado no cálculo do desconto
        BigDecimal value = selectedProduct.getPrecoRegular();
        String discountText = editTextDiscount.getText().toString();

        BigDecimal discount = new BigDecimal(Double.parseDouble(discountText.replaceAll("[^\\d.]", "")));

        // Calcular o desconto
        BigDecimal discountAmount = value.multiply(discount.divide(new BigDecimal(100)));
        BigDecimal valueAfterDiscount = value.subtract(discountAmount);

        // Obter o valor por ponto do produto selecionado
        BigDecimal valorPorPonto = selectedProduct.getPrecoMembro().divide(selectedProduct.getPv());

        // Exibir os resultados no TextView com a cor correspondente
        BigDecimal diferenca = valueAfterDiscount.subtract( selectedProduct.getPrecoMembro());
        String resultText = "Valor Original: " + NumberFormat.getCurrencyInstance().format(selectedProduct.getPrecoRegular())
                + "\nValor Atacado: " + NumberFormat.getCurrencyInstance().format(selectedProduct.getPrecoMembro())
                + "\nValor após desconto%: " + NumberFormat.getCurrencyInstance().format(valueAfterDiscount)
                + "\nValor por ponto: " + NumberFormat.getCurrencyInstance().format(valorPorPonto)
                + "\nValor ganho: " + NumberFormat.getCurrencyInstance().format(diferenca)
                + "\n\n";

        textViewResult.setText(resultText);
        if (diferenca.compareTo(BigDecimal.ZERO) > 0) {
            textViewResult.setTextColor(Color.DKGRAY);
        } else {
            textViewResult.setTextColor(Color.RED);
        }
        // textViewResult.setTextColor(cor); // Defina a cor do texto no textViewResult
    }

    // Método para interpolar cores
    private int interpolarCores(int corInicial, int corFinal, double fator) {
        int redInicial = Color.red(corInicial);
        int greenInicial = Color.green(corInicial);
        int blueInicial = Color.blue(corInicial);

        int redFinal = Color.red(corFinal);
        int greenFinal = Color.green(corFinal);
        int blueFinal = Color.blue(corFinal);

        int redInterpolado = (int) (redInicial + fator * (redFinal - redInicial));
        int greenInterpolado = (int) (greenInicial + fator * (greenFinal - greenInicial));
        int blueInterpolado = (int) (blueInicial + fator * (blueFinal - blueInicial));

        return Color.rgb(redInterpolado, greenInterpolado, blueInterpolado);
    }

    private void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new AlertDialog.Builder(this)
                    .setMessage("Carregando...")
                    .setCancelable(false)
                    .create();
        }
        progressDialog.show();
    }

    private void hideLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
