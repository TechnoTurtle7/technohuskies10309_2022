    package org.firstinspires.ftc.team10309.autoOp;

    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

    import org.firstinspires.ftc.team10309.API.Robot;

    @Autonomous(name="RussellAutoOp", group="Examples")
    public class RussellAutoOp extends LinearOpMode {

        private Robot robot;

        @Override
        public void runOpMode() throws InterruptedException {
            //init
            this.robot = new Robot(this, false);

            waitForStart();



            robot.strafe(21,.7f);
            robot.drive(32,.7f);
            //open claw
            robot.drive(2,.7f);
            //  move the robot to the pole and
            //  place the cone on the pole.
            robot.strafe(2,.7f);


        }
    }
