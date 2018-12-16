package fii.industrial.cidesoft.horariofii.cursosLista_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fii.industrial.cidesoft.horariofii.R;
import fii.industrial.cidesoft.horariofii.model.CursoS;

public class Panel_Curso extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panel__curso);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerviewcursos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        List<CursoS> datos = createData();
        mAdapter = new RecyclerAdapter(datos);
        mRecyclerView.setAdapter(mAdapter);
        //mAdapter.setData(datos);
        //mAdapter.notifyDataSetChanged();
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
