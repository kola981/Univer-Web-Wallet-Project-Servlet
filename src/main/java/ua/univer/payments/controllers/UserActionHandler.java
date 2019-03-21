package ua.univer.payments.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.univer.payments.actions.user.AddNewCardAction;
import ua.univer.payments.actions.user.CardBlockAction;
import ua.univer.payments.actions.user.ForgetCardAction;
import ua.univer.payments.actions.user.ExecutePaymentAction;
import ua.univer.payments.actions.user.ExecuteTransferAction;
import ua.univer.payments.actions.user.GetHomepageAction;
import ua.univer.payments.actions.user.TopupAccountAction;

public class UserActionHandler  {
	
	private UserActionHandler() {
		super();
	}		
	
	public static void handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String action = req.getParameter("action");
		if(action == null) action = "";
		
		switch(action) {
			case "block":
				CardBlockAction cba = new CardBlockAction();
				cba.handleRequest(req, res);
				break;
			case "forget-card":
				ForgetCardAction cua = new ForgetCardAction();
				cua.handleRequest(req, res);
				break;
			case "topup":
				TopupAccountAction taa = new TopupAccountAction();
				taa.handleRequest(req, res);
				break;
			case "transfer":
				ExecuteTransferAction eta = new ExecuteTransferAction();
				eta.handleRequest(req, res);
				break;
			case "payment":
				ExecutePaymentAction epa = new ExecutePaymentAction();
				epa.handleRequest(req, res);
				break;
			case "save-new-card":
				AddNewCardAction anca = new AddNewCardAction();
				anca.handleRequest(req, res);
				break;	
			default:
				GetHomepageAction gha = new GetHomepageAction();
				gha.handleRequest(req, res);
		}
	}	
}
