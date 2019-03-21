package ua.univer.payments.actions.user;

import java.rmi.RemoteException;
import java.util.Map;

import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class ForgetCardAction extends AbstractUserAction {

	private static final String CARD_NUMBER = "cnum";
	
	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
		final String cardNum = getParameter(parameters, CARD_NUMBER);
		
		CardDTO card = new CardDTO();
		card.setCardNumber(Long.valueOf(cardNum));

		try {
			clientDtoInterface.forgetCard(card);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}
	}

}
