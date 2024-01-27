import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Currency Selection
        System.out.print("Enter the base currency code (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency code (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Step 2: Fetch real-time exchange rates from the API
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate == -1) {
            System.out.println("Failed to fetch exchange rates. Exiting...");
            System.exit(1);
        }

        // Step 3: Amount Input
        System.out.print("Enter the amount in " + baseCurrency + ": ");
        double amountToConvert = scanner.nextDouble();

        // Step 4: Currency Conversion
        double convertedAmount = amountToConvert * exchangeRate;

        // Step 5: Display Result
        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String apiKey = "YOUR_EXCHANGE_RATE_API_KEY";  // Replace with your API key
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Parse the JSON response to get the exchange rate
            String jsonResponse = response.toString();
            double exchangeRate = Double.parseDouble(jsonResponse.split("\"" + targetCurrency + "\":")[1].split(",")[0]);

            return exchangeRate;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
