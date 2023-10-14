package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Constants;

public class tunableNumber {
    
    public static final String tabKey = "Tunable Numbers";

    private final String key;
    private double defaultValue;
    private double oldValue = defaultValue;
    
    public tunableNumber(String DashboardKey){
        this.key = tabKey + "/" + DashboardKey;
    }

    public tunableNumber(String DashboardKey, double defaultValue){
        this(DashboardKey);
        setDefault(defaultValue);
    }

    public void setDefault(double defaultValue){
        this.defaultValue = defaultValue;
        if (Constants.inTune){
            SmartDashboard.putNumber(key, defaultValue);
        } else {
            SmartDashboard.clearPersistent(key);
        }
    }

    public double get(){
        return 0; //C.inTune ? SmartDashboard.getNumber(key, defaultValue) : defaultValue;
    }

    public boolean hasChanged() {
        double currentValue = get();
        if (currentValue != oldValue) {
            oldValue = currentValue;
            return true;
        }
        return false;
    }

}
