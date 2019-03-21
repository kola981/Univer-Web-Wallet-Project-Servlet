package ua.univer.payments.actions.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.univer.payments.controllers.Dispatcher;
import ua.univer.rmi.dto.CardDTO;
import ua.univer.rmi.dto.ClientDTO;
import ua.univer.rmi.stubs.ClientDTOInterface;

public class GetTopupPageAction extends AbstractUserAction {

	private static final String PAGE = "/jsp/dynamic/topup.jsp";
	private final Dispatcher dispatcher = Dispatcher.getInstance();
	private CardDTO card;
	
	@Override
	protected void doUserAction(ClientDTO client, Map<String, String[]> parameters,
			ClientDTOInterface clientDtoInterface) {
		
		Long cardNum = Long.parseLong(getParameter(parameters, "cnum"));
		List<CardDTO> cards = client.getCards();
		card = cards.stream()
							.filter(c->cardNum.equals(c.getCardNumber()))
							.findFirst()
							.get();		
	}

	@Override
	protected void forwardToPage(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("cardToUse", card);
		dispatcher.forwardToPage(req, res, PAGE);		
	}
}
