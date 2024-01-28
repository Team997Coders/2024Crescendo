package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class IndexAndShoot extends Command{

    
    private Shooter m_shooter;
    private Indexer m_indexer;
    private Timer timer;

    private double defaultVoltage;
    private boolean bool;
    
    public IndexAndShoot(Indexer indexer, Shooter shooter) {
        m_indexer = indexer;
        m_shooter = shooter;
        timer = new Timer();
    }

    @Override
    public void initialize() {
        bool = false;
    }

    @Override
    public void execute() {
        if (m_indexer.getSensorStatus() == false && bool == false) {
            m_indexer.setIntakeVoltage(defaultVoltage);
            m_indexer.setFeederVoltage(defaultVoltage);
            bool = !bool;
        } else if(m_indexer.getSensorStatus() == true && bool == true) {
            timer.start();
            m_indexer.setIntakeVoltage(0);
            m_indexer.setFeederVoltage(0);
            m_shooter.setMotorVoltage(defaultVoltage);
           
        } else if (m_indexer.getSensorStatus() == false && timer.get() > 0) {
             m_indexer.setIntakeVoltage(0);
             m_indexer.setFeederVoltage(0);
             m_shooter.setMotorVoltage(defaultVoltage);
        }

        if (timer.get() >= 5) {
            m_indexer.setFeederVoltage(defaultVoltage);

            if (m_indexer.getSensorStatus() == false && timer.get() >= 9) {
            m_indexer.setFeederVoltage(0);
            m_shooter.setMotorVoltage(0);
            timer.stop();
            timer.reset();
        }
            } 

        
        
    }

    @Override 
    public void end(boolean interrupted) {
    }

    @Override 
    public boolean isFinished() {
        return (timer.get() >= 10);
    }
}
