package frc.robot.program;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class AutonomousMode {

    /**
     * Run once at the start of autonomous
     * Perform all setup here
     */
    public static void initialize() {
        AutonomousDatabase.createAutonomousSendable(DriverStation.getInstance().isFMSAttached());//TODO change so that you can get the info right from the driver station
        //RobotMap.m_autoSelected = RobotMap.m_chooser.getSelected();
        // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        System.out.println("Auto selected: " + AutonomousDatabase.getSendableSelected());

        CommandScheduler.getInstance().schedule(AutonomousDatabase.getSendableSelected());
    }

    /**
     * Repeats every 20ms during the autonomous period
     * It's not too likely to need something here, the command scheduler will handle everything
     */
    public static void periodic() {
    }
}