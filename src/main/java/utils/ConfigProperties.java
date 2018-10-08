package utils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
            try {
                fileInputStream = new FileInputStream("src/main/resources/config.properties");
                InputStreamReader input = new InputStreamReader(fileInputStream, "windows-1251");
                PROPERTIES = new Properties();
                PROPERTIES.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null)
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

    public static String getTestProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
