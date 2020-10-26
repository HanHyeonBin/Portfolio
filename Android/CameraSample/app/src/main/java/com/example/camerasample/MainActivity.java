package com.example.camerasample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Camera mCamera;
    private CameraPreview mPreview;
    Button btnCapture;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 카메라 인스턴스 얻고 멤버에 설정
        mCamera = getCameraInstance();



        btnCapture = findViewById(R.id.btn_capture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.autoFocus(new Camera.AutoFocusCallback(){

                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if(success){
                            mCamera.takePicture(null,null, mPicture);
                        }
                    }
                });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (checkAutoFocusFunction()){
            setAutoFocusFunction();
            setAutoFocusPreview();
        }
        else{
            Toast.makeText(getApplicationContext(), "자동 초점 기능 미지원 기기!", Toast.LENGTH_SHORT).show();
        }
        if (checkCameraHArdware(this)){
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);

            hideSystemUI();
        }

        else {
            Toast.makeText(getApplicationContext(), "장치에 카메라를 지원하지 않습니다.\n 앱을 종료합니다!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCameraHArdware(Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            return true;
        else
            return false;
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try{
            c = Camera.open();
        }catch (Exception e){

        }
        return c;
    }

    private void hideSystemUI(){
       View decorView = getWindow().getDecorView();
       decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE|
               View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
               View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
               View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //JPEG 콜백 객체

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {


            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if(pictureFile == null){
                Log.d("TAG", "Error creating media file, check storage permisiions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d("TAG", "File not found : "+ e.getMessage());
            } catch (IOException e) {
                Log.d("TAG", "Error accessing file : "+ e.getMessage());
            }
        }
    };

    //이미지 또는 비디오 저장을 위한 파일 객체 메서드
    private static File getOutputMediaFile(int type){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"");

        if(! mediaStorageDir.exists()){
            if(! mediaStorageDir.mkdir()){
                Log.d("TAG", "디렉토리 생성 실패!");
                return null;
            }
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if(type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + " IMG_" + timestamp + ".jpg");

        }else if (type == MEDIA_TYPE_VIDEO){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + " VID_" + timestamp + ".mp4");
        }else {
            return null;
        }

        return mediaFile;


    }

    private boolean checkAutoFocusFunction(){
        boolean isOk = false;
        Camera.Parameters params = mCamera.getParameters();
        List<String> focusModes = params.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
            isOk = true;
        }
        return isOk;

    }

    private void setAutoFocusFunction(){
        Camera.Parameters params = mCamera.getParameters();

        params.setFocusMode(Camera.Parameters.FLASH_MODE_AUTO);

        mCamera.setParameters(params);
    }

    private void setAutoFocusPreview(){
        Camera.Parameters params = mCamera.getParameters();
        if(params.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)){
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        }
        mCamera.setParameters(params);
    }
}