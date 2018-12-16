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
    private boolean hasHorarios = false;
    private ArrayList<Horario> mHorarios;
    private ArrayList<Horario> mHorariosFiltrados;

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
        for (Horario horario: mHorarios) {
            if(horario.getCiclo().equals(getCICLO()+"")&&horario.getEscuela().equals(getESCUELA()+"")){
                mHorariosFiltrados.add(horario);
            }
        }
    }

    public void setHorarios(ArrayList<Horario> horarios){
        mHorarios = horarios;
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
        for (Horario horario: mHorarios) {
            result.append(horario.toString());
        }
        return result.toString();
    }
}
