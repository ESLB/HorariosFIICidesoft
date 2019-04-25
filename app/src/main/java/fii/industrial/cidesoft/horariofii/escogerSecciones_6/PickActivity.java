package fii.industrial.cidesoft.horariofii.escogerSecciones_6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.escogerCiclo_5.CiclesAct;
import fii.industrial.cidesoft.horariofii.model.Curso;
import fii.industrial.cidesoft.horariofii.model.Horario;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;

public class PickActivity extends AppCompatActivity {

    private TextView textView_curso;
    private TextView textView_Profesor;
    private Button button_Guardar;
    private Spinner spinner_seccion;
    private CheckBox checkBox_estado_curso;
    private SingletonFII mSingletonFII;
    private ArrayList<Horario> mHorarios;
    private TextView Check;
    private RecyclerView RVHorarios;
    private RVAdapter Adapter;
    private TextView Spinner;
    private ArrayList<String> indextemp = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_e);

        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());
        mHorarios = mSingletonFII.getmHorariosFiltrados();

        RVHorarios = (RecyclerView) findViewById(R.id.rv_horarios);
        RVHorarios.setLayoutManager(new LinearLayoutManager(this));
        Adapter = new RVAdapter(mHorarios);
        RVHorarios.setAdapter(Adapter);


//        Check = (TextView) findViewById(R.id.txt_checktext);
//        Spinner = (TextView) findViewById(R.id.txt_spinnerText);
//
//        textView_curso = (TextView)findViewById(R.id.text_view_curso);
//        textView_Profesor = (TextView)findViewById(R.id.text_view_profesor);
//        button_Guardar = (Button)findViewById(R.id.boton_guardar);
//        spinner_seccion = (Spinner)findViewById(R.id.spinner_seccion);
//
//        checkBox_estado_curso = (CheckBox)findViewById(R.id.check_estado_curso);
//        checkBox_estado_curso.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cadena = textView_curso.getText().toString() + " " + textView_Profesor.getText().toString() + " " + spinner_seccion.getSelectedItem().toString() + " index "+ + mHorarios.get(1).getIndex();
//                Check.setText(cadena);
//            }
//        });

//        spinner_seccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String cadena = textView_curso.getText().toString() + " " + textView_Profesor.getText().toString() + " " + spinner_seccion.getSelectedItem().toString() + " position "+ + position;
//                Spinner.setText(cadena);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        MostrarInformacion();

//        button_Guardar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, CiclesAct.class);
        startActivity(i);
    }

    private void MostrarInformacion(){

        if(mHorarios.size()>0){
//            Horario horario = mHorarios.get(1);
//            textView_curso.setText(horario.getNombre());
//            textView_Profesor.setText(horario.getProfesores().get(0));
        }else {
            Toasty.info(this, "No hay horarios con estos filtros").show();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[] {"1", "4", "5"});
//        spinner_seccion.setAdapter(arrayAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Integer> positions = new ArrayList<>();
        for(String i : indextemp){
            String horarioTemp = i.split("-")[0];
            for(String a : mSingletonFII.getIndexes()){
                String horario = i.split("-")[0];
                if(horarioTemp.equals(horario)){
                    positions.add(mSingletonFII.getIndexes().indexOf(a));
                    temp.add(i);
                }
            }
        }

        for(int i = (positions.size()-1); i>-1; i--){
            int a = positions.get(i);
            mSingletonFII.getIndexes().remove(a);
        }
        for(String i : temp){
            mSingletonFII.getIndexes().add(i);
        }
        positions.clear();
        indextemp.clear();
        Log.i("PICKACTIVITY.class", mSingletonFII.getIndexes().toString());
    }

    private class HorarioHolder extends RecyclerView.ViewHolder{

        private TextView Curso;
        private TextView Profesor;
        private TextView Horario;
        private TextView Salon;
        private Spinner Secciones;
        private CheckBox Listo;
        private int pos;
        private int initiation = 0;

        private Horario mHorario;

        public HorarioHolder(View itemView) {
            super(itemView);

            Curso = (TextView) itemView.findViewById(R.id.txt_rvCurso);
            Profesor = (TextView) itemView.findViewById(R.id.txt_rvProfesor);
            Horario = (TextView) itemView.findViewById(R.id.txt_rvHorario);
            Salon = (TextView) itemView.findViewById(R.id.txt_rvSalon);
            Secciones = (Spinner) itemView.findViewById(R.id.spin_rvSecciones);
            Listo = (CheckBox) itemView.findViewById(R.id.cb_rvListo);

        }

        public void bindHorario(Horario horario){
            mHorario = horario;
            pos = 0;
            Curso.setText(mHorario.getNombre());
            Profesor.setText(mHorario.getProfesores().get(pos));
            Horario.setText(mHorario.getHorarios().get(pos));
            Salon.setText(mHorario.getSalones().get(pos));
            ArrayList<String> cadenas = new ArrayList<String>();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PickActivity.this, android.R.layout.simple_spinner_item, mHorario.getSecciones());
            Secciones.setAdapter(arrayAdapter);
            Secciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    updateHorario(position);
                    pos = position;
                    String index = mHorario.getIndex() + "-" + pos;
                    if(Listo.isChecked()){
                        indextemp.add(index);
                        Log.i("PICKACTIVITY.class", mSingletonFII.getIndexes().toString());

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Listo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String index = mHorario.getIndex() + "-" + pos;
                    if(Listo.isChecked()){
                        mSingletonFII.getIndexes().add(index);

                    } else {
                        mSingletonFII.getIndexes().remove(index);
                    }
                    Log.i("PICKACTIVIT.class", mSingletonFII.getIndexes().toString());
                }
            });

            for(String i : mSingletonFII.getIndexes()){
                if((mHorario.getIndex()+"").equals(i.split("-")[0])){
                    Listo.setChecked(true);
                    Secciones.setSelection(Integer.valueOf(i.split("-")[1]));
                    initiation = 1;
                }
            }
        }

        private void updateHorario(int position){
            Profesor.setText(mHorario.getProfesores().get(position));
            Horario.setText(mHorario.getHorarios().get(position));
            Salon.setText(mHorario.getSalones().get(position));
        }

    }



    private class RVAdapter extends RecyclerView.Adapter<HorarioHolder>{

        public ArrayList<Horario> horarios;

        public RVAdapter(ArrayList<Horario> horarios){
            this.horarios = horarios;
        }

        @NonNull
        @Override
        public HorarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(PickActivity.this);
            View view = layoutInflater.inflate(R.layout.item_horario, parent, false);
            return new HorarioHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HorarioHolder holder, int position) {
            Horario horario = horarios.get(position);
            holder.bindHorario(horario);

        }

        @Override
        public int getItemCount() {
            return mHorarios.size();
        }

        public void setHorarios(ArrayList<Horario> horarioEnviado){
            horarios = horarioEnviado;
        }

    }


}
