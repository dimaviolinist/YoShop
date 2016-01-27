package by.itacademy.training.yoshop.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;
import by.itacademy.training.yoshop.entity.OnlineUsers;
import by.itacademy.training.yoshop.entity.User;

public class RememberMeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);

		if (session.getAttribute("user") == null) {

			Cookie[] cookies = httpRequest.getCookies();

			if (cookies == null) {
				chain.doFilter(request, response);
				return;
			}

			String login = null;
			String userHash = null;
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if ("login".equals(c.getName())) {
					login = c.getValue();
				}
				if ("userHash".equals(c.getName())) {
					userHash = c.getValue();
				}
			}

			if (login != null && userHash != null) {
				IDaoFactory factory = DaoFactory.getInstance();
				IUserDao userDao = factory.getUserDao();
				User user = userDao.getUser(login);
				if (user != null && userHash.equals(Integer.toString(user.hashCode()))) {
					if (user.getIsBannedUntil() != null) {
						if (user.getIsBannedUntil().after(new Date())) {
							request.setAttribute("error",
									"You are BANNED until" + user.getIsBannedUntil() + ". \n\t SHAME.");
							request.getRequestDispatcher("/error.jsp").forward(request, response);
							return;
						}
					}
					if (OnlineUsers.addUser(login) == false) {
						request.setAttribute("error", "You are already logged. Or not you. :)");
						request.getRequestDispatcher("/error.jsp").forward(request, response);
						return;
					}
					session.setAttribute("user", login);
					if ("admin".equals(user.getRole())) {
						session.setAttribute("userRole", "admin");
						request.getRequestDispatcher("/AdminPage").forward(request, response);
						return;
					}
					return;
				} else {
					request.setAttribute("error", "You think you are the cleverest... No, you aren't :) ");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
					return;
				}
			}
		}
		chain.doFilter(request, response);
		return;

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
