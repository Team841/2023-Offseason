package frc.robot.Superstructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Constants;
import frc.robot.Constants.SC;

public class Intake extends SubsystemBase {

  private final TalonFX IntakeMotor = new TalonFX(Constants.CANid.IntakeTalon); 

  public Intake() {
    
    IntakeMotor.configFactoryDefault();
    IntakeMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    IntakeMotor.setNeutralMode(NeutralMode.Brake);
    
  }
  
  public void toggleIntakeIn() {
    if(IntakeMotor.getMotorOutputPercent()==0){
      IntakeMotor.set(ControlMode.PercentOutput, SC.Intake.teleopPower);
    } else {
      IntakeMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void toggleIntakeOut() {
    if(IntakeMotor.getMotorOutputPercent()==0){
      IntakeMotor.set(ControlMode.PercentOutput, -SC.Intake.teleopPower);
    } else {
      IntakeMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  @Override
  public void periodic() {
  }
}
