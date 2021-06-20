/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveLiftToHeight extends Command {
  private double m_height; // Height is in inches
  private double m_speed; // Speed is a _positive_ number, between 0 and 1

  private final double m_tolerance = 1.0f;

  public MoveLiftToHeight(double height, double speed) {
      m_height = height;
      m_speed = speed;
      
      // I'm assuming there is a subsystem for the lift called 'lift'
      requires(Robot.lift);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
      // Robot.lift.getHeight is the `encoder.getDistance` method
      if(Robot.lift.getHeight() < m_height) {
          // The lift is lower than it should be.
          // Raise the lift.
          Robot.lift.setLiftSpeed(Math.abs(m_speed));
      }
      if(Robot.lift.getHeight() > m_height) {
          // The lift is higher than it should be.
          // Lower the lift.
          Robot.lift.setLiftSpeed(-1 * Math.abs(m_speed));
      }
  }

  @Override
  protected boolean isFinished() {
      final double distanceToHeight = Robot.lift.getHeight() - m_height; // This can be negative depending on whether the lift is higher or lower than the desired height.
      final boolean isWithinTolerance = Math.abs(distanceToHeight) < m_tolerance;
      return isWithinTolerance;
  }

  @Override
  protected void end() {
      // Stop the lift
      Robot.lift.setLiftSpeed(0);
  }

  @Override
  protected void interrupted() {
      // Stop the lift
      Robot.lift.setLiftSpeed(0);
  }
}