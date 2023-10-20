package com.team841.offseason2023.Auto.PIDControllers;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team841.offseason2023.Constants.Drive;
import com.team841.offseason2023.Drive.Drivetrain;

public class AutoTurn extends CommandBase {
  
  /** Creates a new AutoTurn. */
  private Drivetrain m_subsystem ;
  private double m_intial_angle = 0;
  private double m_sub_goal_angle = 0;
  private double m_goal_angle = 0;

  public AutoTurn(Drivetrain subsytem, double goal_angle) {
    m_subsystem = subsytem; 
    m_sub_goal_angle = goal_angle % 360; 
    if(goal_angle <= 0){
      m_sub_goal_angle *= -1;
    }
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_sub_goal_angle >= 180){
      m_goal_angle = -1*(360 - m_sub_goal_angle);
    } else if(m_goal_angle <= -180){
      m_goal_angle =  360 - Math.abs(m_sub_goal_angle);
    }
    else {
      m_goal_angle = m_sub_goal_angle;
    }
    m_subsystem.setLeftRight(0, 0);
    m_subsystem.turnpid.reset(); // reset any pid variables from last time used
    m_subsystem.resetIMU();
    m_intial_angle = m_subsystem.getYaw();  // get the current starting angle
    m_subsystem.turnpid.setP(Drive.Drivetrain.turn_kp);
    m_subsystem.turnpid.setI(Drive.Drivetrain.turn_ki);
    m_subsystem.turnpid.setD(Drive.Drivetrain.turn_kd);
    m_subsystem.turnpid.setIntegratorRange(-Drive.Drivetrain.antiWindUp, Drive.Drivetrain.antiWindUp);
    m_subsystem.turnpid.setSetpoint(m_intial_angle+m_goal_angle); // set the goal of the angle
    SmartDashboard.putNumber("goal angle", m_intial_angle+m_goal_angle);
    m_subsystem.turnpid.setTolerance(Drive.Drivetrain.turn_tolerance, Drive.Drivetrain.turn_velocity_tolerance); // set the goal tolerance to know when you're finished
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double c_angle = m_subsystem.getYaw();  // Get the current sample, changed becuase the robot has turned
    double output_PID = m_subsystem.turnpid.calculate(c_angle); // computes the motor power
    SmartDashboard.putNumber("pid output", output_PID);
    SmartDashboard.putNumber("error", m_subsystem.turnpid.getPositionError()); 
    m_subsystem.setLeftRight(output_PID, -output_PID); // to the right, to the right, to the right
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setLeftRight(0, 0); // Stops all motors just in case
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    SmartDashboard.putNumber("crobot angle", m_subsystem.getYaw());
    return m_subsystem.turnpid.atSetpoint(); // Are we there yet???? if so end, or else run execute again!
  }
}
