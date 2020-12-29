package SpringBootExpertVendas.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String nome;
	
	@OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
	private Set<Pedido>pedidos;
	

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public Set<Pedido> getPedidos() {
		return pedidos;
	}


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + "]";
	}
	
	

}
