package br.com.consagracao.model.services.utility;

import java.text.ParseException;

import org.springframework.stereotype.Component;

import br.com.consagracao.model.services.exceptions.DataIntegrityException;

@Component
public class TextFormatUtilities {

	/**Character   Description
	 *  #           Qualquer numero valido.
	 *
	 *  '           Usado para não usar nenhum caracter especial na formatacao ("n", "t"....)
	 *
	 *  U           Qualquer caracter
	 *  Todas as letras minusculas sao passadas para maiuscula.
	 *
	 *  L           Qualquer caracter
	 * Todas as letras maiusculas sao passadas para minusculas
	 *
	 *  A          Qualquer caracter ou numero
	 *  ( Character.isLetter or Character.isDigit )
	 *
	 *  ?           Qualquer caracter ( Character.isLetter ).
	 *
	 *  *           Qualquer Coisa.
	 *
	 *  H           Qualquer caracter hexa (0-9, a-f ou A-F).
	 *
	 * ====================================
	 * ex:
	 * value = "A1234B567Z"
	 * mask = "A-AAAA-AAAA-A"
	 * output : A-1234-B567-Z
	 *
	 * ===================================
	 * @param string
	 * @param mask
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String formatString(String string, String mask)
	        throws java.text.ParseException {
	    javax.swing.text.MaskFormatter mf =
	            new javax.swing.text.MaskFormatter(mask);
	    mf.setValueContainsLiteralCharacters(false);
	    return mf.valueToString(string);
	}
	
	public static String cnpjFormat(String cnpj) {

		if(cnpj.length() > 14) {
			return cnpj;
		}
		try {
			cnpj = cnpj.replace(".", "");
			cnpj = cnpj.replace("-", "");
			cnpj = cnpj.replace("/", "");
			return formatString(cnpj, "##.###.###/####-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new DataIntegrityException("Erro no CNPJ informado!");
	}

	public static String cpfFormat(String cpf) {

		if(cpf.length() > 11) {
			return cpf;
		}
		
		try {
			cpf = cpf.replace(".", "");
			cpf = cpf.replace("-", "");
			return formatString(cpf, "###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new DataIntegrityException("Erro no CPF informado!");
	}

	// CREF 000000-G/XX
	// CREF 000000-P/XX
	// XX = Estado de Registro ex.: SP, SC ...
	// P = Provisionados
	// G = Graduado
	public static String crefFormat(String cref) {

		if(cref.length() > 9) {
			return cref;
		}
		try {
			cref = cref.replace(".", "");
			cref = cref.replace("-", "");
			cref = cref.replace("/", "");
			return "CREF " + formatString(cref, "######-U/UU");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new DataIntegrityException("Erro no CREF informado!");
	}
}
