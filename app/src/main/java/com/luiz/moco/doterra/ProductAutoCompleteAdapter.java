package com.luiz.moco.doterra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

public class ProductAutoCompleteAdapter extends ArrayAdapter<Produto> {

    private LayoutInflater inflater;
    private Produto selectedProduct; // Armazena o produto selecionado
    private List<Produto> produtos;

    public ProductAutoCompleteAdapter(Context context, List<Produto> productsList) {
        super(context, 0, productsList);
        inflater = LayoutInflater.from(context);
        this.produtos = productsList;
    }

    public void setSelectedProduct(Produto product) {
        selectedProduct = product;
        notifyDataSetChanged(); // Atualizar a lista para refletir o item selecionado
    }

    public Produto getSelectedProduct() {
        return selectedProduct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }

        Produto produto = getItem(position);
        if (produto != null) {
            TextView productNameView = convertView.findViewById(android.R.id.text1);
            productNameView.setText(produto.getProduto() + " - " +
                    NumberFormat.getCurrencyInstance().format(produto.getPrecoRegular()));

            // Destacar o item selecionado com um fundo diferente
            if (produto.equals(selectedProduct)) {
                productNameView.setBackgroundColor(getContext().getResources().getColor(android.R.color.darker_gray));
            } else {
                productNameView.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
            }
        }

        return convertView;
    }

    // Método para atualizar a lista de produtos no adaptador
    public void updateList(List<Produto> novaListaProdutos) {
        produtos.clear();
        produtos.addAll(novaListaProdutos);
        notifyDataSetChanged();
    }
}
