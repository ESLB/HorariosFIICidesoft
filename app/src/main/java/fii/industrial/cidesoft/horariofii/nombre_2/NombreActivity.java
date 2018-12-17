package fii.industrial.cidesoft.horariofii.nombre_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.escogerEscuela_4.SchoolAct;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;

import static fii.industrial.cidesoft.horariofii.utils.stringVerifier.ValidString;

public class NombreActivity extends AppCompatActivity {

    private Button Aceptar;
    private EditText Nombre;
    private SingletonFII mSingletonFII;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());

        Aceptar = (Button) findViewById(R.id.btn_aceptar);
        Nombre = (EditText) findViewById(R.id.edit_nombre);

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato = Nombre.getText().toString();
                if(ValidString(dato)){
                    CrearUsuario(dato);
                }else{
                    Toasty.info(NombreActivity.this,"Ingrese un nombre").show();
                }

            }
        });

    }


    private void CrearUsuario(String nombreCompleto){

        mSingletonFII.getUsuario().setNombre(nombreCompleto);
        mSingletonFII.getUsuario().setContador(1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios").child(mSingletonFII.getUsuario().getCodigo());
        myRef.setValue(mSingletonFII.getUsuario()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                PasarACursos();
            }
        });

    }

    private void PasarACursos() {
        Intent i = new Intent(this, SchoolAct.class);
        startActivity(i);
    }

}
