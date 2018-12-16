package fii.industrial.cidesoft.horariofii.login_1;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.acercaDe_9.AboutActivity;
import fii.industrial.cidesoft.horariofii.cursosLista_3.Panel_Curso;
import fii.industrial.cidesoft.horariofii.escogerEscuela_4.SchoolAct;
import fii.industrial.cidesoft.horariofii.escogerSecciones_6.PickActivity;
import fii.industrial.cidesoft.horariofii.nombre_2.NombreActivity;

public class loginActivity extends AppCompatActivity {

    private Button AcercaDe;
    private Button Ingresar;
    private ImageView LogoFII;
    private TextInputEditText Codigo;
    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AcercaDe = (Button) findViewById(R.id.btn_acercaDe_login);
        Ingresar = (Button) findViewById(R.id.btn_ingresar_login);
        Codigo = (TextInputEditText) findViewById(R.id.txt_codigo);

        AcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrActivity("about");
            }
        });

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });

        LogoFII = (ImageView) findViewById(R.id.img_logoFII);
        LogoFII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LlamarCreador();
            }
        });
    }

    private void IrActivity(String act) {
            Intent i = null;
        switch (act){
            case "about":
                i = new Intent(loginActivity.this, AboutActivity.class);
                break;
            case "school":
                i = new Intent(loginActivity.this, SchoolAct.class);
                break;
            case "horariofinal":
                i = new Intent(loginActivity.this, PickActivity.class);
                break;
            case "registrarNombre":
                i = new Intent(loginActivity.this, NombreActivity.class);
                break;
        }
        if(i!=null)
        startActivity(i);

    }

    private void validarDatos() {

        boolean firstTime = Codigo.getText().toString().length()==0;
        if(firstTime){
            IrActivity("registrarNombre");
        } else{
            IrActivity("horariofinal");
        }
    }

    private void LlamarCreador() {
        contador++;
        if(contador>2){
            Toasty.success(loginActivity.this, "Andre León").show();
        }
    }


}
