// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.commands.Drive;
import frc.robot.commands.climbCommand.ClimberDown;
import frc.robot.commands.climbCommand.ClimberStop;
import frc.robot.commands.climbCommand.ClimberUp;
import frc.robot.commands.indexAndShootCommands.Index;
import frc.robot.commands.indexAndShootCommands.Shoot;
import frc.robot.commands.indexAndShootCommands.StopIndex;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivebaseSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final AHRS gyro = new AHRS();

  private final DrivebaseSubsystem drivebase = new DrivebaseSubsystem(gyro);
  private final IndexerSubsystem indexer = new IndexerSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();

  private static XboxController driveStick = new XboxController(0);

  // private static CommandXboxController c_driveStick2 = new
  // CommandXboxController(1);
  private static CommandXboxController c_driveStick = new CommandXboxController(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    drivebase.setDefaultCommand(
        new Drive(
            drivebase,
            () -> getScaledXY(),
            () -> scaleRotationAxis(driveStick.getRawAxis(4))));

    configureBindings();
  }

  /**
   * {@link edu.wpi.first.math.MathUtil}
   */
  private double deadband(double input, double deadband) {
    if (Math.abs(input) < deadband) {
      return 0;
    } else {
      return input;
    }
  }

  private double[] getXY() {
    double[] xy = new double[2];
    xy[0] = deadband(driveStick.getLeftX(), DriveConstants.deadband);
    xy[1] = deadband(driveStick.getLeftY(), DriveConstants.deadband);
    return xy;
  }

  private double[] getScaledXY() {
    double[] xy = getXY();

    // Convert to Polar coordinates
    double r = Math.sqrt(xy[0] * xy[0] + xy[1] * xy[1]);
    double theta = Math.atan2(xy[1], xy[0]);

    // Square radius and scale by max velocity
    r = r * r * drivebase.getMaxVelocity();

    // Convert to Cartesian coordinates
    xy[0] = r * Math.cos(theta);
    xy[1] = r * Math.sin(theta);

    return xy;
  }

  private double squared(double input) {
    return Math.copySign(input * input, input);
  }

  public void updateDashboard() {
    SmartDashboard.putNumber("Scaled_X", getScaledXY()[0]);
    SmartDashboard.putNumber("Scaled_Y", getScaledXY()[1]);
    SmartDashboard.putNumber("Rotation", scaleRotationAxis(driveStick.getRawAxis(4)));
    
    SmartDashboard.putBoolean("Climber Sensor: ", climber.getLeftClimberSensor());
    SmartDashboard.putBoolean("Is Climber Moving?", climber.isClimberMoving());
    SmartDashboard.putNumber("Climber Position: ", climber.getEncoderPosition());
  }

  @SuppressWarnings("unused")
  private double cube(double input) {
    return Math.copySign(input * input * input, input);
  }

  @SuppressWarnings("unused")
  private double scaleTranslationAxis(double input) {
    return deadband(-squared(input), DriveConstants.deadband) * drivebase.getMaxVelocity();
  }

  private double scaleRotationAxis(double input) {
    return deadband(squared(input), DriveConstants.deadband) * drivebase.getMaxAngleVelocity() * -0.6;
  }

  public void resetGyro() {
    gyro.reset();
  }

  public double getGyroYaw() {
    return -gyro.getYaw();
  }

  public boolean onBlueAlliance() {
    var alliance = DriverStation.getAlliance();
    if (alliance.isPresent()) {
      return alliance.get() == Alliance.Blue;
    }
    return false;
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Gyro Reset
    c_driveStick.povUp().onTrue(Commands.runOnce(gyro::reset));
    // Drivetrain Lock
    c_driveStick.povDown().onTrue(Commands.runOnce(drivebase::SwerveModuleLock));
    // Intake: a
    c_driveStick.a().onTrue(new Index(indexer)).onFalse(new StopIndex(indexer));
    // Shoot: b
    c_driveStick.b().onTrue(new Shoot(shooter, 100)).onFalse(new Shoot(shooter, 0));
    // Spinnup Shooter: X
    c_driveStick.x().onTrue(shooter.SpinnupShooterCommand(100)).onFalse(shooter.StopShooterCommand());
    
    c_driveStick.rightBumper().onTrue(new ClimberUp(climber)).onFalse(new ClimberStop(climber));
    c_driveStick.leftBumper().onTrue(new ClimberDown(climber)).onFalse(new ClimberStop(climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new InstantCommand();
  }

  public void populateDashboard() {
    SmartDashboard.putBoolean("Note Sensor", indexer.getSensorStatus());
    SmartDashboard.putNumber("Shooter Velocity", shooter.getShooterVelocity());
    SmartDashboard.putNumber("Feeder Power", indexer.getFeederMotorVoltage());
    SmartDashboard.putNumber("Intake Power", indexer.getIntakeMotorVoltage());
    SmartDashboard.putNumber("Climber Position", climber.getEncoderPosition());
    SmartDashboard.putBoolean("Left Climber Down?", climber.getLeftClimberSensor());
    SmartDashboard.putBoolean("Right Climber Down?", climber.getRightClimberSensor());
    SmartDashboard.putNumber("Gyro Angle", getGyroYaw());
  }
}