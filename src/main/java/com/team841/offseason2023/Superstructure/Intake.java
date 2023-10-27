package com.team841.offseason2023.Superstructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team841.offseason2023.Constants.Constants;
import com.team841.offseason2023.Constants.SC;
import com.team841.offseason2023.Constants.SubsystemManifest;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;
import com.team841.offseason2023.states.States;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private final TalonFX IntakeMotor = new TalonFX(Constants.CANid.IntakeTalon);

  private final SuperstructureFactoryBeta factory = SubsystemManifest.factory;

  private Double thresh = 0.0;

  private boolean notTransition = true;

  private Double timer = Double.NaN;

  // public GamePiece gamePiece = GamePiece.Empty;

  public Intake() {

    IntakeMotor.configFactoryDefault();
    IntakeMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    IntakeMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void toggleIntakeIn() { // Cone in
    if (IntakeMotor.getMotorOutputPercent() == 0) {
      this.IntakeMotor.set(ControlMode.PercentOutput, SC.Intake.teleopPower);
      this.thresh = SC.Intake.ConeCThresh;
      this.timer = Double.NaN;
    } else if (IntakeMotor.getMotorOutputPercent() < 0) {
      this.IntakeMotor.set(ControlMode.PercentOutput, SC.Intake.teleopPower);
      this.thresh = SC.Intake.ConeCThresh;
      this.timer = 0.0;
      this.notTransition = false;
    } else {
      IntakeMotor.set(ControlMode.PercentOutput, 0);
      this.thresh = 0.0;
      this.timer = Double.NaN;
    }
  }

  public void toggleIntakeOut() { // cone out
    if (IntakeMotor.getMotorOutputPercent() == 0) {
      this.IntakeMotor.set(ControlMode.PercentOutput, -SC.Intake.teleopPower);
      this.thresh = SC.Intake.CubeCThresh;
      this.timer = Double.NaN;
    } else if (IntakeMotor.getMotorOutputPercent() > 0) {
      this.IntakeMotor.set(ControlMode.PercentOutput, -SC.Intake.teleopPower);
      this.thresh = SC.Intake.ConeCThresh;
      this.timer = 0.0;
      this.notTransition = false;
    } else {
      this.IntakeMotor.set(ControlMode.PercentOutput, 0);
      this.thresh = 0.0;
      this.timer = Double.NaN;
    }
  }

  @Override
  public void periodic() {

    if (this.timer >= 0.0) {
      timer += 1.0;

      if (timer >= 20.0) {
        notTransition = true;
        timer = Double.NaN;
        return;
      }
    }

    if (SC.superstructureState == States.Ground
        && notTransition
        && IntakeMotor.getSupplyCurrent() > thresh) {
      factory.moveHome().schedule();
    }
  }

  public GamePiece pickedUp() {

    if (IntakeMotor.getSupplyCurrent() < thresh) {
      return GamePiece.Empty;
    } else {
      return this.thresh == SC.Intake.CubeCThresh ? GamePiece.Cube : GamePiece.Cone;
    }
  }
}
