package ua.univer.payments.actions.admin;

import java.util.Map;

import ua.univer.rmi.dto.AdminDTO;
import ua.univer.rmi.stubs.AdminDTOInterface;

public class GetHomepageAction extends AbstractAdminAction {

	@Override
	protected void doAdminAction(AdminDTO admin, Map<String, String[]> parameters,
			AdminDTOInterface adminDtoInterface) {
		//NOOP - already implemented in superclass		
	}	
	
}
