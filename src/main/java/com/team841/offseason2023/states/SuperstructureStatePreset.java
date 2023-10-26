package com.team841.offseason2023.states;

public class SuperstructureStatePreset {

  private final Double shoulder;
  private final Double elbow;
  private final Double elbow_tolerance;
  private final Double shoulder_tolerance;
  private final States state;

  public SuperstructureStatePreset(
      States state,
      Double shoulder,
      Double elbow,
      Double elbow_tolerance,
      Double shoulder_tolerance) {
    this.shoulder = shoulder;
    this.elbow = elbow;
    this.elbow_tolerance = elbow_tolerance;
    this.shoulder_tolerance = shoulder_tolerance;
    this.state = state;
  }

  public boolean inPreset(Double shoulder_angle, Double elbow_angle) {
    if ((this.shoulder - this.shoulder_tolerance) < shoulder_angle
        && shoulder_angle < (this.shoulder + this.shoulder_tolerance)
        && (this.elbow - this.elbow_tolerance) < elbow_angle
        && elbow_angle < (this.elbow + this.elbow_tolerance)) {

      return true;
    }
    return false;
  }

  public States getState() {
    return this.state;
  }

  public Double getShoulderGoal() {
    return this.shoulder;
  }

  public Double getElbowGoal() {
    return this.elbow;
  }
}
