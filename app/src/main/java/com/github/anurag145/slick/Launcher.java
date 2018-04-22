package com.github.anurag145.slick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Launcher extends AppCompatActivity {
    EditText editText;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        editText=findViewById(R.id.name);
        button=findViewById(R.id.next);
        SharedPreferences preferences= getSharedPreferences("UserName",0);
        String s =preferences.getString("Name",null);
        if(s!=null)
        {
            UserInfo.user=s;

            Intent intent = new Intent(Launcher.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        editText.findFocus();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= editText.getText().toString();

                if(s.length()>0)
                {

                    SharedPreferences preferences= getSharedPreferences("UserName",0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("Name",s);

                    editor.apply();
                    UserInfo.user=s;

                    Intent intent = new Intent(Launcher.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }else
                    Toast.makeText(getApplicationContext(),"Please enter all the details",Toast.LENGTH_LONG).show();
            }
        });
    }


}
