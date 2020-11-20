package br.com.consagracao.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.consagracao.model.entity.domain.Consagrado;

public interface IConsagradoRepository extends JpaRepository<Consagrado, Long>{

}
