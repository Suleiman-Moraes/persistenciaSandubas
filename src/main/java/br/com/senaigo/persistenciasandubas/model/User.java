package br.com.senaigo.persistenciasandubas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_m")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String senha;
	
	private String nome;
	
	private String login;
	
	private String email;
	
	private String cpf;

	public User() {}
	public User(Long id, String senha, String nome, String login, String email, String cpf) {
		super();
		this.id = id;
		this.senha = senha;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.cpf = cpf;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", senha=" + senha + ", nome=" + nome + ", login=" + login + ", email=" + email
				+ ", cpf=" + cpf + "]";
	}
}
