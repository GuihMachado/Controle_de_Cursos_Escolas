package com.example.ac2_poo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Escola {
    private int codigo;
    private String nome;
    private String endereco;
    private String cidade;
    private int qtdprof;
    
    @JsonIgnore
    private ArrayList<Curso> cursos = new ArrayList<Curso>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getQtdprof() {
        return qtdprof;
    }

    public void setQtdprof(int qtdprof) {
        this.qtdprof = qtdprof;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos){
        this.cursos = cursos;
    }

    public boolean addCurso(Curso curso){
        return cursos.add(curso);
    }

    public boolean removeCurso(Curso curso){
        return cursos.remove(curso);
    }

}
