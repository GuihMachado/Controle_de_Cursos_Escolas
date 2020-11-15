package com.example.ac2_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;

import org.springframework.stereotype.Component;

@Component
public class EscolasRepository {
    
    private ArrayList<Escola> escolas = new ArrayList<Escola>();
    private int nextCode=1;

    public List<Escola> getEscolas(){
        return escolas;
    }

    public Optional<Escola> getEscolasByCodigos(int codigo){
        for(Escola aux : escolas) {
            if(aux.getCodigo() == codigo){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Escola save(Escola escola)
    {
        escola.setCodigo(nextCode++);
        escolas.add(escola);
        return escola ;
    }

	public void remove(Escola escola) {
        escolas.remove(escola);
	}

	public Escola update(Escola escola) {
        Escola aux = getEscolasByCodigos(escola.getCodigo()).get();

        if(aux != null){
            aux.setEndereco(escola.getEndereco());
            aux.setQtdprof(escola.getQtdprof());
        }
        return aux;
    }
        
        public Optional<Escola> getEscolaByCurso(int codigo){
            List<Escola> escolas = getEscolas();
            for(Escola e : escolas){
                for(Curso c: e.getCursos()){
                    if(c.getCodigo() == codigo){
                        return Optional.of(e);
                    }
                }
            }
            return Optional.empty();
        }
    
}
