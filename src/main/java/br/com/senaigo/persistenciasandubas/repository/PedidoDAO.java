package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {

	Pedido findByDataIsNullAndUserId(Long id);


}
