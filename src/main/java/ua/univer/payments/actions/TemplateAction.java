package ua.univer.payments.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.univer.rmi.stubs.RmiInterface;

public abstract class TemplateAction {

	public TemplateAction() {
		super();
	}

	public final void handleRequest(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, String[]> parameters = getRequestParameter(req);
		HttpSession session = getSession(req);
		if (rmiRequired()) {
			RmiInterface rmi = getRmiInterface();
			doAction(session, parameters, rmi);
		}
		else doAction(session, parameters);
		forwardToPage(req, res);
		
	}		

	private Map<String, String[]> getRequestParameter(HttpServletRequest req) {
		return req.getParameterMap();		
	}

	private HttpSession getSession(HttpServletRequest req) {
		return req.getSession(false);
	}

	protected boolean rmiRequired() {
		return true;
	}
	
	protected RmiInterface getRmiInterface() {
		return null;
	}
	
	protected void doAction(HttpSession session, Map<String, String[]> parameters) {}
	
	protected void doAction(HttpSession session, Map<String, String[]> parameters, RmiInterface rmi) {}
	
	protected abstract void forwardToPage(HttpServletRequest req, HttpServletResponse res);
		
	protected final String getParameter(Map<String, String[]> parameters, String str) {
		String[] parameter = parameters.get(str);
		if(parameter != null)
			return parameter[0];
		else return "";
	}
}
