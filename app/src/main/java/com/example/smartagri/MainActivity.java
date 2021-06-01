package com.example.smartagri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseReference reference;

    TextView tempText;
    TextView humidText;
    TextView soilText;
    TextView phText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempText = findViewById(R.id.temp_text);
        humidText = findViewById(R.id.humid_text);
        soilText = findViewById(R.id.soil_text);
        phText = findViewById(R.id.ph_text);

        SetTemperature();
        SetHumidity();
        //SetSoilMoisture();
      //SetSoilPH();
    }

    public void SetTemperature(){

        List<String> temperatures = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("Temperature");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                temperatures.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    temperatures.add(snapshot.child("temp").getValue().toString());
                }
                tempText.setText(temperatures.get(temperatures.size()-1)+"\u2103");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void SetHumidity(){

        List<String> humudities = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("Humidity");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                humudities.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    humudities.add(snapshot.child("humid").getValue().toString());
                }
                humidText.setText(humudities.get(humudities.size()-1)+"%");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   /* public void SetSoilMoisture(){

        List<String> soilMoistures = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("SoilMoisture");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                soilMoistures.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    soilMoistures.add(snapshot.child("soil").getValue().toString());
                }
                soilText.setText(soilMoistures.get(soilMoistures.size()-1));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void SetSoilPH(){

        List<String> soilPH = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("PH");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                soilPH.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    soilPH.add(snapshot.child("ph").getValue().toString());
                }
                phText.setText(soilPH.get(soilPH.size()-1));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/






}
