// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexAndShootCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PIDShoot extends PIDCommand {

    public PIDShoot(ShooterSubsystem shooter, double speed) {
        super(
                // The controller that the command will use
                new PIDController(0.001, 0.0, 0.005),
                // This should return the measurement
                shooter::getShooterVelocity,
                // This should return the setpoint (can also be a constant)
                () -> speed,
                // This uses the output
                shooter::setLeftMotorVoltage);

        addRequirements(shooter);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
