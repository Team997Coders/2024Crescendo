package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class FlywheelCommand extends Command {

    private ShooterSubsystem shooter;
  
    private double seconds;
    private double voltage;

    public FlywheelCommand (double seconds, double voltage, ShooterSubsystem shooter){
        this.seconds = seconds;
        this.voltage = voltage;
        this.shooter = shooter;
    }
    
      private Timer timer;

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        timer.start();
        while (seconds < 10){
            shooter.setShooterVoltage(voltage);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
