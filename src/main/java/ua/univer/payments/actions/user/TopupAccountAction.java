package ua.univer.payments.actions.user;

import java.rmi.RemoteException;
import java.util.Map;

import ua.univer.payments.converters.RequestToDtoConverter;
import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.dto.DetailsDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class TopupAccountAction extends AbstractUserAction {
	private static final String AMOUNT = "amount";
	private static final String CARD_NUMBER = "cnum";
	
	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
		
		DetailsDTO cardDetails = RequestToDtoConverter.getPayerDetailsFromRequest(parameters, client);
		
		double amount = Double.parseDouble(getParameter(parameters, AMOUNT));
		long cardNumber = Long.parseLong(getParameter(parameters, CARD_NUMBER));
		int accountNumber = client.getAccount().getAccountNumber();

		final String reference = createReference(accountNumber, cardNumber);

		try {
			clientDtoInterface.addMoneyToAccount(cardDetails, amount, reference);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}
	}	
		
	private String createReference(int accountNumber, long cardNumber) {
		return "Ref: " + accountNumber + " recharge using the card " + cardNumber;
	}

}
