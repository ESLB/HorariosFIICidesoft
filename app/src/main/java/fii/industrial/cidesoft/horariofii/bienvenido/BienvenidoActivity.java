package fii.industrial.cidesoft.horariofii.bienvenido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import fii.industrial.cidesoft.horariofii.R;

public class BienvenidoActivity extends AppCompatActivity implements BienvenidoContract.View{

    private ImageView Logo;
    private Button BotonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenido_act);



    }



    @Override
    public void setPresenter(BienvenidoContract.Presenter presenter) {

    }
}
