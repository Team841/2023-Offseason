// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Constants;

import frc.lib.util.Gains;
import frc.robot.Superstructure.States;

import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;

public final class SC {

    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    public static final boolean kSensorPhase = true;
    public static final double[] fxOutputs = {1, -1};

    public static final double manualIncrementOffset = 0.06;

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

        public static final int IndexChannel = 1;

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

        public static final int IndexChannel = 0;

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

        
        public static final RangeMap<Double, States> elbowRange = TreeRangeMap.create();
        public static final RangeMap<Double, States> shoulderRange = TreeRangeMap.create();

        public PresetPositions(){
            applyRange();
        }

        public void applyRange(){

            elbowRange.put(Range.closed(Home[1] - SC.Elbow.tolerance, Home[1] + SC.Elbow.tolerance), States.Home);
            elbowRange.put(Range.closed(Intermediate[1] - SC.Elbow.tolerance, Intermediate[1] + SC.Elbow.tolerance), States.Intermediate);
            elbowRange.put(Range.closed(PreExtractIn[1] - SC.Elbow.tolerance, PreExtractIn[1] + SC.Elbow.tolerance), States.PreIntermediate);
            elbowRange.put(Range.closed(MidScoreCube[1] - SC.Elbow.tolerance, MidScoreCube[1] + SC.Elbow.tolerance), States.MidScoreCube);
            elbowRange.put(Range.closed(MidScoreCone[1] - SC.Elbow.tolerance, MidScoreCone[1] + SC.Elbow.tolerance), States.MidScoreCone);
            elbowRange.put(Range.closed(HighScoreCube[1] - SC.Elbow.tolerance, HighScoreCube[1] + SC.Elbow.tolerance), States.TopScoreCube);
            elbowRange.put(Range.closed(HighScoreCone[1] - SC.Elbow.tolerance, HighScoreCone[1] + SC.Elbow.tolerance), States.TopScoreCone);
            elbowRange.put(Range.closed(Ground[1] - SC.Elbow.tolerance, Ground[1] + SC.Elbow.tolerance), States.Ground);

            shoulderRange.put(Range.closed(Home[0] - SC.Shoulder.tolerance, Home[0] + SC.Shoulder.tolerance), States.Home);
            shoulderRange.put(Range.closed(Intermediate[0] - SC.Shoulder.tolerance, Intermediate[0] + SC.Shoulder.tolerance), States.Intermediate);
            shoulderRange.put(Range.closed(PreExtractIn[0] - SC.Shoulder.tolerance, PreExtractIn[0] + SC.Shoulder.tolerance), States.PreIntermediate);
            shoulderRange.put(Range.closed(MidScoreCube[0] - SC.Shoulder.tolerance, MidScoreCube[0] + SC.Shoulder.tolerance), States.MidScoreCube);
            shoulderRange.put(Range.closed(MidScoreCone[0] - SC.Shoulder.tolerance, MidScoreCone[0] + SC.Shoulder.tolerance), States.MidScoreCone);
            shoulderRange.put(Range.closed(HighScoreCube[0] - SC.Shoulder.tolerance, HighScoreCube[0] + SC.Shoulder.tolerance), States.TopScoreCube);
            shoulderRange.put(Range.closed(HighScoreCone[0] - SC.Shoulder.tolerance, HighScoreCone[0] + SC.Shoulder.tolerance), States.TopScoreCone);
            shoulderRange.put(Range.closed(Ground[0] - SC.Shoulder.tolerance, Ground[0] + SC.Shoulder.tolerance), States.Ground);
        }
    }

}
