package fii.industrial.cidesoft.horariofii.nombre_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.escogerEscuela_4.SchoolAct;

import static fii.industrial.cidesoft.horariofii.utils.stringVerifier.ValidString;

public class NombreActivity extends AppCompatActivity {

    private Button Aceptar;
    private EditText Nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre);

        Aceptar = (Button) findViewById(R.id.btn_aceptar);
        Nombre = (EditText) findViewById(R.id.edit_nombre);

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato = Nombre.getText().toString();
                if(ValidString(dato)){
                    CrearUsuario(dato);
                }else{
                    //String invalidad, toast
                }

            }
        });

    }


    private void CrearUsuario(String nombreCompleto){

        //OnSuccess
        PasarACursos();
        //else, notificar error

    }

    private void PasarACursos() {
        Intent i = new Intent(this, SchoolAct.class);
        startActivity(i);
    }

}
