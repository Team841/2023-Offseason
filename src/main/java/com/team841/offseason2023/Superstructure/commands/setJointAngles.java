package com.team841.offseason2023.Superstructure.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team841.offseason2023.Superstructure.Superstructure;

public class setJointAngles extends CommandBase {

  private Superstructure superstructure;
  private double[] goalAngles;

  public setJointAngles(Superstructure _Superstructure, double[] _goalAngles) {
    this.superstructure = _Superstructure;
    // this.goalAngles = new double[]{shoulderAngle, elbowAngle};
    this.goalAngles = _goalAngles;
    addRequirements(_Superstructure);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    superstructure.moveJointAngles(this.goalAngles);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    superstructure.stopJoints();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return superstructure.isElbowAtPosition(this.goalAngles[1]) && superstructure.isShoulderAtPosition(this.goalAngles[0]);
  }
}
