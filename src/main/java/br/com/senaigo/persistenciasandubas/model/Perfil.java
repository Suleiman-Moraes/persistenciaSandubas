package br.com.senaigo.persistenciasandubas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Manoel Albino Neto
 */
@Getter
@Setter
@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false, length = 30)
	private String nome;

	@NotNull
	@Column(nullable = false, length = 250)
	private String descricao;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "perfil_funcionalidade", joinColumns = { @JoinColumn(name = "id_perfil") }, inverseJoinColumns = {
			@JoinColumn(name = "id_funcionalidade") })
	private List<Funcionalidade> funcionalidades;
	
	@Column(name = "tipo_perfil")
	private String tipoPerfil;

	public Perfil() {}
	public Perfil(Long id, String nome, String descricao, List<Funcionalidade> funcionalidades) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.funcionalidades = funcionalidades;
	}
	public Perfil(Long id, String nome, String descricao, String tipoPerfil, Funcionalidade funcionalidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipoPerfil = tipoPerfil;
		getFuncionalidades().add(funcionalidade);
	}

	public List<Funcionalidade> getFuncionalidades() {
		if (funcionalidades == null) {
			funcionalidades = new ArrayList<Funcionalidade>(0);
		}
		return funcionalidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", funcionalidades="
				+ funcionalidades + "]";
	}
}
