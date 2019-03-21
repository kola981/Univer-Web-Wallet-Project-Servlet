package ua.univer.payments.converters;

import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.dto.CardDetailsDTO;
import ua.univer.rmi.dto.DetailsDTO;

public class DtoToDtoConverter {

	private DtoToDtoConverter() {
		super();
	}
	
	public static DetailsDTO convert(CardDTO card) {
		CardDetailsDTO cdd = new CardDetailsDTO();
		cdd.setCardNumber(String.valueOf(card.getCardNumber()));
		cdd.setValidTillMonth(card.getValidTillMonth());
		cdd.setValidTillYear(card.getValidTillYear());
		return cdd;
	}	
}
