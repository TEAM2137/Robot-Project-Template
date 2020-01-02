package frc.robot.program;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.autonomous.ExamplePositionRight;
import frc.robot.commands.autonomous.ExamplePositionLeft;
import frc.robot.commands.autonomous.ExampleSetNumber;
import frc.robot.program.ControlsManager.Control;

public class TeleopMode {

    /**
     * Initializes teleop, run at start of every teleop
     */
    public static void initialize() {
        CommandScheduler.getInstance().cancelAll(); //Cancel all commands so that we don't continue auto into teleop
    }

    /**
     * Periodic run every 20ms during teleop
     */
    public static void periodic() {
        if (ControlsManager.getButton(Control.A_SingleIncrement)) {
            CommandScheduler.getInstance().schedule(new ExamplePositionLeft());
        }
        if (ControlsManager.getButton(Control.A_ResetCounter)) {
            CommandScheduler.getInstance().schedule(new ExampleSetNumber(0));
        }
        if (ControlsManager.getAxis(Control.B_10SecondIncrement) >= .5) {
            CommandScheduler.getInstance().schedule(new ExamplePositionRight(10));
        }
        if (ControlsManager.getPOVBoolean(Control.B_20SecondIncrement)) {
            CommandScheduler.getInstance().schedule(new ExamplePositionRight(20));
        }
    }
}
