package ua.univer.payments.actions.admin;

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
import ua.univer.rmi.dto.AdminDTO;
import ua.univer.rmi.stubs.AdminDTOInterface;
import ua.univer.rmi.stubs.RmiInterface;

public abstract class AbstractAdminAction extends TemplateAction {
	
	private static Configuration config = Configuration.getInstance();
	
	private static final String USERNAME = "username";
	private static final String ADMIN = "admin";
	private static final String HOMEPAGE = "/jsp/dynamic/admin.jsp";
	
	private static final String SERVICE = config.getProperty("ADMIN_SERVICE");
	private static final String ADDRESS = config.getProperty("ADDRESS");
	private static final String PORT = config.getProperty("PORT");
	private static final String URL = "//" + ADDRESS + ":" + PORT + "/" + SERVICE;

	private final Dispatcher dispatcher = Dispatcher.getInstance();

	public AbstractAdminAction() {
		super();
	}

	@Override
	protected RmiInterface getRmiInterface() {
		AdminDTOInterface adminDtoInterface = null;
		try {
			adminDtoInterface = (AdminDTOInterface) Naming.lookup(URL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new RmiServiceNotFoundException();
		}
		return adminDtoInterface;
	}
	
	@Override
	protected void doAction(HttpSession session, Map<String, String[]> parameters, RmiInterface rmi) {
		AdminDTO admin = (AdminDTO) session.getAttribute(ADMIN);
		String username = getUsername(admin, session);
		AdminDTOInterface adminDtoInterface = (AdminDTOInterface) rmi;
		doAdminAction(admin, parameters, adminDtoInterface);
		admin = getAdminData(adminDtoInterface, username);
		session.setAttribute(ADMIN, admin);
	}
		
	private String getUsername(AdminDTO adminDto, HttpSession session) {
		return (adminDto != null)? adminDto.getUsername():((String) session.getAttribute(USERNAME));
	}
	
	protected abstract void doAdminAction(AdminDTO admin, Map<String, String[]> parameters,
			AdminDTOInterface adminDtoInterface);

	private AdminDTO getAdminData(AdminDTOInterface adminDto, String username) {
		AdminDTO admin = null;
		try {
			admin = adminDto.getUserData(username);
		} catch (RemoteException e) {
			throw new FailedRmiTransactionException(e.toString());
		}
		return admin;
	}
	
	@Override
	protected void forwardToPage(HttpServletRequest req, HttpServletResponse res) {
		dispatcher.forwardToPage(req, res, HOMEPAGE);		
	}	
}