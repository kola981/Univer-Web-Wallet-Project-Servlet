package ua.univer.payments.actions.admin;

import java.rmi.RemoteException;
import java.util.Map;

import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.AdminDTO;
import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.stubs.AdminDTOInterface;

public class CardUnblockAction extends AbstractAdminAction {

	@Override
	protected void doAdminAction(AdminDTO admin, Map<String, String[]> parameters,
			AdminDTOInterface adminDtoInterface) {
		final String cardNum = getParameter(parameters, "cnum");
		
		CardDTO card = new CardDTO();
		card.setCardNumber(Long.valueOf(cardNum));
		card.setBlockedStatus(false);
		
		try {
			adminDtoInterface.unblockCard(card);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}		
	}	
	
}
