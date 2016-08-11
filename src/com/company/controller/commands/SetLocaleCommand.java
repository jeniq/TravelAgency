package com.company.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class sets language for user on the pages.
 * 
 * @author Yevhen Hryshchenko
 * @version 23 Jule 2016
 *
 */
public class SetLocaleCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String language = request.getParameter(CommandConstants.LOCALE);
		request.getSession(true).setAttribute(CommandConstants.LANG, language);

		return CommandConstants.INDEX_PAGE;
	}

}
