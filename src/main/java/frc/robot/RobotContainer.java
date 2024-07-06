// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Arm;
//import frc.robot.commands.MoveArmCommand;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import edu.wpi.first.wpilibj2.command.button.Trigger;



public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DriveBase m_db = new DriveBase();
  private final Arm m_arm = new Arm();

  //private MoveArmCommand m_go90Cmd = new MoveArmCommand(m_arm, 90);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().whileTrue(m_arm.getMoveArmCommand(180));
    m_driverController.y().whileTrue(m_arm.getMoveArmCommand(0));
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_driverController.x().onTrue(m_db.getDriveUntilCommand(500).withTimeout(5));     //break-it-down loop, so needs to be put in timeout

    m_driverController.leftBumper().onTrue(m_db.getGoToSetpointCommand(180));

    m_db.setDefaultCommand(getArcadeDriveCommand());
    m_arm.setDefaultCommand(m_arm.getMoveArmCommand(90));
  }

  private Command getArcadeDriveCommand() {
    return new RunCommand(() -> m_db.arcadeDrive(-m_driverController.getLeftY(), m_driverController.getLeftX()), m_db);
  }


  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}

