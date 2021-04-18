package com.xml.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// Especifica la clase raíz que vamos a convertir a XML
@XmlRootElement(name = "coche")
// Indicamos el orden de los nodos del XML
@XmlType(propOrder = {"marca", "modelo", "color"})
public class Coche {

    private String marca;
    private String modelo;
    private String color;

    @XmlElement(name = "marca")
    public String getMarca() { return  marca; }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
