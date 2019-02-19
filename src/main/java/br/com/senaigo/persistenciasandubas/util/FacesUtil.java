package br.com.senaigo.persistenciasandubas.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import br.com.senaigo.persistenciasandubas.message.MessagesLoader;

public class FacesUtil {

	public static Properties propertiesLoader() {
		return properties("sistema_pt.properties");
	}
	
	public static Properties propertiesLoaderURL() {
		return properties("url_pt.properties");
	}

	public static Properties properties(String name) {
		Properties prop = new Properties();
		try {
			String atualDir = MessagesLoader.class.getResource("").getPath();
			File file = new File(atualDir + name);
			FileInputStream fileInputStream = new FileInputStream(file);
			prop.load(fileInputStream);
			fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return prop;
	}
}