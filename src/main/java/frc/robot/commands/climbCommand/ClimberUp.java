// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.climbCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberUp extends Command {
    private ClimberSubsystem climber;

    public ClimberUp(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        if (climber.getEncoderPosition() >= 100) {
            climber.setMotorVoltage(0);
        } else {
            climber.setMotorVoltage(Constants.ClimberConstants.climberVoltage);
        }
    }

    @Override
    public void end(boolean interrupted) {
        climber.setMotorVoltage(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
