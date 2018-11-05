package fii.industrial.cidesoft.horariofii.acercaDe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;

public class planillaH extends AppCompatActivity {

    private ImageView Yuri;
    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planilla_h);

        Yuri = (ImageView) findViewById(R.id.imagen_acercade);
        Yuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                if(contador>2){
                    Toasty.success(planillaH.this, "Yuri Fernández").show();
                }

            }
        });

    }
}
