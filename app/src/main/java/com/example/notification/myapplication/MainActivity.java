package com.example.notification.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener,CompoundButton.OnCheckedChangeListener {

    private TextView HeadingText,BaterieText,TimeText;
    private Switch Switch,Switch2;
    private CheckBox CheckBox1 , CheckBox2;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mHeadingReference = mRootReference.child("temperatura");
    private DatabaseReference mtimeReference = mRootReference.child("Timp");
    private DatabaseReference mBaterieReference = mRootReference.child("baterie");
    private DatabaseReference mLed1Reference = mRootReference.child("led1");
    private DatabaseReference mLed2Reference = mRootReference.child("led2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HeadingText = (TextView)findViewById(R.id.headingText);
        TimeText = (TextView)findViewById(R.id.timeText);
        BaterieText = (TextView)findViewById(R.id.baterieText);
        Switch = (Switch)findViewById(R.id.switch1);
        Switch2 = (Switch)findViewById(R.id.switch2);
        CheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox2 = (CheckBox)findViewById(R.id.checkBox2);
        Switch.setOnCheckedChangeListener(this);
        Switch2.setOnCheckedChangeListener(this);


    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue()!=null)
        {
            String key = dataSnapshot.getKey();

            if(key.equals("temperatura"))//temperatura
            {
                String temperatura = dataSnapshot.getValue(String.class);
                HeadingText.setText(temperatura);
            }
           // else HeadingText.setText(key);
            if(key.equals("baterie"))//baterie
            {
                //String baterie =Long.toString((Long) dataSnapshot.getValue());
                String baterie = String.valueOf(dataSnapshot.getValue());
                BaterieText.setText(baterie);
            }
            if(key.equals("Timp"))//timp
            {
                String Timp = dataSnapshot.getValue(String.class);
                TimeText.setText(Timp);
            }
            if(key.equals("led1"))
            {   String fleg = String.valueOf(dataSnapshot.getValue());
                if(fleg == "0" )
                 Switch.setChecked(false);
                else
                 Switch.setChecked(true);
            }
            if(key.equals("led2"))
            {   String fleg = String.valueOf(dataSnapshot.getValue());
                if(fleg == "0" )
                    Switch2.setChecked(false);
                else
                    Switch2.setChecked(true);
            }

      }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mHeadingReference.addValueEventListener(this);
        mBaterieReference.addValueEventListener(this);
        mtimeReference.addValueEventListener(this);
        mLed1Reference.addValueEventListener(this);
        mLed2Reference.addValueEventListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(Switch.isChecked()){
            boolean stateled1 = Switch.isChecked();
            int myInt = (stateled1) ? 1 : 0;
            mLed1Reference.setValue(myInt);
        }else{
            boolean stateled1 = Switch.isChecked();
            int myInt = (stateled1) ? 1 : 0;
            mLed1Reference.setValue(myInt);
        }
        if(Switch2.isChecked()) {
            boolean stateled1 = Switch2.isChecked();
            int myInt = (stateled1) ? 1 : 0;
            mLed2Reference.setValue(myInt);
        }
        else{
            boolean stateled1 = Switch2.isChecked();
            int myInt = (stateled1) ? 1 : 0;
            mLed2Reference.setValue(myInt);
        }
    }
}