// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class RobotContainer {
  private final CommandSwerveDrivetrain driveSubsystem = new CommandSwerveDrivetrain();
  private final CommandXboxController driverController = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
    configureDefaultCommands();
  }

  private void configureDefaultCommands() {
    driveSubsystem.setDefaultCommand(
      driveSubsystem.run(() -> 
        driveSubsystem.drive(
          -driverController.getLeftY(),   // Forward/backward
          -driverController.getLeftX(),   // Left/right strafe
          -driverController.getRightX(),  // Rotation
          true                          // Field-relative (false for robot-relative)
        )
      )
    );
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
