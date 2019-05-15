package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.DetalhePedido;

public interface DetalhePedidoDAO extends JpaRepository<DetalhePedido, Long> {

}
