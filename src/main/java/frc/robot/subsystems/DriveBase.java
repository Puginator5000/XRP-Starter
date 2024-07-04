// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.math.MathUtil;

import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.RunCommand;

public class DriveBase extends SubsystemBase {
  /** Creates a new DriveBase. */
  private XRPMotor m_leftMotor, m_rightMotor;
  private AnalogInput m_eyes;   //"eyes" = ultrasonic sensor

  public DriveBase() {
    m_leftMotor = new XRPMotor(0);
    m_rightMotor = new XRPMotor(1);
    m_rightMotor.setInverted(true);

    m_eyes = new AnalogInput(2);
  }

  public void arcadeDrive(double fwd, double turn) {
      m_leftMotor.set(fwd + turn);
      m_rightMotor.set(fwd - turn);
  }

  //math way to convert volt to dist (0v = 20 mm, 5v = 4k mm)
  public double getDistance() {
    return (796 * m_eyes.getVoltage() + 20);
  }

  //belong to drivebase so make cmd in here (encapsulation)
  public Command getDriveUntilCommand(double distance) {
    return new FunctionalCommand(           //functional cmd = compact representation of a command & its life cycle (init, ex, end, fin...)
      () -> {},                             //initialize (not doing anything)
      () -> {                               //execute 
        if (getDistance() < distance) {     //if too close, go backwards
          arcadeDrive(-0.5, 0);
        } else{
        arcadeDrive(.5, 0); }      //else go
      },
      interrupted -> { arcadeDrive(0, 0);},      //consumer end (takes in a boolean, but java funny so interrupted is automatically a boolean)
      () -> { return MathUtil.isNear(distance, getDistance(), 1e-3); },    //is finished
      this);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
