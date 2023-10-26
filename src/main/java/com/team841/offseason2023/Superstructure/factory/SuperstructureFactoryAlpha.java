package com.team841.offseason2023.Superstructure.factory;

import com.team841.offseason2023.Constants.SC;
import com.team841.offseason2023.Superstructure.Superstructure;
import com.team841.offseason2023.Superstructure.commands.setJointAngles;
import com.team841.offseason2023.states.States;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

@Deprecated
public class SuperstructureFactoryAlpha extends AbstractFactoryLogic {

  private Superstructure superstructure;

  private static CommandScheduler scheduler = CommandScheduler.getInstance();

  public SuperstructureFactoryAlpha(Superstructure _superstructure) {
    this.superstructure = _superstructure;
  }

  public CommandBase moveGround() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.Ground))
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming)
        .handleInterrupt(() -> this.superstructure.stopJoints());
  }

  public CommandBase moveMidScoreCube() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.MidScoreCube))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveMidScoreCone() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.MidScoreCone))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHighScoreCube() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.HighScoreCube))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHighScoreCone() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.HighScoreCone))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHome() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.Home))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveHomeFromHigh() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.PreExtractIn),
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
            new setJointAngles(this.superstructure, SC.PresetPositions.Home))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveIntermediateAndPreExtractIn() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.PreExtractIn),
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase moveIntermediate() {
    return new SequentialCommandGroup(
            new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
  }

  public CommandBase toGround() {
    return superstructure
        .runOnce(this::moveGround)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase toMidScoreCube() {
    return superstructure
        .runOnce(this::moveMidScoreCube)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase toMidScoreCone() {
    return superstructure
        .runOnce(this::moveMidScoreCone)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase toHighScoreCube() {
    return superstructure
        .runOnce(this::moveHighScoreCube)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase toHighScoreCone() {
    return superstructure
        .runOnce(this::moveHighScoreCone)
        .unless(
            () ->
                this.superstructure.getState() == States.Nothing
                    || !(this.superstructure.getState() == States.Home));
  }

  public CommandBase toHome() {
    return superstructure.runOnce(
        () -> {
          if (this.superstructure.getState() == States.Home) {
          } else if (this.superstructure.getState() == States.TopScoreCone
              || this.superstructure.getState() == States.TopScoreCube) {
            this.superstructure.setInput(States.Home);
            var toHomeFromHigh = moveHomeFromHigh();
            scheduler.schedule(toHomeFromHigh);
          } else {
            this.superstructure.setInput(States.Home);
            var toHome = toHome();
            scheduler.schedule(toHome);
          }
        });
  }
}
