package com.team841.offseason2023.Superstructure.factory;

import edu.wpi.first.wpilibj2.command.CommandBase;

public interface AbstractFactoryMovement {

  public CommandBase moveGround();

  public CommandBase moveMidScoreCube();

  public CommandBase moveMidScoreCone();

  public CommandBase moveHighScoreCube();

  public CommandBase moveHighScoreCone();

  public CommandBase moveHome();

  public CommandBase moveHomeFromHigh();

  public CommandBase moveIntermediateAndPreExtractIn();

  public CommandBase moveIntermediate();
}
