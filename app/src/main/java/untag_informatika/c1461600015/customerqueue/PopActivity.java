package untag_informatika.c1461600015.customerqueue;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class PopActivity extends AppCompatActivity {

    TextView TV_panggil, TV_nama; Button BT_confirm, BT_pending;
    Handler handler;
    String daftar[];
    Cursor cursor;
    MediaPlayer playerantrian, player1 ,
            player2, player3, playerloket;
    int x;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String selectedkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        TV_panggil = findViewById(R.id.TV_panggil);
        BT_confirm = findViewById(R.id.BT_confirm);
        TV_nama = findViewById(R.id.TV_nama);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("antrian");

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
        String angka = getIntent().getStringExtra("angka");
        String kosong = getIntent().getStringExtra("kosong");
        String nama = getIntent().getStringExtra("nama");
        final String loket = getIntent().getStringExtra("loket");
        selectedkey = getIntent().getStringExtra("key");
//        String loket = getIntennt().getStringExtra("loket");
        TV_panggil.setText(angka);
        TV_nama.setText(nama);
//        TV_panggil.setText(loket);

        if(playerantrian != null) {
            playerantrian.reset();
        } else {playerantrian = MediaPlayer.create(PopActivity.this, R.raw.no_antrian);
            playerantrian.start();}

        if (player1!=null){
            player1.reset();
        }
        if (player2 != null){
            player2.reset();
        }
        translateAngka(angka, kosong);
        loketKe(loket);





//        SQLiteDatabase dbtampil = dbHelper.getReadableDatabase();
//        cursor = dbtampil.rawQuery("SELECT * FROM Antrian WHERE nomor =? and status =?" , new String[] {TV_panggil.getText().toString(), "uncall"});
//        cursor.moveToPosition(0);
//        TV_nama.setText(cursor.getString(1));
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.execSQL("update Antrian set status='"+
//                "called" + "' where nomor='" +
//                TV_panggil.getText().toString()+"' AND status ='uncall' and nama='"+ TV_nama.getText().toString() +"'");



        BT_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(playerantrian != null){
                            playerantrian.reset();
                            playerantrian = null;}
                        if(player1 != null){
                            player1.reset();
                            player1 = null;}
                        if(player2 != null){
                            player2.reset();
                            player2 = null;
                        }
                        if(player3 != null){
                            player3.reset();
                        }
                        if (playerloket != null){
                            playerloket.reset();
                        }
                    }
                },3000);
                databaseReference
                        .child(selectedkey)
                        .child("status").setValue("called")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(getBaseContext(), "Data Changed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                databaseReference
                        .child(selectedkey)
                        .child("loket").setValue(loket)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(getBaseContext(), "Data Changed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
    }

    void translateAngka(String angka, String kosong){
        int panjang = angka.length();
        char[] satuan = angka.toCharArray();
        for(int i =0; i< panjang; i++){
            int durasi = 0;
            if(i==1){
                durasi = 800;
                fsatuan2(satuan[i], durasi-100);
            }else if(i==2){
                durasi = 1900;
                fsatuan3(satuan[i], durasi-100);
            }else
                fsatuan(satuan[i], durasi-200);
        }

//        int panjang = angka.length();
//        char[] satuan = angka.toCharArray();
//        for (int i =0; i< panjang; i++){
//            if(panjang == 1 || i+1 == panjang) {
//                int durasi =0;
//                if(panjang == 2){
//                    durasi = 900;
//                }
//                fsatuan(angka.charAt(i), durasi);
//            }else if(panjang == 2 || i+2 == panjang){
//                if(satuan[i] == '1' && satuan[i+1] == '0'){
//                    belasan(satuan[i+1]);
//                    i+=1;
//                } else if(satuan[i] == '1'){
//                    belasan(satuan[i+1]);
//                    i+=1;
//                } else if(i+1 != panjang){ puluhan(angka.charAt(i));}
//            }else if(panjang == 3 ){
//                if(satuan[i] == '1' && satuan[i+1] == '0' && satuan [i+2] == 0 ){
//                    ratusan(satuan[i]);
//                    i+=2;
//                }else if(satuan[i] == '1' && satuan[i+1] == '0'){
//                    ratusan(satuan[i]);
//                    i+=1;
//                } else ratusan(satuan[i]);
//            }
//            else {
//            }
//        }

    }

    void fsatuan(char angka, int durasi){
        if( angka == '0') {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s0);
                    player1.start();
                }
            }, playerantrian.getDuration() - 400 + durasi);
        }else if( angka == '1'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s1);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '2' ){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s2);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '3'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s3);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '4'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s4);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '5'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s5);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '6'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s6);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '7'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s7);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '8'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s8);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '9'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s9);
                    player1.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }
    }

    void fsatuan2(char angka, int durasi){
        if( angka == '0') {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s0);
                    player2.start();
                }
            }, playerantrian.getDuration() - 400 + durasi);
        }else if( angka == '1'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s1);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '2' ){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s2);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '3'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s3);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '4'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s4);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '5'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s5);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '6'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s6);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '7'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s7);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '8'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s8);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '9'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s9);
                    player2.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }
    }

