package br.com.senaigo.persistenciasandubas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.senaigo.persistenciasandubas.model.enummeration.FuncaoUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.StatusUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Size(min = 6, message = "A confirmação da senha deve ter pelo menos 6 caracteres")
	@Column(length = 32)
	private String senha;

	@NotNull
	@Column(length = 180)
	private String nome;
	
	@NotNull
	@Column(length = 100, unique = true)
	private String login;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ativacao")
	private Date dataAtivacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_desativacao")
	private Date dataDesativacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusUsuarioEnum statusUsuarioEnum;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_funcao")
	private FuncaoUsuarioEnum funcaoUsuarioEnum;
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "id_email")
	private Email email;

	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "id_telefone")
	private Telefone telefone;

	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "usuario_perfil", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_perfil") })
	private List<Perfil> perfis;

	public Usuario() {}
	public Usuario(Long id, String senha, String nome, String login, Email email, Telefone telefone,
			StatusUsuarioEnum statusUsuarioEnum, FuncaoUsuarioEnum funcaoUsuarioEnum,
			Date dataAtivacao, Date dataDesativacao, List<Perfil> perfis) {
		this(id, senha, nome, login, statusUsuarioEnum, funcaoUsuarioEnum, email, telefone);
		this.dataAtivacao = dataAtivacao;
		this.dataDesativacao = dataDesativacao;
		this.perfis = perfis;
	}
	public Usuario(Long id, String senha, String nome, String login, StatusUsuarioEnum statusUsuarioEnum, 
			FuncaoUsuarioEnum funcaoUsuarioEnum, Email email, Telefone telefone) {
		this(senha, nome, login, statusUsuarioEnum, funcaoUsuarioEnum, email, telefone);
		this.id = id;
	}
	public Usuario(String senha, String nome, String login, StatusUsuarioEnum statusUsuarioEnum, 
			FuncaoUsuarioEnum funcaoUsuarioEnum, Email email, Telefone telefone) {
		this.senha = senha;
		this.nome = nome;
		this.login = login;
		this.statusUsuarioEnum = statusUsuarioEnum;
		this.funcaoUsuarioEnum = funcaoUsuarioEnum;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Email getEmail() {
		if (email == null) {
			email = new Email();
		}
		return email;
	}

	public List<Perfil> getPerfis() {
		if (perfis == null) {
			perfis = new ArrayList<Perfil>(0);
		}
		return perfis;
	}
	
	public Telefone getTelefone(){
		if(this.telefone == null) {
			this.telefone = new Telefone();
		}
		return this.telefone;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", senha=" + senha + ", nome=" + nome + ", login=" + login + ", email=" + email
				+ ", telefone=" + telefone + ", statusUsuarioEnum=" + statusUsuarioEnum + ", funcaoUsuarioEnum="
				+ funcaoUsuarioEnum + ", dataAtivacao=" + dataAtivacao + ", dataDesativacao=" + dataDesativacao
				+ ", perfis=" + perfis + "]";
	}
}
