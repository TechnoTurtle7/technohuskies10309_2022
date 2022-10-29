package org.firstinspires.ftc.team10309.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team10309.API.RobotHardware;

@TeleOp(name="manipulater | Ben")
public class ManipulaterBen extends OpMode {

    private RobotHardware hardware;
    double left;
    double right;
    double drive;
    double turn;
    double max;

    @Override
    public void init() {
        // isFinal true for robot false for robobobo
        this.hardware = new RobotHardware(this, true);
        this.hardware.getLift().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {
        boolean ArmPosR = gamepad2.dpad_right;
        boolean ArmPosC = gamepad2.dpad_up;
        boolean ArmPosL = gamepad2.dpad_left;
        boolean  grab = gamepad2.right_bumper;
        boolean release = gamepad2.left_bumper;
        double liftPos = this.hardware.getLift().getCurrentPosition();
        telemetry.addData("liftpos", liftPos);
        telemetry.update();

        this.hardware.getClawRotater().setPosition(.4);

        if (ArmPosC) {
            this.hardware.getLift().setTargetPosition(-1154);
            this.hardware.getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.getLift().setPower(-.25);

            while(this.hardware.getLift().isBusy()) {}

            while(this.hardware.getClawRotater().getPosition() != 0.4) {
                this.hardware.getClawRotater().setPosition(.4);
            }

            this.hardware.getLift().setTargetPosition(0);
            this.hardware.getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.getLift().setPower(.25);
        }
        if (ArmPosL) {
            this.hardware.getLift().setTargetPosition(-1154);
            this.hardware.getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.getLift().setPower(-.25);

            while(this.hardware.getLift().isBusy()) {}

            while(this.hardware.getClawRotater().getPosition() != 0.05) {
                this.hardware.getClawRotater().setPosition(0.05);
            }
            this.hardware.getLift().setTargetPosition(0);
            this.hardware.getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.getLift().setPower(.25);
        }
        if (ArmPosR) {
            this.hardware.getLift().setTargetPosition(-1154);
            this.hardware.getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.getLift().setPower(-.25);

            while(this.hardware.getLift().isBusy()) {}

            this.hardware.getClawRotater().setPosition(.8);

            while(this.hardware.getClawRotater().getPosition() != .8) {}

            this.hardware.getLift().setTargetPosition(0);
            this.hardware.getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.getLift().setPower(.25);
        }
        if (grab) {
            this.hardware.getClaw().setPosition(0.15);
        }
        if (release) {
            this.hardware.getClaw().setPosition(0.4);
        }
    }
}