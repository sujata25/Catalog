package utils;

import java.io.File;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

public class LoggerImplemetation {

	public static org.apache.log4j.Logger logConfig(String classname) {
		Logger logger = Logger.getLogger("API");
		String log4jConfigFile = System.getProperty("user.dir")
				+ File.separator + "src//main//resources//logger//log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		return logger;
	}
}
