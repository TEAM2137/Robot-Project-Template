package frc.robot.program;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class robotConfig {

    // Motor CAN IDs
    private HashMap<String, Integer> dblLeftMotor1ID = new HashMap<String, Integer>();
    private HashMap<String, Integer> dblLeftMotor2ID = new HashMap<String, Integer>();
    private HashMap<String, Integer> dblRightMotor1ID = new HashMap<String, Integer>();
    private HashMap<String, Integer> dblRightMotor2ID = new HashMap<String, Integer>();

    // Drive train information
    private HashMap<String, Double> dblMotorGearReduction = new HashMap<String, Double>();// dblMotorGearReduction:1 -- dblMotorGearReduction is the amount of time the motor has to spin for the output of the motor gear box to rotate 360
    private HashMap<String, Double> dblDriveGearReduction = new HashMap<String, Double>();// dblDriveGearReduction:1 -- dblDriveGearReduction is the amount of time the gear box has to rotate for the wheels on the base to move 360
    private HashMap<String, Double> dblMaximumVelocity = new HashMap<String, Double>();// dblMaximumVelocity ft/s -- This is how far the robot can move in feet in one second
    private HashMap<String, Double> dblWheelDia = new HashMap<String, Double>();// dblWheelDia inches -- This is how wide the wheel is and most common for FRC is 6 inch
    private HashMap<String, Double> dblP1 = new HashMap<String, Double>();// dblP1 -- This is the P value of the first PID controller on the robot
    private HashMap<String, Double> dblI1 = new HashMap<String, Double>();// dblI1 -- THis is the I value of the first PID controller on the robot
    private HashMap<String, Double> dblD1 = new HashMap<String, Double>();// dblD1 -- This is the D value of the first PID contorller on the robot
    private HashMap<String, Double> dblFF1 = new HashMap<String, Double>();// dblFF1 -- This is the FeedForward value of the first PID controller on the robot
    private HashMap<String, Double> dblIZ1 = new HashMap<String, Double>();// dblIZ1 -- This is the I Zone value of the first PID controller on the robot
    private HashMap<String, Double> dblRobotYear = new HashMap<String, Double>();// dblRobotYear -- This is the year that the robot is built
    private HashMap<String, Double> dblCPR = new HashMap<String, Double>();// dblCPR -- This is the amount of counts for the motor to rotate once
    private HashMap<String, Double> dblMotorRPM = new HashMap<String, Double>();// dblCPR -- This is the amount of counts for the motor to rotate once
    private HashMap<String, Boolean> boolReverseLeftMotor1 = new HashMap<String, Boolean>();// boolReverseLeftMotor1 -- To flip the direction of the motor
    private HashMap<String, Boolean> boolReverseLeftMotor2 = new HashMap<String, Boolean>();// boolReverseLeftMotor2 -- To flip the direction of the motor
    private HashMap<String, Boolean> boolReverseRightMotor1 = new HashMap<String, Boolean>();// boolReverseRightMotor1 -- To flip the direction of the motor
    private HashMap<String, Boolean> boolReverseRightMotor2 = new HashMap<String, Boolean>();// boolReverseRightMotor2 -- To flip the direction of the motor
    private String strRobotKey = "DeepSpace";

    public void robotConfig(String robotKey, boolean usingXML){

        this.strRobotKey = robotKey;

        if (usingXML)
            openXML(robotKey);
        else
            openHardCodeValues();
    }

    public void openHardCodeValues(){
        // CAN IDs
        dblLeftMotor1ID.put("PowerUp", 0);
        dblLeftMotor2ID.put("PowerUp", 1);
        dblRightMotor1ID.put("PowerUp", 2);
        dblRightMotor2ID.put("PowerUp", 3);

        //Gear Reduction
        dblMotorGearReduction.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblDriveGearReduction.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblMaximumVelocity.put("PowerUp", 5000.0); // TODO: 12/3/2019 Add real value

        //RobotInformation
        dblWheelDia.put("PowerUp", 6.0); // TODO: 12/3/2019 Add real value

        //PID Controller
        dblP1.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblI1.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblD1.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblFF1.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblIZ1.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value

        dblCPR.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value
        dblMotorRPM.put("PowerUp", 0.0); // TODO: 12/3/2019 Add real value

        //Motor Information
        boolReverseLeftMotor1.put("PowerUp", false); // TODO: 12/3/2019 Add real value
        boolReverseLeftMotor2.put("PowerUp", false); // TODO: 12/3/2019 Add real value
        boolReverseRightMotor1.put("PowerUp", false); // TODO: 12/3/2019 Add real value
        boolReverseRightMotor2.put("PowerUp", false); // TODO: 12/3/2019 Add real value

    }

    public void openXML(String robotKey){
        File stepFile = new File("/home/lvuser/Sequences", robotKey);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stepFile);
            
            doc.getDocumentElement().normalize();
            
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            
            NodeList nStepList = doc.getElementsByTagName("Step");
            System.out.println("Step elements :" + nStepList.getLength());
            
            for (int i = 0; i < nStepList.getLength(); i++){
                Node step = nStepList.item(i);
                System.out.println("Step elements :" + step.getNodeName());
            
                if (step.getNodeType() == Node.ELEMENT_NODE) {
                    if (step.getNodeName().equalsIgnoreCase("Setup")) {
                        Element eElement = (Element) step;

                        // CAN IDs
                        dblLeftMotor1ID.put(robotKey, Integer.parseInt(getElementByTag(eElement, "LeftMotor1ID")));
                        dblLeftMotor2ID.put(robotKey, Integer.parseInt(getElementByTag(eElement, "LeftMotor2ID")));
                        dblRightMotor1ID.put(robotKey, Integer.parseInt(getElementByTag(eElement, "RightMotor1ID")));
                        dblRightMotor2ID.put(robotKey, Integer.parseInt(getElementByTag(eElement, "RightMotor2")));

                        //Gear Reduction
                        dblMotorGearReduction.put(robotKey, toDouble(getElementByTag(eElement, "MotorGearReduction")));
                        dblDriveGearReduction.put(robotKey, toDouble(getElementByTag(eElement, "DriveGearReduction")));
                        dblMaximumVelocity.put(robotKey, toDouble(getElementByTag(eElement, "MaxVelocity")));

                        //RobotInformation
                        dblWheelDia.put(robotKey, toDouble(getElementByTag(eElement, "WheelDia")));

                        //PID Controller
                        dblP1.put(robotKey, toDouble(getElementByTag(eElement, "P1")));
                        dblI1.put(robotKey, toDouble(getElementByTag(eElement, "I1")));
                        dblD1.put(robotKey, toDouble(getElementByTag(eElement, "D1")));
                        dblFF1.put(robotKey, toDouble(getElementByTag(eElement, "FF1")));
                        dblIZ1.put(robotKey, toDouble(getElementByTag(eElement, "IZ1")));

                        dblCPR.put(robotKey, toDouble(getElementByTag(eElement, "CPR")));
                        dblMotorRPM.put(robotKey, toDouble(getElementByTag(eElement, "MotorRPM")));

                        //Motor Information
                        boolReverseLeftMotor1.put(robotKey, Boolean.parseBoolean(getElementByTag(eElement, "ReverseLeft1")));
                        boolReverseLeftMotor2.put(robotKey, Boolean.parseBoolean(getElementByTag(eElement, "ReverseLeft2")));
                        boolReverseRightMotor1.put(robotKey, Boolean.parseBoolean(getElementByTag(eElement, "ReverseRight1")));
                        boolReverseRightMotor2.put(robotKey, Boolean.parseBoolean(getElementByTag(eElement, "ReverseRight2")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double toDouble(String string){
        return Double.parseDouble(string);
    }

    public String getElementByTag(Element eElement, String tag){
        return eElement.getElementsByTagName(tag).item(0).getTextContent();
    }

    public HashMap<String, Double> getDblCPR() {
        return dblCPR;
    }

    public HashMap<String, Double> getDblMotorRPM() {
        return dblMotorRPM;
    }

    public void setDblMotorRPM(HashMap<String, Double> dblMotorRPM) {
        this.dblMotorRPM = dblMotorRPM;
    }

    public void setDblCPR(HashMap<String, Double> dblCPR) {
        this.dblCPR = dblCPR;
    }

    public int getLeftMotor1CAN_ID(String robotKey){
        return dblLeftMotor1ID.get(robotKey);
    }

    public int getLeftMotor1CAN_ID(){
        return dblLeftMotor1ID.get(strRobotKey);
    }

    public int getLeftMotor2CAN_ID(String robotKey){
        return dblLeftMotor2ID.get(robotKey);
    }

    public int getLeftMotor2CAN_ID(){
        return dblLeftMotor2ID.get(strRobotKey);
    }

    public int getRightMotor1CAN_ID(String robotKey){
        return dblLeftMotor1ID.get(robotKey);
    }

    public int getRightMotor1CAN_ID(){
        return dblLeftMotor1ID.get(strRobotKey);
    }

    public int getRightMotor2CAN_ID(String robotKey){
        return dblLeftMotor1ID.get(robotKey);
    }

    public int getRightMotor2CAN_ID(){
        return dblLeftMotor1ID.get(strRobotKey);
    }

    public double getMotorGearReduction(String robotKey) {
        return dblMotorGearReduction.get(robotKey);
    }

    public double getMotorGearReduction() {
        return dblMotorGearReduction.get(strRobotKey);
    }

    public double getDriveGearReduction(String robotKey){
        return dblDriveGearReduction.get(robotKey);
    }

    public double getDriveGearReduction(){
        return dblDriveGearReduction.get(strRobotKey);
    }

    public double getMaxVelocity(String robotKey){
        return dblMaximumVelocity.get(robotKey);
    }

    public double getMaxVelocity(){
        return dblMaximumVelocity.get(strRobotKey);
    }

    public double getWheelDiameter(String robotKey){
        return dblWheelDia.get(robotKey);
    }

    public double getWheelDiameter(){
        return dblWheelDia.get(strRobotKey);
    }

    public double getP1(String robotKey){
        return dblP1.get(robotKey);
    }

    public double getP1(){
        return dblP1.get(strRobotKey);
    }

    public double getI1(String robotKey){
        return dblI1.get(robotKey);
    }

    public double getI1(){
        return dblI1.get(strRobotKey);
    }

    public double getD1(String robotKey){
        return dblD1.get(robotKey);
    }

    public double getD1(){
        return dblD1.get(strRobotKey);
    }

    public double getFF1(String robotKey){
        return dblFF1.get(robotKey);
    }

    public double getFF1(){
        return dblFF1.get(strRobotKey);
    }

    public double getIZ1(String robotKey){
        return dblIZ1.get(robotKey);
    }

    public double getIZ1(){
        return dblIZ1.get(strRobotKey);
    }

    public boolean getLeftMotor1Reverse(String robotKey){
        return boolReverseLeftMotor1.get(robotKey);
    }

    public boolean getLeftMotor1Reverse(){
        return boolReverseLeftMotor1.get(strRobotKey);
    }

    public boolean getLeftMotor2Reverse(String robotKey){
        return boolReverseLeftMotor2.get(robotKey);
    }

    public boolean getLeftMotor2Reverse(){
        return boolReverseLeftMotor2.get(strRobotKey);
    }

    public boolean getRightMotor1Reverse(String robotKey){
        return boolReverseRightMotor1.get(robotKey);
    }

    public boolean getRightMotor1Reverse(){
        return boolReverseRightMotor1.get(strRobotKey);
    }

    public boolean getRightMotor2Reverse(String robotKey){
        return boolReverseRightMotor2.get(robotKey);
    }

    public boolean getRightMotor2Reverse(){
        return boolReverseRightMotor2.get(strRobotKey);
    }
}