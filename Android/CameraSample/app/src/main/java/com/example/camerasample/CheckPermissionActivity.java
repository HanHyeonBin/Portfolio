package com.example.camerasample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CheckPermissionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_permission);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 사용자 os 버전 검사 -> 23 이상 버전인지 판단 허가권 요청 정보 표시 유무
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int permissionCheck = checkSelfPermission(Manifest.permission.CAMERA);
            int permissionCheck2 = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);


            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
         else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
        else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override //허가권 메서드
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1000){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_DENIED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "권한 요청을 거부하였습니다", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "권한 설정 실패!\n앱을 다시 설치하세요! \n앱 종료!",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
