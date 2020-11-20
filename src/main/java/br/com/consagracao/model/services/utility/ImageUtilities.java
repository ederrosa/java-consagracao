package br.com.consagracao.model.services.utility;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.consagracao.model.services.exceptions.FileException;

@Component
public class ImageUtilities {

	@Value("${image.size}")
	private Long imgSize;

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {

		String theExtension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(theExtension) && !"jpg".equals(theExtension)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}

		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(theExtension)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {

		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String theExtensionension) {

		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, theExtensionension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public InputStream getInputStream(MultipartFile uploadedFile) {

		String theExtension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"jpg".equals(theExtension) && !"png".equals(theExtension)) {
			throw new FileException("Somente vídeos no formato: JPG, e PNG são permitidos");
		}
		if (uploadedFile.getSize() > this.imgSize) {
			throw new FileException("A Imagem ultrapassa o limite do sistema de: " + this.imgSize + " bytes ("
					+ (this.imgSize / (1024 * 1024)) + "MB)!");
		}
		try {
			return new BufferedInputStream(uploadedFile.getInputStream());
		} catch (IOException e) {
			throw new FileException("Erro ao ler o imagem");
		}
	}

	public BufferedImage cropSquare(BufferedImage sourceImg) {

		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(sourceImg, (sourceImg.getWidth() / 2) - (min / 2), (sourceImg.getHeight() / 2) - (min / 2),
				min, min);
	}

	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}

}
