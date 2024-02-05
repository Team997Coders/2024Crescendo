package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IndexAndShoot extends Command{
    //I decide to put them together, because the indexer should be happening all the time, without pressing a button.
    
    private Timer timer;
    private double intakeVoltage;
    private double feederVoltage;
    private double shooterVoltage;
    private ShooterSubsystem shooter;
    private IndexerSubsystem indexer;
    public IndexAndShoot(double intakeVoltage, double feederVoltage, double shooterVoltage, ShooterSubsystem shoot, IndexerSubsystem index){
        this.intakeVoltage = intakeVoltage;
        this.feederVoltage = feederVoltage;
        this.shooterVoltage = shooterVoltage;
        this.shooter = shoot;
        this.indexer = index;
    }
    @Override
    public void initialize(){
        timer = new Timer();
        timer.start();
    }
    @Override
    public void execute(){
        
        


        while (indexer.getSensorStatus() == true ) {  // there is notes
            indexer.setIntakeVoltage(0);//stop intake
            indexer.setFeederVoltage(0);//stop feeder
            shooter.setLeftMotorVoltage(shooterVoltage); //start the shooter
            if (timer.get() > 3){ // wait 3 second
                indexer.setFeederVoltage(feederVoltage); //start the feeder
                timer.reset();// reset time
            }
        } 
        while(indexer.getSensorStatus() == false){ //while there is a notes inside
            shooter.setLeftMotorVoltage(0);
            indexer.setIntakeVoltage(intakeVoltage);//start intake
            indexer.setFeederVoltage(feederVoltage);//start feeder
            shooter.setLeftMotorVoltage(0);
        }
        
    }   
    @Override
    public void end(boolean interrupted){
        
    }
    
    @Override 
    public boolean isFinished(){
        return false;
    }
    
}
