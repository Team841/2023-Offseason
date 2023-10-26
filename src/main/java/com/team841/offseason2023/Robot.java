package com.team841.offseason2023;

import com.team841.offseason2023.Constants.Constants;
import com.team841.offseason2023.Constants.SC;
import com.team841.offseason2023.Constants.SubsystemManifest;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();

    SC.stateManager.addPreset(SC.PresetPositions.HomePreset);
    SC.stateManager.addPreset(SC.PresetPositions.IntermediatePreset);
    SC.stateManager.addPreset(SC.PresetPositions.PreExtractInPreset);
    SC.stateManager.addPreset(SC.PresetPositions.MidScoreCubePreset);
    SC.stateManager.addPreset(SC.PresetPositions.HighScoreCubePreset);
    SC.stateManager.addPreset(SC.PresetPositions.HighScoreConePreset);
    SC.stateManager.addPreset(SC.PresetPositions.MidScoreConePreset);
    SC.stateManager.addPreset(SC.PresetPositions.GroundPreset);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    SC.stateManager.updateState(
        SubsystemManifest.superstructure.getJointAngles()[0],
        SubsystemManifest.superstructure.getJointAngles()[1]);
  }

  @Override
  public void disabledInit() {
    Constants.isDisabled = true;
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {

    Constants.isDisabled = false;

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {

    Constants.isDisabled = false;

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
