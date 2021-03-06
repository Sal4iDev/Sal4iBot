package soon;

import java.io.IOException;
import java.util.Properties;

public class MessagesManager {
    private final Properties properties = new Properties();

    public MessagesManager() {
        try {
            properties.load(getClass().getResourceAsStream("/soon/messages.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}
