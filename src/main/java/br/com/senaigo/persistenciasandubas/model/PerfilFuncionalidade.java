package br.com.senaigo.persistenciasandubas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "perfil_funcionalidade")
public class PerfilFuncionalidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Funcionalidade id_funcionalidade;
	
	private Perfil id_perfil;
}
