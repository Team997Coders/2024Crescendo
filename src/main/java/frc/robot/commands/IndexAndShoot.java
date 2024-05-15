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
        boolean status = indexer.getSensorStatus();
         while (!status){
            shooter.setLeftMotorVoltage(0);
            indexer.setFeederVoltage(feederVoltage);
            indexer.setIntakeVoltage(intakeVoltage);
            status = indexer.getSensorStatus();
            if(status){
                shooter.setLeftMotorVoltage(shooterVoltage);
                indexer.setFeederVoltage(0);
                indexer.setIntakeVoltage(0);
            }
        }
        


      
        while (status ){
            status = indexer.getSensorStatus();
            shooter.setLeftMotorVoltage(shooterVoltage);
            indexer.setFeederVoltage(0);
            indexer.setIntakeVoltage(0);
            if (timer.get() > 3){
                indexer.setFeederVoltage(feederVoltage);
                timer.reset();
            }
            if (!status){
                shooter.setLeftMotorVoltage(0);
                indexer.setFeederVoltage(feederVoltage);
                indexer.setIntakeVoltage(intakeVoltage);
            }
            
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