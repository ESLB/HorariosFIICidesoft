package fii.industrial.cidesoft.horariofii.escogerEscuela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.escogerCiclo.CiclesAct;

public class SchoolAct extends AppCompatActivity {

    private LinearLayout Fondo;
    private int Contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela);
        Button boton = (Button) findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),CiclesAct.class);
                        startActivityForResult(intent,0);
            }
        });
        Button botonn = (Button) findViewById(R.id.button2);
        botonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),CiclesAct.class);
                startActivityForResult(intent,0);
            }
        });
        Button bot = (Button) findViewById(R.id.button3);
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),CiclesAct.class);
                startActivityForResult(intent,0);
            }
        });

        Fondo = (LinearLayout) findViewById(R.id.linearLayout);
        Fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contador++;
                if(Contador>3){
                    Toasty.success(SchoolAct.this, "Lesly Vera").show();
                }
            }
        });
    }
}
