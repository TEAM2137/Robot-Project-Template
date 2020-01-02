package frc.robot.program;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Class to manage controls all in one place
 * This allows easy changing of controls without needing modify the main robot code
 */
public class ControlsManager {

    static XboxController driverController = new XboxController(0);
    static XboxController operatorController = new XboxController(1);

    public enum Control {

        A_SingleIncrement(0, driverController, InputType.Button),
        A_ResetCounter(1, driverController, InputType.Button),
        B_10SecondIncrement(1, operatorController, InputType.Axis),
        B_20SecondIncrement(180, operatorController, InputType.POV),
        ;

        private int id;
        private GenericHID controller;
        private InputType inputType;
        Control(int id, GenericHID controller, InputType inputType) {
            this.id = id;
            this.controller = controller;
            this.inputType = inputType;
        }
    }

    public static enum InputType {
        Axis, Button, POV;
    }

    /**
     * Gets the value of an axis
     * @param control The control to grab the value from
     * @return The value of the axis
     */
    public static double getAxis(Control control) {
        if (control.inputType != InputType.Axis) {
            return 0;
        }
        return control.controller.getRawAxis(control.id);
    }

    /**
     * Gets whether a button is pressed or not
     * @param control The control to grab the value from
     * @return Whether the button is pressed or not
     */
    public static boolean getButton(Control control) {
        if (control.inputType != InputType.Button) {
            return false;
        }
        return control.controller.getRawButton(control.id);
    }

    /**
     * Gets whether a POV (ex: D-pad) is pointing in a certain direction
     * @param control The control to reference (id = direction)
     * @return Whether the POV is pointing in the requested direction
     */
    public static boolean getPOVBoolean(Control control) {
        if (control.inputType != InputType.POV) {
            return false;
        }
        return control.controller.getPOV() == control.id;
    }

    /**
     * Gets the angle that a POV is pointing
     * @param control The control to reference (just checks controller's POV)
     * @return The angle the POV is pointing
     */
    public static int getPOVAngle(Control control) {
        if (control.inputType != InputType.POV) {
            return 0;
        }
        return control.controller.getPOV();
    }
}
