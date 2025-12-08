package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CommandSwerveDrivetrain extends SubsystemBase {
  // Module positions relative to robot center (in meters)
  // Adjust these to match your robot's dimensions
  private static final double TRACK_WIDTH = 0.6; // Distance between left and right wheels
  private static final double WHEEL_BASE = 0.6; // Distance between front and back wheels
  
  private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
    new Translation2d(WHEEL_BASE / 2, TRACK_WIDTH / 2),  // Front Left
    new Translation2d(WHEEL_BASE / 2, -TRACK_WIDTH / 2), // Front Right
    new Translation2d(-WHEEL_BASE / 2, TRACK_WIDTH / 2), // Back Left
    new Translation2d(-WHEEL_BASE / 2, -TRACK_WIDTH / 2) // Back Right
  );
  
  // Swerve modules - adjust CAN IDs and angle offsets to match your robot
  private final SwerveModule frontLeft = new SwerveModule(1, 2, 1, 0.0);
  private final SwerveModule frontRight = new SwerveModule(3, 4, 2, 0.0);
  private final SwerveModule backLeft = new SwerveModule(5, 6, 3, 0.0);
  private final SwerveModule backRight = new SwerveModule(7, 8, 4, 0.0);
  
  public CommandSwerveDrivetrain() {
  }
  
  public void drive(double xSpeed, double ySpeed, double rotSpeed, boolean fieldRelative) {
    // Convert speeds to meters per second
    xSpeed *= Constants.TeleopDriveConstants.MAX_TELEOP_VELOCITY;
    ySpeed *= Constants.TeleopDriveConstants.MAX_TELEOP_VELOCITY;
    rotSpeed *= Constants.TeleopDriveConstants.MAX_TELEOP_ANGULAR_VELOCITY;
    
    // Create chassis speeds
    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
    
    // Convert to module states
    SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(chassisSpeeds);
    
    // Normalize wheel speeds
    SwerveDriveKinematics.desaturateWheelSpeeds(
      moduleStates, 
      Constants.TeleopDriveConstants.MAX_TELEOP_VELOCITY
    );
    
    // Set module states
    setModuleStates(moduleStates);
  }
  
  public void setModuleStates(SwerveModuleState[] desiredStates) {
    frontLeft.setDesiredState(desiredStates[0]);
    frontRight.setDesiredState(desiredStates[1]);
    backLeft.setDesiredState(desiredStates[2]);
    backRight.setDesiredState(desiredStates[3]);
  }
  
  public void stop() {
    frontLeft.stop();
    frontRight.stop();
    backLeft.stop();
    backRight.stop();
  }
}