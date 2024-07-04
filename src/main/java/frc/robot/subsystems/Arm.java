// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.Command;

public class Arm extends SubsystemBase {
  private XRPServo m_servo;

  /** Creates a new Arm. */
  public Arm() {
    m_servo = new XRPServo(4);
    m_servo.setAngle(0);
  }

  public void setAngle(double angle) {
    m_servo.setAngle(angle);
  }

  public Command getMoveArmCommand(double angle) {
    // return new RunCommand(() -> setAngle(angle), this);
    return run(() -> setAngle(angle));
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
