package untag_informatika.c1461600015.customerqueue;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText ET_user,ET_pass; Button BT_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.3));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y =-20;

        getWindow().setAttributes(params);

        ET_user = findViewById(R.id.ET_username);
        ET_pass = findViewById(R.id.ET_pass);
        BT_login = findViewById(R.id.BT_login);

        BT_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ET_user.getText().toString().equals("admin") && ET_pass.getText().toString().equals("kelompok6")){
                    Intent intent = new Intent(v.getContext(), RecordActivity.class);
                    startActivity(intent);
                }else Toast.makeText(getApplicationContext(),"ID tidak terdaftar !", Toast.LENGTH_SHORT).show();

            }
        });

    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 1000 :
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    Toast.makeText(this, "GRANTED" ,Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//
//        }
//    }
}
