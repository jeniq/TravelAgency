package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This class performes ending of user session.
 * 
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 */
public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession(true).invalidate();
		return CommandConstants.INDEX_PAGE;
	}

}
