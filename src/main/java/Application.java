

public class Application {
    public static void main(String[] args) {
        double[] Xi= new double[]{6.7, 6.9, 7.2, 7.3, 8.4, 8.8, 9.1, 9.8, 10.6, 10.7, 11.1, 11.8, 12.1, 12.4 };
        double[] Yi= new double[]{2.8, 2.2, 3, 3.5, 3.2, 3.7, 4, 4.8, 6, 5.4, 5.2, 5.4, 6, 9 };
        Solution s = new Solution(Xi, Yi);
        //s.estimationOfCorrelationCoefficient();
        //s.hypothesisAboutSignificanceOfCorrelationCoefficient(0.05);
        s.regressionLine();
    }
}
