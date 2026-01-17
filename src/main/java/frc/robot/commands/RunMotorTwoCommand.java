package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MainSubsystem;

public class RunMotorTwoCommand extends Command {

    private final MainSubsystem mainSubsystem;
    
    public RunMotorTwoCommand(MainSubsystem mainSubsystem) {
        this.mainSubsystem = mainSubsystem;

        addRequirements(mainSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        mainSubsystem.runMotorTwo();
    }

    @Override
    public void end(boolean interrupted) {
        mainSubsystem.stop();
    }
}
