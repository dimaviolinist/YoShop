package by.itacademy.training.yoshop.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.itacademy.training.yoshop.dao.IBanManagementDao;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;
import by.itacademy.training.yoshop.entity.BlackListRecord;
import by.itacademy.training.yoshop.entity.User;

public class UserManagerController extends HttpServlet {

	private static final long serialVersionUID = 8130252325529911000L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userLogin = request.getParameter("user");
		if (userLogin == null) {
			request.setAttribute("error", "doGet from AdminController didn't get the 'user' parameter");
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		IUserDao userDao = DaoFactory.getInstance().getUserDao();
		User user = userDao.getUser(userLogin);

		IBanManagementDao banDao = DaoFactory.getInstance().getManagementDao();
		List<BlackListRecord> banListStory = banDao.UserBanListStory(user);
		request.setAttribute("banListStory", banListStory);

		request.setAttribute("userForManagement", user);
		getServletContext().getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doBan request handling
		if (request.getParameter("doBan") != null) {
			String banTerm = request.getParameter("banTerm");
			if (banTerm != null) {
				// TODO need checking for correct parsing and result
				int banDays = Integer.parseInt(banTerm);

				// TODO
				String userId = request.getParameter("userId");

				int userIdInt = Integer.parseInt(userId);

				IBanManagementDao userDao = DaoFactory.getInstance().getManagementDao();
				if (!userDao.addUserToBanList(userIdInt, banDays)) {
					request.setAttribute("error", "Attempt of adding user " + userIdInt + "to ban list has failed.");
					getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
					return;
				}
			} else {
				request.setAttribute("error", "doPost from UserManager didn't get the 'banTerm' parameter");
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
		} else

		// stop Ban request handling
		if (request.getParameter("stopBan") != null) {

			// TODO
			String userId = request.getParameter("userId");
			int userIdInt = Integer.parseInt(userId);

			IBanManagementDao userDao = DaoFactory.getInstance().getManagementDao();

			if (!userDao.stopBanUser(userIdInt)) {
				request.setAttribute("error", "Attempt of stopping ban user " + userIdInt + " has failed.");
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
		} else {

			request.setAttribute("error", "doPost from UserManager didn't get any 'post' parameter");
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		getServletContext().getRequestDispatcher("/AdminPage").forward(request, response);
		return;

	}
}
