package frc.robot.subsystems;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MainSubsystem extends SubsystemBase {

    private final TalonFX motorOne = new TalonFX(0);
    private final TalonFX motorTwo = new TalonFX(1);

    private final SlotConfigs pid = new SlotConfigs();

    private final double velocity = 60;

    private final VelocityTorqueCurrentFOC oneControl = new VelocityTorqueCurrentFOC(0.0);
    private final VelocityTorqueCurrentFOC twoControl = new VelocityTorqueCurrentFOC(0.0);

    private final DutyCycleOut cycle = new DutyCycleOut(0);

    public MainSubsystem() {
        var config = new TalonFXConfiguration();
        pid.kV = .05;
        pid.kP = 1;
        pid.kI = 0;
        pid.kD = 0;

        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motorOne.getConfigurator().apply(config);
        motorTwo.getConfigurator().apply(config);
        motorOne.getConfigurator().apply(pid);
        motorTwo.getConfigurator().apply(pid);

        motorTwo.setControl(new Follower(0, MotorAlignmentValue.Opposed));
    }

    public void runMotorOne() {
        motorOne.setControl(oneControl.withVelocity(velocity));
    }

    public void runMotorTwo() {
        motorTwo.setControl(twoControl.withVelocity(velocity));
    }

    public void runMotors() {
        motorOne.setControl(oneControl.withVelocity(velocity));
    }

    public void runDutyCycle() {
        motorOne.setControl(cycle.withOutput(.1));
    }

    public void stop() {
        motorOne.stopMotor();
    }

}
