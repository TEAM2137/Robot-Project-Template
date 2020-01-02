package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * SubSystem are hardware file that you want to use to access the parts of the robot
 */
public class ExampleSubsystem extends SubsystemBase {

    private int number;

    public ExampleSubsystem(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void incrementNumber() {
        this.number++;
    }
}
