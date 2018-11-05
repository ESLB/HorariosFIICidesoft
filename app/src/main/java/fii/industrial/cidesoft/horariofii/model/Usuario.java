package fii.industrial.cidesoft.horariofii.model;

public class Usuario {

    private String Cursos;
    private String Nombre;
    private String Codigo;
    private int Contador;

    public Usuario() {
    }

    public Usuario(String codigo, int contador, String cursos, String nombre) {
        Codigo = codigo;
        this.Contador = contador;
        Cursos = cursos;
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

    public String getCursos() {
        return Cursos;
    }

    public void setCursos(String cursos) {
        Cursos = cursos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
