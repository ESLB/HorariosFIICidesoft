package fii.industrial.cidesoft.horariofii.escogerEscuela_4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.escogerCiclo_5.CiclesAct;
import fii.industrial.cidesoft.horariofii.model.Horario;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;
import io.reactivex.disposables.CompositeDisposable;

public class SchoolAct extends AppCompatActivity {

    private ConstraintLayout Fondo;
    private int Contador = 0;
    private Button industrial ;
    private Button seguridad;
    private Button textil;
    private ProgressBar progressBar;
    private SingletonFII mSingletonFII;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela);
        industrial = (Button) findViewById(R.id.btn_ciclo_1);
        seguridad= (Button) findViewById(R.id.btn_ciclo_3);
        textil = (Button) findViewById(R.id.btn_ciclo_2);
        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());
        progressBar = (ProgressBar) findViewById(R.id.prog_escuela);
        industrial.setVisibility(View.INVISIBLE);
        textil.setVisibility(View.INVISIBLE);
        seguridad.setVisibility(View.INVISIBLE);

        industrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setESCUELA(SingletonFII.INDUSTRIAL);
                IrActivity();
            }
        });
        textil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setESCUELA(SingletonFII.TEXTIL);
                IrActivity();
            }
        });
        seguridad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingletonFII.setESCUELA(SingletonFII.SEGURIDAD);
                IrActivity();
            }
        });

        Fondo = (ConstraintLayout) findViewById(R.id.linearLayout);
        Fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contador++;
                if(Contador>3){
                    Toasty.success(SchoolAct.this, "Lesly Vera").show();
                }
            }
        });

        getData();
    }

    private void getData() {
        if(mSingletonFII.isHasHorarios()){
            DataReady_ChangeUI();
        } else {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("cursos");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<ArrayList<Horario>> t = new GenericTypeIndicator<ArrayList<Horario>>() {};
                    ArrayList<Horario> Horarios = new ArrayList<Horario>();
                    Horarios = dataSnapshot.getValue(t);
                    if(Horarios!=null) {
                        Toasty.success(SchoolAct.this, Horarios.get(0).toString()).show();
                        Toasty.success(SchoolAct.this, Horarios.size()+"").show();
                        mSingletonFII.setHorarios(Horarios);
                        DataReady_ChangeUI();
                    }
                    else
                        ShowError();
                    myRef.removeEventListener(this);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toasty.error(SchoolAct.this, "Problemas").show();
                    myRef.removeEventListener(this);
                }
            });
            //Get data from firebase, store it in Singleton, change UI, That's it
        }
    }

    private void ShowError() {
        Toasty.error(SchoolAct.this, "Problemas, cargó, pero la data vino null").show();
    }

    private void IrActivity() {
        Intent i = null;
        i = new Intent(this, CiclesAct.class);
        startActivity(i);

    }

    private void DataReady_ChangeUI(){
        progressBar.setVisibility(View.INVISIBLE);
        industrial.setVisibility(View.VISIBLE);
        seguridad.setVisibility(View.VISIBLE);
        textil.setVisibility(View.VISIBLE);
    }




}
