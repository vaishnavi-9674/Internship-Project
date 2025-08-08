package in.WeatherApp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WeatherAppWrtt {
	
	public static void main(String[] args) {
        String city = JOptionPane.showInputDialog(null, "Enter city name:", "Weather App (wttr.in)", JOptionPane.QUESTION_MESSAGE);

        if (city == null || city.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "City name cannot be empty!");
            return;
        }

        try {
            String endpoint = "https://wttr.in/" + city + "?format=j1";

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBuilder.toString());

            JsonNode current = root.get("current_condition").get(0);
            String tempC = current.get("temp_C").asText();
            String condition = current.get("weatherDesc").get(0).get("value").asText();
            String feelsLike = current.get("FeelsLikeC").asText();
            String humidity = current.get("humidity").asText();

            String result = String.format(
                "📍 Weather Report for %s\n" +
                "Temperature : %s °C\n" +
                "Feels Like  : %s °C\n" +
                "Condition   : %s\n" +
                "Humidity    : %s%%",
                city, tempC, feelsLike, condition, humidity
            );

            // Display
            JOptionPane.showMessageDialog(null, result, "Live Weather", JOptionPane.INFORMATION_MESSAGE);

            // Save to file
            Files.write(Paths.get("weather_report.txt"), result.getBytes());
            System.out.println("✅ Weather report saved to weather_report.txt");

        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, " Failed to fetch weather data.\nError: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

