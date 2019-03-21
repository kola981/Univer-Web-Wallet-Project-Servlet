package ua.univer.payments.util;

import javax.servlet.http.HttpServletRequest;

public class UriParser {
		
	private UriParser() {
		super();
	}

	public static String parse(HttpServletRequest req) {
		String pageUrl = req.getRequestURI();
		String context = req.getContextPath();
		return pageUrl.substring(context.length());
	}
}
