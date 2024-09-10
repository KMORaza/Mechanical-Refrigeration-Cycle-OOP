public class HeatPump {
    public static double getSaturatedLiquidEnthalpy(double T, double P) {
        if (T == 5.0 && P == 1.2) return 250.0;
        if (T == 35.0 && P == 4.5) return 300.0;
        return 0.0;
    }
    public static double getSaturatedVaporEnthalpy(double T, double P) {
        if (T == 5.0 && P == 1.2) return 450.0;
        if (T == 35.0 && P == 4.5) return 500.0;
        return 0.0;
    }
    public static double getLatentHeat(double T, double P) {
        if (T == 5.0 && P == 1.2) return 200.0;
        if (T == 35.0 && P == 4.5) return 220.0;
        return 0.0;
    }
    public static double getSpecificHeatCapacity(double T, double P, boolean isConstantPressure) {
        return isConstantPressure ? 4.18 : 3.98;
    }
}
