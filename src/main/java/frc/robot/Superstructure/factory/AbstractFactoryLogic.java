package frc.robot.Superstructure.factory;

import edu.wpi.first.wpilibj2.command.CommandBase;


abstract class AbstractFactoryLogic implements AbstractFactoryMovement {

    @Override
    public CommandBase moveGround() {
        return null;
    }

    @Override
    public CommandBase moveMidScoreCube() {
        return null;
    }

    @Override
    public CommandBase moveMidScoreCone() {
        return null;
    }

    @Override
    public CommandBase moveHighScoreCube() {
        return null;
    }

    @Override
    public CommandBase moveHighScoreCone() {
        return null;
    }

    @Override
    public CommandBase moveHome() {
        return null;
    }

    @Override
    public CommandBase moveHomeFromHigh() {
        return null;
    }

    @Override
    public CommandBase moveIntermediateAndPreExtractIn() {
        return null;
    }

    @Override
    public CommandBase moveIntermediate() {
        return null;
    }

    public CommandBase toGround() {
        return null;
    }

    public CommandBase toMidScoreCube() {
        return null;
    }

    public CommandBase toMidScoreCone() {
        return null;
    }

    public CommandBase toHighScoreCube() {
        return null;
    }

    public CommandBase toHighScoreCone() {
        return null;
    }

    public CommandBase toHome() {
        return null;
    }
}
