package fii.industrial.cidesoft.horariofii.cursosLista_3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.Curso;
import fii.industrial.cidesoft.horariofii.model.CursoS;
import fii.industrial.cidesoft.horariofii.model.Horario;
import fii.industrial.cidesoft.horariofii.model.SingletonFII;
import fii.industrial.cidesoft.horariofii.model.database.DataBaseFirebase;

public class Panel_Curso extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private ArrayList<DatabaseReference> databaseReferenceList = new ArrayList<DatabaseReference>();
    private ArrayList<ValueEventListener> valueEventListenerLest = new ArrayList<ValueEventListener>();

    private SingletonFII mSingletonFII;
    private ArrayList<Horario> mHorariosEscogidos = new ArrayList<Horario>();
    ArrayList<CursoS> datos = new ArrayList<CursoS>();
    @Override
    protected void onResume() {
        super.onResume();
        Toasty.info(Panel_Curso.this, "Se llamo").show();
        mSingletonFII.getUsuario().setContador(mSingletonFII.getUsuario().getContador()+1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefT = database.getReference("usuarios").child(mSingletonFII.getUsuario().getCodigo()).child("contador");
        myRefT.setValue(mSingletonFII.getUsuario().getContador());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel__curso);

        mSingletonFII = SingletonFII.getSingletonFII(getApplicationContext());


        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerviewcursos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdapter = new RecyclerAdapter(datos);
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter.setData(datos);
        //mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mSingletonFII.NeedExcecuteForLoop()){
            setForLoop();
            mSingletonFII.setNeedExcecuteForLoop(false);
        }
    }

    private void setForLoop() {
        String lugar;

        for (String dato: mSingletonFII.getIndexes()) {
            lugar = dato.split("-")[0];
            final String seccion = dato.split("-")[1];
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference();
            myRef.child("cursos").child(lugar)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Horario horario = new Horario();
                    horario = dataSnapshot.getValue(Horario.class);
                    horario.setSeccion(Integer.valueOf(seccion));
                    mHorariosEscogidos.add(horario);
                    generarHorarios();
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //Mostrar mensaje de error
                }
            });
        }
    }

    private void generarHorarios() {
        int pos = 0;
        boolean erase = false;
        int cont = 0;
        for(Horario horario : mHorariosEscogidos){
            if(horario.getId_curso().equals(mHorariosEscogidos.get(mHorariosEscogidos.size()-1).getId_curso())){
                cont++;
            }
        }

        if(cont>1) {
            Horario horario1 = mHorariosEscogidos.get(mHorariosEscogidos.size() - 1);
            mHorariosEscogidos.remove(mHorariosEscogidos.size() - 1);
            if (mHorariosEscogidos.size() > 2) {
                for (Horario horario : mHorariosEscogidos) {
                    if (horario.getId_curso().equals(horario1.getId_curso())) {
                        if (mHorariosEscogidos.indexOf(horario) != mHorariosEscogidos.size() - 1)
                            pos = mHorariosEscogidos.indexOf(horario);
                        erase = true;
                    }
                }

                if (erase) {
                    mHorariosEscogidos.add(pos, horario1);
                    mHorariosEscogidos.remove(pos + 1);
                }
            }
        }
        datos.clear();
        for(Horario horario: mHorariosEscogidos){
            int i = horario.getSeccion();
            CursoS curso1 = new CursoS();
            curso1.setNombre_Cruso(horario.getNombre());
            curso1.setSalon(horario.getSalones().get(i));
            curso1.setSeccion(horario.getSecciones().get(i));
            curso1.setProfesor(horario.getProfesores().get(i));
            curso1.setHorario(horario.getHorarios().get(i));
            datos.add(curso1);
        }
        mAdapter.setCursos(datos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSingletonFII.setNeedExcecuteForLoop(true);
    }

    private class DatosHolder extends RecyclerView.ViewHolder {
        private TextView nombcurso,nombprofesor,seccion,horario,salon;
        private CursoS mCurso;

        public DatosHolder(View itemView){
            super(itemView);

            nombcurso = (TextView) itemView.findViewById(R.id.txt_curso);
            nombprofesor = (TextView) itemView.findViewById(R.id.txt_profesor);
            seccion = (TextView) itemView.findViewById(R.id.txt_seccion);
            horario= (TextView) itemView.findViewById(R.id.txt_horario);
            salon= (TextView) itemView.findViewById(R.id.txt_salon);

        }

        public void bindData(CursoS curso){
            mCurso = curso;
            nombcurso.setText(curso.getNombre_Cruso());
            nombprofesor.setText(curso.getProfesor());
            seccion.setText(curso.getSeccion());
            horario.setText(curso.getHorario());
            salon.setText(curso.getSalon());

        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<DatosHolder>{

        private List<CursoS> mCursos;

        public RecyclerAdapter(List<CursoS> datos){
            mCursos = datos;
        }

        @Override
        public DatosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.item_curso, parent, false);
            return  new DatosHolder(view);
        }

        @Override
        public void onBindViewHolder(DatosHolder holder, int position) {
            CursoS dato = mCursos.get(position);
            holder.bindData(dato);
        }

        @Override
        public int getItemCount() {
            return mCursos.size();
        }

        public void setCursos(List<CursoS> datos){
            mCursos = datos;
        }

    }



}
