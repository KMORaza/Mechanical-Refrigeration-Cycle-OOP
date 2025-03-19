## Mechanical Refrigeration Cycle designed using Object Oriented Principles

The design models the mechanical refrigeration cycle with inputs that are typical for an HVAC or heat pump system. The code uses the principles of thermodynamics to calculate and output relevant parameters of the cycle such as:
- **Enthalpy at key states**: Evaporator inlet (state 1), compressor outlet (state 2), condenser outlet (state 3), and expansion valve outlet (state 4).
- **Work and efficiency calculations**: The work done by the compressor and turbine, both ideal and actual, accounting for efficiencies.
- **Heat transfer rates**: Heat absorbed by the evaporator and rejected by the condenser, as well as heat transfer coefficients.
- **Humidity calculations**: Dew point and wet bulb temperature, important for understanding the air properties in the system.

### Java classes & their tasks:

* **Main.java**: The main driver of the refrigeration cycle simulation. It calculates the thermodynamic properties, work, and heat transfer rates and prints the results.
* **HeatPump.java**: Contains methods for retrieving enthalpy, specific heat capacity, and latent heat values for the refrigerant at given temperatures and pressures.
* **HeatTransfer.java**: Provides the calculations for heat transfer coefficients and rates using established correlations for fluid flow inside pipes.
* **HumidityCalculator.java**: Calculates humidity-related properties like the humidity ratio, dew point, and wet bulb temperature. These are important for understanding the moisture content and heat transfer performance.
* **RefrigerationCycle.java**: The core class for performing calculations such as work done, heat absorbed/rejected, and adjusting for pressure drops. It calculates the efficiency of the system and handles pressure drop calculations in the cycle.

---

### Main.java
The `Main.java` file is where the refrigeration cycle is initialized, and its thermodynamic properties are calculated. Here’s how it works:

- **Evaporator and Condenser Parameters**: 
    - The key temperatures and pressures for both the evaporator and condenser are defined (`T_evap`, `T_cond`, `P_evap`, `P_cond`). These values are critical for calculating the refrigerant’s enthalpy and specific heat capacity.
    - For example, at the evaporator inlet (state 1), the refrigerant is a saturated liquid, and at the condenser outlet (state 3), it is a saturated liquid again, but at a higher pressure and temperature.

- **Enthalpy Calculations**:
    - The enthalpy at each state is determined using `HeatPump` methods, which return enthalpies for the saturated liquid and vapor at the specified temperatures and pressures. 
    - **Saturated liquid enthalpy (h1)** and **saturated vapor enthalpy (h2)** are essential for calculating the heat absorbed and rejected.

- **Work Calculations**:
    - The compressor and turbine are modeled using ideal and actual work calculations. The ideal work done by the compressor and turbine (`Wc_ideal`, `Wt_ideal`) are based on the difference in enthalpy across the components.
    - Actual work calculations are adjusted based on the efficiencies of the compressor (`eta_c`) and turbine (`eta_t`), as real-world machines are less efficient than the ideal model.

- **Heat Transfer Rates**:
    - The heat transfer rates in the evaporator and condenser (`Qe_actual`, `Qc_actual`) are calculated using the enthalpy differences and latent heat values. This shows how much heat is absorbed from the environment (in the evaporator) and rejected to the environment (in the condenser).

- **Pressure Drops**:
    - The pressure drops across the evaporator and condenser are calculated using the Darcy-Weisbach equation, which accounts for fluid dynamics in pipes. This is done using the `RefrigerationCycle.calculatePressureDrop()` method.
    - Pressure drop in the expansion valve is also calculated to understand the real impact on refrigerant enthalpy.

- **Coefficient of Performance (COP)**:
    - The efficiency of the refrigeration cycle is quantified using COP, which is the ratio of the heat absorbed (Qe_actual) to the work done by the compressor (Wc_actual). A higher COP indicates a more efficient refrigeration system.

- **Humidity Calculations**:
    - The system also considers the moisture content in the air using `HumidityCalculator`. This includes calculating the humidity ratio, dew point, and wet bulb temperature. These values are important for understanding the air handling properties and heat exchange in HVAC applications.

---

### **HeatPump.java**
The `HeatPump.java` class provides methods to retrieve key thermodynamic properties of the refrigerant, primarily focused on enthalpy and specific heat:

- **Enthalpy**: The enthalpy values for the saturated liquid and vapor states are given at specific conditions (temperature and pressure). These values are crucial for the calculations of work and heat transfer.

- **Specific Heat**: Specific heat capacities at constant pressure (Cp) and constant volume (Cv) are used to determine the amount of energy required to change the temperature of the refrigerant. This is important for calculating energy balances and heat transfer rates.

---

### **HeatTransfer.java**
This class handles the calculation of heat transfer properties:

- **Heat Transfer Coefficient**: The heat transfer coefficient is determined using the Dittus-Boelter equation for turbulent flow inside pipes. This coefficient is essential for determining how efficiently heat is transferred between the refrigerant and the surrounding environment.

- **Heat Transfer Rate**: The rate at which heat is transferred is calculated using the standard formula:
  \[
  Q = h \cdot A \cdot \Delta T
  \]
  Where:
  - `h` is the heat transfer coefficient.
  - `A` is the surface area for heat exchange.
  - `ΔT` is the temperature difference between the fluid and the surrounding medium.

---

### **HumidityCalculator.java**
Humidity plays a significant role in HVAC and refrigeration cycles, especially in systems where air handling is involved:

- **Humidity Ratio**: The mass of water vapor per unit mass of dry air is important for understanding moisture content in the system.
  
- **Dew Point and Wet Bulb Temperature**: The dew point is the temperature at which air becomes saturated with water vapor, and condensation begins. The wet bulb temperature is crucial for assessing the system’s performance in humid conditions.

---

### **RefrigerationCycle.java**
This class implements several important thermodynamic calculations and adjusts the cycle for real-world factors:

- **Work Done**: Calculates the work done by the compressor and turbine, including actual work that accounts for system inefficiencies.
  
- **Heat Absorbed and Rejected**: These calculations provide insight into how much energy is absorbed by the evaporator (cooling the space) and rejected by the condenser (releasing heat into the environment).
  
- **Pressure Drop Calculations**: Pressure losses are accounted for in the evaporator, condenser, and expansion valve, affecting the performance of the refrigeration cycle.
  
- **COP**: The coefficient of performance is calculated by dividing the actual heat absorbed by the actual work input to the compressor. This is a key indicator of the system’s efficiency.

---

## Example Calculation

Given the following inputs:

- **Evaporator**:
  - Temperature = 5°C
  - Pressure = 1.2 bar
  - Saturated liquid enthalpy = 250 kJ/kg
  - Saturated vapor enthalpy = 450 kJ/kg
  
- **Compressor Efficiency**: 85%
- **Turbine Efficiency**: 90%

The program calculates the work done by the compressor, the work done by the turbine, the heat transfer rates in the evaporator and condenser, and the COP. For example:

- The **work done by the compressor** will depend on the difference in enthalpy between the vapor state at the compressor inlet and the saturated vapor at the outlet, considering the efficiency.
- The **heat rejected by the condenser** is calculated based on the enthalpy differences and the refrigerant flow rate.

---

## Usage

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/mechanical-refrigeration-cycle.git
