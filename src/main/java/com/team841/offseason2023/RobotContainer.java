// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team841.offseason2023;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import com.team841.offseason2023.Drive.*;
import com.team841.offseason2023.Superstructure.*;
import com.team841.offseason2023.Constants.Constants;
import com.team841.offseason2023.Constants.SubsystemManifest;
import com.team841.offseason2023.Superstructure.factory.SuperstructureFactoryBeta;

public class RobotContainer {

  private final Drivetrain drivetrain = SubsystemManifest.drivetrain;
  private final Superstructure superstructure = SubsystemManifest.superstructure;
  private final SuperstructureFactoryBeta factory = SubsystemManifest.factory;
  private final Intake intake = SubsystemManifest.intake;

  private final CommandXboxController m_codriverCtrl = new CommandXboxController(Constants.OI.codriverPort);
  private final CommandPS4Controller m_driverCtrl = new CommandPS4Controller(Constants.OI.driverPortLeft);

  // Create the button commands

  // Driver
  final Trigger qT = m_driverCtrl.R1();
  final Trigger BrakeOn = m_driverCtrl.cross();
  final Trigger BrakeOff = m_driverCtrl.square();

  final Trigger ljakdsczvjbduebfjd = m_driverCtrl.circle();

  // CoDriver
  private final Trigger MoveHome = m_codriverCtrl.x();
  private final Trigger MoveGround = m_codriverCtrl.b();
  private final Trigger MoveMidScoreCube = m_codriverCtrl.rightBumper();
  private final Trigger MoveMidScoreCone = m_codriverCtrl.leftBumper();
  private final Trigger MoveTopScoreCube = m_codriverCtrl.rightTrigger();
  private final Trigger MoveTopScoreCone = m_codriverCtrl.leftTrigger();

  private final Trigger coneIntakeToggle = m_codriverCtrl.y();
  private final Trigger cubeIntakeToggle = m_codriverCtrl.a();

  public RobotContainer() {
    
    configureBindings();
    drivetrain.setDefaultCommand(
      new RunCommand(() -> drivetrain.Drive(m_driverCtrl.getRightX(), m_driverCtrl.getLeftY()),drivetrain));
  }
    
  private void configureBindings() {

    /* https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/Button.html */

    /* Driver Commands */
    //Quick turn
    qT.onTrue(new InstantCommand(drivetrain::setQuickTurn, drivetrain));  
    qT.onFalse(new InstantCommand(drivetrain::resetQuickTurn, drivetrain));
    /* final Trigger AutoBalance = m_driverCtrl.cross();
      AutoBalance.whileTrue(new AutoBalance(drivetrain));
    final Trigger AutoTurn = m_driverCtrl.square();
      AutoTurn.onTrue(new AutoTurn(drivetrain, 270));
    final Trigger AutoDistance = m_driverCtrl.circle();
      AutoDistance.onTrue(new AutoDriveToDistance(drivetrain, 48)); */
    // ljakdsczvjbduebfjd.onTrue(new AutoDriveToDistance(drivetrain, 48));
    BrakeOn.onTrue(new InstantCommand(drivetrain::BrakeOn, drivetrain));
    BrakeOff.onTrue(new InstantCommand(drivetrain::BrakeOff, drivetrain));

    ////////////////////////// CODRIVER COMMANDS //////////////////////////

    // Intake
    coneIntakeToggle.onTrue(new InstantCommand(intake::toggleIntakeIn, intake));
    cubeIntakeToggle.onTrue(new InstantCommand(intake::toggleIntakeOut, intake)); 

    // Movement
    MoveHome.onTrue(factory.moveHome()); 
    MoveGround.onTrue(factory.moveGround());
    MoveMidScoreCube.onTrue(factory.moveMidScoreCube());
    MoveMidScoreCone.onTrue(factory.moveMidScoreCone());
    MoveTopScoreCube.onTrue(factory.moveHighScoreCube());
    MoveTopScoreCone.onTrue(factory.moveHighScoreCone());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}