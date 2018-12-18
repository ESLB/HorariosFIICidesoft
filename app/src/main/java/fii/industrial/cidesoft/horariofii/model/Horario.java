package fii.industrial.cidesoft.horariofii.model;

import java.util.ArrayList;

public class Horario {

    String id_curso = "";
    String nombre = "";
    ArrayList<String> horarios = new ArrayList<String>();
    ArrayList<String> profesores = new ArrayList<String>();
    ArrayList<String> salones = new ArrayList<String>();
    ArrayList<String> secciones = new ArrayList<String>();
    String ciclo = "";
    String escuela = "";
    int index;
    int seccion = 0;

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public ArrayList<String> getSecciones() {
        return secciones;
    }

    public void setSecciones(ArrayList<String> secciones) {
        this.secciones = secciones;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Horario(String id_curso, String nombre, ArrayList<String> horarios, ArrayList<String> profesores, ArrayList<String> salones, String ciclo,  String escuela, ArrayList<String> secciones) {
        this.id_curso = id_curso;
        this.nombre = nombre;
        this.horarios = horarios;
        this.profesores = profesores;
        this.salones = salones;
        this.ciclo = ciclo;
        this.escuela = escuela;
        this.secciones = secciones;
    }

    public String toString(){
        String cadena = "Inicio de cadena => {id_curso: " + id_curso +
                ", nombre: " + nombre +
                ", horarios: " + horarios.toString()+
                ", profesores: " + profesores.toString()+
                ", salones: " + salones.toString() +
                ", ciclo: " + ciclo +
                ", escuela: " + escuela +
                ", seccion: " + secciones.toString()  +"} . Fin de cadena.";

        return cadena;
    }

    public Horario() {
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<String> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<String> profesores) {
        this.profesores = profesores;
    }

    public ArrayList<String> getSalones() {
        return salones;
    }

    public void setSalones(ArrayList<String> salones) {
        this.salones = salones;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
}
