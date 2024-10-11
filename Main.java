import java.io.IOException;

// Пример использования ConfigurationManager
class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Поток 1
        Thread t1 = new Thread(() -> {
            ConfigurationManager config = ConfigurationManager.getInstance();
            config.set("language", "Java");
            System.out.println("Поток 1: language = " + config.get("language"));
        });

        // Поток 2
        Thread t2 = new Thread(() -> {
            ConfigurationManager config = ConfigurationManager.getInstance();
            System.out.println("Поток 2: language = " + config.get("language"));
        });

        // Запуск потоков
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Главный поток
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.save("config.txt"); // Сохранение настроек в файл
        System.out.println("Main: language = " + config.get("language"));
    }
}