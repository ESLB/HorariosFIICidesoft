package fii.industrial.cidesoft.horariofii.model;

import android.content.Context;

import java.util.ArrayList;

public class SingletonFII {

    private static SingletonFII mSingletonFII;
    private Context context;

    public static final int INDUSTRIAL = 0;
    public static final int TEXTIL = 1;
    public static final int SEGURIDAD = 2;
    private int ESCUELA = INDUSTRIAL;

    private int CICLO = 1;
    private Usuario usuario;
    private String codigo;

    private ArrayList<String> indexes = new ArrayList<String>(); /* {"4-1","5-2"} Primero: Horario, Segundo: Sección (positión del número de la sección)*/
    private boolean hasHorarios = false;
    private ArrayList<Horario> mHorarios;
    private ArrayList<Horario> mHorariosFiltrados = new ArrayList<Horario>();
    private boolean needExcecuteForLoop = true;

    public static final String SHARED_PREFERENCES = "Horario-FII";
    private String source = SHARED_PREFERENCES;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean NeedExcecuteForLoop() {
        return needExcecuteForLoop;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNeedExcecuteForLoop(boolean needExcecuteForLoop) {
        this.needExcecuteForLoop = needExcecuteForLoop;
    }

    public ArrayList<Horario> getmHorariosEscogidos() {
        return mHorariosEscogidos;
    }

    public void setmHorariosEscogidos(ArrayList<Horario> mHorariosEscogidos) {
        this.mHorariosEscogidos = mHorariosEscogidos;
    }

    private ArrayList<Horario> mHorariosEscogidos = new ArrayList<Horario>();
    private ArrayList<CursoS> mCursoS = new ArrayList<CursoS>();



    public ArrayList<CursoS> getCursoS() {
        return mCursoS;
    }

    public void GenerateCursosS(){
        Horario horario;
        int seccion;
        for (String index: indexes) {
            String[] datos = index.split("-");
            horario = mHorarios.get(Integer.valueOf(datos[0]));
            seccion = Integer.valueOf(datos[1]);
            CursoS cursoS = new CursoS();
            cursoS.setHorario(horario.getHorarios().get(seccion));
            cursoS.setProfesor(horario.getProfesores().get(seccion));
            cursoS.setNombre_Cruso(horario.getNombre());
            cursoS.setSeccion(horario.getSecciones().get(seccion));
            cursoS.setSalon(horario.getSalones().get(seccion));
            mCursoS.add(cursoS);
        }
    }

    public ArrayList<String> getIndexes() {
        return indexes;
    }

    public void setIndexes(ArrayList<String> indexes) {
        this.indexes = indexes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private SingletonFII(Context context){
        this.context = context;
    }

    public static SingletonFII getSingletonFII(Context context){
        if(mSingletonFII==null){
            mSingletonFII = new SingletonFII(context);
        }
        return mSingletonFII;
    }

    public int getESCUELA() {
        return ESCUELA;
    }

    public void setESCUELA(int ESCUELA) {
        this.ESCUELA = ESCUELA;
        filtrarHorarios();
    }

    private void filtrarHorarios() {
        mHorariosFiltrados.clear();
        for (Horario horario: mHorarios) {
            String escuela = "industrial";
            switch (getESCUELA()){
                case 0:
                    escuela = "industrial";
                    break;
                case 1:
                    escuela = "textil";
                    break;
                case 2:
                    escuela = "seguridad";
                    break;
            }
            if(horario.getCiclo().equals(getCICLO()+"")&&horario.getEscuela().equals(escuela)){
                mHorariosFiltrados.add(horario);
            }
        }
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        mHorarios = horarios;
        int i = 0;
        for (Horario horario: mHorarios) {
            horario.setIndex(i);
            i++;
        }

        hasHorarios = true;
    }

    public boolean isHasHorarios() {
        return hasHorarios;
    }

    public void setHasHorarios(boolean hasHorarios) {
        this.hasHorarios = hasHorarios;
    }

    public int getCICLO() {
        return CICLO;
    }

    public void setCICLO(int CICLO) {
        this.CICLO = CICLO;
        filtrarHorarios();
    }

    public ArrayList<Horario> getmHorariosFiltrados() {
        return mHorariosFiltrados;
    }

    public String getHorariosFiltradosString(){
        StringBuilder result = new StringBuilder();
        for (Horario horario: mHorariosFiltrados) {
            result.append(horario.toString());
        }
        return result.toString();
    }
}


