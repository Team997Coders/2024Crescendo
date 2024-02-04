package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;



public class Index extends Command {
    private final IndexerSubsystem indexer;
    
    private Timer timer;

    private double intakeVoltage = 2;
    private double feederVoltage = 2;
    public static boolean bool;

    private  ShooterSubsystem shoot;
    public Index(IndexerSubsystem indexer, ShooterSubsystem shoot) {
        this.indexer = indexer;
        this.shoot = shoot;
        
        timer = new Timer();
    }
    

    @Override
    public void initialize() {
        timer.start();
    }

    /**
     * A series of actions to intake a note, migrate it through the feeder
     * A minor state maching:
     * 1. Intake the ball
     * a. If the sensor is not tripped, intake the ball
     * 2. Wait for the ball to reach the feeder
     */
    @Override
    public void execute() {
        while (indexer.getSensorStatus() == true ) {  // there is notes
            indexer.setIntakeVoltage(0);//stop intake
            indexer.setFeederVoltage(0);//stop feeder

        } 
            indexer.setIntakeVoltage(intakeVoltage);//start intake
            indexer.setFeederVoltage(feederVoltage);//start feeder
            shoot.setLeftMotorVoltage(0);
         

    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return (false);
    }
}
