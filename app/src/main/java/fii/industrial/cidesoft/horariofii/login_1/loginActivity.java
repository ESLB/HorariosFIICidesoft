package fii.industrial.cidesoft.horariofii.login_1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import fii.industrial.cidesoft.horariofii.model.SingletonFII;
import fii.industrial.cidesoft.horariofii.model.Usuario;
import fii.industrial.cidesoft.horariofii.nombre_2.NombreActivity;

import static fii.industrial.cidesoft.horariofii.utils.stringVerifier.ValidString;

public class loginActivity extends AppCompatActivity {

    private Button AcercaDe;
    private Button Ingresar;
    private ImageView LogoFII;
    private TextInputEditText Codigo;
    private int contador = 0;
    private SingletonFII mSingletonFII;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());

        Ingresar = (Button) findViewById(R.id.btn_ingresar_login);
        AcercaDe = (Button) findViewById(R.id.btn_acercaDe_login);
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
                String codigo = Codigo.getText().toString();
                if(ValidString(codigo)){
                    validarDatos(codigo);
                } else {
                    Toasty.info(loginActivity.this, "Ingrese un código").show();
                }

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
                i = new Intent(loginActivity.this, Panel_Curso.class);
                break;
            case "registrarNombre":
                i = new Intent(loginActivity.this, NombreActivity.class);
                break;
        }
        if(i!=null)
        startActivity(i);

    }

    private void validarDatos(String codigo) {
        final String codigoN = codigo;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("usuarios").child(codigo);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                if(usuario == null){
                    Usuario usuarioN = new Usuario();
                    ArrayList<String> indexes = new ArrayList<>();
                    usuarioN.setContador(0);
                    usuarioN.setCodigo(codigoN);
                    usuarioN.setNombre("");
                    usuarioN.setIndexes(indexes);
                    mSingletonFII.setUsuario(usuarioN);
                    IrActivity("registrarNombre");
                    myRef.removeEventListener(this);
                }
                else{
                    mSingletonFII.setUsuario(usuario);
                    IrActivity("horariofinal");
                    myRef.removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(loginActivity.this, "Salió mal, intente de nuevo más tarde", Toast.LENGTH_SHORT).show();
            }
        });

        /*boolean firstTime = Codigo.getText().toString().length()==0;
        if(firstTime){
            IrActivity("registrarNombre");
        } else{
            IrActivity("horariofinal");
        }*/
    }

    private void LlamarCreador() {
        contador++;
        if(contador>2){
            Toasty.success(loginActivity.this, "Andre León").show();
        }
    }


}
