package com.example.ac2_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.model.Curso;

import org.springframework.stereotype.Component;

@Component
public class CursosRepository {
    
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    private int nextCode=1;

    public List<Curso> getCursos(){
        return cursos;
    }

    public Optional<Curso> getCursosByCodigos(int codigo){
        for(Curso aux : cursos) {
            if(aux.getCodigo() == codigo){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

        public Curso save(Curso curso)
        {
            curso.setCodigo(nextCode++);
            cursos.add(curso);
            return curso ;
        }

		public void remove(Curso curso) {
            cursos.remove(curso);
		}

		public Curso update(Curso curso) {
            Curso aux = getCursosByCodigos(curso.getCodigo()).get();

            if(aux != null){
                aux.setDescricao(curso.getDescricao());
            }
            return aux;
        }
        
}
