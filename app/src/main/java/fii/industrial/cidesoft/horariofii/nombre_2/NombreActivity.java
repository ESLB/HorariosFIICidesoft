package fii.industrial.cidesoft.horariofii.nombre_2;

import android.content.Intent;
import android.content.SharedPreferences;
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
import fii.industrial.cidesoft.horariofii.login_1.loginActivity;
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
        mSingletonFII.getUsuario().setContador(0);

        SharedPreferences settings = getSharedPreferences(SingletonFII.SHARED_PREFERENCES, MODE_PRIVATE);

        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("UsuarioInfo", true);
        editor.apply();
        editor.putString("NombreUsuario", mSingletonFII.getUsuario().getNombre());
        // Reading from SharedPreferences
        String value = settings.getString("key", "");

        //Guardar en el SharePreferences Datos Usuario Sí - Datos Cursos No

        //Guardar en el Firebase

        PasarACursos();
    }

    private void PasarACursos() {
        Intent i = new Intent(this, SchoolAct.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, loginActivity.class);
        startActivity(i);
    }
}
