package com.team841.offseason2023.Auto.PIDControllers;

import java.lang.Math;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team841.offseason2023.Drive.Drivetrain;
import com.team841.offseason2023.Constants.Drive;

public class AutoDriveToDistance extends CommandBase {
  
  /** Creates a new AutoDriveToDistance. */
  private Drivetrain m_subsystem;
  private double m_goal_distance;

  public AutoDriveToDistance(Drivetrain subsystem, double goal_distance) {
    m_subsystem = subsystem;
    m_goal_distance = goal_distance;
    addRequirements(m_subsystem);
  }
    
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setDistancePID(m_goal_distance);
    m_subsystem.resetEncoders();
    m_subsystem.enableDistancePID(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.enableDistancePID(false);
    m_subsystem.setLeftRight(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { 

    if(Math.abs(m_subsystem.getPIDdistanceError()) <= Drive.Drivetrain.distance_tolerance){
      return true;
    }
    return false;
  }
}
