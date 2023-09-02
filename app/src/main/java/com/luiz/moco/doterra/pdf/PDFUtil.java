package com.luiz.moco.doterra.pdf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.luiz.moco.doterra.model.Produto;
import com.luiz.moco.doterra.util.Constants;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PDFUtil {

    Context context;
    private List<Produto> productsList;

    public PDFUtil(Context context, List<Produto> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    public void checarUltimoDownload() {
        final long SEVEN_DAYS_IN_MILLIS = 7 * 24 * 60 * 60 * 1000;

        // Retrieve the last execution timestamp from SharedPreferences
        long lastExecutionTime = this.context.getSharedPreferences("MyPrefs", this.context.MODE_PRIVATE)
                .getLong("lastExecutionTime", 0);

        // Get the current time
        long currentTime = System.currentTimeMillis();

        // Check if it's been at least 7 days since the last execution
        if (currentTime - lastExecutionTime >= SEVEN_DAYS_IN_MILLIS) {
            // Execute the code since it's been more than 7 days
            // Chame essa função para iniciar o download do PDF e obter o InputStream.
            PDFDownloadTask.OnDownloadCompleteListener listener = inputStream -> {
                try {
                    if (inputStream != null) {
                        parsePdfData(inputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            PDFDownloadTask downloadTask = new PDFDownloadTask(listener);
            downloadTask.execute(Constants.URL_PRICE_LIST);
            // Update the last execution timestamp in SharedPreferences
            currentTime = System.currentTimeMillis();
            this.context.getSharedPreferences("MyPrefs", this.context.MODE_PRIVATE)
                    .edit()
                    .putLong("lastExecutionTime", currentTime)
                    .apply();
        } else {
            // Do nothing, the code was executed less than 7 days ago
        }
    }

    // Chame essa função para iniciar a tarefa de leitura do PDF.
    private void parsePdfData(InputStream inputStream) {
        PDFParserTaskPdf.OnParsingCompleteListener listener = produtos -> {
            // Preencher o AutoCompleteTextView com os produtos
            checarPrecos(produtos);
        };
        PDFParserTaskPdf parserTask = new PDFParserTaskPdf(this.context, listener);
        parserTask.execute(inputStream);
    }

    private void checarPrecos(List<Produto> produtos) {
        Set<Produto> produtosInvalidos = new HashSet();
        for (Produto produto : produtos) {
            for (Produto product : productsList) {
                if (produto.getCodigo() == product.getCodigo()) {
                    if (produto.getPrecoMembro().compareTo(product.getPrecoMembro()) != 0 || produto.getPrecoRegular().compareTo(product.getPrecoRegular()) != 0) {
                        produtosInvalidos.add(produto);
                    }
                }
            }
        }
        if (!produtosInvalidos.isEmpty()) {
            StringBuilder message = new StringBuilder("Produtos com preços inválidos:\n");
            for (Produto produtoInvalido : produtosInvalidos) {
                message.append(produtoInvalido.getProduto()).append("\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setTitle("Produtos com preços inválidos")
                    .setMessage(message.toString())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
