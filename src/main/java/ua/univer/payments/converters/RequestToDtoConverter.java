package ua.univer.payments.converters;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ua.univer.rmi.dto.BankDetailsDTO;
import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.dto.CardDetailsDTO;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.dto.DetailsDTO;
import ua.univer.rmi.dto.UserDTO;

public class RequestToDtoConverter {
	private static final String FIRST_NAME = "fname";
	private static final String LAST_NAME = "lname";
	private static final String USERNAME = "uname";
	private static final String EMAIL = "email";
	private static final String PASSW = "passw";	
	private static final String CARD_NUMBER = "cnum";
	private static final String MONTH = "tillM";
	private static final String YEAR = "tillY";
	private static final String ACCOUNT_NUMBER = "acnum";
	private static final String BANK_NUMBER = "bnum";
	private static final String COUNTERPARTY = "cp";	
	
	private RequestToDtoConverter() {
		super();
	}

	public static UserDTO parseUserDTO(Map<String, String[]> parameters) {
		UserDTO userData = new UserDTO();
		userData.setName(getParameter(parameters, FIRST_NAME));
		userData.setSurname(getParameter(parameters, LAST_NAME));
		userData.setUsername(getParameter(parameters, USERNAME));
		userData.setEmail(getParameter(parameters, EMAIL));
		userData.setPassword(getParameter(parameters, PASSW));
		return userData;
	}
	
	public static CardDTO parseCardDto(Map<String, String[]> parameters) {
		CardDTO card = new CardDTO();
		card.setCardNumber(Long.valueOf(getParameter(parameters, CARD_NUMBER)));
		card.setValidTillMonth(getMonthAsInteger(getParameter(parameters, MONTH)));
		card.setValidTillYear(Integer.valueOf(getParameter(parameters, YEAR)));
		card.setBlockedStatus(false);
		return card;
	}

	private static String getParameter(Map<String, String[]> parameters, String key) {
		String[] arr = parameters.get(key);
		if (arr != null) {
			List<String> temp = Arrays.asList(parameters.get(key));
			return temp.get(0);
		}
		else return null;
	}
	
	private static int getMonthAsInteger(String str) {
		switch (str) {
			case "Jan": return 1;
			case "Feb": return 2;
			case "Mar": return 3;
			case "Apr": return 4;
			case "May": return 5;
			case "Jun": return 6;
			case "Jul": return 7;
			case "Aug": return 8;
			case "Sep": return 9;
			case "Oct": return 10;
			case "Nov": return 11;
			case "Dec": return 12;
			default: return -1;
		}
	}
	
	public static DetailsDTO getPayerDetailsFromRequest(Map<String, String[]> parameters, ClientDTO client) {
		if (getParameter(parameters, ACCOUNT_NUMBER) != null) {
			return getBankDetailsFromRequest(parameters);
		}				
		else {
			CardDetailsDTO cardDetails = getCardDetailsFromRequest(parameters);
			return addCardOwnerInformation(cardDetails, client);
		}			
	}
	
	public static DetailsDTO getPayeeDetailsFromRequest(Map<String, String[]> parameters) {
		if (getParameter(parameters, ACCOUNT_NUMBER) != null) {
			return getBankDetailsFromRequest(parameters);
		}				
		else {
			return getCardDetailsFromRequest(parameters);
		}			
	}
	
	private static BankDetailsDTO getBankDetailsFromRequest(Map<String, String[]> parameters) {
		BankDetailsDTO bankDetails = new BankDetailsDTO();
		bankDetails.setAccountNumber(getParameter(parameters, ACCOUNT_NUMBER));
		bankDetails.setBankNumber(getParameter(parameters, BANK_NUMBER));
		bankDetails.setCounterpartyName(getParameter(parameters, COUNTERPARTY));
		return bankDetails;
	}
	
	private static CardDetailsDTO getCardDetailsFromRequest(Map<String, String[]> parameters) {
		CardDetailsDTO cardDetails = new CardDetailsDTO();
		
		cardDetails.setCardNumber(getParameter(parameters, CARD_NUMBER));		
		int month = getMonthAsInteger(getParameter(parameters, MONTH));
		if (month != -1)
			cardDetails.setValidTillMonth(month);
		else
			cardDetails.setValidTillMonth(Integer.valueOf(getParameter(parameters, MONTH)));		
		cardDetails.setValidTillYear(Integer.valueOf(getParameter(parameters, YEAR)));
		
		return cardDetails;
	}
	
	private static CardDetailsDTO addCardOwnerInformation(CardDetailsDTO cardDetails,  ClientDTO client) {
		cardDetails.setName(client.getName());
		cardDetails.setSurname(client.getSurname());
		return cardDetails;
	}
	
	
}
