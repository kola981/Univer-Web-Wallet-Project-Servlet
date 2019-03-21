package ua.univer.payments.actions.admin;

import java.rmi.RemoteException;
import java.util.Map;

import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.rmi.dto.AccountDTO;
import ua.univer.rmi.dto.AdminDTO;
import ua.univer.rmi.stubs.AdminDTOInterface;

public class AccountBlockAction extends AbstractAdminAction {

	private static final String ACCOUNT_NUMBER = "acnum";
	
	@Override
	protected void doAdminAction(AdminDTO admin, Map<String, String[]> parameters,
			AdminDTOInterface adminDtoInterface) {
			
		String accNum = getParameter(parameters, ACCOUNT_NUMBER);
				
		AccountDTO acc = new AccountDTO();
		acc.setAccountNumber(Integer.valueOf(accNum));
		acc.setBlockedStatus(true);		

		try {
			adminDtoInterface.changeAccountStatus(acc);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}
	}	
	
}
