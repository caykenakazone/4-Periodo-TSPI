package com.iftm.lockotimista.models;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codProduto;
    @Column(name = "des_produto",nullable = false)
    private String desProduto;

    @Column(name = "vlr_produto")
    private double vlrProduto;
    @Column(name = "qtd_estoque")
    private Integer qtdEstoque;

    //Código de versão
    @Column(name = "cod_vers")
    private Integer codVrs;

    public Produto() {
    }

    public Produto(Integer codProduto, String desProduto, double vlrProduto, Integer qtdEdtoque, Integer codVrs) {
        this.codProduto = codProduto;
        this.desProduto = desProduto;
        this.vlrProduto = vlrProduto;
        this.qtdEstoque = qtdEstoque;
        this.codVrs = codVrs;
    }

    public Produto(Produto produto) {
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }

    public String getDesProduto() {
        return desProduto;
    }

    public void setDesProduto(String desProduto) {
        this.desProduto = desProduto;
    }

    public double getVlrProduto() {
        return vlrProduto;
    }

    public void setVlrProduto(double vlrProduto) {
        this.vlrProduto = vlrProduto;
    }

    public Integer getQtdEdtoque() {
        return qtdEstoque;
    }

    public void setQtdEdtoque(Integer qtdEdtoque) {
        this.qtdEstoque = qtdEdtoque;
    }

    public Integer getCodVrs() {
        return codVrs;
    }

    public void setCodVrs(Integer codVrs) {
        this.codVrs = codVrs;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codProduto=" + codProduto +
                ", desProduto='" + desProduto + '\'' +
                ", vlrProduto=" + vlrProduto +
                ", qtdEdtoque=" + qtdEstoque +
                ", codVrs=" + codVrs +
                '}';
    }
}
