package frc.robot.Superstructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Constants;
import frc.robot.Constants.SC;
import frc.robot.Superstructure.GamePiece;
import frc.states.States;

public class Intake extends SubsystemBase {

  private final TalonFX IntakeMotor = new TalonFX(Constants.CANid.IntakeTalon);

  private Double thresh = 0.0;

  // public GamePiece gamePiece = GamePiece.Empty;

  public Intake() {
    
    IntakeMotor.configFactoryDefault();
    IntakeMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    IntakeMotor.setNeutralMode(NeutralMode.Brake);
    
  }
  
  public void toggleIntakeIn() {
    if(IntakeMotor.getMotorOutputPercent()==0){
      this.IntakeMotor.set(ControlMode.PercentOutput, SC.Intake.teleopPower);
      this.thresh = SC.Intake.ConeCThresh;
    } else {
      IntakeMotor.set(ControlMode.PercentOutput, 0);
      this.thresh = 0.0;
    }
  }

  public void toggleIntakeOut() {
    if(IntakeMotor.getMotorOutputPercent()==0){
      this.IntakeMotor.set(ControlMode.PercentOutput, -SC.Intake.teleopPower);
      this.thresh = SC.Intake.CubeCThresh;
    } else {
      this.IntakeMotor.set(ControlMode.PercentOutput, 0);
      this.thresh = 0.0;
    }
  }

  @Override
  public void periodic() {

    if (thresh >= 0.0 && SC.superstructureState == States.Ground && pickedUp() != GamePiece.Empty){
      SC.superstructureState = States.Home;
    }
  }

  public GamePiece pickedUp(){

    if (IntakeMotor.getSupplyCurrent() < thresh) {
      return GamePiece.Empty;
    } else {
      return this.thresh == SC.Intake.CubeCThresh ? GamePiece.Cube : GamePiece.Cone;
    }

  }
}
