package frc.robot.Superstructure;
 
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.SC;

public class SuperstructureFactory{

    private Superstructure superstructure;

    public SuperstructureFactory(Superstructure _superstructure){
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

    public CommandBase toGround(){
      if (this.superstructure.ifInPreset() && this.superstructure.getState() == States.Home){
        this.superstructure.setInput(States.Ground);
        var ground = moveGround();
        return ground;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toMidScoreCube(){
      if (this.superstructure.ifInPreset() && this.superstructure.getState() == States.Home){
        this.superstructure.setInput(States.MidScoreCube);
        var MidScoreCube = moveMidScoreCube();
        return MidScoreCube;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toMidScoreCone(){
      if (this.superstructure.ifInPreset() && this.superstructure.getState() == States.Home){
        this.superstructure.setInput(States.MidScoreCone);
        var MidScoreCone = moveMidScoreCone();
        return MidScoreCone;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toHighScoreCube(){
      if (this.superstructure.ifInPreset() && this.superstructure.getState() == States.Home){
        this.superstructure.setInput(States.MidScoreCube);
        var HighScoreCube = toHighScoreCone();
        return HighScoreCube;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toHighScoreCone(){
      if (this.superstructure.ifInPreset() && this.superstructure.getState() == States.Home){
        this.superstructure.setInput(States.TopScoreCone);
        var HighScoreCone = moveHighScoreCone();
        return HighScoreCone;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toHome(){
      if (this.superstructure.ifInPreset()){
        if (this.superstructure.getState() == States.Home){
          return Commands.none();
        }
        else if(this.superstructure.getState() == States.TopScoreCone || this.superstructure.getState() == States.TopScoreCube){
          this.superstructure.setInput(States.Home);
          var HomeFromHigh = moveHomeFromHigh();
          return HomeFromHigh;
        }
        this.superstructure.setInput(States.Home);
        var Home = moveHome();
        return Home;
      }
      return Commands.none();   
    }
}
