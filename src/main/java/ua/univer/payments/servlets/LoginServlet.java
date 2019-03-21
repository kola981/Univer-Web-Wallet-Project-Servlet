package ua.univer.payments.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.univer.payments.actions.login.LoginAction;
import ua.univer.payments.actions.registration.RegistrationAction;
import ua.univer.payments.config.Configuration;
import ua.univer.payments.util.UriParser;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login", "/register", "/logout" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(){
		Configuration.loadConfig(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String pageUrl = UriParser.parse(req);

		switch (pageUrl) {
		case "/login":
			req.getRequestDispatcher("/jsp/dynamic/login.jsp").forward(req, res);
			break;
		case "/register":
			req.getRequestDispatcher("/jsp/dynamic/register.jsp").forward(req, res);
			break;
		case "/logout":
			HttpSession session = req.getSession();
			session.invalidate();
			req.getRequestDispatcher("/jsp/dynamic/logout.jsp").forward(req, res);
			break;
		default:
			session = req.getSession();
			session.invalidate();
			req.getRequestDispatcher("/jsp/index.jsp").forward(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pageUrl = UriParser.parse(req);

		switch (pageUrl) {
		case "/login":
				LoginAction la = new LoginAction();
				la.handleRequest(req, res);
			break;
		case "/register":
				RegistrationAction ra = new RegistrationAction();
				ra.handleRequest(req, res);
		break;
		default:
		}
	}
	
}