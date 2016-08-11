package com.company.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface of command pattern sets methods for executing commands.
 * 
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 *
 */
public interface Command {
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
