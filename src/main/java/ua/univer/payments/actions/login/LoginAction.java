package ua.univer.payments.actions.login;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.univer.payments.actions.TemplateAction;
import ua.univer.payments.config.Configuration;
import ua.univer.payments.controllers.Dispatcher;
import ua.univer.payments.exceptions.FailedRmiTransactionException;
import ua.univer.payments.exceptions.RmiServiceNotFoundException;
import ua.univer.rmi.dto.Role;
import ua.univer.rmi.stubs.RmiInterface;
import ua.univer.rmi.stubs.RoleDTOInterface;

public class LoginAction extends TemplateAction {

	private static Configuration config = Configuration.getInstance();

	private static final String USERNAME = "username";
	private static final String PASSW = "password";
	private static final String ROLE = "role";
	
	private static final String SERVICE_URI = "/account";
	private static final String ADMIN_URI = "/admin";
	private static final String INDEX_URI = "/";
	
	private static final String SERVICE = config.getProperty("AUTH_SERVICE");
	private static final String ADDRESS = config.getProperty("ADDRESS");
	private static final String PORT = config.getProperty("PORT");
	private static final String URL = "//" + ADDRESS + ":" + PORT + "/" + SERVICE;

	private final Dispatcher dispatcher = Dispatcher.getInstance();
	
	public LoginAction() {
		super();
	}

	@Override
	protected RmiInterface getRmiInterface() {
		RoleDTOInterface roleDTOInterface = null;
		try {
			roleDTOInterface = (RoleDTOInterface) Naming.lookup(URL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new RmiServiceNotFoundException();
		}
		return roleDTOInterface;
	}

	@Override
	protected void doAction(HttpSession session, Map<String, String[]> parameters, RmiInterface rmi) {		
		Role role = null;
		
		final String login = getParameter(parameters, USERNAME);
		final String passw = getParameter(parameters, PASSW);
		RoleDTOInterface roleDTOInterface = (RoleDTOInterface) rmi;
 
		try {
			role = roleDTOInterface.getUserRole(login, passw);
			session.setAttribute(USERNAME, login);
			session.setAttribute(ROLE, role);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}		
	}

	@Override
	protected void forwardToPage(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Role role = (Role) session.getAttribute(ROLE);
		
		if (role.equals(Role.USER))
			dispatcher.redirectToPage(req, res, SERVICE_URI);
		else if (role.equals(Role.ADMIN))
			dispatcher.redirectToPage(req, res, ADMIN_URI);
		else
			dispatcher.redirectToPage(req, res, INDEX_URI);
	}
}