public class Solution {
    private double[] Xi;
    private double[] Yi;
    private int length;
    private double xExpectation;
    private double yExpectation;
    private double selectionCorrelationsAssignment; //центральный момент 11
    private double xDispersion; //неисправленная!!

    public Solution(double[] Xi, double[] Yi) {
        this.Xi = Xi;
        this.Yi = Yi;
        length = Xi.length;
    }

    public void estimationOfCorrelationCoefficient() {

    }

    public void regressionLine() {
        double bYX = selectionCorrelationsAssignment / Math.sqrt(xDispersion);
        System.out.printf("b_yx = %.3f/%.3f = %.3f\n", selectionCorrelationsAssignment, Math.sqrt(xDispersion), bYX);
        System.out.println("y_x = b_yx * x + (<y> -  b_yx * <x>)");
        System.out.printf("y_x = %.3f * x + (%.3f -  %.3f * %.3f)", bYX, yExpectation, bYX, xExpectation);
        double b = yExpectation - bYX * xExpectation;
        System.out.printf("y_x = %.3f * x + %.3f\n", bYX, b);
    }
}
