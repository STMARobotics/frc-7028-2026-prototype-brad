package frc.robot;

public class Constants {
    public static class TeleopDriveConstants {
        public static final double MAX_TELEOP_VELOCITY = 4.5; // meters per second
        public static final double MAX_TELEOP_ANGULAR_VELOCITY = Math.PI; // radians per second (180 deg/s)
    }
    
    public static class SwerveConstants {
        // Robot dimensions (adjust to match your robot)
        public static final double TRACK_WIDTH = 0.6; // meters - distance between left and right wheels
        public static final double WHEEL_BASE = 0.6; // meters - distance between front and back wheels
        
        // Gear ratios (adjust to match your gearing)
        public static final double DRIVE_GEAR_RATIO = 6.75; // L2 gear ratio
        public static final double TURN_GEAR_RATIO = 12.8; // MK4i turn ratio
        public static final double WHEEL_CIRCUMFERENCE = 0.1016 * Math.PI; // 4 inch wheel in meters
        
        // CAN IDs - UPDATE THESE TO MATCH YOUR ROBOT
        // Format: [drive motor, turn motor, canCoder]
        public static final int[] FRONT_LEFT_IDS = {1, 2, 1};
        public static final int[] FRONT_RIGHT_IDS = {3, 4, 2};
        public static final int[] BACK_LEFT_IDS = {5, 6, 3};
        public static final int[] BACK_RIGHT_IDS = {7, 8, 4};
        
        // CANcoder offsets in rotations - calibrate these using Phoenix Tuner X
        public static final double FRONT_LEFT_OFFSET = 0.0;
        public static final double FRONT_RIGHT_OFFSET = 0.0;
        public static final double BACK_LEFT_OFFSET = 0.0;
        public static final double BACK_RIGHT_OFFSET = 0.0;
    }
}
