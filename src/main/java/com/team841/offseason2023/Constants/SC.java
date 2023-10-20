 // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team841.offseason2023.Constants;

import com.team841.offseason2023.Superstructure.SuperstructureStateManager;
import com.team841.offseason2023.states.States;
import com.team841.offseason2023.states.SuperstructureStatePreset;
import com.team841.offseason2023.util.Gains;

public final class SC {

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static final boolean kSensorPhase = true;
    public static final double[] fxOutputs = {1, -1};

    public static final double manualIncrementOffset = 0.06;

    public static States superstructureState = States.Nothing; 

    public static final SuperstructureStateManager stateManager = new SuperstructureStateManager();
    
    public static final class Shoulder{

        public static final double GearRatio = 0.0037; // 1/240;

        /*public static final double kp = 0.0003;
        public static final double ki = 0;
        public static final double kd = 0;
        public static final double kff = 0;
        public static final double kIz = 0 ; */
        public static final double tolerance = 5; //in degrees
        public static final double maxOutput = 1;
        public static final double minOutput = -1;

        public static final Gains gains = new Gains(0.0003, 0, 0, 0, 0, 5000, 5000000, 0);
        public static final Gains[] setupGains = {gains, new Gains(), new Gains()};

        public static final int IndexChannel = 0;

        // in sensor units per 100ms
        public static final double MMAccelLimit = 5000;
        public static final double MMVelocityLimit = 5000000;
        public static final int MMSCurveStrength = 0;
    }
    
    public static final class Elbow{

        public static final double GearRatio = 0.0033333; // 1/300;

        /* public static final double kp = 0.0005;
        public static final double ki = 0;
        public static final double kd = 0;
        public static final double kff = 0;
        public static final double kIz = 0; */ 
        public static final double tolerance = 5;//in degrees
        public static final double maxOutput = 1;
        public static final double minOutput = -1;

        public static final Gains gains = new Gains(0.0005, 0, 0, 0, 0, 5000, 5000000, 0);
        public static final Gains[] setupGains = {gains, new Gains(), new Gains()};

        public static final int IndexChannel = 1;

        // in sensor units per 100ms
        public static final double MMAccelLimit = 5000;
        public static final double MMVelocityLimit = 5000000;
        public static final int MMSCurveStrength = 0;
    }

    public static final class Intake{
        public static final double teleopPower = 0.6; // in percent power
        public static final double autoPower = 0.335; // in percent power

        public static final double CubeCThresh = 10;
        public static final double ConeCThresh = 25;
    }

    public static class PresetPositions{
        /* {Should_angle, Elbow_angle} */
        public static final double[] Home = {0.0,0.0};
        public static final double[] Intermediate = {51.0, 0.0};
        public static final double[] PreExtractIn = {149.3,-41};
        public static final double[] MidScoreCube = {61.9,-12.2};
        public static final double[] HighScoreCube = {163.5, -161.4};
        public static final double[] HighScoreCone = {185.5, -188.4};
        public static final double[] MidScoreCone = {122.9 , -123.5};
        public static final double[] Ground = {39.8,-88.2};

        public static final SuperstructureStatePreset HomePreset = new SuperstructureStatePreset(States.Home, Home[0], Home[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset IntermediatePreset = new SuperstructureStatePreset(States.Intermediate, Intermediate[0], Intermediate[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset PreExtractInPreset = new SuperstructureStatePreset(States.PreIntermediate, PreExtractIn[0], PreExtractIn[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset MidScoreCubePreset = new SuperstructureStatePreset(States.MidScoreCube, MidScoreCube[0], MidScoreCube[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset HighScoreCubePreset = new SuperstructureStatePreset(States.TopScoreCube, HighScoreCube[0], HighScoreCube[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset HighScoreConePreset = new SuperstructureStatePreset(States.TopScoreCone, HighScoreCone[0], HighScoreCone[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset MidScoreConePreset = new SuperstructureStatePreset(States.MidScoreCone, MidScoreCone[0], MidScoreCone[1], Shoulder.tolerance, Elbow.tolerance);
        public static final SuperstructureStatePreset GroundPreset = new SuperstructureStatePreset(States.Ground, Ground[0], Ground[1], Shoulder.tolerance, Elbow.tolerance);
    }
}