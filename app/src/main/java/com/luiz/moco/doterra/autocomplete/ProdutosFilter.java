package com.luiz.moco.doterra.autocomplete;

import android.widget.Filter;

import com.luiz.moco.doterra.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutosFilter extends Filter {

    private final List<Produto> originalProductsList;
    private final ProdutosArrayAdapter adapter;

    public ProdutosFilter(List<Produto> productsList, ProdutosArrayAdapter adapter) {
        super();
        originalProductsList = new ArrayList<>(productsList);
        productsList.addAll(originalProductsList);
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() == 0) {
            // Nenhum filtro, retorna a lista original
            results.values = originalProductsList;
            results.count = originalProductsList.size();
        } else {
            List<Produto> filteredProducts = new ArrayList<>();
            String filterPattern = constraint.toString().toLowerCase().trim();

            for (Produto produto : originalProductsList) {
                if (produto.getProduto().toLowerCase().contains(filterPattern)) {
                    filteredProducts.add(produto);
                }
            }

            results.values = filteredProducts;
            results.count = filteredProducts.size();
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.clear();
        adapter.addAll((List<Produto>) results.values);
        adapter.notifyDataSetChanged();
    }
}