import org.apache.xmlrpc.*;
import java.io.*;
import java.net.*;

public class WeatherWebService {
    
    private static final String API_KEY = "65c8e3a6f82804d304d17c21890b61aa"; // API-ключ від OpenWeatherMap
    
    // Метод для отримання погоди на завтра за локацією
    public String getWeatherForecast(String city) throws Exception {
        String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + API_KEY + "&units=metric";

        // Отримання JSON відповіді від API
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Обробка JSON відповіді вручну
        String json = response.toString();
        
        // Знаходимо прогноз на завтра в отриманому JSON
        String forecastBlock = json.split("\"list\":\\[")[1].split("\\},\\{")[8]; // 8-ма позиція - це прогноз на наступні 24 години
        String description = forecastBlock.split("\"description\":\"")[1].split("\"")[0];
        String temp = forecastBlock.split("\"temp\":")[1].split(",")[0];

        
        return "Завтра у " + city + ": " + description + ", температура: " + temp + "°C";
    }

    public static void main(String[] args) {
        try {
            System.out.println("Спроба запуску XML-RPC Web Serviece...");
            WebServer server = new WebServer(8080);
            server.addHandler("weather", new WeatherWebService());
            server.start();
            System.out.println("Запущено успішно.");
            System.out.println("Очікування запитів...");
        } catch (Exception exception) {
            System.err.println("Web Service: " + exception);
        }
    }
}