//    void belasan(char angka){
//        if( angka == '0' ) {
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s10);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if( angka == '1'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s11);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '2'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s12);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '3'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s13);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '4'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s14);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '5'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s15);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '6'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s16);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '7'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s17);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '8'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s18);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '9'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player1 = MediaPlayer.create(getBaseContext(), R.raw.s19);
//                    player1.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }
//    }

    void fsatuan3(char angka, int durasi){
        if( angka == '0') {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s0);
                    player3.start();
                }
            }, playerantrian.getDuration() - 400 + durasi);
        }else if( angka == '1'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s1);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '2' ){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s2);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '3'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s3);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '4'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s4);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '5'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s5);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '6'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s6);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '7'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s7);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '8'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s8);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }else if(angka == '9'){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    player3 = MediaPlayer.create(getBaseContext(), R.raw.s9);
                    player3.start();
                }
            }, playerantrian.getDuration()-400+durasi);
        }
    }

//    void puluhan(char angka){
//        if( angka == '2'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s20);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '3'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s30);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '4'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s40);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '5'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s50);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '6'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s60);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '7'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s70);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '8'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s80);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }else if(angka == '9'){
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    player2 = MediaPlayer.create(getBaseContext(), R.raw.s90);
//                    player2.start();
//                }
//            }, playerantrian.getDuration()-400);
//        }
//    }

//    private void ratusan(char angka){
//        if( angka == '1'){
//            BT_angka.setText(BT_angka.getText()+"seratus ");
//        }else if( angka == '2'){
//            BT_angka.setText(BT_angka.getText()+"dua ratus ");
//        }else if(angka == '3'){
//            BT_angka.setText(BT_angka.getText()+"tiga ratus ");
//        }else if(angka == '4'){
//            BT_angka.setText(BT_angka.getText()+"empat ratus ");
//        }else if(angka == '5'){
//            BT_angka.setText(BT_angka.getText()+"lima ratus ");
//        }else if(angka == '6'){
//            BT_angka.setText(BT_angka.getText()+"enam ratus ");
//        }else if(angka == '7'){
//            BT_angka.setText(BT_angka.getText()+"tujuh ratus ");
//        }else if(angka == '8'){
//            BT_angka.setText(BT_angka.getText()+"delapan ratus ");
//        }else if(angka == '9'){
//            BT_angka.setText(BT_angka.getText()+"sembilan ratus ");
//        }
//    }

    void loketKe(String data){
        if(data.equals("loket 1")){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(playerloket == null){
                        playerloket = MediaPlayer.create(getBaseContext(), R.raw.loket_1);
                    }
                    playerloket.start();
                }
            }, playerantrian.getDuration()-400+2700);
        }else if(data.equals("loket 2")){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(playerloket == null){
                        playerloket = MediaPlayer.create(getBaseContext(), R.raw.loket_2);
                    }
                    playerloket.start();
                }
            }, playerantrian.getDuration()-400+2700);
        }else if(data.equals("loket 3")){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(playerloket == null){
                        playerloket = MediaPlayer.create(getBaseContext(), R.raw.loket_3);
                    }
                    playerloket.start();
                }
            }, playerantrian.getDuration()-400+2700);
        }
    }
}

