// package frc.robot.subsystems;

// import static edu.wpi.first.units.Units.RotationsPerSecond;

// import com.ctre.phoenix6.configs.TalonFXSConfiguration;
// import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
// import com.ctre.phoenix6.hardware.TalonFXS;
// import com.ctre.phoenix6.signals.InvertedValue;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class FeederSubsytem extends SubsystemBase {
//     private final TalonFXS motorSix = new TalonFXS(0);

//     private final VelocityTorqueCurrentFOC oneControl = new VelocityTorqueCurrentFOC(RotationsPerSecond.of(0));

//     private final double velocity = 0;

// public FeederSubsytem(){
//     var config = new TalonFXSConfiguration();
//     config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

//     motorSix.getConfigurator().apply(config);
// }

// public void runMotors(){
//     motorSix.setControl(oneControl.withVelocity(velocity));
// }

// public void stop(){
//     motorSix.stopMotor();
// }
// }
