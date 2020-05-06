package com.anddev.restaurantrecommendationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Notification_activity extends AppCompatActivity {


    DatabaseReference reference;

    Button Change;

    EditText cust_name;

    String Channel = "RestaurantApp";

    int notificationId = 100;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activity);

        Intent intent = new Intent(Notification_activity.this,MyService.class);
        startService(intent);


        cust_name = findViewById(R.id.customer_name);

        Change = findViewById(R.id.change_button);

        reference = FirebaseDatabase.getInstance().getReference("Customers");

        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cust_name.getText().toString();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Name",name );
                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(Notification_activity.this, "Name Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Customers");
                reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {

                    createNotificationChannel();
                    getNotification();
                }










            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    private void getNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channel);
        builder.setSmallIcon(R.drawable.ic_notify);
        builder.setContentTitle("Name Changed");
        builder.setContentText("A new notification has arrived");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

 
        notificationManager.notify(notificationId, builder.build());
    }

    private  void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Name";
            String description = cust_name.getText().toString();
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Channel, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(Notification_activity.this,MyService.class);
        startService(intent);

        super.onDestroy();
    }

}
