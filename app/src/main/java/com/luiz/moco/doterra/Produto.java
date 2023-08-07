package com.luiz.moco.doterra;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class Produto {

    Integer codigo;
    String produto;
    BigDecimal precoRegular;
    BigDecimal precoMembro;
    BigDecimal pv;

    public Produto() {
        // TODO Auto-generated constructor stub
    }

    public static Produto getByDescricao(String text, List<Produto> produtos) {
            for (Produto produto : produtos) {
                if (produto.getProduto().equalsIgnoreCase(text)) {
                    return produto;
                }
            }
            return null; // Retorna null caso não encontre o produto com o código especificado
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

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", produto=" + produto + ", precoRegular=" + precoRegular
                + ", precoMembro=" + precoMembro + ", pv=" + pv + "]";
    }



}
