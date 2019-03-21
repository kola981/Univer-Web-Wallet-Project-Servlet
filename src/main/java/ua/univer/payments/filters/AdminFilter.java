package ua.univer.payments.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.univer.rmi.dto.Role;

@WebFilter(filterName = "AdminFilter", urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)

			throws IOException, ServletException {

		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		final HttpSession session = req.getSession(false);
		
		if (session != null) {
			final Role role = (Role) session.getAttribute("role");
			if (!Role.ADMIN.equals(role))
				res.sendRedirect(req.getContextPath() + "/");
			else
				filterChain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
	}
}
