package fii.industrial.cidesoft.horariofii.bienvenido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import fii.industrial.cidesoft.horariofii.R;

public class BienvenidoActivity extends AppCompatActivity implements BienvenidoContract.View{

    private ImageView Logo;
    private Button BotonEntrar;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenido_act);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        BotonEntrar = (Button) findViewById(R.id.btn_entrar);
        BotonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("Usuario", "16170057");
                mFirebaseAnalytics.logEvent("entro_app", params);
                Toast.makeText(BienvenidoActivity.this, "Click", Toast.LENGTH_SHORT).show();

            }
        });
    }



    @Override
    public void setPresenter(BienvenidoContract.Presenter presenter) {

    }
}
