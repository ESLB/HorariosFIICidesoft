package fii.industrial.cidesoft.horariofii.bienvenido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;


import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.login_1.loginActivity;
import fii.industrial.cidesoft.horariofii.pruebas.PruebasFirebase;
import fii.industrial.cidesoft.horariofii.pruebas.TestHorarios;

public class TempwelcomeActivity extends AppCompatActivity {

    private ImageView Logo;
    private Button AcercaDe;
    private Button Pantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenido_act);

        AcercaDe = (Button) findViewById(R.id.btn_acercaDe);
        AcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TempwelcomeActivity.this, PruebasFirebase.class);
                startActivity(i);
            }

        });

        AcercaDe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(TempwelcomeActivity.this, TestHorarios.class);
                startActivity(i);
                return true;
            }
        });

        Pantalla = (Button) findViewById(R.id.btn_pantalla);
        Pantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TempwelcomeActivity.this, loginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


}
