package com.example.ac2_poo.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.ac2_poo.dto.CursoDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CursoController {


    @Autowired
    private CursoService service;

    @GetMapping("/cursos")
    public List<Curso> getCursos(){
        return service.getCursos();
    }

    @GetMapping("/curso/{codigo}")
    public ResponseEntity<Curso> getCursos(@PathVariable int codigo){
        
        Curso cursos = service.getCursosByCodigos(codigo);
        return ResponseEntity.ok(cursos);
    }

    @PostMapping("/curso")
    public ResponseEntity<Curso> salvar(@RequestBody Curso curso){ //essa anotação pega os dados do postman e salva
        Curso novoCurso = service.save(curso);
        URI uri = URI.create("http://localhost:8080/curso/" + novoCurso.getCodigo());
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping("/curso/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        
        service.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/curso/{codigo}")
    public ResponseEntity<Curso> atualizar(@RequestBody CursoDTO cursoDTO, HttpServletRequest request, UriComponentsBuilder builder, @PathVariable int codigo){ 
        
        Curso curso = service.fromDTO(cursoDTO);

            curso.setCodigo(codigo);
            service.update(curso);
            return ResponseEntity.ok(curso);
    }
}
