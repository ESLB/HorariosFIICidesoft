package fii.industrial.cidesoft.horariofii.splashScreen_0;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.acercaDe_9.AboutActivity;
import fii.industrial.cidesoft.horariofii.cursosLista_3.Panel_Curso;
import fii.industrial.cidesoft.horariofii.escogerEscuela_4.SchoolAct;
import fii.industrial.cidesoft.horariofii.login_1.loginActivity;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;
import fii.industrial.cidesoft.horariofii.model.Usuario;
import fii.industrial.cidesoft.horariofii.nombre_2.NombreActivity;

public class SplashActivity extends AppCompatActivity {
    private SingletonFII mSingletonFII;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ValidarSesion();
            }
        }, 1000);


    }


    private void ValidarSesion(){

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SingletonFII.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        boolean registradoInfoUsuario = sharedPref.getBoolean("UsuarioInfo", false);
        boolean registradoInfoCursos = sharedPref.getBoolean("CursosInfo", false);

        if(registradoInfoCursos&&registradoInfoUsuario){
            IrActivity("horariofinal");
            //Leer info y guardarla en SingleTone
        } else if(registradoInfoUsuario){
            IrActivity("school");
            //Leer info y guardarla en SingleTone
        } else {
            editor.remove("UsuarioInfo");
            editor.remove("CursosInfo");
            IrActivity("login");
        }

        /*String codigo = sharedPref.getString("CODIGO", "Not found");
        editor.clear();
        editor.apply();
        if(!codigo.equals("Not found")){
            Toasty.success(this, "NN " + codigo).show();
            if(!codigo.equals("N")) {
                Toasty.success(this, "Llegó" +  codigo).show();
                mSingletonFII.setCodigo(codigo);
                IrACursosFinal();
                finish();
            }
        } else {
            IrALogin();
        }*/

    }



    private void IrALogin(){

        Intent i = new Intent(SplashActivity.this, loginActivity.class);
        Toasty.success(this, "No Hubo Código").show();
        startActivity(i);


        finish();
    }

    private void IrActivity(String act) {
        Intent i = null;
        switch (act){
            case "about":
                i = new Intent(SplashActivity.this, AboutActivity.class);
                break;
            case "school":
                i = new Intent(SplashActivity.this, SchoolAct.class);
                break;
            case "horariofinal":
                i = new Intent(SplashActivity.this, Panel_Curso.class);
                break;
            case "registrarNombre":
                i = new Intent(SplashActivity.this, NombreActivity.class);
                break;
            case "login":
                i = new Intent(SplashActivity.this, loginActivity.class);
                break;
        }
        if(i!=null)
            startActivity(i);
        finish();
    }

    private void IrACursosFinal(){

        Intent i = new Intent(SplashActivity.this, Panel_Curso.class);
        startActivity(i);

        finish();
    }
}
