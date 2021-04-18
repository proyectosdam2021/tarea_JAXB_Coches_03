package com.xml.jaxb.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

// Mediante una anotación le indicamos cual es la raíz del XML
@XmlRootElement(namespace = "concesionario")
// Inicio el orden de los nodos del XML
@XmlType(propOrder = {"nombre", "localizacion", "cocheList"})
public class Concesionario {

    // Con Wrapper le indicamos que es un envoltorio, es decir que dentro hay datos hijos
    @XmlElementWrapper(name = "cocheList")
    // Con el name le indicamos lo que retorna el envoltorio de XML - elemento = nodo
    @XmlElement(name = "coche")
    
    private List<Coche> cocheList;
    private String Nombre;
    private String Localizacion;


    public void setCocheList(List<Coche> cocheList) {
        this.cocheList = cocheList;
    }

    public List<Coche> getCochesList() {
        return cocheList;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getLocalizacion() {
        return Localizacion;
    }

    public void setLocalizacion(String localizacion) {
        Localizacion = localizacion;
    }
}
