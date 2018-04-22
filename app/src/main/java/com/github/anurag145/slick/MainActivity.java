package com.github.anurag145.slick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.SystemRequirementsChecker;
import com.github.anurag145.slick.estimote.BeaconID;
import com.github.anurag145.slick.estimote.EstimoteCloudBeaconDetails;
import com.github.anurag145.slick.estimote.EstimoteCloudBeaconDetailsFactory;
import com.github.anurag145.slick.estimote.ProximityContentManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean beaconNotificationsEnabled = false;
    private static final String TAG = "MainActivity";
    TextView textView;
    ProgressBar progressBar;
    Button[] button = new Button[3];
    Long tsLong;
    int count=0;
    private ProximityContentManager proximityContentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= findViewById(R.id.indeterminateBar);
        textView=findViewById(R.id.text_main);
        button[0]=findViewById(R.id.ac);
        button[1]=findViewById(R.id.tv);
        button[2]=findViewById(R.id.fan);
        button[0].setOnClickListener(this);
        button[1].setOnClickListener(this);
        button[2].setOnClickListener(this);
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
                    String s=beaconDetails.toString();
                    textView.setText(s);
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    button[0].setVisibility(View.VISIBLE);
                    button[1].setVisibility(View.VISIBLE);
                    button[2].setVisibility(View.VISIBLE);
                     tsLong = System.currentTimeMillis()/1000;

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    Data ob= new Data(UserInfo.user,"true","false",tsLong,0,0);
                   databaseReference.child("contracts").child(tsLong.toString()).setValue(ob);


                } else {
                    textView.setText("No beacons near by...");
                    textView.setVisibility(View.VISIBLE);
                    button[0].setVisibility(View.VISIBLE);
                    button[1].setVisibility(View.VISIBLE);
                    button[2].setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

            }
        });

    }
    @Override
    public void onClick(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, "https://majorproject-808f7.firebaseio.com/contracts/"+tsLong.toString()+"/count.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res",response);
                         Long tss=System.currentTimeMillis()/1000;

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        Data ob= new Data(UserInfo.user,"true","true",tsLong,tss,Integer.parseInt(response));
                        databaseReference.child("contracts").child(tsLong.toString()).setValue(ob);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        queue.add(getRequest);



    }
    @Override
    protected void onResume() {
        super.onResume();



        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e("Permission","Denied");
        } else {


            proximityContentManager.startContentUpdates();


        }

    }



    @Override
    protected void onStop() {
        super.onStop();


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


