package com.team841.offseason2023.Superstructure;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.team841.offseason2023.Constants.Constants;
import com.team841.offseason2023.Constants.SC;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;
import com.team841.offseason2023.states.States;
import com.team841.offseason2023.util.BioFalcon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Superstructure extends SubsystemBase {

  CommandScheduler scheduler = CommandScheduler.getInstance();

  public final BioFalcon shoulderMotor_starboard =
      new BioFalcon(
          Constants.CANid.shoulderMotor_Starboard,
          false,
          false,
          SC.Shoulder.setupGains,
          SC.kPIDLoopIdx,
          SC.kTimeoutMs,
          SC.fxOutputs,
          true);
  public final BioFalcon shoulderMotor_port =
      new BioFalcon(
          Constants.CANid.shoulderMotor_Port,
          false,
          true,
          SC.Shoulder.setupGains,
          SC.kPIDLoopIdx,
          SC.kTimeoutMs,
          SC.fxOutputs,
          true);
  public final BioFalcon elbowMotor =
      new BioFalcon(
          Constants.CANid.elbowMotor,
          false,
          false,
          SC.Elbow.setupGains,
          SC.kPIDLoopIdx,
          SC.kTimeoutMs,
          SC.fxOutputs,
          true);

  DigitalInput ElbowIndexSensor = new DigitalInput(SC.Elbow.IndexChannel);
  DigitalInput ShoulderIndexSensor = new DigitalInput(SC.Shoulder.IndexChannel);

  private States state = States.Home;

  public boolean canMove = false;

  public SC.PresetPositions rangePreset = new SC.PresetPositions();

  public Superstructure() {
    shoulderMotor_port.follow(shoulderMotor_starboard);
  }

  @Override
  public void periodic() {

    this.state = SC.superstructureState;

    if (this.state == States.Home || Constants.isDisabled) {
      if (getElbowIndexSensor()) {
        elbowMotor.setSelectedSensorPosition(0);
        elbowMotor.setNeutralMode(NeutralMode.Brake);
      }

      if (getShoulderIndexSensor()) {
        shoulderMotor_starboard.setSelectedSensorPosition(0);
        shoulderMotor_starboard.setNeutralMode(NeutralMode.Brake);
        shoulderMotor_port.setNeutralMode(NeutralMode.Brake);
      }
    }

    updateShuffleBoard();
    // checkPickedUp();
    // SC.superstructureState = this.state;
    // SC.superstructureState = this.state == States.Ground ? States.Ground : States.Nothing;
    // if (this.state == States.Ground){
    //    SC.superstructureState = States.Ground;
    // }
  }

  ///////////////////////////////// Commands ///////////////////////////////

  public void setInput(States state) {
    this.state = state;
  }

  ///////////////////////////////// End Commands ///////////////////////////////

  /////////////////////////////// Control Methods ///////////////////////////////

  public void moveJointAngles(double[] angles) {
    shoulderMotor_starboard.set(
        TalonFXControlMode.Position, angleToCounts(angles[0], SC.Shoulder.GearRatio));
    elbowMotor.set(TalonFXControlMode.Position, angleToCounts(angles[1], SC.Elbow.GearRatio));
  }

  public void stopJoints() {
    shoulderMotor_starboard.set(TalonFXControlMode.PercentOutput, 0);
    elbowMotor.set(TalonFXControlMode.PercentOutput, 0);
  }

  public void armSetBrakeMode(boolean brakeMode) {
    if (brakeMode) {
      shoulderMotor_starboard.setNeutralMode(NeutralMode.Brake);
      shoulderMotor_port.setNeutralMode(NeutralMode.Brake);
      elbowMotor.setNeutralMode(NeutralMode.Brake);
    } else {
      shoulderMotor_starboard.setNeutralMode(NeutralMode.Coast);
      shoulderMotor_port.setNeutralMode(NeutralMode.Coast);
      elbowMotor.setNeutralMode(NeutralMode.Coast);
    }
  }

  public void moveShoulder(double speed) {
    shoulderMotor_starboard.set(ControlMode.PercentOutput, speed);
  }

  public void moveElbow(double speed) {
    elbowMotor.set(ControlMode.PercentOutput, speed);
  }

  /////////////////////////////// End Control Methods ///////////////////////////////

  /////////////////////////////// Math ///////////////////////////////

  public double angleToCounts(double angle, double gearRatio) {
    return (angle / 360.0 * 2048) / gearRatio;
  }

  public double countsToAngle(double counts, double gearRatio) {
    return counts * gearRatio / 2048 * 360.0; // flipped from angletocounts
  }

  /////////////////////////////// End Math ///////////////////////////////

  /////////////////////////////// Getters ///////////////////////////////

  public boolean isShoulderAtPosition(double angle) {
    return (Math.abs(
            countsToAngle(
                    shoulderMotor_starboard.getSelectedSensorPosition(), SC.Shoulder.GearRatio)
                - angle)
        <= Math.abs(SC.Shoulder.tolerance));
  }

  public boolean isElbowAtPosition(double angle) {
    return (Math.abs(
            countsToAngle(elbowMotor.getSelectedSensorPosition(), SC.Elbow.GearRatio) - angle)
        <= Math.abs(SC.Elbow.tolerance));
  }

  public boolean isAtPosition(double[] angles) {
    SmartDashboard.putBoolean("shoulder in Position?", isShoulderAtPosition(angles[0]));
    SmartDashboard.putBoolean("elbow in Position?", isElbowAtPosition(angles[1]));
    return isShoulderAtPosition(angles[0]) & isElbowAtPosition(angles[1]);
  }

  public boolean getElbowIndexSensor() {
    return !ElbowIndexSensor.get();
  }

  public boolean getShoulderIndexSensor() {
    return !ShoulderIndexSensor.get();
  }

  public double[] getJointAngles() {
    double[] angles = new double[2];
    angles[0] =
        countsToAngle(shoulderMotor_starboard.getSelectedSensorPosition(), SC.Shoulder.GearRatio);
    angles[1] = countsToAngle(elbowMotor.getSelectedSensorPosition(), SC.Elbow.GearRatio);
    return angles;
  }

  public States getState() {
    return this.state;
  }

  /////////////////////////////// End Getters ///////////////////////////////

  /////////////////////////////// Periodic  ///////////////////////////////

  public void updateShuffleBoard() {

    SmartDashboard.putNumber(
        "Shoulder Postion",
        countsToAngle(shoulderMotor_starboard.getSelectedSensorPosition(), SC.Shoulder.GearRatio));
    SmartDashboard.putNumber(
        "Elbow Postion",
        countsToAngle(elbowMotor.getSelectedSensorPosition(), SC.Shoulder.GearRatio));

    SmartDashboard.putBoolean("Shoulder Index", getShoulderIndexSensor());
    SmartDashboard.putBoolean("Elbow Index", getElbowIndexSensor());

    SmartDashboard.putString("State", this.state.toString());
    SmartDashboard.putString("Sc.State", SC.superstructureState.toString());

    SmartDashboard.putBoolean("Ready to move to new state", canMove);

    SmartDashboard.putBoolean("Disabled", Constants.isDisabled);

    // SmartDashboard.putString("Shoulderstate",
    // SC.PresetPositions.shoulderRange.get(getJointAngles()[0]).toString());
    // SmartDashboard.putString("Elbow State",
    // SC.PresetPositions.elbowRange.get(getJointAngles()[1]).toString());
  }

  public void checkPickedUp() {
    if (SC.superstructureState == States.Home) {
      scheduler.schedule(new SuperstructureFactoryBeta(this).moveHome());
    }
  }

  /////////////////////////////// End Periodic  ///////////////////////////////
}
