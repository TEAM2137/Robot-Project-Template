package frc.robot.program;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.autonomous.ExamplePositionRight;
import frc.robot.commands.autonomous.ExamplePositionLeft;

public class AutonomousDatabase {

    private static SendableChooser<Command> autonomousSendable = null;

    /**
     * This is a list of the Autonomouses that are available.
     */
    public enum Autonomouses {
        CountOnce("Left", ExamplePositionLeft.class),
        Count5s("Right", ExamplePositionRight.class),
        ;

        String name;
        Class<? extends Command> aClass;

        Autonomouses(String name, Class<? extends Command> _Class) {
            this.name = name;
            this.aClass = _Class;
        }

        public String toString(){return this.name;}

        /**
         * Returns a new instance of the selected command.
         *
         * @return Command Class of the selected Autonomous
         */
        public Command getCommand(){
            try {
                return this.aClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Gets an Autonomous by its name
     * @param name The name of the autonomous
     * @return An Autonomous that matches the name
     */
    public static Autonomouses getAutonByName(String name) {
        for (Autonomouses auton : Autonomouses.values()) {
            if (auton.name == name) {
                return auton;
            }
        }
        return null;
    }

    /**
     * Returns an autonomous command to be scheduled
     * @param auton The autonomous to be retreived
     * @return A command to be scheduled
     */
    public static Command getAutonomous(Autonomouses auton) {
        switch (auton) {
            case CountOnce:
                return new ExamplePositionLeft();
            case Count5s:
                return new ExamplePositionRight(5000);
            default:
                return new InstantCommand();
        }
    }

    /**
     * This is a sendable JUST for debug and not for a real match
     * @param fake
     * @return if this was real or fake. if false skipped the auton sendable
     */
    public static SendableChooser<Command> createAutonomousSendable(boolean fake){
        if (fake) {
            autonomousSendable = new SendableChooser<Command>();

            for (Autonomouses auton : Autonomouses.values()) {
                autonomousSendable.addOption(auton.toString(), auton.getCommand());
            }

            SmartDashboard.putData("Autonomous Options", autonomousSendable);
        }

        return autonomousSendable;
    }

    public static Command getSendableSelected(){
        return autonomousSendable.getSelected();
    }
}