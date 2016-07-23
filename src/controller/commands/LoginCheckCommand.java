package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import log.LogMessageConstants;
import model.entities.User;
import model.services.AuthenticationService;

/**
 * This class executes when the user enters to the system.
 * 
 * @author Yevhen Hryshchenko
 * @version 22 Jule 2016
 * 
 */
public class LoginCheckCommand implements Command {
	private AuthenticationService service = AuthenticationService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter(CommandConstants.LOGIN);
		String password = request.getParameter(CommandConstants.PASSWORD);
		User user = service.checkLogin(login, password);
		
		if (user == null) {
			request.setAttribute(CommandConstants.WRONG_DATA, CommandConstants.WRONG_LOGIN_DATA);
			return CommandConstants.INDEX_PAGE;
		}
		session.setAttribute(CommandConstants.USER, user);
		session.setAttribute(CommandConstants.PERMISSIONS, user.getPermission(user));
		Logger.getLogger(LoginCheckCommand.class.getName()).info(LogMessageConstants.USER_LOGIN + login);
		return CommandConstants.FIND_ALL_TOURS_PAGE;
	}

}
