package fii.industrial.cidesoft.horariofii.escogerSecciones_6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.Curso;

public class PickActivity extends AppCompatActivity {

    private TextView textView_curso;
    private TextView textView_Profesor;
    private Button button_Guardar;
    private Spinner spinner_seccion;
    private CheckBox checkBox_estado_curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_e);

        textView_curso = (TextView)findViewById(R.id.text_view_curso);
        textView_Profesor = (TextView)findViewById(R.id.text_view_profesor);
        button_Guardar = (Button)findViewById(R.id.boton_guardar);
        spinner_seccion = (Spinner)findViewById(R.id.spinner_seccion);
        checkBox_estado_curso = (CheckBox)findViewById(R.id.check_estado_curso);

        //Objeto de ejemplo
        Curso curso_ejemplo = new Curso();

        //Al iniciar la actividad se muestra la informacion
        MostrarInformacion(curso_ejemplo);

        button_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private Curso MostrarInformacion(Curso curso){

        Curso informacion_curso = curso;

        textView_curso.setText(curso.getCurso());
        textView_Profesor.setText(curso.getProfesor());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, curso.getSeccion());
        spinner_seccion.setAdapter(arrayAdapter);

        return informacion_curso;
    }

}
