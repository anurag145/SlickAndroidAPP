package com.github.anurag145.slick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.estimote.proximitycontent.R;
import com.github.anurag145.slick.estimote.BeaconID;
import com.github.anurag145.slick.estimote.EstimoteCloudBeaconDetails;
import com.github.anurag145.slick.estimote.EstimoteCloudBeaconDetailsFactory;
import com.github.anurag145.slick.estimote.ProximityContentManager;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.Arrays;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView textView1;
    private ProximityContentManager proximityContentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=(TextView)findViewById(R.id.hi);

        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(

                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 44248, 25292),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 25373, 24529),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 3, 3)),
                new EstimoteCloudBeaconDetailsFactory());
        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {


                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;

                     textView1.setText(beaconDetails.getBeaconName());
                    //Toast.makeText(getApplicationContext(),beaconDetails.getBeaconName(),Toast.LENGTH_LONG).show();

                } else {
                     textView1.setText("Ghanta");
                    //Toast.makeText(getApplicationContext(),"ghanta",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            //wont be an issue (Say Amen)!
        } else {

            proximityContentManager.startContentUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        proximityContentManager.destroy();
    }
}


