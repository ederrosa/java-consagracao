package br.com.consagracao.model.entity.domain;

import java.net.URI;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Consagrado extends EntidadeDominio {

	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "VARCHAR(25)")
	private String cargoAtual;
	@Column(columnDefinition = "VARCHAR(25)")
	private String cargoConsagracao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConsagracao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataOrdenacao;
	@Column(unique = true, columnDefinition = "VARCHAR(100)", nullable = false)
	@javax.validation.constraints.Email(message = "ERRO - EMail informado é invalido!")
	private String email;
	@Column(columnDefinition = "VARCHAR(20)")
	private String estadoCivil;
	@Column(columnDefinition = "VARCHAR(25)")
	private String nacionalidade;
	@Column(columnDefinition = "VARCHAR(100)")
	private String nome;
	private String nomeImgPerfil;
	private String pastorPresidente;
	@Column(unique = true, columnDefinition = "VARCHAR(15)")
	private String rg;
	@Column(columnDefinition = "VARCHAR(15)")
	private String telefone;
	@Lob
	@Column(columnDefinition = "BLOB")
	private URI uriImgPerfil;

	public Consagrado() {

	}

	public Consagrado(Long id, String cargoAtual, String cargoConsagracao, Date dataConsagracao, Date dataOrdenacao,
			String email, String estadoCivil, String nacionalidade, String nome, String pastorPresidente, String rg,
			String telefone) {
		super(id);
		this.cargoAtual = cargoAtual;
		this.cargoConsagracao = cargoConsagracao;
		this.dataConsagracao = dataConsagracao;
		this.dataOrdenacao = dataOrdenacao;
		this.email = email;
		this.estadoCivil = estadoCivil;
		this.nacionalidade = nacionalidade;
		this.nome = nome;
		this.pastorPresidente = pastorPresidente;
		this.rg = rg;
		this.telefone = telefone;
	}

	public String getCargoAtual() {
		return cargoAtual;
	}

	public void setCargoAtual(String cargoAtual) {
		this.cargoAtual = cargoAtual;
	}

	public String getCargoConsagracao() {
		return cargoConsagracao;
	}

	public void setCargoConsagracao(String cargoConsagracao) {
		this.cargoConsagracao = cargoConsagracao;
	}

	public Date getDataConsagracao() {
		return dataConsagracao;
	}

	public void setDataConsagracao(Date dataConsagracao) {
		this.dataConsagracao = dataConsagracao;
	}

	public Date getDataOrdenacao() {
		return dataOrdenacao;
	}

	public void setDataOrdenacao(Date dataOrdenacao) {
		this.dataOrdenacao = dataOrdenacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeImgPerfil() {
		return nomeImgPerfil;
	}

	public void setNomeImgPerfil(String nomeImgPerfil) {
		this.nomeImgPerfil = nomeImgPerfil;
	}

	public String getPastorPresidente() {
		return pastorPresidente;
	}

	public void setPastorPresidente(String pastorPresidente) {
		this.pastorPresidente = pastorPresidente;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public URI getUriImgPerfil() {
		return uriImgPerfil;
	}

	public void setUriImgPerfil(URI uriImgPerfil) {
		this.uriImgPerfil = uriImgPerfil;
	}
}
