package frc.robot.subsystems;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MainSubsystem extends SubsystemBase {

    private final TalonFX motorOne = new TalonFX(0);
    private final TalonFX motorTwo = new TalonFX(1);
    private final TalonFX motorThree = new TalonFX(2);
    private final TalonFX motorFour = new TalonFX(3);

    private final SlotConfigs pid = new SlotConfigs();

    private final double velocity = 50; // rps change this one for speed

    private final VelocityTorqueCurrentFOC oneControl = new VelocityTorqueCurrentFOC(0.0);

    private final DutyCycleOut cycle = new DutyCycleOut(0);

    public MainSubsystem() {
        var config = new TalonFXConfiguration();
        pid.kV = .085;
        pid.kP = 11;
        pid.kI = 0;
        pid.kD = 0;

        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motorThree.getConfigurator().apply(config);
        motorOne.getConfigurator().apply(pid);
        motorTwo.getConfigurator().apply(pid);
        motorThree.getConfigurator().apply(pid);
        motorFour.getConfigurator().apply(pid);

        motorFour.setControl(new Follower(2, MotorAlignmentValue.Opposed));
        // motorTwo.setControl(new Follower(2, MotorAlignmentValue.Opposed));

    }

    public void runMotors() {
        motorThree.setControl(oneControl.withVelocity(velocity));
    }

    public void runDutyCycle() {
        motorThree.setControl(cycle.withOutput(.1));
    }

    public void stop() {
        motorThree.stopMotor();
    }

}
