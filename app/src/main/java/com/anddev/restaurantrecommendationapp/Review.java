package com.anddev.restaurantrecommendationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;

public class Review extends AppCompatActivity {

    EditText fname,lname,restname,review;
    ImageView ione,itwo,ithree,ifour,ifive;
    Button btn_submit;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    StorageTask uploadTask;

    private final int IMAGE_REQUEST = 1;

    private Uri imageUri;

    private  int i =1;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Reviews");

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        restname = findViewById(R.id.restname);
        review = findViewById(R.id.review);

        ione = findViewById(R.id.imageone);
        itwo = findViewById(R.id.imagetwo);
        ithree = findViewById(R.id.imagethree);
        ifour = findViewById(R.id.imagefour);
        ifive = findViewById(R.id.imagefive);


        ione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });
        ifive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                openImage();
            }
        });
        itwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                openImage();
            }
        });
        ithree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                openImage();
            }
        });
        ifour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                openImage();
            }
        });

        btn_submit = findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fname = fname.getText().toString();
                String Lname = lname.getText().toString();
                String Restname = restname.getText().toString();
                String Review = review.getText().toString();

                storeData(Fname,Lname,Restname,Review);
                for(int j =1;j<=i;j++) {
                    String im = "imageURL" ;
                    uploadImage("imageURL" + j,i);
                }
            }
        });






    }

    private void storeData(String fname, String lname, String restname, String review) {

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("First Name",fname);
        hashMap.put("Last Name",lname);
        hashMap.put("Restaurant Name",restname);
        hashMap.put("Review",review);

        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Review.this, "Review Submitted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            imageUri = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                imageUri);
                if(i == 1)
                    ione.setImageBitmap(bitmap);
                else if(i==2)
                    itwo.setImageBitmap(bitmap);
                else if(i==3)
                    ithree.setImageBitmap(bitmap);
                else if(i==4)
                    ifour.setImageBitmap(bitmap);
                else
                    ifive.setImageBitmap(bitmap);

            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void openImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadImage(final String imageURL,final int i){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if(imageUri != null){

            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation< UploadTask.TaskSnapshot,Task<Uri> >() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                    if(! task.isSuccessful()){
                        throw task.getException();
                    }

                    return fileReference.getDownloadUrl();

                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();



                        HashMap<String,Object> map = new HashMap<>();
                        map.put(imageURL,mUri);
                        map.put("Image number",i);
                        reference.push().setValue(map);

                        Toast.makeText(Review.this, "Uploaded", Toast.LENGTH_SHORT).show();



                        pd.dismiss();

                    }
                    else{
                        Toast.makeText(Review.this,"Failed!",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Review.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });

        }else{
            Toast.makeText(Review.this,"No Image Selected",Toast.LENGTH_SHORT).show();
        }

    }

}
