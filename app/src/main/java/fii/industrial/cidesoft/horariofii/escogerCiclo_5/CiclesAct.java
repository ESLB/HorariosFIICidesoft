package fii.industrial.cidesoft.horariofii.escogerCiclo_5;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;

public class CiclesAct extends AppCompatActivity {

    private LinearLayout mEscuela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclos);

        mEscuela = (LinearLayout) findViewById(R.id.linearLayoutCiclos);
        setBackground();
    }

    private void setBackground(){

        switch (SingletonFII.getSingletonFII(this).getESCUELA())
        {
            case SingletonFII.INDUSTRIAL:
                mEscuela.setBackgroundResource(R.drawable.indust);
                break;
            case SingletonFII.TEXTIL:
                mEscuela.setBackgroundResource(R.drawable.texti);
                break;
            case SingletonFII.SEGURIDAD:
                mEscuela.setBackgroundResource(R.drawable.segur);
                break;

        }

    }

}
