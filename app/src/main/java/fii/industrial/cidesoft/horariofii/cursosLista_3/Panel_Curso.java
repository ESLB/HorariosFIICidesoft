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
        List<CursoS> datos = createData();
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
        }
    }

    private void setForLoop() {
        String lugar;
        for (String dato: mSingletonFII.getIndexes()) {
            lugar = dato.split("-")[0];
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference();
            Toasty.success(Panel_Curso.this, lugar).show();
            myRef.child("cursos").child(lugar)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Horario horario = new Horario();
                    horario = dataSnapshot.getValue(Horario.class);
                    Toasty.success(Panel_Curso.this, dataSnapshot.toString()).show();
                    Toasty.success(Panel_Curso.this, horario.toString()).show();
                    mSingletonFII.setNeedExcecuteForLoop(false);
                    ArrayList<Horario> horariosEscogidos = mSingletonFII.getmHorariosEscogidos();
                    for(Horario horarioS: horariosEscogidos){
                        if(horarioS.getId_curso().equals(horario.getId_curso())){
                            horariosEscogidos.remove(horarioS);
                        }
                    }

                    horariosEscogidos.add(horario);
                    mSingletonFII.setmHorariosEscogidos(horariosEscogidos);
                    databaseReferenceList.add(myRef);
                    valueEventListenerLest.add(this);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //Mostrar mensaje de error
                    databaseReferenceList.add(myRef);
                    valueEventListenerLest.add(this);
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for(int i = 0; i < databaseReferenceList.size(); i++){
            databaseReferenceList.get(i).removeEventListener(valueEventListenerLest.get(i));

        }
    }

    private List<CursoS> createData() {

        List<CursoS> cursos = new ArrayList<CursoS>();
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));
        cursos.add(new CursoS("OPE II", "JUARI", "2","Salon","HOJRA"));

        return cursos;

    }

    // TODO: Rename method, update argument and hook method into UI event

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
