package ua.univer.payments.actions.user;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import ua.univer.payments.converters.DtoToDtoConverter;
import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.AccountDTO;
import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.dto.DetailsDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class ExecuteTransferAction extends AbstractUserAction {	
	private static final String AMOUNT = "amount";
	private static final String REFERENCE = "ref";
	private static final String FROM_CARD = "fromCard";
	private static final String TO_CARD = "toCard";
	
	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
		
		final List<CardDTO> cards = client.getCards();
		final AccountDTO account = client.getAccount();
		
		final Double amount = Double.valueOf(getParameter(parameters, AMOUNT));
		final String ref = getParameter(parameters, REFERENCE);
		final Long payerCardNumber = Long.parseLong(getParameter(parameters, FROM_CARD));
		final Long payeeCardNumber = Long.parseLong(getParameter(parameters, TO_CARD));
		
		final CardDTO fCard = findCard(cards, payerCardNumber);
		final CardDTO tCard = findCard(cards, payeeCardNumber);		
		
		DetailsDTO fromCard = DtoToDtoConverter.convert(fCard);
		fromCard.setName(client.getName());
		fromCard.setSurname(client.getSurname());
		DetailsDTO toCard = DtoToDtoConverter.convert(tCard);				
		
		String reference = createReference(account.getAccountNumber(), payerCardNumber,
										   payeeCardNumber, amount, ref);		
		try {		
			clientDtoInterface.transferBetweenCards(fromCard, toCard, amount, reference);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}
	}	
	
	private String createReference(int accountNumber, long fromCard, long toCard, double amount, String ref) {
		return "Ref: " + accountNumber + " transfer from " + fromCard + " to "
				+ toCard + " " + amount + "UAH " + ref;
	}
	
	private CardDTO findCard(List<CardDTO> cards, Long cardNumber) {
		return cards.stream()
				 .filter(c -> cardNumber.equals(c.getCardNumber()))
				 .findFirst()
				 .get();	
	}

}
