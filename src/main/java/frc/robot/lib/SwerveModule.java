package frc.robot.lib;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.util.WPIUtilJNI;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Constants.Swerve;
import frc.robot.lib.LinearMap;
public class SwerveModule{
    private static final CANSparkMax backLeftDrive = new CANSparkMax(Constants.Swerve.Back_Left_Driving_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    private static final CANSparkMax backLeftTurn = new CANSparkMax(Constants.Swerve.Back_Left_Turing_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

    private static final CANSparkMax backRightDrive = new CANSparkMax(Constants.Swerve.Back_Right_Driving_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    private static final CANSparkMax backRightTurn = new CANSparkMax(Constants.Swerve.Back_Right_Turning_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

    private static final CANSparkMax frontLeftDrive = new CANSparkMax(Constants.Swerve.Front_Left_Driving_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    private static final CANSparkMax frontLeftTurn = new CANSparkMax(Constants.Swerve.Front_Left_Turning_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

    private static final CANSparkMax frontRightDrive = new CANSparkMax(Constants.Swerve.Front_Right_Driving_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);    
    private static final CANSparkMax frontRightTurn = new CANSparkMax(Constants.Swerve.Front_Right_Turning_ID, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

    private RelativeEncoder backLeftDriveEncoder;
    private AbsoluteEncoder backLeftTurnEncoder;

    private RelativeEncoder backRightDriveEncoder;
    private AbsoluteEncoder backRightTurnEncoder;

    private RelativeEncoder frontLeftDriveEncoder;
    private AbsoluteEncoder frontLeftTurnEncoder;

    private RelativeEncoder frontRightDriveEncoder;
    private AbsoluteEncoder frontRightTurnEncoder;

    public SwerveModule (){
        backLeftDriveEncoder = backLeftDrive.getEncoder();
        backRightDriveEncoder = backRightDrive.getEncoder();
        frontLeftDriveEncoder = frontLeftDrive.getEncoder();
        frontRightDriveEncoder = frontRightDrive.getEncoder();
        
    }


}
