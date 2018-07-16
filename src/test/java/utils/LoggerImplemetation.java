package utils;

import java.io.File;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

public class LoggerImplemetation {

	public static Logger logConfig(String classname) {
		Logger logger = Logger.getLogger("API");
		String log4jConfigFile = System.getProperty("user.dir")
				+ File.separator + "src//main//resources//logger//log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		return logger;
	}
}
