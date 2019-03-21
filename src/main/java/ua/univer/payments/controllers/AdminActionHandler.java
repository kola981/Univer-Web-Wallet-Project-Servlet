package ua.univer.payments.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.univer.payments.actions.admin.AccountBlockAction;
import ua.univer.payments.actions.admin.AccountUnblockAction;
import ua.univer.payments.actions.admin.CardUnblockAction;
import ua.univer.payments.actions.admin.GetHomepageAction;


public class AdminActionHandler  {
	
	private AdminActionHandler() {
		super();
	}		
	
	public static void handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String action = req.getParameter("action");
		if(action == null) action = "";
		
		switch(action) {
			case "card-unblock":
				CardUnblockAction cua = new CardUnblockAction();
				cua.handleRequest(req, res);
				break;
			case "account-block":
				AccountBlockAction aba = new AccountBlockAction();
				aba.handleRequest(req, res);
				break;
			case "account-unblock":
				AccountUnblockAction aua = new AccountUnblockAction();
				aua.handleRequest(req, res);
				break;
			default:
				GetHomepageAction gha = new GetHomepageAction();
				gha.handleRequest(req, res);
		}
	}	
}
