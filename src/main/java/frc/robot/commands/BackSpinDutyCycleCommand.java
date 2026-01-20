package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MainSubsystem;

public class BackSpinDutyCycleCommand extends Command {

	private final MainSubsystem mainSubsystem;
    
	public BackSpinDutyCycleCommand(MainSubsystem mainSubsystem) {
		this.mainSubsystem = mainSubsystem;
		addRequirements(mainSubsystem);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		mainSubsystem.runBackSpinDutyCycle();
	}

	@Override
	public void end(boolean interrupted) {
		mainSubsystem.stop();
	}
}
