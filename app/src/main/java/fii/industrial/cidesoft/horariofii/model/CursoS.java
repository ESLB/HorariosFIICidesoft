package fii.industrial.cidesoft.horariofii.model;

public class CursoS {

    private String Nombre_Cruso;
    private String Profesor;
    private String Seccion;
    private String Salon;
    private String Horario;

    public CursoS(String nombre_Cruso, String profesor, String seccion, String salon, String horario) {
        Nombre_Cruso = nombre_Cruso;
        Profesor = profesor;
        Seccion = seccion;
        Salon = salon;
        Horario = horario;
    }

    public CursoS() {
        Nombre_Cruso= "";
        Profesor="";
        Seccion="";
        Salon="";
        Horario="";

    }

    public String getNombre_Cruso() {
        return Nombre_Cruso;
    }

    public void setNombre_Cruso(String nombre_Cruso) {
        Nombre_Cruso = nombre_Cruso;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String seccion) {
        Seccion = seccion;
    }

    public String getSalon() {
        return Salon;
    }

    public void setSalon(String salon) {
        Salon = salon;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }
}
