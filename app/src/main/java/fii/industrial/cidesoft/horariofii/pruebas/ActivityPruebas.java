package fii.industrial.cidesoft.horariofii.pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;

public class ActivityPruebas extends AppCompatActivity {

    private TextView CursosFiltrados;
    private SingletonFII mSingletonFII;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);
        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());
        CursosFiltrados = (TextView) findViewById(R.id.txt_cursosFiltrados);
        CursosFiltrados.setText(mSingletonFII.getCursoS().get(0).getNombre_Cruso());
        Toasty.success(this, mSingletonFII.getCursoS().get(0).getProfesor()).show();
    }

}

