    package org.firstinspires.ftc.team10309.autoOp.master;

    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.hardware.DcMotor;

    import org.firstinspires.ftc.team10309.API.ClawController;
    import org.firstinspires.ftc.team10309.API.Robot;
    import org.firstinspires.ftc.team10309.API.SleeveDetect;
    import org.firstinspires.ftc.team10309.API.info.RobotInfo;

    // Algin with a jenga block 1 jenga block from the right side edge of mat.
    @Autonomous(name="RED CONE Auto Op | FINAL", group="Examples")
    public class REDAutoOpCone extends LinearOpMode {

        private Robot robot;
        private ClawController clawController;

        @Override
        public void runOpMode() throws InterruptedException {
    
            final float liftSpeed = 0.9f;
            final float armPosLeft = 0.05f;
            final float armPosCenter = 0.4f;
            final float armPosRight = 0.75f;
            final float clawOpenPos = 0.4f;
            final float clawClosePos = 0.15f;
            //init
            this.robot = new Robot(this, true);
            this.clawController = new ClawController(this.robot.getHardware(), this);
            
            robot.getHardware().getClaw().setPosition(clawOpenPos);
            Thread.sleep(3000);
            robot.getHardware().getClaw().setPosition(clawClosePos);
            robot.initDetect();
            SleeveDetect.SignalState state = robot.scanSleeve();
            telemetry.addLine("Press Start");
    
            telemetry.addData("Signal state", state.name());
            telemetry.update();
            waitForStart();
   
            // constants, copied from TeleOpMain.
    
//
//            SleeveDetect.SignalState state = robot.scanSleeve();
//            telemetry.addData("Signal state", state.name());
//            telemetry.update();
//
//            Thread.sleep(1000);
//            robot.turn(-90,.05f,1f); // FIx,  Plus move out before....
//            robot.strafe(21,.7f);
//            robot.drive(32,.7f); // a bit more?
//            robot.drive(2,.7f);
//            clawController.setClawState(ClawController.ClawState.OPEN);
//            robot.strafe(26,.7f);
//            robot.strafeTiles(1, 0.5f, 3f);
//            robot.strafe(-3, 0.5f);
//            robot.driveTiles(1, 0.5f);
//            robot.strafeTiles(1, 0.5f);
//            robot.getHardware().getLift().setTargetPosition(-1100);// Lowest elevator height for
//            // moving claw rotator
//            robot.getHardware().getLift().setPower(liftSpeed);
//            clawController.setClawState(ClawController.ClawState.OPEN);
//            robot.getHardware().getClawRotator().setPosition(armPosLeft);
//
            Thread moveElevator = new Thread() {
                @Override
                public synchronized void start() {
                    super.start();
                }
    
                @Override
                public void run() {
                    robot.getHardware().getLift().setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.getHardware().getLift().setTargetPosition(RobotInfo.liftTop);
                    robot.getHardware().getLift().setPower(-0.8);
                }
    
                @Override
                public void interrupt() {
                    super.interrupt();
                }
            };
            moveElevator.start();
            robot.strafeTiles(2.55f, 0.5f);
            while(robot.getHardware().getLift().isBusy() && opModeIsActive()) {}
            robot.drive(-4f, 0.2f);
            clawController.setClawRotation(ClawController.ClawRotation.BACK);
            Thread.sleep(1000);
            clawController.setClawState(ClawController.ClawState.OPEN);
            robot.drive(6f, 0.5f);
            robot.strafeTiles(-0.5f, 0.5f);
            clawController.setClawState(ClawController.ClawState.CLOSED);
            clawController.setClawRotation(ClawController.ClawRotation.FRONT);
            Thread lowerLift = new Thread() {
                @Override
                public synchronized void start() {
                    super.start();
                }
    
                @Override
                public void run() {
                    clawController.setLiftPosition(ClawController.LiftPosition.GROUND);
                }
            };
            lowerLift.start();
            if (state == SleeveDetect.SignalState.RED_SQUARE) {
                robot.driveTiles(1, 0.5f, -1);
            } else if (state == SleeveDetect.SignalState.GREEN_CIRCLE) {
                // done
            } else if (state == SleeveDetect.SignalState.BLUE_TRIANGLE) {
                robot.driveTiles(-1.1f, 0.5f);
    
            } else {
                // do same as green circle...
                // done
            }
            
            while(lowerLift.isAlive()) {}
        }
    }