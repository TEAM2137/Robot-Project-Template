/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    static final int jumperPort = 9;
    public boolean isPracticeBot = false;

    //FMS items
    private boolean boolIsFMSConnected = false;
    private DriverStation.Alliance mintAlliance = DriverStation.Alliance.Invalid;
    private String strEventName = "Default";
    private int intMatchNumber = 0;

    public static int intTeamNumber;

    /**
     * Creates the class based on whether the robot is competition or practice robot
     *
     * The format for the initialize method setting values is as follows:
     * isPracticeBot ? [practice value] : [competition value]
     */
    public Constants() {
        isPracticeBot = new DigitalInput(jumperPort).get();

        intTeamNumber = isPracticeBot ? 7312 : 2137;
    }

    private void LoadDriverStationInfo(){
        DriverStation driverStation = DriverStation.getInstance();

        this.mintAlliance = driverStation.getAlliance();
        this.strEventName = driverStation.getEventName();
        this.intMatchNumber = driverStation.getMatchNumber();
        this.boolIsFMSConnected = driverStation.isFMSAttached();
    }

    public boolean isBoolIsFMSConnected() {
        return boolIsFMSConnected;
    }
    public DriverStation.Alliance getMintAlliance() {
        return mintAlliance;
    }
    public String getStrEventName() {
        return strEventName;
    }
    public int getIntMatchNumber() {
        return intMatchNumber;
    }
    public String getLogName(){
        //rlog_Detroit_Red_31_032759298356
        return "rlog_" + getStrEventName() + "_" + getMintAlliance() + "_" + getIntMatchNumber() + "_" + System.currentTimeMillis();
    }
}