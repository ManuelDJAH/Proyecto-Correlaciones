import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String filePath = "startup-profit.csv";
        ArrayList<Double> profit = new ArrayList<>();
        ArrayList<Double> rdSpend = new ArrayList<>();
        ArrayList<Double> marketingSpend = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                profit.add(Double.parseDouble(values[4]));
                rdSpend.add(Double.parseDouble(values[0]));
                marketingSpend.add(Double.parseDouble(values[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double correlacionProfitRD = calcularCorrelacion(profit, rdSpend);
        double correlacionProfitMarketing = calcularCorrelacion(profit, marketingSpend);

        System.out.println("Coeficiente de correlación entre Profit y R&D Spend: " + correlacionProfitRD);
        System.out.println("Coeficiente de correlación entre Profit y Marketing Spend: " + correlacionProfitMarketing);
    }

    private static double calcularCorrelacion(ArrayList<Double> x, ArrayList<Double> y) {
        double sumX = 0.0, sumY = 0.0, sumXY = 0.0;
        double sumXSquare = 0.0, sumYSquare = 0.0;
        int n = x.size();

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * y.get(i);
            sumXSquare += x.get(i) * x.get(i);
            sumYSquare += y.get(i) * y.get(i);
        }

        double numerador = n * sumXY - sumX * sumY;
        double denominador = Math.sqrt((n * sumXSquare - sumX * sumX) * (n * sumYSquare - sumY * sumY));

        return numerador / denominador;
    }
}
