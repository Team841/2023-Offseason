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

    public CommandBase moveGround = Commands.sequence(
      new setJointAngles(this.superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure ,SC.PresetPositions.Ground))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
    
    public CommandBase moveMidScoreCube = Commands.sequence(
      new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.MidScoreCube))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
    
    public CommandBase moveMidScoreCone = Commands.sequence(
      new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.MidScoreCone))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);

    public CommandBase moveHighScoreCube= Commands.sequence(
      new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.HighScoreCube))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
    
    public CommandBase moveHighScoreCone = Commands.sequence(
      new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.HighScoreCone))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
      
    public CommandBase moveHome = Commands.sequence(
      new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.Home))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
    
    public CommandBase moveHomeFromHigh = Commands.sequence(
      new setJointAngles(superstructure, SC.PresetPositions.PreExtractIn)
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.Home))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
      
    public CommandBase moveIntermediateAndPreExtractIn = Commands.sequence( // Go to pre extract in and then intermediate
      new setJointAngles(superstructure, SC.PresetPositions.PreExtractIn))
      .andThen(() -> new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);
        
    public CommandBase moveIntermediate = Commands.sequence( // Go to Intermediate
      new setJointAngles(superstructure, SC.PresetPositions.Intermediate))
      .handleInterrupt(() -> superstructure.stopJoints())
      .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming);

    public CommandBase toGround(){
      if (superstructure.isInPreset() || superstructure.getState() == States.Home){
        return moveGround;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toMidScoreCube(){
      if (superstructure.isInPreset() || superstructure.getState() == States.Home){
        return moveMidScoreCube;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toMidScoreCone(){
      if (superstructure.isInPreset() || superstructure.getState() == States.Home){
        return moveMidScoreCone;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toHighScoreCube(){
      if (superstructure.isInPreset() || superstructure.getState() == States.Home){
        return moveHighScoreCube;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toHighScoreCone(){
      if (superstructure.isInPreset() || superstructure.getState() == States.Home){
        return moveHighScoreCone;
      }
      else {
        return Commands.none();
      }
    }

    public CommandBase toHome(){
      if (superstructure.isInPreset()){
        if (superstructure.getState() == States.Home){
          return Commands.none();
        }
        else if(superstructure.getState() == States.TopScoreCone || superstructure.getState() == States.TopScoreCube){
          return moveHomeFromHigh;
        }
      }

      return moveHome;
    }
}
