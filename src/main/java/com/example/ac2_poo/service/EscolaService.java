package com.example.ac2_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.dto.EscolaDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;
import com.example.ac2_poo.repository.EscolasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EscolaService {

    @Autowired
    private EscolasRepository repository;
    
    public Escola fromDTO(EscolaDTO dto){
        Escola escola = new Escola();
        escola.setEndereco(dto.getEndereco());
        escola.setQtdprof(dto.getQtdprof());
        return escola;
    }

	public List<Escola> getEscolas() {
		return repository.getEscolas();
	}

	public void removeByCodigo(int codigo) {
        repository.remove(getEscolasByCodigos(codigo));
	}

	public Escola update(Escola escola) {
        getEscolasByCodigos(escola.getCodigo());
        return repository.update(escola);
	}

	public Escola getEscolasByCodigos(int codigo) {
        Optional<Escola> esc = repository.getEscolasByCodigos(codigo);
		return esc.orElseThrow( ( ) -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa escola não está cadastrado!"));
	}

	public Escola save(Escola escola) {
		return repository.save(escola);
	}

	public void removeCursoByEscola(Curso curso) {

        Optional<Escola> op = repository.getEscolaByCurso(curso.getCodigo());
        op.get().removeCurso(curso);
    }
}
