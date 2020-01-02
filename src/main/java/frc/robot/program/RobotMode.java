package frc.robot.program;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class RobotMode {
    public static void initialize() {
        RobotMap.initialize();
    }

    public static void periodic() {
        CommandScheduler.getInstance().run();
    }
}
