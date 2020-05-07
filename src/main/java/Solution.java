import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Solution {
    private double[] Xi;
    private double[] Yi;
    private int length;
    private double xExpectation;
    private double yExpectation;
    private double selectionCorrelationsAssignment; //центральный момент 11
    private double xDispersion; //неисправленная!!
    private double yDispersion;
    private double r = 0.9;

    public Solution(double[] Xi, double[] Yi) {
        this.Xi = Xi;
        this.Yi = Yi;
        length = Xi.length;
        xExpectation = 1.0/length * (DoubleStream.of(Xi).sum());
        yExpectation = 1.0/length * (DoubleStream.of(Yi).sum());
        add2Rows();
        selectionCorrelationsAssignment = GetKxy(false);
        xDispersion = calcDispersion(Xi, xExpectation, false);
        yDispersion = calcDispersion(Yi, yExpectation, false);
        r = GetR(false);


    }
    //1
    public void estimationOfCorrelationCoefficient() {
        System.out.printf("\n___________Task_1___________\n");
        StringBuilder XExp = new StringBuilder();
        XExp.append("XExpectation = 1/").append(length).append("(");
        for (double i:this.Xi){
            XExp.append(i).append(" + ");
        }
        XExp.append(") = ").append("1/").append(length).append("*").append(DoubleStream.of(Xi).sum()).append(" = ").append(xExpectation);
        System.out.println(XExp.toString());
        StringBuilder YExp = new StringBuilder();
        YExp.append("YExpectation = 1/").append(length).append("(");

        for (double i:this.Yi){
            YExp.append(i).append(" + ");
        }
        YExp.append(") = ").append("1/").append(length).append("*").append(DoubleStream.of(Yi).sum()).append(" = ").append(yExpectation);
        System.out.println(YExp.toString());

        GetKxy(true);


        System.out.printf("Sx^2 = 1/%d * (", length);
        calcDispersion(Xi, xExpectation, true);
        System.out.printf(" = %.3f \n", xDispersion);
        System.out.printf("Sx = %.2f\n", Math.sqrt(xDispersion));


        System.out.printf("Sy^2 = 1/%d * (", length);
        calcDispersion(Yi, yExpectation, true);
        System.out.printf(" = %.3f \n", yDispersion);
        System.out.printf("Sy = %.2f\n", Math.sqrt(xDispersion));

        GetR(true);
    }

    public void  hypothesisAboutSignificanceOfCorrelationCoefficient(double alpha){
        Scanner scaner = new Scanner(System.in);
        double Kest= (r* Math.sqrt(length-2))/(Math.sqrt(1-r*r));
        System.out.printf("\n___________Task_2___________\n" +
                "Our alpha = %.3f\n" +
                "Our hypothesis:\n" +
                "H0: r = 0;\n" +
                "H1: r != 0\n" +
                "Kest= (r * sqrt(n - 2)) / sqrt(1 - r^2) = " +
                "(%.3f * sqrt(%d - 2)) / sqrt(1 - %.3f^2) = " +
                "%.3f\n", alpha, r, length, r*r, Kest);

        System.out.printf("Pls, enter the value from " +
                "Student distribution table:\nalpha = %.3f and n(%d - 2) = %d \n" +
                "Separator - ',' \n", alpha, length, length-2);
        double Kcr = scaner.nextDouble();
        System.out.printf("\nGreat! You have enter: %.3f\n" +
                "Let's compare: must be |Kest| > Kcr\n" +
                "In our case: |%.3f| > %.3f\n" +
                "!!! For conclusion open manual and look through  \n" +
                "In short, if inequality is true, then reject H0,\n" +
                "otherwise, reject H1 \n\n", Kcr, Kest, Kcr);


    }

    public void regressionLine() {
        System.out.printf("\n___________Task_3___________\n");
        double bYX = selectionCorrelationsAssignment / xDispersion;
        System.out.printf("b_yx = %.3f/%.3f = %.3f\n", selectionCorrelationsAssignment, xDispersion, bYX);
        System.out.println("y_x = b_yx * x + (<y> -  b_yx * <x>)");
        System.out.printf("y_x = %.3f * x + (%.3f -  %.3f * %.3f)\n", bYX, yExpectation, bYX, xExpectation);
        double b = yExpectation - bYX * xExpectation;
        System.out.printf("y_x = %.3f * x + %.3f\n", bYX, b);
    }

    public void add2Rows(){
        for (int i = 0; i < length; i++) {
            System.out.printf(" %.3f ", Xi[i]-xExpectation);
        }
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.printf(" %.3f ", Yi[i]-yExpectation);
        }
        System.out.println();
    }

    private double GetR(boolean print)
    {
        double R = selectionCorrelationsAssignment / (Math.sqrt(xDispersion) * Math.sqrt(yDispersion));
        if (print)
            System.out.printf("r = %.3f / (sqrt(%.3f) * sqrt(%.3f) = %.3f", selectionCorrelationsAssignment, xDispersion, yDispersion, R);
        return R;
    }


    private double GetKxy(boolean print)
    {
        double temp = 0;
        double Kxy = 0;
        if (print)
            System.out.printf("Kxy (M11) = 1/%d *(", length);
        for (int i = 0; i < length; i++) {
            temp = (Xi[i] - xExpectation) * (Yi[i] - yExpectation);
            Kxy += temp;
            if (i + 1 == length && print) {
                System.out.printf("%.3f)", temp);
            } else if (print)
                System.out.printf("%.3f + ", temp);

        }
        Kxy *= (1/ (double)length);
        if (print)
            System.out.printf(" = %.3f \n", Kxy);

        return Kxy;
    }

    private double calcDispersion(double[] row, double Expectation, boolean print)
    {
        double result = 0;
        double temp = 0;
        for (int i = 0; i < length; i++) {
            temp = Math.pow(row[i] - Expectation,2);
            result += temp;
            if (i + 1 == length && print)
            {
                System.out.printf("%.3f) ", temp);
            } else if (print)
            {
                System.out.printf("%.3f +", temp);
            }
        }
        result *= (1/ (double)length);
        return result;
    }

}
