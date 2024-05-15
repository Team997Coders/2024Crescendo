// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Climb;
import frc.robot.commands.IndexAndShoot;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimberSubsystem m_ClimberSubsystem = new ClimberSubsystem();
  
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings

    configureBindings();
    populateDashboard();
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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    // .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is
    // pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    m_driverController.a().whileTrue(new IndexAndShoot(2, 2, 3, m_shooterSubsystem, m_indexerSubsystem));

    m_driverController.rightBumper().whileTrue(new Climb(m_ClimberSubsystem, -3));
    m_driverController.leftBumper().whileTrue(new Climb(m_ClimberSubsystem, 3)); 

    m_driverController.rightBumper().onFalse(new Climb(m_ClimberSubsystem, 0));
    m_driverController.leftBumper().onFalse(new Climb(m_ClimberSubsystem, 0));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_indexerSubsystem, m_shooterSubsystem);
  }

  public void populateDashboard() {
    SmartDashboard.putData("IndexerSubsystem", m_indexerSubsystem);
    SmartDashboard.putData("ShooterSubsystem", m_shooterSubsystem);
    SmartDashboard.putBoolean("Note Sensor", m_indexerSubsystem.getSensorStatus());
    SmartDashboard.putNumber("Shooter Velocity", m_shooterSubsystem.getFlywheelVelocity());
    SmartDashboard.putNumber("Feeder Velocity", m_indexerSubsystem.getFeederMotorVoltage());
    SmartDashboard.putNumber("Intake Velocity", m_indexerSubsystem.getIntakeMotorVoltage());
    SmartDashboard.putBoolean("bool key", Autos.run_state);
    SmartDashboard.putNumber("Intake Encoder Position", m_indexerSubsystem.getIntakeEncoderPosition());
    SmartDashboard.putNumber("Feeder Encoder Position", m_indexerSubsystem.getFeederEncoderPosition());
    SmartDashboard.putNumber("Left FLywheel Encoder Position", m_shooterSubsystem.getFlywheelPosition());
    SmartDashboard.putNumber("climber motor rotations",m_ClimberSubsystem.getEncoderRotations());

  } 

}
