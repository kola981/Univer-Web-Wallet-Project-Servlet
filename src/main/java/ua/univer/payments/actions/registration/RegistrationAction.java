package ua.univer.payments.actions.registration;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.univer.payments.actions.TemplateAction;
import ua.univer.payments.config.Configuration;
import ua.univer.payments.controllers.Dispatcher;
import ua.univer.payments.converters.RequestToDtoConverter;
import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.payments.exceptions.RmiServiceNotFoundException;
import ua.univer.rmi.dto.Role;
import ua.univer.rmi.dto.UserDTO;
import ua.univer.rmi.exceptions.UsernameAlreadyExistsException;
import ua.univer.rmi.stubs.NewUserDTOInterface;
import ua.univer.rmi.stubs.RmiInterface;

public class RegistrationAction extends TemplateAction {

	private static Configuration config = Configuration.getInstance();

	private static final String USERNAME_IN_REQUEST = "uname";
	private static final String USERNAME = "username";
	private static final String ROLE = "role";
	private static final String REGISTRATION_WITH_ERRORS = "/jsp/dynamic/register-invalid.jsp";
	private static final String SERVICE_URI = "/account";
	
	private static final String SERVICE = config.getProperty("REGISTRATION_SERVICE");
	private static final String ADDRESS = config.getProperty("ADDRESS");
	private static final String PORT = config.getProperty("PORT");
	private static final String URL = "//" + ADDRESS + ":" + PORT + "/" + SERVICE;

	private final Dispatcher dispatcher = Dispatcher.getInstance();
	
	private boolean usernameExists = false;

	

	public RegistrationAction() {
		super();
	}

	@Override
	protected RmiInterface getRmiInterface() {
		NewUserDTOInterface newUserDtoInterface = null;
		try {
			newUserDtoInterface = (NewUserDTOInterface) Naming.lookup(URL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new RmiServiceNotFoundException();
		}
		return newUserDtoInterface;
	}

	@Override
	protected void doAction(HttpSession session, Map<String, String[]> parameters, RmiInterface rmi) {
		UserDTO userData = RequestToDtoConverter.parseUserDTO(parameters);
		NewUserDTOInterface newUserDtoInterface = (NewUserDTOInterface) rmi;

		try {
			newUserDtoInterface.registerNewUser(userData);
			session.setAttribute(USERNAME, userData.getUsername());
			session.setAttribute(ROLE, Role.USER);
		} catch (UsernameAlreadyExistsException e) {
			usernameExists = true;			
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		} 
	}

	@Override
	protected void forwardToPage(HttpServletRequest req, HttpServletResponse res) {
		if (usernameExists) {
			Map<String, String[]> parameters = new HashMap<>(req.getParameterMap());
			parameters.remove(USERNAME_IN_REQUEST);
			req.setAttribute("userData", parameters);
			dispatcher.forwardToPage(req, res, REGISTRATION_WITH_ERRORS);
		} else {
			dispatcher.redirectToPage(req, res, SERVICE_URI);
		}
	}
}