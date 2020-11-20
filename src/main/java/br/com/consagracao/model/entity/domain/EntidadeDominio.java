package br.com.consagracao.model.entity.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@DynamicUpdate
public class EntidadeDominio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@Column(updatable = false)
	private Long verificationCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@LastModifiedDate
	private Date dtUltimaAlteracao;

	public EntidadeDominio() {
		if (dtCriacao == null) {
			dtCriacao = new Date(System.currentTimeMillis());
		}
		if (verificationCode == null) {
			verificationCode = new Random().nextLong();
		}
	}

	public EntidadeDominio(Long id) {
		this.id = id;
		this.verificationCode = new Random().nextLong();
		if (dtCriacao == null) {
			this.dtCriacao = new Date(System.currentTimeMillis());
		}
	}

	public EntidadeDominio(Long id, Long verificationCode, Date dtCriacao, Date dtUltimaAlteracao) {
		this.id = id;
		this.verificationCode = verificationCode;
		this.dtCriacao = dtCriacao;
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVerificationCode() {
		return verificationCode;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((verificationCode == null) ? 0 : verificationCode.hashCode());
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
		EntidadeDominio other = (EntidadeDominio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (verificationCode == null) {
			if (other.verificationCode != null)
				return false;
		} else if (!verificationCode.equals(other.verificationCode))
			return false;
		return true;
	}

}
