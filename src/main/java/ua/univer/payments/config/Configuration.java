package ua.univer.payments.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ua.univer.payments.exceptions.ConfigurationNotFoundException;
import ua.univer.payments.servlets.LoginServlet;
import ua.univer.payments.servlets.PaymentsServlet;

public class Configuration extends Properties {

	private static final long serialVersionUID = 1L;
	private static final Configuration INSTANCE = new Configuration();
	private static boolean isLoaded = false;

	private Configuration() {
	}

	public static void loadConfig(PaymentsServlet ps) {
		if (!isLoaded) {
			InputStream input = ps.getServletContext().getResourceAsStream("/WEB-INF/config/config.txt");
			try {
				INSTANCE.load(input);
			} catch (IOException e) {
				throw new ConfigurationNotFoundException();
			}
			isLoaded = true;
		}
	}

	public static void loadConfig(LoginServlet ls) {
		if (!isLoaded) {
			InputStream input = ls.getServletContext().getResourceAsStream("/WEB-INF/config/config.txt");
			try {
				INSTANCE.load(input);
			} catch (IOException e) {
				throw new ConfigurationNotFoundException();
			}
			isLoaded = true;
		}
	}

	public static Configuration getInstance() {
		return INSTANCE;
	}
}
