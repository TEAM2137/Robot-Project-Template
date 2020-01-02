package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.program.RobotMap.exampleSubsystem;

public class ExampleSetNumber extends CommandBase {

    int number;

    public ExampleSetNumber(int number) {
        this.number = number;
        addRequirements(exampleSubsystem);
    }

    @Override
    public void initialize() {
        exampleSubsystem.setNumber(number);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
