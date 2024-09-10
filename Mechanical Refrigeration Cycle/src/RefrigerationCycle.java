public class RefrigerationCycle {
    public static final double THERMAL_CONDUCTIVITY_WATER = 0.606;
    public static final double DEW_POINT_A = 17.27;
    public static final double DEW_POINT_B = 237.7;
    public static double calculateWorkDone(double hOut, double hIn, double m) {
        return m * (hOut - hIn);
    }
    public static double calculateHeatRejected(double hOut, double hIn, double m, double latentHeat) {
        return m * (hOut - hIn) + latentHeat;
    }
    public static double calculateHeatAbsorbed(double hOut, double hIn, double m, double latentHeat) {
        return m * (hOut - hIn) - latentHeat;
    }
    public static double calculateCOP(double Qe, double WcActual) {
        return Qe / WcActual;
    }
    public static double calculateActualCompressorWork(double h2s, double h1, double efficiency, double m) {
        double WcIdeal = calculateWorkDone(h2s, h1, m);
        return (WcIdeal > 0) ? WcIdeal / efficiency : 0.0;
    }
    public static double calculateActualTurbineWork(double h2, double h1s, double efficiency, double m) {
        double WtIdeal = calculateWorkDone(h1s, h2, m);
        return (WtIdeal > 0) ? WtIdeal * efficiency : 0.0;
    }
    public static double adjustEnthalpyForPressureDrop(double h, double deltaP) {
        return h - (deltaP * 0.01);
    }
    public static double calculatePressureDrop(double L, double D, double rho, double v, double f) {
        return f * (L / D) * (0.5 * rho * v * v) / 1000; // kPa
    }
    public static double calculateExpansionPressureDrop(double P1, double T1, double T2) {
        return (P1 - (T2 / T1) * P1) * 100; // kPa
    }
    public static double calculateIsentropicEnthalpy(double h1, double P1, double P2) {
        return h1 * (P2 / P1);
    }
    public static double calculateIdealWork(double h1, double h2, double m) {
        return m * (h2 - h1);
    }
    public static double calculateSecondLawEfficiency(double WActual, double WIdeal) {
        if (WIdeal == 0) return 0.0;
        return WActual / WIdeal;
    }
}
