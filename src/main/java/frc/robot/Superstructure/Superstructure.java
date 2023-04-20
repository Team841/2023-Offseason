package frc.robot.Superstructure;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SC;
import frc.lib.util.BioFalcon;
import frc.robot.Constants.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.google.common.collect.RangeMap;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Superstructure extends SubsystemBase {

  private CommandScheduler scheduler = CommandScheduler.getInstance();

  public final BioFalcon shoulderMotor_starboard = new BioFalcon(Constants.CANid.shoulderMotor_Starboard, false, false, SC.Shoulder.setupGains, SC.kPIDLoopIdx, SC.kTimeoutMs, SC.fxOutputs, true);
  public final BioFalcon shoulderMotor_port = new BioFalcon(Constants.CANid.shoulderMotor_Port, false, true, SC.Shoulder.setupGains, SC.kPIDLoopIdx, SC.kTimeoutMs, SC.fxOutputs, true);
  public final BioFalcon elbowMotor = new BioFalcon(Constants.CANid.elbowMotor, false, false, SC.Elbow.setupGains, SC.kPIDLoopIdx, SC.kTimeoutMs, SC.fxOutputs, true);

  private final TalonFX IntakeMotor = new TalonFX(Constants.CANid.IntakeTalon); 

  DigitalInput ElbowIndexSensor = new DigitalInput(SC.Elbow.IndexChannel);
  DigitalInput ShoulderIndexSensor = new DigitalInput(SC.Shoulder.IndexChannel);

  enum GamePiece{
    Cone,
    Cube,
    Empty
  }

  private States state = States.Home;
  private States input = States.Nothing;

  public boolean isInPreset = false;

  public SC.PresetPositions rangePreset = new SC.PresetPositions();

  public Superstructure() {

    shoulderMotor_port.follow(shoulderMotor_starboard);

    IntakeMotor.configFactoryDefault();
    IntakeMotor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    IntakeMotor.setNeutralMode(NeutralMode.Brake);

    rangePreset.applyRange();
  }

  @Override
  public void periodic() {

    inTolerance(getJointAngles());

    if (state == States.Home){
      checkSensors();
    }

    if (input != States.Nothing){
      move();
    }
    
  }

  ///////////////////////////////// Commands ///////////////////////////////

  public void setInput(States state){
    this.input = state;
  }

  public void move(){
    if (input != States.Home && isInPreset){
      switch (input){
        case Ground -> {
          if (input != States.TopScoreCone && input != States.TopScoreCube){
            scheduler.schedule(moveGround());
          } else {
            scheduler.schedule(moveIntermediateAndPreExtractIn(), moveGround());
          }
        } 

        default -> {
          break;
        }
      }
    }
  }

  public CommandBase moveGround(){
    return this.run(() -> 
    setJointAngles(SC.PresetPositions.Intermediate))
    .andThen(() -> setJointAngles(SC.PresetPositions.Ground))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveMidScoreCube(){
    return this.run(() -> 
    setJointAngles(SC.PresetPositions.Intermediate))
    .andThen(() -> setJointAngles(SC.PresetPositions.MidScoreCube))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveMidScoreCone(){
    return this.run(() -> 
    setJointAngles(SC.PresetPositions.Intermediate))
    .andThen(() -> setJointAngles(SC.PresetPositions.MidScoreCone))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHighScoreCube(){
    return this.run(() -> 
    setJointAngles(SC.PresetPositions.Intermediate))
    .andThen(() -> setJointAngles(SC.PresetPositions.HighScoreCube))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHighScoreCone(){
    return this.run(() ->
    setJointAngles(SC.PresetPositions.Intermediate))
    .andThen(() -> setJointAngles(SC.PresetPositions.HighScoreCone))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHome(){
    return this.run(() ->
    setJointAngles(SC.PresetPositions.Intermediate))
    .andThen(() -> setJointAngles(SC.PresetPositions.Home))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveIntermediateAndPreExtractIn(){
    return this.run(() ->
    setJointAngles(SC.PresetPositions.PreExtractIn))
    .andThen(() -> setJointAngles(SC.PresetPositions.Intermediate))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveIntermediate(){
    return this.run(() ->
    setJointAngles(SC.PresetPositions.Intermediate))
    .handleInterrupt(() -> stopJoints())
    .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }


  ///////////////////////////////// End Commands ///////////////////////////////

  /////////////////////////////// Control Methods ///////////////////////////////

  public void setJointAngles(double[] angles){
    shoulderMotor_starboard.set(TalonFXControlMode.Position, angleToCounts(angles[0], SC.Shoulder.GearRatio));
    elbowMotor.set(TalonFXControlMode.Position, angleToCounts(angles[1], SC.Elbow.GearRatio));
  }

  public void stopJoints(){
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

  public boolean isShoulderAtPosition(double angle){
    return (Math.abs(countsToAngle(shoulderMotor_starboard.getSelectedSensorPosition(), SC.Shoulder.GearRatio) - angle) <= Math.abs(SC.Shoulder.tolerance));
  }

  public boolean isElbowAtPosition(double angle){
    return (Math.abs(countsToAngle(elbowMotor.getSelectedSensorPosition(), SC.Elbow.GearRatio) - angle) <= Math.abs(SC.Elbow.tolerance));
  }

  public boolean isAtPosition(double[] angles){
    SmartDashboard.putBoolean("shoulder in Position?",isShoulderAtPosition(angles[0]));
    SmartDashboard.putBoolean("elbow in Position?", isElbowAtPosition(angles[1]));
    return isShoulderAtPosition(angles[0]) & isElbowAtPosition(angles[1]);
  }

  public boolean getElbowIndexSensor(){
    return !ElbowIndexSensor.get();
  }

  public boolean getShoulderIndexSensor(){
    return !ShoulderIndexSensor.get();
  }

  public double[] getJointAngles(){
    double[] angles = new double[2];
    angles[0] = countsToAngle(shoulderMotor_starboard.getSelectedSensorPosition(), SC.Shoulder.GearRatio);
    angles[1] = countsToAngle(elbowMotor.getSelectedSensorPosition(), SC.Elbow.GearRatio);
    return angles;
  }

  public States getState(){
    return this.state;
  }

  /////////////////////////////// End Getters ///////////////////////////////

  /////////////////////////////// Periodic  ///////////////////////////////

  public void checkSensors(){
    if(getElbowIndexSensor()){
      elbowMotor.setSelectedSensorPosition(0);
      elbowMotor.setNeutralMode(NeutralMode.Brake);
    }

    if(getShoulderIndexSensor()){
      shoulderMotor_starboard.setSelectedSensorPosition(0);
      shoulderMotor_starboard.setNeutralMode(NeutralMode.Brake);
      shoulderMotor_port.setNeutralMode(NeutralMode.Brake);
    }
  }

  public void inTolerance(double[] angles){
    if (SC.PresetPositions.elbowRange.get(angles[0]) == null || SC.PresetPositions.shoulderRange.get(angles[1]) == null){
      isInPreset = false; return;
    } 
    isInPreset = true; return;
  }

  /////////////////////////////// End Periodic  ///////////////////////////////
}
