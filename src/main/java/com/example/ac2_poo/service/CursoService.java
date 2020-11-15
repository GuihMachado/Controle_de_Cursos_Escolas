package com.example.ac2_poo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ac2_poo.dto.CursoDTO;
import com.example.ac2_poo.dto.CursoDTOLista;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;
import com.example.ac2_poo.repository.CursosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {

    @Autowired
    private CursosRepository repository;

    @Autowired
    private EscolaService escolaService;

    public Curso fromDTO(CursoDTO dto){
        Curso curso = new Curso();
        curso.setDescricao(dto.getDescricao());
        return curso;
    }

	public List<Curso> getCursos() {
		return repository.getCursos();
	}

	public Curso getCursosByCodigos(int codigo) {
        Optional<Curso> crs = repository.getCursosByCodigos(codigo);
        return crs.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse curso não esta cadastrado!"));
	}

	public Curso save(Curso curso) {
		return repository.save(curso);
	}

	public void remove(int codigo){
        repository.remove(getCursosByCodigos(codigo));
	}

	public Curso update(Curso curso) {
        getCursosByCodigos(curso.getCodigo());
        return repository.update(curso);
	}
    
    public Curso salvar(Curso curso, int codigo){

        Escola escola = escolaService.getEscolasByCodigos(codigo);

        curso.setEscola(escola);
        escola.addCurso(curso);
        return repository.save(curso);
    }

    public CursoDTOLista toDTO(Curso curso){
        CursoDTOLista dto = new CursoDTOLista();

        dto.setCodigo(curso.getCodigo());
        dto.setNome(curso.getNome());
        dto.setGrau(curso.getGrau());
        dto.setDescricao(curso.getDescricao());
        dto.setArea(curso.getArea());
        return dto;
    }

    public List<CursoDTOLista> toListDTO(List<Curso> cursos){

        ArrayList<CursoDTOLista> listDTO = new ArrayList<CursoDTOLista>();
        for(Curso aux : cursos){
            listDTO.add(toDTO(aux));
        }
        return listDTO;
    }

    public void removeByCodigo(int codigo){

        escolaService.removeCursoByEscola(getCursosByCodigos(codigo));
        repository.remove(getCursosByCodigos(codigo));
    }
}
