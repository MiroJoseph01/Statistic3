public class Solution {
    private double[] Xi;
    private double[] Yi;
    private int length;
    private double xExpectation;
    private double yExpectation;
    private double selectionCorrelationsAssignment; //центральный момент 11
    private double xDispersion;

    public Solution(double[] Xi, double[] Yi) {
        this.Xi = Xi;
        this.Yi = Yi;
        length = Xi.length;
    }

    public void estimationOfCorrelationCoefficient() {

    }

    public void regressionLine() {
        double bYX = selectionCorrelationsAssignment / Math.sqrt(xDispersion);
        System.out.printf("b yx = %.3f/%.3f = %.3f", selectionCorrelationsAssignment, Math.sqrt(xDispersion), bYX);

    }
}
