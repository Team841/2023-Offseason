package frc.robot.util;

import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class BioFalcon extends TalonFX{

    /**
     * 
     * @param CANid
     * @param bandWidthCAN
     * @param _gains
     * @param kPIDLoopIdx
     * @param kTimeoutMs
     * @param outputs
     * @param sensorPhrase
     */
    public BioFalcon(int CANid, Boolean bandWidthCAN, Boolean Inverted, Gains[] _gains, int kPIDLoopIdx, int kTimeoutMs, double[/* {max,min}*/] outputs, boolean sensorPhrase) {
        super(CANid);

        this.configFactoryDefault();

        if (bandWidthCAN){
            bandWithLimitMotorCAN(this);
        }

        this.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40, 0, 0));

        this.configNominalOutputForward(outputs[0], kTimeoutMs);
        this.configNominalOutputReverse(outputs[1], kTimeoutMs);
        this.configPeakOutputForward(outputs[0], kTimeoutMs);
        this.configPeakOutputReverse(outputs[1], kTimeoutMs);


        this.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

        if (sensorPhrase){
            this.setSensorPhase(true);
        }

        this.configureSlot(_gains[0].getSlotConfiguration(), 0, kTimeoutMs);
        this.configureSlot(_gains[1].getSlotConfiguration(), 1, kTimeoutMs);
        this.configureSlot(_gains[2].getSlotConfiguration(), 2, kTimeoutMs);

        this.configMotionAcceleration(_gains[0].getMMMA());
        this.configMotionCruiseVelocity(_gains[0].getMMMV());
        this.configMotionSCurveStrength(_gains[0].getSCurveStrength());
        
        this.setInverted(Inverted);
        
    }

    /**
    * Bandwith config motor CAN for TalonFX Motor
    * @param motor
    */
    private void bandWithLimitMotorCAN(TalonFX motor) {
        // Maybe this is needed for motion magic. 

        //Set relevant frame periods to be at least as fast as periodic rate
        motor.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic,10); 
        motor.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0,10);
        motor.setStatusFramePeriod(StatusFrame.Status_9_MotProfBuffer,10);

        
        //Keep at default.
        //motor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 255); 
        //motor.setStatusFramePeriod(StatusFrame.Status_12_Feedback1,255);
        //motor.setStatusFramePeriod(StatusFrame.Status_1_General,40); 
        
        //slow it down
        motor.setStatusFramePeriod(StatusFrame.Status_4_AinTempVbat, 255);
        motor.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1,255);
        motor.setStatusFramePeriod(StatusFrame.Status_15_FirmwareApiStatus,255);
  }

}
