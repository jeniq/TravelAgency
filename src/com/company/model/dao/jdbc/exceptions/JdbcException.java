package com.company.model.dao.jdbc.exceptions;

import java.util.ResourceBundle;

/**
 * This class extends unchecked Runtime exception and implements exception at
 * JDBC dao. This exception is localized.
 * 
 * @author Yevhen Hryshchenko
 * @version 27 Jule 2016
 *
 */
public class JdbcException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public static final String BUNDLE_NAME = "localization/errors";

	static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

	public JdbcException(String... message) {
		StringBuffer sb = new StringBuffer();

		sb.append(bundle.getString(ErrorsConstants.ERROR));
		for (String msg : message) {
			sb.append(ErrorsConstants.SPACE);
			sb.append(bundle.getString(msg));
		}

		throw new RuntimeException(sb.toString());
	}
}
