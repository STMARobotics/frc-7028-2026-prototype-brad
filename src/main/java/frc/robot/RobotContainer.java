// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.RunDutyCycleCommand;
import frc.robot.commands.RunMotorOneCommand;
import frc.robot.commands.RunMotorTwoCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.MainSubsystem;

public class RobotContainer {

  private final MainSubsystem mainSubsystem = new MainSubsystem();
  
  public RobotContainer() {
    configureBindings();
  }

  private final CommandXboxController controller = new CommandXboxController(0);

  private void configureBindings() {
    controller.rightTrigger().whileTrue(new ShootCommand(mainSubsystem));
    controller.a().toggleOnTrue(new RunMotorOneCommand(mainSubsystem));
    controller.b().toggleOnTrue(new RunMotorTwoCommand(mainSubsystem));
    controller.leftTrigger().toggleOnTrue(new RunDutyCycleCommand(mainSubsystem));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
