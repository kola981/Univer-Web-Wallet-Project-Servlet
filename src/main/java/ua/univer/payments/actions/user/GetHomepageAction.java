package ua.univer.payments.actions.user;

import java.util.Map;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class GetHomepageAction extends AbstractUserAction {

	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
		//NOOP - already implemented in superclass
	}

}
