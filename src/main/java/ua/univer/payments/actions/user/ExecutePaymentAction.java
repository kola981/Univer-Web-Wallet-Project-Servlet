package ua.univer.payments.actions.user;

import java.rmi.RemoteException;
import java.util.Map;

import ua.univer.payments.converters.RequestToDtoConverter;
import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.AccountDTO;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.dto.DetailsDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class ExecutePaymentAction extends AbstractUserAction {
	private static final String AMOUNT = "amount";
	private static final String REFERENCE = "ref";
	
	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
		
		AccountDTO account = client.getAccount();
		int accountNumber = account.getAccountNumber();
		
		final String ref = getParameter(parameters, REFERENCE);
		double amount = Double.parseDouble(getParameter(parameters, AMOUNT));
		DetailsDTO details = RequestToDtoConverter.getPayeeDetailsFromRequest(parameters);				
		
		final String reference = createReference(accountNumber, ref);
		
		try {
			clientDtoInterface.transferMoney(details, amount, reference);
		} 
		catch (RemoteException e) { 
			throw new FailedRmiTransactionException();
		}
	}	
	
	private String createReference(int accountNumber, String reference) {
		return "Ref: "+ accountNumber + " payment " + reference;
	}

}
