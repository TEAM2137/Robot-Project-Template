package frc.robot.program;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * RobotMap is simply a static container for all subsystems.
 * This makes accessing subsystems easy, and prevents any duplicates.
 * The initialize method must be run on robot initialization to construct all subsystems.
 */
public class RobotMap {
    public static Constants constants;

    public static ControlsManager controlsManager;

    public static ExampleSubsystem exampleSubsystem;

    public static final String kCountOnceAuto = "Count Once";
    public static final String kCount5sAuto = "Count 5 seconds";
    public static String m_autoSelected;
    /**
     * Initializes the map so that the map can be used
     */
    public static void initialize() {
        constants = new Constants();

        controlsManager = new ControlsManager();

        exampleSubsystem = new ExampleSubsystem(constants.intTeamNumber);
    }
}
