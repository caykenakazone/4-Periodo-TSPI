package com.iftm.imobiliarianosqql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "imoveis")
public class Imovel implements Serializable {
    @Id
    private String id;
    private String endereco;

    private String tipoImovel;
    private double area;
    private double preco;

    public Imovel(String id, String endereco, String tipoImovel, double area, double preco) {
        this.id = id;
        this.endereco = endereco;
        this.tipoImovel = tipoImovel;
        this.area = area;
        this.preco = preco;
    }

    public Imovel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return Double.compare(area, imovel.area) == 0 && Double.compare(preco, imovel.preco) == 0 && Objects.equals(id, imovel.id) && Objects.equals(endereco, imovel.endereco) && Objects.equals(tipoImovel, imovel.tipoImovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endereco, tipoImovel, area, preco);
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id='" + id + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tipoImovel='" + tipoImovel + '\'' +
                ", area=" + area +
                ", preco=" + preco +
                '}';
    }
}
