package com.luiz.moco.doterra.activity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.luiz.moco.doterra.R;
import com.luiz.moco.doterra.autocomplete.ProdutosArrayAdapter;
import com.luiz.moco.doterra.model.Produto;
import com.luiz.moco.doterra.pdf.PDFUtil;
import com.luiz.moco.doterra.util.Constants;
import com.luiz.moco.doterra.util.Produtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityPrincipal extends AppCompatActivity {

    private LinearLayout rootView;
    private AlertDialog progressDialog;

    private List<Produto> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (Objects.nonNull(actionBar)) {
            // Defina a cor desejada para a Action Bar
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(Constants.COR_DOTERRA)));
            actionBar.setTitle(R.string.doterra);
        }

        // Criar o layout principal (LinearLayout)
        rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setPadding(16, 16, 16, 16);
        setContentView(rootView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoadingDialog();

        productsList = Produtos.getProdutos();

        // Crie um adaptador para preencher a lista
        ProdutosArrayAdapter adaptadorDeProdutos = new ProdutosArrayAdapter(this, productsList);

        // Criar o SearchView
        SearchView searchView = new SearchView(this);
        int searchViewId = View.generateViewId(); // Gerar um ID exclusivo
        searchView.setId(searchViewId);

        // Configurar o ouvinte de consulta de pesquisa
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Ação quando o usuário pressionar "Enter" na barra de busca (opcional)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar a lista de produtos com base no texto de busca do usuário
                adaptadorDeProdutos.getFilter().filter(newText);
                return true;
            }
        });

        // Adicionar o SearchView à LinearLayout
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rootView.addView(searchView, params);

        ListView listaDeProdutos = new ListView(this);
        rootView.addView(listaDeProdutos);

        // Configure o adaptador para a ListView
        listaDeProdutos.setAdapter(adaptadorDeProdutos);

        PDFUtil pdfUtil = new PDFUtil(this, productsList);
        pdfUtil.checarUltimoDownload();

        hideLoadingDialog();
    }

    private void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new AlertDialog.Builder(this)
                    .setMessage(R.string.carregando)
                    .setCancelable(false)
                    .create();
        }
        progressDialog.show();
    }

    private void hideLoadingDialog() {
        if (Objects.nonNull(progressDialog) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
