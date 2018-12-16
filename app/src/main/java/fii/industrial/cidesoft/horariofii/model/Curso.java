package fii.industrial.cidesoft.horariofii.model;

public class Curso {

    private String curso;
    private String profesor;
    private String[] seccion;
    private boolean check_estado_curso = false;

    public String getCurso() {

        //Este es un ejemplo en realidad será llenado con una base de datos
        curso = "Ope 1";
        return curso;
    }

    public String getProfesor() {

        //Este es un ejemplo en realidad será llenado con una base de datos
        profesor = "Zamora";
        return profesor;
    }

    public String[] getSeccion() {

        //Este es un ejemplo en realidad será llenado con una base de datos
        seccion = new String[] {"1", "2", "3", "4", "5"};
        return seccion;
    }

    public void setCheck_estado_curso(boolean check_estado_curso) {
        this.check_estado_curso = check_estado_curso;
    }

}
