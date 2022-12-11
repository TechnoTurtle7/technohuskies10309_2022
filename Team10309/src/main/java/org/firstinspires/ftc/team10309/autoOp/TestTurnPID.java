package org.firstinspires.ftc.team10309.autoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team10309.API.Robot;
import org.firstinspires.ftc.team10309.API.RobotTest;

@Autonomous(name="Test turn PID")
    public class TestTurnPID extends LinearOpMode {
        
        private RobotTest robot;
        
        @Override
        public void runOpMode() throws InterruptedException {
            //init
            this.robot = new RobotTest(this, true);
            
            telemetry.addLine("Init Completed");
            telemetry.update();
            waitForStart();
    
            robot.turnPID(10f, 0.2);
            Thread.sleep(2000);
            telemetry.addLine("20 Completed");
            telemetry.update();
            Thread.sleep(2000);
            robot.turnPID(-10f, 0.2);
            Thread.sleep(2000);
            telemetry.addLine("-20 Completed");
            telemetry.update();
            Thread.sleep(2000);
//            robot.turnPID(40f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("40 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(-40f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("-40 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(60f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("60 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(-60f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("-60 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(75f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("75 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(-75f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("-75 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
            robot.turnPID(91f, 0.2);
            Thread.sleep(2000);
            telemetry.addLine("90 Completed");
            telemetry.update();
            Thread.sleep(2000);
            robot.turnPID(-91f,  0.2);
            Thread.sleep(2000);
            telemetry.addLine("-90 Completed");
            telemetry.update();
            Thread.sleep(2000);
//            robot.turnPID(120f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("120 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(-120f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("-120 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(150f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("150 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
//            robot.turnPID(-150f,  0.2);
//            Thread.sleep(2000);
//            telemetry.addLine("-150 Completed");
//            telemetry.update();
//            Thread.sleep(2000);
        }
    }
