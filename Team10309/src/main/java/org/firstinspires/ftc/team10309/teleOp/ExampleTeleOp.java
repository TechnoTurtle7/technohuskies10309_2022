package org.firstinspires.ftc.team10309.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team10309.API.RobotHardware;

@TeleOp(name="Example TeleOp")
public class ExampleTeleOp extends OpMode {

    private RobotHardware hardware;

    @Override
    public void init() {
        this.hardware = new RobotHardware(this);
    }

    @Override
    public void loop() {
        this.hardware.getFLMotor().setPower(-this.gamepad1.left_stick_y);
        this.hardware.getFRMotor().setPower(-this.gamepad1.left_stick_y);
        this.hardware.getBLMotor().setPower(-this.gamepad1.left_stick_y);
        this.hardware.getBRMotor().setPower(-this.gamepad1.left_stick_y);

        telemetry.addData("Left Stick Y", this.gamepad1.left_stick_y);
        telemetry.update();
    }
}
