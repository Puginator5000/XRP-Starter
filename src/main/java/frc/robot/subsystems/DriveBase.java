// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class DriveBase extends SubsystemBase {
  /** Creates a new DriveBase. */
  private XRPMotor m_leftMotor, m_rightMotor;

  public DriveBase() {
    m_leftMotor = new XRPMotor(0);
    m_rightMotor = new XRPMotor(1);
    m_rightMotor.setInverted(true);
  }

  public void arcadeDrive(double fwd, double turn) {
      m_leftMotor.set(fwd + turn);
      m_rightMotor.set(fwd - turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
