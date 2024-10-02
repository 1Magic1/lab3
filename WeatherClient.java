import java.util.*;
import org.apache.xmlrpc.*;


public class WeatherClient {
	public static void main(String[] args) {
        try {
            XmlRpcClient server = new XmlRpcClient("http://localhost:8080");
            Vector<String> params = new Vector<>();
            
            // Отримання назви міста від користувача
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введіть назву міста: ");
            String city = scanner.nextLine();
            params.addElement(city);
            
            // Виклик методу getWeatherForecast на сервері
            Object result = server.execute("weather.getWeatherForecast", params);
            String forecast = (String) result;
            
            // Виведення результату
            System.out.println(forecast);
            
        } catch (Exception exception) {
            System.err.println("WeatherClient: " + exception);
        }
    }
}
