package com.anddev.restaurantrecommendationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.anddev.restaurantrecommendationapp.Model.Users;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {




    TextView rest_one,rest_two,rest_three,rest_four,rest_five,rest_six,rest_seven,rest_eight,rest_nine,rest_ten;
    ImageView r11,r12,r13,r14,r15;
    ImageView r21,r22,r23,r24,r25;
    ImageView r31,r32,r33,r34,r35;
    ImageView r41,r42,r43,r44,r45;
    ImageView r51,r52,r53,r54,r55;

    TextView rev1,rev2;

    ImageButton logout;



    GoogleSignInClient mGoogleSignInClient;


    FirebaseUser firebaseUser;
    DatabaseReference reference1, reference2,reference3,reference4, reference5;


    Button notify;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        notify = findViewById(R.id.notification);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,Notification_activity.class));
            }
        });


        logout = findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(StartActivity.this,MainActivity.class));
                    }
                });
            }
        });




        rest_one = findViewById(R.id.rest_name_one);
        rest_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,DiveNDine.class));
            }
        });
        rest_two = findViewById(R.id.rest_name_two);
        rest_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,Joy.class));
            }
        });
        rest_three = findViewById(R.id.rest_name_three);
        rest_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,HookieDookie.class));
            }
        });
        rest_four = findViewById(R.id.rest_name_four);
        rest_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,HashTag.class));
            }
        });
        rest_five = findViewById(R.id.rest_name_five);
        rest_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,MomsPlace.class));
            }
        });


        r11 = findViewById(R.id.image_restone_One);
        r12 = findViewById(R.id.image_restone_Two);
        r13 = findViewById(R.id.image_restone_Three);
        r14 = findViewById(R.id.image_restone_Four);
        r15 = findViewById(R.id.image_restone_Five);

        r21 = findViewById(R.id.image_resttwo_One);
        r22 = findViewById(R.id.image_resttwo_Two);
        r23 = findViewById(R.id.image_resttwo_Three);
        r24 = findViewById(R.id.image_resttwo_Four);
        r25 = findViewById(R.id.image_restfive_Five);

        r31 = findViewById(R.id.image_restthree_One);
        r32 = findViewById(R.id.image_restthree_Two);
        r33 = findViewById(R.id.image_restthree_Three);
        r34 = findViewById(R.id.image_restthree_Four);
        r35 = findViewById(R.id.image_restthree_Five);

        r41 = findViewById(R.id.image_restfour_One);
        r42 = findViewById(R.id.image_restfour_Two);
        r43 = findViewById(R.id.image_restfour_Three);
        r44 = findViewById(R.id.image_restfour_Four);
        r45 = findViewById(R.id.image_restfive_Five);

        r51 = findViewById(R.id.image_restfive_One);
        r52 = findViewById(R.id.image_restfive_Two);
        r53 = findViewById(R.id.image_restfive_Three);
        r54 = findViewById(R.id.image_restfive_Four);
        r55 = findViewById(R.id.image_restfive_Five);






        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference1 = FirebaseDatabase.getInstance().getReference("DiveNDine");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int r1 = 0;
                int i1=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    int i = (int)dataSnapshot1.getChildrenCount();
                    if(i==4)
                    {

                        String fname = dataSnapshot1.child("First Name").getValue(String.class);
                        String lname = dataSnapshot1.child("Last Name").getValue(String.class);
                        String review = dataSnapshot1.child("Review").getValue(String.class);
                        String vis = dataSnapshot1.child("Visited").getValue(String.class);
                        if(vis.equals("Yes")){
                            r1++;
                            if(r1 == 1){
                                rev1 = findViewById(R.id.review_restone_one);
                              rev1.setText(review + " - " + fname + " " + lname);}
                            if(r1 == 2){
                                rev2 = findViewById(R.id.review_restone_two);
                                rev2.setText(review + " - " + fname + " " + lname);}


                        }
                    }
                    if(i == 1)
                    {
                       i1++;
                       String im = dataSnapshot1.child("imageURL").getValue(String.class);
                       if(i1==1)
                           Glide.with(getApplicationContext()).load(im).into(r11);
                       if(i1==2)
                           Glide.with(getApplicationContext()).load(im).into(r12);
                        if(i1==3)
                            Glide.with(getApplicationContext()).load(im).into(r13);
                        if(i1==4)
                            Glide.with(getApplicationContext()).load(im).into(r14);
                        if(i1==5)
                            Glide.with(getApplicationContext()).load(im).into(r15);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        reference2 = FirebaseDatabase.getInstance().getReference("Joy Restaurant");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int r1 = 0;
                int i1=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    int i = (int)dataSnapshot1.getChildrenCount();
                    if(i==4)
                    {

                        String fname = dataSnapshot1.child("First Name").getValue(String.class);
                        String lname = dataSnapshot1.child("Last Name").getValue(String.class);
                        String review = dataSnapshot1.child("Review").getValue(String.class);
                        String vis = dataSnapshot1.child("Visited").getValue(String.class);
                        if(vis.equals("Yes")){
                            r1++;
                            if(r1 == 1){
                                rev1 = findViewById(R.id.review_resttwo_one);
                                rev1.setText(review + " - " + fname + " " + lname);}
                            if(r1 == 2){
                                rev2 = findViewById(R.id.review_resttwo_two);
                                rev2.setText(review + " - " + fname + " " + lname);}


                        }
                    }
                    if(i == 1)
                    {
                        i1++;
                        String im = dataSnapshot1.child("imageURL").getValue(String.class);
                        if(i1==1)
                            Glide.with(getApplicationContext()).load(im).into(r21);
                        if(i1==2)
                            Glide.with(getApplicationContext()).load(im).into(r22);
                        if(i1==3)
                            Glide.with(getApplicationContext()).load(im).into(r23);
                        if(i1==4)
                            Glide.with(getApplicationContext()).load(im).into(r24);
                        if(i1==5)
                            Glide.with(getApplicationContext()).load(im).into(r25);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference3 = FirebaseDatabase.getInstance().getReference("Hookie Dookie");
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int r1 = 0;
                int i1=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    int i = (int)dataSnapshot1.getChildrenCount();
                    if(i==4)
                    {

                        String fname = dataSnapshot1.child("First Name").getValue(String.class);
                        String lname = dataSnapshot1.child("Last Name").getValue(String.class);
                        String review = dataSnapshot1.child("Review").getValue(String.class);
                        String vis = dataSnapshot1.child("Visited").getValue(String.class);
                        if(vis.equals("Yes")){
                            r1++;
                            if(r1 == 1){
                                rev1 = findViewById(R.id.review_restthree_one);
                                rev1.setText(review + " - " + fname + " " + lname);}
                            if(r1 == 2){
                                rev2 = findViewById(R.id.review_restthree_two);
                                rev2.setText(review + " - " + fname + " " + lname);}


                        }
                    }
                    if(i == 1)
                    {
                        i1++;
                        String im = dataSnapshot1.child("imageURL").getValue(String.class);
                        if(i1==1)
                            Glide.with(getApplicationContext()).load(im).into(r31);
                        if(i1==2)
                            Glide.with(getApplicationContext()).load(im).into(r32);
                        if(i1==3)
                            Glide.with(getApplicationContext()).load(im).into(r33);
                        if(i1==4)
                            Glide.with(getApplicationContext()).load(im).into(r34);
                        if(i1==5)
                            Glide.with(getApplicationContext()).load(im).into(r35);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference4 = FirebaseDatabase.getInstance().getReference("HashTag");
        reference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int r1 = 0;
                int i1=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    int i = (int)dataSnapshot1.getChildrenCount();
                    if(i==4)
                    {

                        String fname = dataSnapshot1.child("First Name").getValue(String.class);
                        String lname = dataSnapshot1.child("Last Name").getValue(String.class);
                        String review = dataSnapshot1.child("Review").getValue(String.class);
                        String vis = dataSnapshot1.child("Visited").getValue(String.class);
                        if(vis.equals("Yes")){
                            r1++;
                            if(r1 == 1){
                                rev1 = findViewById(R.id.review_restfour_one);
                                rev1.setText(review + " - " + fname + " " + lname);}
                            if(r1 == 2){
                                rev2 = findViewById(R.id.review_restfour_two);
                                rev2.setText(review + " - " + fname + " " + lname);}


                        }
                    }
                    if(i == 1)
                    {
                        i1++;
                        String im = dataSnapshot1.child("imageURL").getValue(String.class);
                        if(i1==1)
                            Glide.with(getApplicationContext()).load(im).into(r41);
                        if(i1==2)
                            Glide.with(getApplicationContext()).load(im).into(r42);
                        if(i1==3)
                            Glide.with(getApplicationContext()).load(im).into(r43);
                        if(i1==4)
                            Glide.with(getApplicationContext()).load(im).into(r44);
                        if(i1==5)
                            Glide.with(getApplicationContext()).load(im).into(r45);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference5 = FirebaseDatabase.getInstance().getReference("Mom's Place");
        reference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int r1 = 0;
                int i1=0;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    int i = (int)dataSnapshot1.getChildrenCount();
                    if(i==4)
                    {

                        String fname = dataSnapshot1.child("First Name").getValue(String.class);
                        String lname = dataSnapshot1.child("Last Name").getValue(String.class);
                        String review = dataSnapshot1.child("Review").getValue(String.class);
                        String vis = dataSnapshot1.child("Visited").getValue(String.class);
                        if(vis.equals("Yes")){
                            r1++;
                            if(r1 == 1){
                                rev1 = findViewById(R.id.review_restfive_one);
                                rev1.setText(review + " - " + fname + " " + lname);}
                            if(r1 == 2){
                                rev2 = findViewById(R.id.review_restfive_two);
                                rev2.setText(review + " - " + fname + " " + lname);}


                        }
                    }
                    if(i == 1)
                    {
                        i1++;
                        String im = dataSnapshot1.child("imageURL").getValue(String.class);
                        if(i1==1)
                            Glide.with(getApplicationContext()).load(im).into(r51);
                        if(i1==2)
                            Glide.with(getApplicationContext()).load(im).into(r52);
                        if(i1==3)
                            Glide.with(getApplicationContext()).load(im).into(r53);
                        if(i1==4)
                            Glide.with(getApplicationContext()).load(im).into(r54);
                        if(i1==5)
                            Glide.with(getApplicationContext()).load(im).into(r55);


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }



}
