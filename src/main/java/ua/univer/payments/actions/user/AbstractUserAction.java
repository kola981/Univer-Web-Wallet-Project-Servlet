package ua.univer.payments.actions.user;

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
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;
import ua.univer.rmi.stubs.RmiInterface;

public abstract class AbstractUserAction extends TemplateAction {
	
	private static Configuration config = Configuration.getInstance();
	
	private static final String USERNAME = "username";
	private static final String CLIENT = "client";
	private static final String HOMEPAGE = "/jsp/dynamic/user.jsp";
	
	private static final String SERVICE = config.getProperty("CLIENT_SERVICE");
	private static final String ADDRESS = config.getProperty("ADDRESS");
	private static final String PORT = config.getProperty("PORT");
	private static final String URL = "//" + ADDRESS + ":" + PORT + "/" + SERVICE;

	private final Dispatcher dispatcher = Dispatcher.getInstance();

	public AbstractUserAction() {
		super();
	}

	@Override
	protected RmiInterface getRmiInterface() {
		ClientDTOInterface clientDtoInterface = null;
		try {
			clientDtoInterface = (ClientDTOInterface) Naming.lookup(URL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new RmiServiceNotFoundException();
		}
		return clientDtoInterface;
	}
	
	@Override
	protected void doAction(HttpSession session, Map<String, String[]> parameters, RmiInterface rmi) {
		ClientDTO client = (ClientDTO) session.getAttribute(CLIENT);
		String username = getUsername(client, session);
		ClientDTOInterface clientDtoInterface = (ClientDTOInterface) rmi;
		doUserAction(client, parameters, clientDtoInterface);
		client = getUserData(clientDtoInterface, username);
		session.setAttribute(CLIENT, client);
	}
		
	private String getUsername(ClientDTO clientDto, HttpSession session) {
		return (clientDto != null)? clientDto.getUsername():((String) session.getAttribute(USERNAME));
	}
	
	protected abstract void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface);

	private ClientDTO getUserData(ClientDTOInterface clientDto, String username) {
		ClientDTO client = null;
		try {
			client = clientDto.getUserData(username);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException();
		}
		return client;
	}
	
	@Override
	protected void forwardToPage(HttpServletRequest req, HttpServletResponse res) {
		dispatcher.forwardToPage(req, res, HOMEPAGE);		
	}	
}