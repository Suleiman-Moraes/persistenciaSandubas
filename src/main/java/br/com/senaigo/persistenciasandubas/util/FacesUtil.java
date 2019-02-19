package br.com.senaigo.persistenciasandubas.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

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
			String caminho = new File("src\\main\\resources\\br\\com\\senaigo\\persistenciasandubas\\message\\" + name).getAbsolutePath();
			File file = new File(caminho);
			FileInputStream fileInputStream = new FileInputStream(file);
			prop.load(fileInputStream);
			fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return prop;
	}
}