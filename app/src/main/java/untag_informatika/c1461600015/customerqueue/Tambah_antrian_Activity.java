package untag_informatika.c1461600015.customerqueue;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tambah_antrian_Activity extends AppCompatActivity {

    DataHelper dbHelper;
    EditText ET_nama, ET_keperluan;
    Button BT_tambah, BT_batal;

    //firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference_number;
//    FirebaseRecyclerOptions<Post> options;
//    FirebaseRecyclerAdapter<Post, MainRecycler> adapter;

    Post selectedPost;
    String selectedkey;

    String angka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_antrian_);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("antrian");
        databaseReference_number = firebaseDatabase.getReference("number");

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

        ET_nama = (EditText) findViewById(R.id.ET_nama);
        BT_tambah = (Button) findViewById(R.id.BT_tambah);
        ET_keperluan = (EditText) findViewById(R.id.ET_keperluan);
        BT_batal =(Button) findViewById(R.id.BT_batal);

        angka = getIntent().getStringExtra("angka");

        BT_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ET_nama.getText().toString().equals("")|| ET_keperluan.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Masukkan data terlebih dahulu !", Toast.LENGTH_SHORT).show();
                } else{
//                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                    db.execSQL("insert into Antrian(nomor, nama, status, keperluan) values('" +
//                            angka + "','" +
//                            ET_nama.getText().toString()+ "','" +
//                            "uncall" + "','" +
//                            ET_keperluan.getText().toString()+ "')");
//                    MainActivity.ma.tambahlist();
                    MainActivity.ma.poscoment(angka, ET_nama.getText().toString(), "uncall",
                            ET_keperluan.getText().toString(), "none");
                    finish();
                }
            }
        });
        BT_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
