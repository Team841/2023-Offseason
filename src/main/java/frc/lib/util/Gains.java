/**
 *  Class that organizes gains used when assigning values to slots
 */
package frc.lib.util;

import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

/**
 * Note: Falcon Gains
 */
public class Gains {

    private SlotConfiguration config = new SlotConfiguration();
    private double MMMA = 0;
    private double MMMV = 0;
    private double SCurveStrength = 0;

    public Gains(){}
 
    public Gains(double _kP, double _kI, double _kD){
        this.config.kP = _kP;
        this.config.kI = _kI;
        this.config.kD = _kD;
    }

    public Gains(double _kP, double _kI, double _kD, double _kf, double _kIzone){
        this.config.kP = _kP;
        this.config.kI = _kI;
        this.config.kD = _kD;
        this.config.kF = _kf;
        this.config.integralZone = _kIzone;
    }


    /**
     * 
     * @param _kP
     * @param _kI
     * @param _kD
     * @param _kf
     * @param _kIzone
     * @param _MMMA
     * @param _MMMV
     * @param _SCurveStrength
     */
    public Gains(double _kP, double _kI, double _kD, double _kf, double _kIzone, double _MMMA, double _MMMV, int _SCurveStrength){
        this.config.kP = _kP;
        this.config.kI = _kI;
        this.config.kD = _kD;
        this.config.kF = _kf;
        this.config.integralZone = _kIzone;
        this.MMMA = _MMMA;
        this.MMMV = _MMMV;
    }

    public void configMM(TalonFX talon){
        talon.configMotionAcceleration((int) MMMA);
        talon.configMotionCruiseVelocity((int) MMMV);
    }

    public SlotConfiguration getSlotConfiguration(){
        return config;
    }

    public double getMMMA(){
        return MMMA;
    }

    public double getMMMV(){
        return MMMV;
    }

    public int getSCurveStrength() {
        return (int) SCurveStrength;
    }

    
}