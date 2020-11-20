package br.com.consagracao.model.services.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.consagracao.model.services.exceptions.DataIntegrityException;

@Component
public class DateUtilities {

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat mesFormatter = new SimpleDateFormat("MMMM/yyyy");

	public static String dateFormat(Date date) {
		return formatter.format(date);
	}

	public static Date dateFormat(String sDate) {
		try {
			return formatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new DataIntegrityException("Data informada é invalida!");
	}
	
	public static String mesFormat(Date date) {
		return mesFormatter.format(date);
	}

	public static Date MesFormat(String sDate) {
		try {
			return mesFormatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new DataIntegrityException("Data informada é invalida!");
	}
}
