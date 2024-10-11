import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {
    // Переменная для хранения единственного экземпляра
    private static ConfigurationManager instance = null;

    // Словарь для хранения настроек (используем HashMap для простоты)
    private Map<String, String> settings;

    // Приватный конструктор, чтобы никто не мог создать объект извне
    private ConfigurationManager() {
        settings = new HashMap<>();
    }

    // Метод для получения единственного экземпляра класса (Singleton)
    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    // Метод для загрузки настроек из файла
    public void load(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=");
            if (parts.length == 2) {
                settings.put(parts[0], parts[1]);
            }
        }
        reader.close();
    }

    // Метод для сохранения настроек в файл
    public void save(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Map.Entry<String, String> entry : settings.entrySet()) {
            writer.write(entry.getKey() + "=" + entry.getValue());
            writer.newLine();
        }
        writer.close();
    }

    // Метод для получения значения настройки по ключу
    public String get(String key) {
        return settings.get(key);
    }

    // Метод для изменения или добавления новой настройки
    public void set(String key, String value) {
        settings.put(key, value);
    }
}


