package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimberSubsystem;

public class Climb extends Command {
    
    private ClimberSubsystem m_climber;
    private double m_voltage;
   

    public Climb(ClimberSubsystem climber, double voltage) {
        this.m_climber = climber;
        this.m_voltage = voltage;

        addRequirements(m_climber);
    }

    @Override
    public void initialize() {
        m_climber.setMotorVoltage(m_voltage);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.setMotorVoltage(0);
    }

    @Override 
    public boolean isFinished() {
        return false;
    }
}
