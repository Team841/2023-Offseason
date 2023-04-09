package frc.robot.Superstructure;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class setJointAngles extends CommandBase {

  private Superstructure superstructure;
  private double[] goalAngles;

  public setJointAngles(Superstructure _Superstructure, double shoulderAngle, double elbowAngle) {
    this.superstructure = _Superstructure;
    this.goalAngles = new double[]{shoulderAngle, elbowAngle};
    addRequirements(_Superstructure);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    superstructure.setJointAngles(this.goalAngles);
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
