
package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExampleSubsystem;

import static frc.robot.program.RobotMap.exampleSubsystem;

public class ExamplePositionLeft extends CommandBase {

    public ExamplePositionLeft() {
        addRequirements(exampleSubsystem);
    }

    @Override
    public void initialize() {
        exampleSubsystem.incrementNumber();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
