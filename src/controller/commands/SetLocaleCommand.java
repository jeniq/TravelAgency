package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocaleCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ResourceBundle bundle = null;
		HttpSession session = request.getSession(true);
		String language = request.getParameter("locale");
		session.setAttribute("lang", language);
		
		return CommandConstants.INDEX_PAGE;
	}

}
