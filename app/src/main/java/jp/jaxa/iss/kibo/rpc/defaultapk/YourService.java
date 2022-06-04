package jp.jaxa.iss.kibo.rpc.defaultapk;

import android.util.Log;

import org.opencv.core.Mat;

import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;


/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    private final String msg = this.getClass().getSimpleName();
    @Override
    protected void runPlan1() {
        Log.d(msg,"start your engine!");
        // write here your plan 1
        // the mission starts
        api.startMission();


        // move to a point
        Log.d(msg,"Kibo will be move to Point1");
        Point point1 = new Point(10.7300f,-7.7500f,4.4800f);
        Quaternion quaternion1 = new Quaternion(0f,0.707f,0f,0.707f);
        api.moveTo(point1,quaternion1,true);

        // report point1 arrival
        api.reportPoint1Arrival();

        //  front flashlight
        api.flashlightControlFront(5);

        // get a camera image
        Mat image_p1 = api.getMatNavCam();

        //irradiate the laser
        api.laserControl(true);

        // take target1 snapshots
        api.takeTarget1Snapshot();

        // turn the laser off
        api.laserControl(false);

        // save image
        api.saveMatImage(image_p1,"test_camera_image");

        // move to Point2
        Log.d(msg,"Kibo will be move to Point2");
        int loopCounter = 0;
        int a_test = 1;
        int b_test = -1;
        while (a_test != b_test && loopCounter < 5){
            Point point2 = new Point(11.27460f,-9.92284f,5.29881f);
            Quaternion quaternion2 = new Quaternion(0f,0f,-0.707f,0.707f);
            api.moveTo(point2,quaternion2,true);
            loopCounter++;
        }
    }

    @Override
    protected void runPlan2() {
        // write here your plan 2
    }

    @Override
    protected void runPlan3() {
        // write here your plan 3
    }
}

