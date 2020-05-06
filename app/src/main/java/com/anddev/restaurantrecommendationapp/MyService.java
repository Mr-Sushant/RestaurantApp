package com.anddev.restaurantrecommendationapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {


    DatabaseReference reference;
    String Channel = "Channel_id";
    int notificationId = 200;





    @Override
    public void onCreate() {

        reference = FirebaseDatabase.getInstance().getReference("Customers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                   // Toast.makeText(MyService.this, "Value Changed" , Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        CharSequence name = "Name";
                        String description = "Name Changed";
                        int importance = NotificationManager.IMPORTANCE_DEFAULT;
                        NotificationChannel channel = new NotificationChannel(Channel, name, importance);
                        channel.setDescription(description);

                        NotificationManager notificationManager = getSystemService(NotificationManager.class);
                        notificationManager.createNotificationChannel(channel);}

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),Channel);
                    builder.setSmallIcon(R.drawable.ic_notify);
                    builder.setContentTitle("Name Changed");
                    builder.setContentText("A new notification has arrived");
                    builder.setPriority(NotificationCompat.PRIORITY_HIGH);


                    NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(getApplicationContext());

                    notificationmanager.notify(notificationId, builder.build());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Invoke background service onStartCommand method.", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }


    public MyService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
