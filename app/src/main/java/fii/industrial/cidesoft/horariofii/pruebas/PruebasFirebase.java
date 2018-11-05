package fii.industrial.cidesoft.horariofii.pruebas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.Usuario;

public class PruebasFirebase extends AppCompatActivity {

    private EditText Codigo;
    private EditText NombreApellido;
    private Button Comprobar;
    private Button CrearUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_firebase);

        Codigo = (EditText) findViewById(R.id.edt_codigo);
        NombreApellido = (EditText) findViewById(R.id.edt_nombreApellido);
        Comprobar = (Button) findViewById(R.id.btn_comprobar);
        CrearUsuario = (Button) findViewById(R.id.btn_crearUsuario);

        Comprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Codigo!=null||!Codigo.getText().equals("")){
                    comprobar(Codigo.getText().toString());
                }

            }
        });

        CrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Codigo!=null||!Codigo.getText().equals("")){
                    if(NombreApellido!=null||!NombreApellido.getText().equals("")){
                        crearUsuario(Codigo.getText().toString(), NombreApellido.getText().toString());
                    }
                }
            }
        });

    }

    private void comprobar(final String codigo) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("usuarios").child(codigo);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                if(usuario == null){
                    Toast.makeText(PruebasFirebase.this, "1 No existe usuario" + " ", Toast.LENGTH_SHORT).show();
                    myRef.removeEventListener(this);
                }
                else{
                    myRef.removeEventListener(this);
                    Toast.makeText(PruebasFirebase.this, usuario.getNombre() + " 2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PruebasFirebase.this, "Salió mal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void crearUsuario(String codigo, String nombre) {

        Usuario usuario = new Usuario();
        usuario.setCodigo(codigo);
        usuario.setContador(1);
        usuario.setCursos("");
        usuario.setNombre(nombre);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios").child(codigo);
        myRef.setValue(usuario);
    }
}
