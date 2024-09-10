public class HumidityCalculator {
    public static double calculateHumidityRatio(double P_v, double P) {
        return 0.622 * (P_v / (P - P_v));
    }
    public static double calculateDewPoint(double T, double RH) {
        double alpha = (RefrigerationCycle.DEW_POINT_A * T) / (RefrigerationCycle.DEW_POINT_B + T) + Math.log(RH / 100.0);
        return (RefrigerationCycle.DEW_POINT_B * alpha) / (RefrigerationCycle.DEW_POINT_A - alpha);
    }
    public static double calculateWetBulbTemperature(double T, double T_dp) {
        return T - (T - T_dp) / 3.3;
    }
}
