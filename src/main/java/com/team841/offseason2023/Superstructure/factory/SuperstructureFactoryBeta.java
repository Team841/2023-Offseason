package com.team841.offseason2023.Superstructure.factory;

import com.team841.offseason2023.Constants.SC;
import com.team841.offseason2023.Superstructure.Superstructure;
import com.team841.offseason2023.Superstructure.commands.setJointAngles;
import com.team841.offseason2023.states.States;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SuperstructureFactoryBeta extends AbstractFactoryLogicBeta {

  private final Superstructure superstructure;

  public SuperstructureFactoryBeta(Superstructure _superstructure) {
    this.superstructure = _superstructure;
  }

  public CommandBase moveGround() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.Ground))
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf)
        .handleInterrupt(this.superstructure::stopJoints)
        .unless(
            () ->
                (this.superstructure.getState() == States.Nothing)
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase moveMidScoreCube() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.MidScoreCube))
        .handleInterrupt(this.superstructure::stopJoints)
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase moveMidScoreCone() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.MidScoreCone))
        .handleInterrupt(this.superstructure::stopJoints)
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase moveHighScoreCube() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.HighScoreCube))
        .handleInterrupt(this.superstructure::stopJoints)
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase moveHighScoreCone() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.HighScoreCone))
        .handleInterrupt(this.superstructure::stopJoints)
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase moveHome() {
    return Commands.either(
        new SequentialCommandGroup( // to home regular
                new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
                new setJointAngles(this.superstructure, SC.PresetPositions.Home))
            .handleInterrupt(this.superstructure::stopJoints)
            .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf),
        new SequentialCommandGroup( // to home from high
                new setJointAngles(this.superstructure, SC.PresetPositions.PreExtractIn),
                new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
                new setJointAngles(this.superstructure, SC.PresetPositions.Home))
            .handleInterrupt(this.superstructure::stopJoints)
            .withInterruptBehavior(Command.InterruptionBehavior.kCancelSelf),
        () ->
            !((this.superstructure.getState() == States.TopScoreCone
                || this.superstructure.getState() == States.TopScoreCube)));
  }
}
