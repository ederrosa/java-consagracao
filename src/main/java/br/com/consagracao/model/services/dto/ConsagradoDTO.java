package br.com.consagracao.model.services.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.consagracao.model.entity.domain.Consagrado;
import br.com.consagracao.model.entity.domain.EntidadeDominio;

public class ConsagradoDTO extends EntidadeDominio {

	private static final long serialVersionUID = 1L;

	private String cargoAtual;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cargoConsagracao;
	private String dataConsagracao;
	private String dataOrdenacao;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-Mail informado é invalido!")
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String estadoCivil;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nacionalidade;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;
	private String nomeImgPerfil;
	private String pastorPresidente;
	@Column(unique = true, columnDefinition = "VARCHAR(15)")
	private String rg;
	@Column(unique = true, columnDefinition = "VARCHAR(15)")
	private String telefone;
	private String uriImgPerfil;

	public ConsagradoDTO() {

	}

	public ConsagradoDTO(Consagrado theConsagrado) {

		this.cargoAtual = theConsagrado.getCargoAtual();
		this.cargoConsagracao = theConsagrado.getCargoConsagracao();
		this.dataConsagracao = theConsagrado.getDataConsagracao().toString();
		this.dataOrdenacao = theConsagrado.getDataOrdenacao().toString();
		this.email = theConsagrado.getEmail();
		this.estadoCivil = theConsagrado.getEstadoCivil();
		this.nacionalidade = theConsagrado.getNacionalidade();
		this.nome = theConsagrado.getNome();
		this.pastorPresidente = theConsagrado.getPastorPresidente();
		this.rg = theConsagrado.getRg();
		this.telefone = theConsagrado.getTelefone();
		this.uriImgPerfil = theConsagrado.getUriImgPerfil().toString();
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

	public String getDataConsagracao() {
		return dataConsagracao;
	}

	public void setDataConsagracao(String dataConsagracao) {
		this.dataConsagracao = dataConsagracao;
	}

	public String getDataOrdenacao() {
		return dataOrdenacao;
	}

	public void setDataOrdenacao(String dataOrdenacao) {
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

	public String getUriImgPerfil() {
		return uriImgPerfil;
	}

	public void setUriImgPerfil(String uriImgPerfil) {
		this.uriImgPerfil = uriImgPerfil;
	}
}
