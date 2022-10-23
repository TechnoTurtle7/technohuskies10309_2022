package org.firstinspires.ftc.team10309.API;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team10309.API.info.RobotInfo;

/**
 * A class containing the robot's hardware map and configuration. Used for both AutoOp and TeleOp
 */
public class RobotHardware {

    /**
     * Stores whether is configuration is for robobobo or the final robot
     */
    private boolean isFinal;

    /**
     * Represents the front left motor
     */
    private DcMotor flMotor;
    /**
     * Represents the front right motor
     */
    private DcMotor frMotor;
    /**
     * Represents the back left motor
     */
    private DcMotor blMotor;
    /**
     * Represents the back right motor
     */
    private DcMotor brMotor;
    /**
     * The lift slider motor
     */
    private DcMotor lift;

    /**
     * The servo that rotates the claw on the lift
     */
    private Servo clawRotater;
    /**
     * The servo that controls the claw
     */
    private Servo claw;

    /**
     * The IMU (Inertia Measurement Unit) sensor in the control hub
     */
    private BNO055IMU imu;
    /**
     * The parameters and data for the IMU
     */
    public BNO055IMU.Parameters imuParams;

    //Constructors for AutoOp and TeleOp

    //TeleOp
    public RobotHardware(OpMode opMode, boolean isFinal) {
        this.isFinal = isFinal;

        this.mapHardware(opMode.hardwareMap);
        this.configHardware();
    }
    //AutoOp
    public RobotHardware(LinearOpMode opMode, boolean isFinal) {
        this.isFinal = isFinal;

        this.mapHardware(opMode.hardwareMap);
        this.configHardware();
    }

    public DcMotor getFLMotor() {return this.flMotor;}
    public DcMotor getFRMotor() {return this.frMotor;}
    public DcMotor getBLMotor() {return this.blMotor;}
    public DcMotor getBRMotor() {return this.brMotor;}
    public DcMotor getLift() {return this.lift;}

    public Servo getClawRotater() {return this.clawRotater;}
    public Servo getClaw() {return this.claw;}

    public BNO055IMU getIMU() {return this.imu;}

    /**
     * Called to init all the values mappings
     * @param hardwareMap The hardware map of the inputed opmode
     */
    private void mapHardware(HardwareMap hardwareMap) {
        this.flMotor = hardwareMap.get(DcMotor.class, RobotInfo.flMotorName);
        this.frMotor = hardwareMap.get(DcMotor.class, RobotInfo.frMotorName);
        this.blMotor = hardwareMap.get(DcMotor.class, RobotInfo.blMotorName);
        this.brMotor = hardwareMap.get(DcMotor.class, RobotInfo.brMotorName);

        this.imu = hardwareMap.get(BNO055IMU.class, RobotInfo.imuName);
        this.imuParams = new BNO055IMU.Parameters();

        if(this.isFinal) {
            this.lift = hardwareMap.get(DcMotor.class, RobotInfo.liftName);
            this.clawRotater = hardwareMap.get(Servo.class, RobotInfo.clawRotaterName);
            this.claw = hardwareMap.get(Servo.class, RobotInfo.clawName);
        }
    }

    /**
     * Applies settings to the hardware (ex. setting the motor direction)
     */
    private void configHardware() {
        if(isFinal) {
            this.blMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            this.brMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        else {
            this.flMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            this.blMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        this.flMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.blMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.brMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.imuParams.mode = BNO055IMU.SensorMode.IMU;
        this.imu.initialize(this.imuParams);
    }
}
