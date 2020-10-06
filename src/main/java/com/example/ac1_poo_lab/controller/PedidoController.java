package com.example.ac1_poo_lab.controller;

import java.net.URI;
import java.util.List;

import com.example.ac1_poo_lab.model.Pedido;
import com.example.ac1_poo_lab.repository.PedidosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @Autowired
    private PedidosRepository repository;

    @GetMapping("/pedido")
    public List<Pedido> getPedido(){
        return repository.getPedidos();
    }

    @GetMapping("/pedidos/{codigo}")
    public ResponseEntity<Pedido> getPedidos(@PathVariable int codigo){
        Pedido pedidos = repository.getPedidosByCodigos(codigo);
        
        if(pedidos != null){
            return ResponseEntity.ok(pedidos);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pedido")
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedidos){ //essa anotação pega os dados do postman e salva
        Pedido novoPedido = repository.save(pedidos);
        URI uri = URI.create("http://localhost:8080/pedidos/" + novoPedido.getCodigo());
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping("/pedidos/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        Pedido pedido = repository.getPedidosByCodigos(codigo);
        
        if(pedido != null){
            repository.remove(pedido);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pedidos/{codigo}")
    public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido, @PathVariable int codigo){
        

        if(repository.getPedidosByCodigos(codigo) != null){
            pedido.setCodigo(codigo);
            repository.update(pedido);
            return ResponseEntity.ok(pedido);

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
