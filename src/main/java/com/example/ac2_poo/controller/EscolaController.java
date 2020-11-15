package com.example.ac2_poo.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.ac2_poo.dto.CursoDTOLista;
import com.example.ac2_poo.dto.EscolaDTO;
import com.example.ac2_poo.model.Curso;
import com.example.ac2_poo.model.Escola;
import com.example.ac2_poo.service.CursoService;
import com.example.ac2_poo.service.EscolaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class EscolaController {
    
    @Autowired
    private EscolaService service;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/escola")
    public List<Escola> getEscolas(){
        return service.getEscolas();
    }

    @GetMapping("/escola/{codigo}")
    public ResponseEntity<Escola> getEscolaByCodigos(@PathVariable int codigo){
        
        Escola escolas = service.getEscolasByCodigos(codigo);
        return ResponseEntity.ok(escolas);
    }

    @PostMapping("/escola")
    public ResponseEntity<Escola> salvar(@RequestBody Escola escola){ //essa anotação pega os dados do postman e salva
        Escola novaEscola = service.save(escola);
        URI uri = URI.create("http://localhost:8080/escola/" + novaEscola.getCodigo());
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping("/escola/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){

        service.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/escola/{codigo}")
    public ResponseEntity<Escola> atualizar(@RequestBody EscolaDTO escolaDTO, HttpServletRequest request, UriComponentsBuilder builder, @PathVariable int codigo){ 
        
        Escola escola = service.fromDTO(escolaDTO);

            escola.setCodigo(codigo);
            service.update(escola);
            return ResponseEntity.ok(escola);
    }

    @PostMapping("/escola/{codigo}/curso")
    public ResponseEntity<Curso> salvar(@PathVariable int codigo, @RequestBody Curso curso, HttpServletRequest request, UriComponentsBuilder builder){
        
        curso =  cursoService.salvar(curso, codigo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + curso.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    } 

    @GetMapping("/escola/{codigo}/curso")
    public List<CursoDTOLista> getCursosEscola(@PathVariable int codigo){
        Escola escola = service.getEscolasByCodigos(codigo);
        return cursoService.toListDTO(escola.getCursos());
    }
}
