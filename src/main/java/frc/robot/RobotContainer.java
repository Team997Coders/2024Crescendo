// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Climb;
import frc.robot.commands.Feeder;
import frc.robot.commands.Intake;
import frc.robot.commands.Shooter;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
  private Timer timer;
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final FeederSubsystem m_feederSubsystem = new FeederSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final SwerveSubsystem m_driveSubsystem = new SwerveSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController = new CommandXboxController(
     // OperatorConstants.kDriverControllerPort);
    private final XboxController m_driverController = new XboxController(
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
    //m_driverController.whileTrue(new RunCommand(() -> m_driveSubsystem.setX(),m_driveSubsystem));
    new JoystickButton(m_driverController, Button.kRightStick.value)
        .whileTrue(new RunCommand(
            () -> m_driveSubsystem.setX(),
            m_driveSubsystem));
    if (m_driverController.getAButtonPressed())
      if (!m_feederSubsystem.getSensorStatus()){
        new Shooter(m_shooterSubsystem, 0);
        new Feeder(m_feederSubsystem, 3);
        new Intake(m_intakeSubsystem, 3);
        m_feederSubsystem.getSensorStatus();
      }else{
        timer.start();
        new Shooter(m_shooterSubsystem, 3);
          if (timer.get() > 3){
            new Feeder(m_feederSubsystem, 3);
            timer.reset();
          }
      }
    while (m_driverController.getRightBumperPressed()){
      new Climb(m_climberSubsystem, -3);
    }
    while (m_driverController.getLeftBumperPressed()){
      new Climb(m_climberSubsystem, 3);
    }
    while (!m_driverController.getRightBumperPressed()){
      new Climb(m_climberSubsystem, 0);
    }
    while (!m_driverController.getLeftBumperPressed()){
      new Climb(m_climberSubsystem, 0);
    }
    
   
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_intakeSubsystem, m_shooterSubsystem, m_feederSubsystem, m_driveSubsystem);
  }

  public void populateDashboard() {
    SmartDashboard.putData("IndexerSubsystem", m_intakeSubsystem);
    SmartDashboard.putData("ShooterSubsystem", m_shooterSubsystem);
    SmartDashboard.putBoolean("Note Sensor", m_feederSubsystem.getSensorStatus());
    SmartDashboard.putNumber("Shooter Velocity", m_shooterSubsystem.getFlywheelVelocity());
    SmartDashboard.putNumber("Feeder Velocity", m_feederSubsystem.getFeederMotorVoltage());
    SmartDashboard.putNumber("Intake Velocity", m_intakeSubsystem.getIntakeMotorVoltage());
    SmartDashboard.putBoolean("Bool key", Autos.run_state);
    SmartDashboard.putNumber("Intake Encoder Position", m_intakeSubsystem.getIntakeEncoderPosition());
    SmartDashboard.putNumber("Feeder Encoder Position", m_feederSubsystem.getFeederEncoderPosition());
    SmartDashboard.putNumber("Left FLywheel Encoder Position", m_shooterSubsystem.getFlywheelPosition());
    SmartDashboard.putNumber("Climber motor rotations",m_climberSubsystem.getEncoderRotations());
    SmartDashboard.putNumber("Heading", m_driveSubsystem.getHeading());
    SmartDashboard.putNumber("Turn rate", m_driveSubsystem.getTurnRate());


  } 

}
