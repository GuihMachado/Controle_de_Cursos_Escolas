package com.example.ac1_poo_lab.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.ac1_poo_lab.model.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidosRepository {
    private ArrayList<Pedido> pedid = new ArrayList<Pedido>();
    private int nextCode;

    public List<Pedido> getPedidos(){
        return pedid;
    }

    public Pedido getPedidosByCodigos(int codigo){
        for(Pedido aux : pedid) {
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return null;
    }

        public Pedido save(Pedido pedidos)
        {
            pedidos.setCodigo(nextCode++);
            pedid.add(pedidos);
            return pedidos ;
        }

		public void remove(Pedido pedido) {
            pedid.remove(pedido);
		}

		public Pedido update(Pedido pedido) {
            Pedido aux = getPedidosByCodigos(pedido.getCodigo());

            if(aux != null){
                aux.setValor(pedido.getValor());
                aux.setDescricao(pedido.getDescricao());
                aux.setCliente(pedido.getCliente());
                aux.setDatapedido(pedido.getDatapedido());
            }
            return aux;
        }
        
}
