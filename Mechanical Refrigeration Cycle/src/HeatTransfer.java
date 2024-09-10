public class HeatTransfer {
    public static double calculateHeatTransferCoefficient(double Re, double Pr, double D, double foulingFactor) {
        double h = 0.023 * Math.pow(Re, 0.8) * Math.pow(Pr, 0.3) * (RefrigerationCycle.THERMAL_CONDUCTIVITY_WATER / D);
        return h * (1 + foulingFactor);
    }
    public static double calculateHeatTransferRate(double h, double area, double dT) {
        return h * area * dT;
    }
}
