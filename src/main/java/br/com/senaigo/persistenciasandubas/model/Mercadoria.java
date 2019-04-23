package br.com.senaigo.persistenciasandubas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mercadoria")
public class Mercadoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_pago")
	private Double precoPago;
	
	@Column(name = "porcentagem_venda")
	private Double porcentagemVenda;
	
	private String marca;
	
	private Double quantidade;
	
	@Column(name = "valor_medida")
	private String valorMedida;
	
	@Column(name = "valor_agrupamento")
	private Double valorAgrupamento;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_produto")
	private TipoProduto tipoProduto;

	@ManyToOne
	@JoinColumn(name = "id_classificacao_mercadoria")
	private ClassificacaoMercadoria classificacaoMercadoria; 
	
	public Mercadoria(Mercadoria objeto) {
		this.id = objeto.id;
		this.precoPago = objeto.precoPago;
		this.porcentagemVenda = objeto.porcentagemVenda;
		this.marca = objeto.marca;
		this.quantidade = objeto.quantidade;
		this.valorMedida = objeto.valorMedida;
		this.valorAgrupamento = objeto.valorAgrupamento;
		this.descricao = objeto.descricao;
		this.tipoProduto = objeto.tipoProduto;
		this.classificacaoMercadoria = objeto.classificacaoMercadoria;
	}

	public ClassificacaoMercadoria getClassificacaoMercadoria(){
		if(this.classificacaoMercadoria == null) {
			this.classificacaoMercadoria = new ClassificacaoMercadoria();
		}
		return this.classificacaoMercadoria;
	}
	
	public TipoProduto getTipoProduto(){
		if(this.tipoProduto == null) {
			this.tipoProduto = new TipoProduto();
		}
		return this.tipoProduto;
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
		Mercadoria other = (Mercadoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}