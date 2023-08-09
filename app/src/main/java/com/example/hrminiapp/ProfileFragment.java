package com.example.hrminiapp;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ProfileFragment extends Fragment {
    View v;
    ImageView imgProfilePic;
    Intent cameraIntent;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile,  container,
                false);
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        imgProfilePic = (ImageView) v.findViewById(R.id.imageProfilePic);
        CaptureImage();
        return v;
    }

    private void CaptureImage()
    {
        imgProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermissionAndOpenCamera();
            }
        });
    }

    //Check Camera permission is denied or not. If denied, request permission
    private void checkPermissionAndOpenCamera(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},5);
        } else {
            takePhoto();
        }
    }

    //Take a picture using implicit Intent
    private void takePhoto()
    {
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startForResult.launch(cameraIntent);
    }

    // Contract Method - find other one
    // A Contract defines an intent which will be used to start an activity and receives
    // the result intent from the started activity and parses it
    ActivityResultLauncher<Intent> startForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //If result if OK or not null
                    if(result!=null && result.getResultCode() == RESULT_OK)
                    {
                        // Receive a photo image and show it
                        if(result.getData() != null)
                        {
                            Bundle extras = result.getData().getExtras();
                            Bitmap photo = (Bitmap) extras.get("data");
                            imgProfilePic.setImageBitmap(photo);
                        }
                    }
                }
            });
}