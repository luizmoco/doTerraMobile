package com.luiz.moco.doterra.autocomplete;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.luiz.moco.doterra.R;
import com.luiz.moco.doterra.model.Produto;
import com.luiz.moco.doterra.util.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

public class ProdutosArrayAdapter extends ArrayAdapter<Produto> {

    private static final float fonteNormal = 14;
    private static final float fontePequena = 11;
    private StringUtil stringUtil;
    private ProdutosFilter filter;

    public ProdutosArrayAdapter(Context context, List<Produto> produtos) {
        super(context, 0, produtos);
        stringUtil = new StringUtil(context);
        filter = new ProdutosFilter(produtos, this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Verifique se uma View existente está sendo reutilizada, caso contrário, crie uma nova View
        LinearLayout card;
        if (convertView == null) {
            card = new LinearLayout(getContext());
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(16, 16, 16, 16);
            card.setBackgroundColor(Color.WHITE);
        } else {
            card = (LinearLayout) convertView;
        }

        Produto produto = getItem(position);

        TextView nomeProdutoTextView = getViewProduto(produto);
        TableLayout tableLayoutNew = getTablePrecos(produto);
        TextView pvTextView = getViewPV(produto);

        card.removeAllViews();
        card.addView(nomeProdutoTextView);
        card.addView(tableLayoutNew);
        card.addView(pvTextView);

        return card;
    }

    private TableLayout getTablePrecos(Produto produto) {
        // Crie um TableLayout
        TableLayout tableLayout = new TableLayout(getContext());
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));
        // Permita o scroll horizontal
        tableLayout.setHorizontalScrollBarEnabled(true);
        tableLayout.setScrollbarFadingEnabled(false);
        int DARK_GREEN = Color.parseColor("#006400");
        int LARANJA = Color.parseColor("#FFA500");

        // Adicione uma linha de cabeçalho
        TableRow header = new TableRow(getContext());
        header.addView(createTextView("Regular", DARK_GREEN, true));
        header.addView(createTextView(" | ", Color.BLACK, true));
        header.addView(createTextView("5%", DARK_GREEN, false));
        header.addView(createTextView(" | ", Color.BLACK, true));
        header.addView(createTextView("10%", Color.BLUE, false));
        header.addView(createTextView(" | ", Color.BLACK, true));
        header.addView(createTextView("15%", Color.BLUE, false));
        header.addView(createTextView(" | ", Color.BLACK, true));
        header.addView(createTextView("20%", LARANJA, false));
        header.addView(createTextView(" | ", Color.BLACK, true));
        header.addView(createTextView("Membro", Color.RED, true));
        tableLayout.addView(header);

        // Adicione uma linha visual acima da linha de cabeçalho
        addHorizontalLine(tableLayout);

        // Adicione uma linha de valores
        TableRow values = new TableRow(getContext());
        values.addView(createTextView(produto.getPrecoRegular(), DARK_GREEN, true));
        values.addView(createTextView(" | ", Color.BLACK, true));
        values.addView(createTextView(produto.getCincoPorCento(), DARK_GREEN, false));
        values.addView(createTextView(" | ", Color.BLACK, true));
        values.addView(createTextView(produto.getDezPorCento(), Color.BLUE, false));
        values.addView(createTextView(" | ", Color.BLACK, true));
        values.addView(createTextView(produto.getQuinzePorCento(), Color.BLUE, false));
        values.addView(createTextView(" | ", Color.BLACK, true));
        values.addView(createTextView(produto.getVintePorCento(), LARANJA, false));
        values.addView(createTextView(" | ", Color.BLACK, true));
        values.addView(createTextView(produto.getPrecoMembro(), Color.RED, true));
        tableLayout.addView(values);

        // Adicione uma linha visual acima da linha de valores
        addHorizontalLine(tableLayout);

        return tableLayout;
    }

    private TextView createTextView(BigDecimal value, int color, boolean bold) {
        return createTextView(value.setScale(2, RoundingMode.HALF_UP).toString(), color, bold);
    }

    private TextView createTextView(String text, int color, boolean bold) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
        textView.setPadding(4, 8, 4, 8);
        textView.setTextSize(fontePequena);
        textView.setTextColor(color);
        if (bold) {
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        textView.setText(text);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return textView;
    }

    private TextView getViewPV(Produto produto) {
        TextView valorPorPonto = getTextView(fontePequena);
        valorPorPonto.setTextColor(Color.GRAY);
        valorPorPonto.setText(stringUtil.getText(R.string.valor_pp, produto.getPrecoMembro().divide(produto.getPv(), 2, RoundingMode.HALF_UP)));
        return valorPorPonto;
    }

    private TextView getViewProduto(Produto produto) {
        TextView viewProduto = getTextView(fonteNormal);
        viewProduto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        viewProduto.setText(produto.getProduto());
        return viewProduto;
    }

    private TextView getTextView(float fonte) {
        TextView textView = new TextView(getContext());
        textView.setPadding(16, 16, 16, 16);
        textView.setTextSize(fonte);
        textView.setTextColor(Color.DKGRAY);
        return textView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    // Função para adicionar uma linha horizontal a um TableLayout
    private void addHorizontalLine(TableLayout tableLayout) {
        int strokeWidth = 2;
        int strokeColor = Color.parseColor("#000000"); // Cor das linhas visuais (preto)

        View line = new View(getContext());
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                strokeWidth
        );
        layoutParams.setMargins(0, 0, 0, 10); // Define a margem inferior para a linha
        line.setLayoutParams(layoutParams);
        line.setBackgroundColor(strokeColor);
        tableLayout.addView(line);
    }

}
