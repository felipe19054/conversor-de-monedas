package com.example.conversor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConversorMonedas {

    private static final String API_KEY = "decbd3031e52a83ab7ce3b4e"; // Clave API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        // Mostrar las fases del desafío
        printChallengePhases();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ver monedas disponibles");
            System.out.println("2. Convertir moneda");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    showAvailableCurrencies();
                    break;
                case 2:
                    convertCurrencyProcess(scanner);
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el conversor de monedas!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Mostrar las monedas disponibles
    private static void showAvailableCurrencies() {
        try {
            String endpoint = BASE_URL + API_KEY + "/codes";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());

            if (jsonResponse.getString("result").equals("success")) {
                System.out.println("Monedas disponibles:");
                for (Object code : jsonResponse.getJSONArray("supported_codes")) {
                    System.out.println("- " + ((JSONArray) code).getString(0) + " (" + ((JSONArray) code).getString(1) + ")");
                }
            } else {
                System.out.println("Error al obtener las monedas disponibles: " + jsonResponse.getString("error-type"));
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    // Proceso de conversión de moneda
    private static void convertCurrencyProcess(Scanner scanner) {
        System.out.println("Introduce la moneda de origen (ejemplo: USD): ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Introduce la moneda de destino (ejemplo: EUR): ");
        String toCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Introduce la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        double result = convertCurrency(fromCurrency, toCurrency, amount);

        if (result != -1) {
            System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);
        } else {
            System.out.println("No se pudo realizar la conversión. Verifica los datos ingresados.");
        }
    }

    // Método para realizar la conversión
    private static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            String endpoint = BASE_URL + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());

            if (jsonResponse.getString("result").equals("success")) {
                double conversionRate = jsonResponse.getDouble("conversion_rate");
                return conversionRate * amount;
            } else {
                System.out.println("Error al obtener la tasa de cambio: " + jsonResponse.getString("error-type"));
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
        return -1;
    }

    // Método para imprimir las fases del desafío
    private static void printChallengePhases() {
        System.out.println("Conversor de monedas");

    }
}