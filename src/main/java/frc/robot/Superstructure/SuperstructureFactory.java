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

    public Command moveGround() {
      return new SequentialCommandGroup(
        new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate),
        new setJointAngles(this.superstructure, SC.PresetPositions.Ground))
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming)
        .handleInterrupt(() -> this.superstructure.stopJoints());
    }
    
    public Command moveMidScoreCube() {
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
      
    public CommandBase moveIntermediateAndPreExtractIn() {  // Go to pre extract in and then intermediate
      return new SequentialCommandGroup(
        new setJointAngles(this.superstructure, SC.PresetPositions.PreExtractIn),
        new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
    }
        
    public CommandBase moveIntermediate () { // Go to Intermediate
      return new SequentialCommandGroup(
        new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate))
        .handleInterrupt(() -> this.superstructure.stopJoints())
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
    }

    public Command toGround(){
      if (this.superstructure.ifInPreset() || this.superstructure.getState() == States.Home){
        return moveGround();
      }
      else {
        return Commands.none();
      }
    }

    public Command toMidScoreCube(){
      if (this.superstructure.ifInPreset() || this.superstructure.getState() == States.Home){
        return moveMidScoreCube();
      }
      else {
        return Commands.none();
      }
    }

    public Command toMidScoreCone(){
      if (this.superstructure.ifInPreset() || this.superstructure.getState() == States.Home){
        return moveMidScoreCone();
      }
      else {
        return Commands.none();
      }
    }

    public Command toHighScoreCube(){
      if (this.superstructure.ifInPreset() || this.superstructure.getState() == States.Home){
        return moveHighScoreCube();
      }
      else {
        return Commands.none();
      }
    }

    public Command toHighScoreCone(){
      if (this.superstructure.ifInPreset() || this.superstructure.getState() == States.Home){
        return moveHighScoreCone();
      }
      else {
        return Commands.none();
      }
    }

    public Command toHome(){
      if (this.superstructure.ifInPreset()){
        if (this.superstructure.getState() == States.Home){
          return Commands.none();
        }
        else if(this.superstructure.getState() == States.TopScoreCone || this.superstructure.getState() == States.TopScoreCube){
          return moveHomeFromHigh();
        }
      }

      return moveHome();
    }
}
