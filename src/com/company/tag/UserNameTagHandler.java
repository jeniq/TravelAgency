package com.company.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * This class handles userName tag. It locates user's name and surname.
 * 
 * @author Yevhen Hryshchenko
 * @version 11 August 2016
 *
 */
public class UserNameTagHandler extends SimpleTagSupport {
	public static final String BUNDLE_NAME = TagConstants.LANG_PATH;
	private String name;
	private String surname;
	private Locale lang;

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * This method sets Locale object depending on input value.
	 * 
	 * @param lang user language
	 */
	public void setLang(String lang) {
		switch (lang) {
		case TagConstants.LANG_UA:
			this.lang = new Locale("uk", "UA");
			break;
		default:
			this.lang = new Locale("en", "US");
		}
	}

	@Override
	public void doTag() throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, lang);
		Writer out = getJspContext().getOut();

		out.write(bundle.getString(name) + " " + bundle.getString(surname));
	}
}
