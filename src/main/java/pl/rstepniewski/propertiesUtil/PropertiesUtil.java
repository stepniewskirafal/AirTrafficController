package pl.rstepniewski.propertiesUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private final static Properties PROPERTIES = new Properties();
    private final static Logger LOGGER = LogManager.getLogger(PropertiesUtil.class);
    private static final String PROPERTIES_FILE = "application.properties";

    static{
        try{
            loadProperties();
        } catch (Throwable e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("FileNotFoundException: file " + PROPERTIES_FILE + " can not be found. Error message:" + e.getMessage());
            }
        }
    }

    private static void loadProperties() throws Throwable {
        try(var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            PROPERTIES.load(stream);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException().initCause(e);
        }
    }

    public static String getString(String key){
        return PROPERTIES.getProperty(key);
    }

    public static Integer getInt(String key){
        int propertyInt;
        try{
            propertyInt = Integer.parseInt(PROPERTIES.getProperty(key));
        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
        return propertyInt;
    }
}
