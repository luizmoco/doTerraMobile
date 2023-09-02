package com.luiz.moco.doterra.model;

import java.math.BigDecimal;
import java.util.List;

public class Produto {

    Integer codigo;
    String produto;
    BigDecimal precoRegular;
    BigDecimal precoMembro;
    BigDecimal pv;
    BigDecimal cincoPorCento;
    BigDecimal dezPorCento;
    BigDecimal quinzePorCento;
    BigDecimal vintePorCento;


    public Produto(Integer codigo,
                   String produto,
                   BigDecimal precoRegular,
                   BigDecimal precoMembro,
                   BigDecimal pv,
                   BigDecimal cincoPorCento,
                   BigDecimal dezPorCento,
                   BigDecimal quinzePorCento,
                   BigDecimal vintePorCento
    ) {
        this.codigo = codigo;
        this.produto = produto;
        this.precoRegular = precoRegular;
        this.precoMembro = precoMembro;
        this.pv = pv;
        this.cincoPorCento = cincoPorCento;
        this.dezPorCento = dezPorCento;
        this.quinzePorCento = quinzePorCento;
        this.vintePorCento = vintePorCento;

    }

    public Produto() {

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getPrecoRegular() {
        return precoRegular;
    }

    public void setPrecoRegular(BigDecimal precoRegular) {
        this.precoRegular = precoRegular;
    }

    public BigDecimal getPrecoMembro() {
        return precoMembro;
    }

    public void setPrecoMembro(BigDecimal precoMembro) {
        this.precoMembro = precoMembro;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }

    public BigDecimal getCincoPorCento() {
        return cincoPorCento;
    }

    public void setCincoPorCento(BigDecimal cincoPorCento) {
        this.cincoPorCento = cincoPorCento;
    }

    public BigDecimal getDezPorCento() {
        return dezPorCento;
    }

    public void setDezPorCento(BigDecimal dezPorCento) {
        this.dezPorCento = dezPorCento;
    }

    public BigDecimal getQuinzePorCento() {
        return quinzePorCento;
    }

    public void setQuinzePorCento(BigDecimal quinzePorCento) {
        this.quinzePorCento = quinzePorCento;
    }

    public BigDecimal getVintePorCento() {
        return vintePorCento;
    }

    public void setVintePorCento(BigDecimal vintePorCento) {
        this.vintePorCento = vintePorCento;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", produto='" + produto + '\'' +
                ", precoRegular=" + precoRegular +
                ", precoMembro=" + precoMembro +
                ", pv=" + pv +
                ", cincoPorCento=" + cincoPorCento +
                ", dezPorCento=" + dezPorCento +
                ", quinzePorCento=" + quinzePorCento +
                ", vintePorCento=" + vintePorCento +
                '}';
    }

    /*@Override
    public String toString() {
        return "produtos.add(newProduto(" + codigo + ", \"" + produto + "\", BigDecimal.valueOf(" + precoRegular + "), BigDecimal.valueOf("
                + precoMembro + "), BigDecimal.valueOf(" + pv + "), " + " BigDecimal.valueOf(" + precoRegular.multiply(BigDecimal.valueOf(0.95)) + ") , BigDecimal.valueOf(" +
                precoRegular.multiply(BigDecimal.valueOf(0.90)) + ") , BigDecimal.valueOf(" + precoRegular.multiply(BigDecimal.valueOf(0.85)) + ") , BigDecimal.valueOf(" + precoRegular.multiply(BigDecimal.valueOf(0.80)) + ")));";
    }*/


}
