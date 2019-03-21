package ua.univer.payments.actions.user;

import java.rmi.RemoteException;
import java.util.Map;

import ua.univer.payments.converters.RequestToDtoConverter;
import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class AddNewCardAction extends AbstractUserAction {
	
	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
				
		String username = client.getUsername();		
		CardDTO card = RequestToDtoConverter.parseCardDto(parameters);
				
		try {
			clientDtoInterface.addNewCard(card, username);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}
	}
}


