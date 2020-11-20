package br.com.consagracao.controller.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.consagracao.controller.helper.ConsagradoVHWEB;
import br.com.consagracao.model.entity.domain.Consagrado;
import br.com.consagracao.model.services.ConsagradoServices;
import br.com.consagracao.model.services.dto.ConsagradoDTO;

@RestController
@RequestMapping(value = "/consagrados")
public class ConsagradoRC {

	@Autowired
	private ConsagradoServices theConsagradoServices;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid ConsagradoDTO objNewDTO,
			@RequestPart(name = "file", required = true) MultipartFile theMultipartFile) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(
						theConsagradoServices.insert(new ConsagradoVHWEB().create(objNewDTO), theMultipartFile).getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ConsagradoDTO> find(@PathVariable Long id) {
		ConsagradoDTO obj = new ConsagradoDTO(theConsagradoServices.find(id));
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ConsagradoDTO>> findAll() {
		List<ConsagradoDTO> list = theConsagradoServices.findAll().stream().map(obj -> new ConsagradoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid ConsagradoDTO objDTO, @PathVariable Long id,
			@RequestPart(name = "file", required = false) MultipartFile theMultipartFile) {
		objDTO.setId(id);
		Consagrado theConsagrado = theConsagradoServices.find(id);
		new ConsagradoVHWEB().update(theConsagrado, objDTO);
		theConsagrado = theConsagradoServices.update(theConsagrado, theMultipartFile);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		find(id);
		theConsagradoServices.delete(id);
		return ResponseEntity.noContent().build();
	}
}
