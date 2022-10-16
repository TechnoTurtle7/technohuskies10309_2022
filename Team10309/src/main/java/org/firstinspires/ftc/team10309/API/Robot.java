package org.firstinspires.ftc.team10309.API;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * A class representing the robot, all of its data, and its capabilities
 */
public class Robot implements Runnable {
    private static final String TFOD_MODEL_FILE = "/storage/emulated/0/FIRST/tflitemodels" +
            "/prototype.tflite"; // MAKE SURE THIS FILE ACTUALLY EXISTS!!!!!!!!!, can change to
    // just the file name if using the manage page *according to javadoc of tfod
    // .loadModelFromFile().
    private static final String[] LABELS = {
            "Blue Triangle",
            "Red Square",
            "Green Circle"
    };
    
    private static final String VUFORIA_KEY =
            "AXxOC5T/////AAABmSJC57yqQENwiMY04jFbMZpnfnBAf5owbyLoU53bsaoA0LpmGf8ud2gFJoOQ7ss6mmjI5vaZ2qUqUAUuKOaiP9zXMrNKsOLSi/5K///mA6hn70YKnuCSNEIS/amvBAnwgX0r9syIb9IjfcLtobMqn5Pbqea10aBH8blAjip9pNnsXeHhybkyoyMiajtOzKx79SCcwAgyvXZOikLqNPz4u1JKpwdPGj3vvTRgVhCc2Wswv13U+Wyp0ti889pKmoazuT6ByUoCl0WEuZKf9I+HZUGaa8MSjNyMMw38Zee9KfwkCYfFmBI6N7XLOp7M6DF5oeNblQMR77jvHj8+yqCTFczzvAWlX+uzXLZTdHnbhwPm";
    
    private static VuforiaLocalizer vuforia;
    
    private static TFObjectDetector tfod;
    /**
     * A 2D Vector representing the robots position
     */
    private Vec2 position;
    /**
     * A float representing the robots rotation in degrees
     */
    private float rotation;

    /**
     * The op mode the robot is running
     */
    private final LinearOpMode opMode;

    /**
     * The hardware of this robot instance
     */
    private final RobotHardware hardware;

    /**
     * The thread running the update loop
     */
    private Thread loop;

    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
        this.position = new Vec2(0, 0);
        this.rotation = 0;

        this.hardware = new RobotHardware(this.opMode);
    }

    public void startUpdateLoop() {
        this.loop = new Thread(this);
        this.loop.start();
    }
    public void exitUpdateLoop() {
        this.loop.interrupt();
    }

    /**
     * A function to move the robot forward and backward
     * @param inches the distance to move the robot
     */
    public void drive(float inches) {
        this.hardware.getFLMotor().setPower(1);
        this.hardware.getFRMotor().setPower(1);
        this.hardware.getBLMotor().setPower(1);
        this.hardware.getBRMotor().setPower(1);
    }
    /**
     * A function to move the robot left and right
     * @param inches the distance to move the robot
     */
    public void strafe(float inches) {}
    /**
     * A function to turn the robot
     * @param degrees the amount of degrees to turn
     */
    public void turn(float degrees) {}
    /**
     * A function to move the robot to a designated tile on the playing field. (0, 0) is at the
     * blue terminal on the blue side
     * @param x the x position of the tile
     * @param y the y position of the tile
     */
    public void gotoTile(float x, float y) {}

    /**
     * DON'T USE THIS OUTSIDE OF API
     */
    public void run() {
        while(this.opMode.opModeIsActive()) {
            this.update();
        }
    }
    /**
     * A function to be called every frame of the program, to handle tasks
     */
    private void update() {

    }

    /**
     * A function containing the PID algorithm
     * @param target The target value for the PID algorithm to try to match
     * @param current The current value for the PID algorithm to move towards the target
     * @param Kp The Kp modifier value
     * @param Ki The Ki modifier value
     * @param Kd The Kd modifier value
     * @return The resulting value of the PID algorithm
     */
    private float PID(double target, double current, float Kp, float Ki, float Kd) {return 0;}
    
    
    
    
    public void initOD(float conf){
        initVuforia();
        initTfod(conf);
        this.opMode.telemetry.addData("Done!", "Ready to press play");
        this.opMode.telemetry.update();
        this.opMode.waitForStart();
    }
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = this.hardware.getCamera();
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    public void detectLevel() {
        if (tfod != null) {
            tfod.activate();
        }
        else {
            this.opMode.telemetry.addData("tfod not initialized in init stage of op mode. please " +
                    "wait " +
                    "until" +
                    " the 'Object Detection Initialized' message appears on the telemetry before " +
                    "pressing the play button", "");
            this.opMode.telemetry.update();
            try {Thread.sleep(10_000);} catch (Exception e) {}
        }
        while (this.opMode.opModeIsActive()) {
            List<Recognition> recognitions = tfod.getUpdatedRecognitions();
            if (recognitions != null) {
                this.opMode.telemetry.addData("object detected...", "");
                this.opMode.telemetry.update();
                if (recognitions.size() == 1) {
                    Recognition recognition = recognitions.get(0);
                    this.opMode.telemetry.addData("Label", recognition.getLabel());
                    this.opMode.telemetry.update();
                } else {
                    this.opMode.telemetry.addLine("More than one recognition!");
                    for (Recognition recognition : recognitions) {
                        this.opMode.telemetry.addData("Label", recognition.getLabel());
                        this.opMode.telemetry.update();
                    }
                }
            }
            
        }
        tfod.deactivate();
    }
    private void initTfod(float conf) {
        int tfodMonitorViewId = this.hardware.getTfodMonitorViewID();
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = conf;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        /**
         Loads a TFLite model from the indicated file, which must be a .tflite file and
         contain the full file path.
         Params:
         fileName – *****either the name of the file that was uploaded using the Manage page or
         the full path to the .tflite model file****
         labels – the labels of the objects in the model
         */
        tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }
}
