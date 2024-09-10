public class Main {
    public static void main(String[] args) {
        // Define constants and parameters
        double L = 10.0; // Length of pipe in evaporator and condenser (m)
        double D = 0.05; // Diameter of pipe in evaporator and condenser (m)
        double rho = 1.0; // Density of the fluid (kg/m³)
        double v = 2.0;   // Velocity of the fluid (m/s)
        double Pr = 7.0;  // Prandtl number
        double T_evap = 5.0;  // Saturation temperature in the evaporator (°C)
        double T_cond = 35.0; // Saturation temperature in the condenser (°C)
        double P_evap = 1.2;  // Saturation pressure in the evaporator (bar)
        double P_cond = 4.5;  // Saturation pressure in the condenser (bar)
        double eta_c = 0.85; // Compressor efficiency
        double eta_t = 0.90; // Turbine efficiency
        double eta_e = 0.90; // Expansion valve efficiency
        double foulingFactor_evap = 0.05; // Fouling factor for evaporator
        double foulingFactor_cond = 0.05; // Fouling factor for condenser
        int mode = 1; // Change this to 2 for Heating Mode
        double h1_liquid = HeatPump.getSaturatedLiquidEnthalpy(T_evap, P_evap);
        double h1_vapor = HeatPump.getSaturatedVaporEnthalpy(T_evap, P_evap);
        double h2_vapor = HeatPump.getSaturatedVaporEnthalpy(T_cond, P_cond);
        double h3_liquid = HeatPump.getSaturatedLiquidEnthalpy(T_cond, P_cond);
        double h4_vapor = HeatPump.getSaturatedVaporEnthalpy(T_evap, P_evap);
        double latentHeat_evap = HeatPump.getLatentHeat(T_evap, P_evap);
        double latentHeat_cond = HeatPump.getLatentHeat(T_cond, P_cond);
        double deltaP_e = RefrigerationCycle.calculatePressureDrop(L, D, rho, v, foulingFactor_evap);
        double deltaP_c = RefrigerationCycle.calculatePressureDrop(L, D, rho, v, foulingFactor_cond);
        double deltaP_v = RefrigerationCycle.calculateExpansionPressureDrop(P_evap, T_evap, T_cond);
        double h1_adjusted = RefrigerationCycle.adjustEnthalpyForPressureDrop(h1_liquid, deltaP_e);
        double h2_adjusted = RefrigerationCycle.adjustEnthalpyForPressureDrop(h2_vapor, deltaP_c);
        double h3_adjusted = RefrigerationCycle.adjustEnthalpyForPressureDrop(h3_liquid, deltaP_c);
        double h4_adjusted = RefrigerationCycle.adjustEnthalpyForPressureDrop(h4_vapor, deltaP_e);
        double massFlowRate = 1.0;
        double Wc_ideal = RefrigerationCycle.calculateWorkDone(h2_vapor, h1_vapor, massFlowRate);
        double Wt_ideal = RefrigerationCycle.calculateWorkDone(h3_liquid, h4_vapor, massFlowRate);
        double Wc_actual = RefrigerationCycle.calculateActualCompressorWork(h2_vapor, h1_adjusted, eta_c, massFlowRate);
        double Wt_actual = RefrigerationCycle.calculateActualTurbineWork(h2_adjusted, h3_liquid, eta_t, massFlowRate);
        double We = RefrigerationCycle.calculateWorkDone(h4_vapor, h3_liquid, massFlowRate);
        double Qe_actual = RefrigerationCycle.calculateHeatAbsorbed(h1_adjusted, h4_adjusted, massFlowRate, latentHeat_evap);
        double Qc_actual = RefrigerationCycle.calculateHeatRejected(h2_adjusted, h3_adjusted, massFlowRate, latentHeat_cond);
        double COP = RefrigerationCycle.calculateCOP(Qe_actual, Wc_actual);
        double h_evap = HeatTransfer.calculateHeatTransferCoefficient(L, D, rho, v);
        double h_cond = HeatTransfer.calculateHeatTransferCoefficient(L, D, rho, v);
        double area = Math.PI * D * L;
        double Q_evap = HeatTransfer.calculateHeatTransferRate(h_evap, area, T_evap - T_cond);
        double Q_cond = HeatTransfer.calculateHeatTransferRate(h_cond, area, T_cond - T_evap);
        double P_v = 0.8;
        double humidityRatio = HumidityCalculator.calculateHumidityRatio(P_v, P_evap);
        double dewPoint = HumidityCalculator.calculateDewPoint(T_evap, 60);
        double wetBulbTemperature = HumidityCalculator.calculateWetBulbTemperature(T_evap, dewPoint);
        System.out.println("Mechanical Refrigeration Cycle");
        System.out.println("------------------------------");
        System.out.printf("Heat Pump Mode: %s\n", (mode == 1 ? "Cooling" : "Heating"));
        System.out.println("\nState 1 (Evaporator Inlet - Saturated Liquid):");
        System.out.printf("  Temperature = %.2f°C\n", T_evap);
        System.out.printf("  Pressure    = %.2f bar\n", P_evap);
        System.out.printf("  Enthalpy    = %.2f kJ/kg\n", h1_liquid);
        System.out.printf("  Cp = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_evap, P_evap, true));
        System.out.printf("  Cv = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_evap, P_evap, false));
        System.out.println("\nState 2 (Compressor Outlet - Saturated Vapor):");
        System.out.printf("  Temperature = %.2f°C\n", T_cond);
        System.out.printf("  Pressure    = %.2f bar\n", P_cond);
        System.out.printf("  Enthalpy    = %.2f kJ/kg\n", h2_vapor);
        System.out.printf("  Cp = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_cond, P_cond, true));
        System.out.printf("  Cv = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_cond, P_cond, false));
        System.out.println("\nState 3 (Condenser Outlet - Saturated Liquid):");
        System.out.printf("  Temperature = %.2f°C\n", T_cond);
        System.out.printf("  Pressure    = %.2f bar\n", P_cond);
        System.out.printf("  Enthalpy    = %.2f kJ/kg\n", h3_liquid);
        System.out.printf("  Cp = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_cond, P_cond, true));
        System.out.printf("  Cv = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_cond, P_cond, false));
        System.out.println("\nState 4 (Expansion Valve Outlet - Saturated Vapor):");
        System.out.printf("  Temperature = %.2f°C\n", T_evap);
        System.out.printf("  Pressure    = %.2f bar\n", P_evap);
        System.out.printf("  Enthalpy    = %.2f kJ/kg\n", h4_vapor);
        System.out.printf("  Cp = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_evap, P_evap, true));
        System.out.printf("  Cv = %.2f kJ/(kg·K)\n", HeatPump.getSpecificHeatCapacity(T_evap, P_evap, false));
        System.out.println("\nPressure Drops:");
        System.out.printf("  Evaporator Pressure Drop   = %.2f kPa\n", deltaP_e);
        System.out.printf("  Condenser Pressure Drop    = %.2f kPa\n", deltaP_c);
        System.out.printf("  Expansion Valve Pressure Drop = %.2f kPa\n", deltaP_v);
        System.out.println("\nAdjusted Enthalpies:");
        System.out.printf("  State 1 (Adjusted)         = %.2f kJ/kg\n", h1_adjusted);
        System.out.printf("  State 2 (Adjusted)         = %.2f kJ/kg\n", h2_adjusted);
        System.out.printf("  State 3 (Adjusted)         = %.2f kJ/kg\n", h3_adjusted);
        System.out.printf("  State 4 (Adjusted)         = %.2f kJ/kg\n", h4_adjusted);
        System.out.println("\nHeat Transfer Coefficients:");
        System.out.printf("  Evaporator Heat Transfer Coefficient (h_evap) = %.2f W/(m²·K)\n", h_evap);
        System.out.printf("  Condenser Heat Transfer Coefficient (h_cond) = %.2f W/(m²·K)\n", h_cond);
        System.out.println("\nHeat Transfer Rates:");
        System.out.printf("  Heat Transfer Rate in Evaporator (Q_evap) = %.2f kJ/s\n", Q_evap / 1000);
        System.out.printf("  Heat Transfer Rate in Condenser (Q_cond) = %.2f kJ/s\n", Q_cond / 1000);
        System.out.printf("\nIdeal work done by compressor (Wc_ideal): %.2f kJ/s\n", Wc_ideal);
        System.out.printf("Actual work done by compressor (Wc_actual): %.2f kJ/s\n", Wc_actual);
        System.out.printf("Second-law efficiency of compressor: %.2f\n", eta_c);
        System.out.printf("Ideal work done by turbine (Wt_ideal): %.2f kJ/s\n", Wt_ideal);
        System.out.printf("Second-law efficiency of turbine: %.2f\n", eta_t);
        System.out.printf("Heat rejected in condenser (Qc): %.2f kJ/s\n", Qc_actual);
        System.out.printf("Heat absorbed in evaporator (Qe): %.2f kJ/s\n", Qe_actual);
        System.out.printf("Work done by expansion valve (We): %.2f kJ/s\n", We);
        System.out.printf("Coefficient of Performance (COP): %.2f\n", COP);
        System.out.println("\nHumidity Calculations:");
        System.out.printf("  Humidity Ratio (ω): %.2f kg/kg\n", humidityRatio);
        System.out.printf("  Dew Point Temperature (T_dp): %.2f°C\n", dewPoint);
        System.out.printf("  Wet Bulb Temperature (T_wb): %.2f°C\n", wetBulbTemperature);
    }
}
