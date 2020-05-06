

public class Application {
    public static void main(String[] args) {
        double[] x = {6.7, 6.9, 7.2, 7.3, 8.4, 8.8, 9.1, 9.8, 10.6, 10.7, 11.1, 11.8, 12.1, 12.4};
        double[] y = {2.8, 2.2, 3, 3.5, 3.2, 3.7, 4, 4.8, 6, 5.4, 5.2, 5.4, 6, 9};
        Solution solution = new Solution(x, y);
        solution.regressionLine();
    }
}
