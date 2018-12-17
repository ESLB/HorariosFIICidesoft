package fii.industrial.cidesoft.horariofii.pruebas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.Horario;

public class TestHorarios extends AppCompatActivity {

    private TextView horarios;
    private Button butonHorarios;
    private Button butonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_horarios);

        horarios = (TextView) findViewById(R.id.txt_horarios);
        butonHorarios = (Button) findViewById(R.id.btn_testHorarios);
        butonAgregar = (Button) findViewById(R.id.btn_agregar);
        butonHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LlamarDatabase();
            }
        });
        butonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("cursos").child("ejemplo");
                ArrayList<Horario> Horarios = new ArrayList<Horario>();
                ArrayList<String> horarios = new ArrayList<String>(); horarios.add("Horaroios");horarios.add("horarios");
                ArrayList<String> profesores = new ArrayList<String>();profesores.add("profesores");profesores.add("Profesores");
                ArrayList<String>salones = new ArrayList<String>();salones.add("salones");salones.add("salones");
                ArrayList<String>secciones = new ArrayList<String>();salones.add("secciones");salones.add("secciones");
                Horarios.add(new Horario("id", "nombre", horarios, profesores, salones, "Ciclo 1", "Industrial", secciones));
                Horarios.add(new Horario("id", "nombre", horarios, profesores, salones, "Ciclo 1", "Industrial", secciones));
                Horarios.add(new Horario("id", "nombre", horarios, profesores, salones, "Ciclo 1", "Industrial", secciones));
                Horarios.add(new Horario("id", "nombre", horarios, profesores, salones, "Ciclo 1", "Industrial", secciones));
                myRef.setValue(Horarios);
            }
        });
    }

    private void LlamarDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        int[] hola = {0,3};
        for(int i = 0; i<hola.length; i++){
            DatabaseReference myRef = database.getReference("cursos").child("algebraLineal_industrial").child(hola[i]+"");
            final int a = hola[i];
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Horario superhorario = new Horario();
                    superhorario = dataSnapshot.getValue(Horario.class);
                    String cadena = horarios.getText().toString();
                    horarios.setText("     Inicio" + cadena + " \n"+a+" = " +superhorario.toString()+ "Fin      ");
                    //Toasty.error(TestHorarios.this, "Hubo un error").show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toasty.error(TestHorarios.this, "The read failed: " + databaseError.getMessage()).show();
                }
            });
        }
        //DatabaseReference myRef = database.getReference("cursos").child("algebraLineal_industrial").child("0");

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Horario>> t = new GenericTypeIndicator<ArrayList<Horario>>() {};
                ArrayList<Horario> Horarios = new ArrayList<Horario>();
                Horarios = dataSnapshot.getValue(t);
                String cadena = "";
                if(Horarios.size()>0) {
                    for (Horario horario : Horarios) {
                        cadena = cadena + horario.toString();
                    }
                    horarios.setText(cadena);
                }
                else
                    Toasty.error(TestHorarios.this, "Hubo un error").show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toasty.error(TestHorarios.this, "The read failed: " + databaseError.getMessage()).show();
            }
        });*/
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Horario superhorario = new Horario();
//                superhorario = dataSnapshot.getValue(Horario.class);
//                String cadena = "";
//                    horarios.setText("0 = " +superhorario.toString());
//                    //Toasty.error(TestHorarios.this, "Hubo un error").show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toasty.error(TestHorarios.this, "The read failed: " + databaseError.getMessage()).show();
//            }
//        });
    }


}
