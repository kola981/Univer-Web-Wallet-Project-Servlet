package ua.univer.payments.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.univer.payments.actions.user.GetTopupPageAction;
import ua.univer.payments.controllers.AdminActionHandler;
import ua.univer.payments.controllers.Dispatcher;
import ua.univer.payments.controllers.UserActionHandler;
import ua.univer.payments.util.UriParser;

@WebServlet(name = "PaymentServlet", urlPatterns = { "/account/*", "/admin/*" })
public class PaymentsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final Dispatcher dispatcher = Dispatcher.getInstance();		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String pageUrl = UriParser.parse(req);
		
		switch (pageUrl) {
		case "/account":
			UserActionHandler.handleRequest(req, res);
			break;			
		case "/account/new-card":
			dispatcher.forwardToPage(req, res, "/jsp/dynamic/new-card.jsp");
			break;
		case "/account/topup":
			dispatcher.forwardToPage(req, res, "/jsp/dynamic/topup.jsp");
			break;	
		case "/account/payment":
			dispatcher.forwardToPage(req, res, "/jsp/dynamic/payment.jsp");
			break;
		case "/account/transfer":
			dispatcher.forwardToPage(req, res, "/jsp/dynamic/transfer.jsp");
			break;	
		case "/admin":
			AdminActionHandler.handleRequest(req, res);
			break;		
		default:
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String pageUrl = UriParser.parse(req);		
		
		switch (pageUrl) {
		case "/account":
				UserActionHandler.handleRequest(req, res);
			break;
		case "/admin":
			if (req.getParameter("action") == null) {
				doGet(req, res);}
			else AdminActionHandler.handleRequest(req, res);
			break;
		case "/account/topup":
			GetTopupPageAction gtpa = new GetTopupPageAction();
			gtpa.handleRequest(req, res);
			break;			
		default:
		}
		
	}			
}
