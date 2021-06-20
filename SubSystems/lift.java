/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  SpeedControllerGroup m_pulley = RobotMap.m_pulley;
  Encoder E = RobotMap.encoderE;

  public int encoderGetENC(){
    return E.get();
  }

  public void resetENC(){
    E.reset();
  }

  public double getHeight(){
    return E.getDistance();
  }

  public void setLiftSpeed(double m_Speed){
    m_pulley.set(m_Speed);
  }


  
  public void LiftDown(){
    m_pulley.set(0.1);
  }

  public void stop(){
    m_pulley.set(0);
  }

  public void LiftUP(){
    m_pulley.set(-.7);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
