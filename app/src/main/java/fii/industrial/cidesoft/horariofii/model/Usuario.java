package fii.industrial.cidesoft.horariofii.model;

import java.util.ArrayList;

public class Usuario {

    private ArrayList<String> indexes;
    private String Nombre;
    private String Codigo;
    private int Contador;

    public Usuario() {
    }

    public Usuario(String codigo, int contador, ArrayList<String> indexes, String nombre) {
        Codigo = codigo;
        this.Contador = contador;
        this.indexes = indexes;
        Nombre = nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public int getContador() {
        return Contador;
    }

    public void setContador(int contador) {
        this.Contador = contador;
    }

    public ArrayList<String> getIndexes() {
        return indexes;
    }

    public void setIndexes(ArrayList<String> indexes) {
        this.indexes = indexes;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
