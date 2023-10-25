package pe.gob.sblm.sgi.scheduler.properties;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class PropiedadesCorreo {
	private static final String BUNDLE_NAME = "configuracionCorreo"; 
	private static final ResourceBundle RESOURCE_BUNDLE_NAME = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH, Thread.currentThread().getContextClassLoader());
	
	public static String getString(String key) {
		
		try {
			return RESOURCE_BUNDLE_NAME.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		return null;
	}

}
