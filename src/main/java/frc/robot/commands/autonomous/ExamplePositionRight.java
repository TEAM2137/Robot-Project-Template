package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExampleSubsystem;

import static frc.robot.program.RobotMap.exampleSubsystem;

public class ExamplePositionRight extends CommandBase {

    int durationMillis;
    Timer timer;

    public ExamplePositionRight(int durationMillis) {
        this.durationMillis = durationMillis;
        runsWhenDisabled(); // Comment to make it NOT run when disabled
        addRequirements(exampleSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        exampleSubsystem.incrementNumber();
    }

    @Override
    public boolean isFinished() {
        return timer.hasPeriodPassed(durationMillis);
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
    }
}
