package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimbCommand extends Command{

    private final ClimberSubsystem climber;
    private double voltage;

    private XboxController controller = new XboxController(0);
    
    public ClimbCommand(ClimberSubsystem climber, double voltage) {
        this.climber = climber;
        this.voltage = voltage;
    }
    
    @Override
    public void initialize() {
        climber.setBrakeMode();
    }
    
    @Override
    public void execute() {
        boolean rightBumper = controller.getRightBumper();

        if (rightBumper == true) {
            climber.setMotorVoltage(voltage);
        }
       
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    public boolean isFinished(){
        return false;
    }

}

