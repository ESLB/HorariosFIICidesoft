package fii.industrial.cidesoft.horariofii.bienvenido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.acercaDe.planillaH;
import fii.industrial.cidesoft.horariofii.escogerEscuela.EscogerEscuela;

public class loginActivity extends AppCompatActivity {

    private Button AcercaDe;
    private Button Ingresar;
    private ImageView LogoFII;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AcercaDe = (Button) findViewById(R.id.btn_acercaDe_login);
        Ingresar = (Button) findViewById(R.id.btn_ingresar_login);

        AcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this, planillaH.class);
                startActivity(i);
            }
        });

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginActivity.this, EscogerEscuela.class);
                startActivity(i);
            }
        });

        LogoFII = (ImageView) findViewById(R.id.img_logoFII);
        LogoFII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                if(contador>2){
                    Toasty.success(loginActivity.this, "Andre León").show();
                }
            }
        });
    }
}
