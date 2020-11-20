package br.com.consagracao.controller.helper;

import br.com.consagracao.model.entity.domain.Consagrado;
import br.com.consagracao.model.services.dto.ConsagradoDTO;
import br.com.consagracao.model.services.utility.DateUtilities;

public class ConsagradoVHWEB {

	public Consagrado create(ConsagradoDTO objDTO) {

		Consagrado theConsagrado = new Consagrado(null, objDTO.getCargoAtual(), objDTO.getCargoConsagracao(),
				DateUtilities.dateFormat(objDTO.getDataConsagracao()),
				DateUtilities.dateFormat(objDTO.getDataOrdenacao()), objDTO.getEmail(), objDTO.getEstadoCivil(),
				objDTO.getNacionalidade(), objDTO.getNome(), objDTO.getPastorPresidente(), objDTO.getRg(),
				objDTO.getTelefone());
		return theConsagrado;
	}

	public void update(Consagrado theConsagrado, ConsagradoDTO objDTO) {

		theConsagrado.setCargoAtual(objDTO.getCargoAtual());
		theConsagrado.setCargoConsagracao(objDTO.getCargoConsagracao());
		theConsagrado.setDataConsagracao(DateUtilities.dateFormat(objDTO.getDataConsagracao()));
		theConsagrado.setDataOrdenacao(DateUtilities.dateFormat(objDTO.getDataOrdenacao()));
		theConsagrado.setEmail(objDTO.getEmail());
		theConsagrado.setEstadoCivil(objDTO.getEstadoCivil());
		theConsagrado.setNacionalidade(objDTO.getNacionalidade());
		theConsagrado.setNome(objDTO.getNome());
		theConsagrado.setPastorPresidente(objDTO.getPastorPresidente());
		theConsagrado.setRg(objDTO.getRg());
		theConsagrado.setTelefone(objDTO.getTelefone());
	}
}
