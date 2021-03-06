package fii.industrial.cidesoft.horariofii.escogerCiclo_5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.cursosLista_3.Panel_Curso;
import fii.industrial.cidesoft.horariofii.escogerEscuela_4.SchoolAct;
import fii.industrial.cidesoft.horariofii.escogerSecciones_6.PickActivity;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;
import fii.industrial.cidesoft.horariofii.pruebas.ActivityPruebas;

public class CiclesAct extends AppCompatActivity {

    private LinearLayout mEscuela;
    private SingletonFII mSingletonFII;
    private Button Ciclo1;
    private Button Ciclo2;
    private Button Ciclo3;
    private Button Ciclo4;
    private Button Ciclo5;
    private Button Ciclo6;
    private Button Ciclo7;
    private Button Ciclo8;
    private Button Ciclo9;
    private Button Ciclo10;
    private Button Finalizar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SchoolAct.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclos);

        mEscuela = (LinearLayout) findViewById(R.id.linearLayoutCiclos);
        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());
        Ciclo1 = (Button) findViewById(R.id.btn_ciclo_1);
        Ciclo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(1);
                irActivity();
            }
        });
        Ciclo2 = (Button) findViewById(R.id.btn_ciclo_2);
        Ciclo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(2);
                irActivity();
            }
        });
        Ciclo3 = (Button) findViewById(R.id.btn_ciclo_3);
        Ciclo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(3);
                irActivity();
            }
        });
        Ciclo4 = (Button) findViewById(R.id.btn_ciclo_4);
        Ciclo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(4);
                irActivity();
            }
        });
        Ciclo5 = (Button) findViewById(R.id.btn_ciclo_5);
        Ciclo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(5);
                irActivity();
            }
        });
        Ciclo6 = (Button) findViewById(R.id.btn_ciclo_6);
        Ciclo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(6);
                irActivity();
            }
        });
        Ciclo7 = (Button) findViewById(R.id.btn_ciclo_7);
        Ciclo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(7);
                irActivity();
            }
        });
        Ciclo8 = (Button) findViewById(R.id.btn_ciclo_8);
        Ciclo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(8);
                irActivity();
            }
        });
        Ciclo9 = (Button) findViewById(R.id.btn_ciclo_9);
        Ciclo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(9);
                irActivity();
            }
        });
        Ciclo10 = (Button) findViewById(R.id.btn_ciclo_10);
        Ciclo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setCICLO(10);
                irActivity();
            }
        });
        Finalizar = (Button) findViewById(R.id.btn_finalizar);
        Finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VerificarValidez()){

                    //Guardar en el SharedPreferences "CursosInfo" true
                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SingletonFII.SHARED_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("CursosInfo", true);
                    editor.putString("Indexes", mSingletonFII.getIndexes().toString());

                    editor.apply();
                    //Al parecer guarda los cursos correctamente. Es que antes la base de datos debía guardar los cursos, ahora no.
                    mSingletonFII.GenerateCursosS();
                    //CrearUsuario();
                    IrACursosS();

                    finish();
                }

            }
        });

        setBackground();

    }
    private void CrearUsuario(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios").child(mSingletonFII.getUsuario().getCodigo());
        myRef.setValue(mSingletonFII.getUsuario()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                SaveCursos();
            }
        });

    }


    private void SaveCursos() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefT = database.getReference("usuarios").child(mSingletonFII.getUsuario().getCodigo()).child("cursos");
        myRefT.setValue(mSingletonFII.getHorariosFiltradosString());
    }

    private void IrACursosS() {
        Intent i = new Intent(this, Panel_Curso.class);
        startActivity(i);
    }

    private boolean VerificarValidez() {
        return mSingletonFII.getIndexes().size()>0;
    }

    private void irActivity() {
        Intent i = new Intent(this, PickActivity.class);
        startActivity(i);
        finish();
    }

    private void setBackground(){

        switch (SingletonFII.getSingletonFII(this).getESCUELA())
        {
        case SingletonFII.INDUSTRIAL:
                mEscuela.setBackgroundResource(R.drawable.indust);
                break;
            case SingletonFII.TEXTIL:
                mEscuela.setBackgroundResource(R.drawable.texti);
                break;
            case SingletonFII.SEGURIDAD:
                mEscuela.setBackgroundResource(R.drawable.segur);
                break;

        }

    }


}
