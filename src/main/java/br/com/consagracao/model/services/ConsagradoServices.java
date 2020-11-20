package br.com.consagracao.model.services;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.consagracao.model.entity.domain.Consagrado;
import br.com.consagracao.model.repository.IConsagradoRepository;
import br.com.consagracao.model.services.exceptions.DataIntegrityException;
import br.com.consagracao.model.services.exceptions.ObjectNotFoundException;
import br.com.consagracao.model.services.utility.ImageUtilities;

@Service
public class ConsagradoServices {

	@Autowired
	private IConsagradoRepository theIConsagradoRepository;

	@Autowired
	private S3Service theS3Service;

	@Autowired
	private ImageUtilities theImageUtilities;

	@Value("${prefix.perfil.imagem}")
	private String prefix;
	
	@Value("${image.profile.size}")
	private Integer size;

	public void delete(Long id) {
		try {
			if (find(id).getNomeImgPerfil() != null) {
				this.theS3Service.deleteFile(find(id).getNomeImgPerfil());
			}
			this.theIConsagradoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há Entidades relacionadas");
		}
	}

	public Consagrado find(Long id) {
		Optional<Consagrado> theEntidade = this.theIConsagradoRepository.findById(id);
		return theEntidade.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Consagrado.class.getSimpleName()));
	}

	public List<Consagrado> findAll() {
		return this.theIConsagradoRepository.findAll();
	}

	@Transactional
	public Consagrado insert(Consagrado theEntidade, MultipartFile theMultipartFile) {
		theEntidade.setId(null);
		BufferedImage jpgImage = theImageUtilities.getJpgImageFromFile(theMultipartFile);
		jpgImage = theImageUtilities.cropSquare(jpgImage);
		jpgImage = theImageUtilities.resize(jpgImage, size);
		theEntidade.setNomeImgPerfil(this.prefix + theEntidade.getNome() + "."
				+ FilenameUtils.getExtension(theMultipartFile.getOriginalFilename()));
		theEntidade.setUriImgPerfil(this.theS3Service.uploadFile(
						this.theImageUtilities.getInputStream(jpgImage,
								FilenameUtils.getExtension(theMultipartFile.getOriginalFilename())),
						theEntidade.getNomeImgPerfil(), "image"));
		theEntidade = this.theIConsagradoRepository.save(theEntidade);
		return theEntidade;
	}

	public Consagrado update(Consagrado theEntidade, MultipartFile theMultipartFile) {
		BufferedImage jpgImage = this.theImageUtilities.getJpgImageFromFile(theMultipartFile);
		theEntidade.setNomeImgPerfil(this.prefix + theEntidade.getNome() + "."
				+ FilenameUtils.getExtension(theMultipartFile.getOriginalFilename()));
		theEntidade.setUriImgPerfil(this.theS3Service.uploadFile(
						this.theImageUtilities.getInputStream(jpgImage,
								FilenameUtils.getExtension(theMultipartFile.getOriginalFilename())),
						theEntidade.getNomeImgPerfil(), "image"));
		return this.theIConsagradoRepository.save(theEntidade);
	}
}
