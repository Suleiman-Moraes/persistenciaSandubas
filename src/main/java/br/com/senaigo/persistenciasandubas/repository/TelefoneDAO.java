package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Telefone;

public interface TelefoneDAO extends JpaRepository<Telefone, Long>{}
