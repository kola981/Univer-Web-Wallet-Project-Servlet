package ua.univer.payments.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.univer.payments.exceptions.PageNotFoundException;

public class Dispatcher {

	private static final Dispatcher INSTANCE = new Dispatcher();
	
	private Dispatcher() {}

	public static Dispatcher getInstance() {		
		return INSTANCE;
	}
	
	public void forwardToPage(HttpServletRequest req, HttpServletResponse res, String uri){
		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher(uri);
			dispatcher.forward(req, res);
		} catch (ServletException | IOException e) {
			throw new PageNotFoundException();
		}	
	}
	
	public void redirectToPage(HttpServletRequest req, HttpServletResponse res, String uri){
		try {
			res.sendRedirect(req.getContextPath()+uri);
		} catch ( IOException e) {
			throw new PageNotFoundException();
		}
	}
}
