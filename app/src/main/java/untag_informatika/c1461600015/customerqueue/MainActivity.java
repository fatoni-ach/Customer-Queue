package untag_informatika.c1461600015.customerqueue;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button  BT_add, BT_reset;
    TextView  TV_next;
    Spinner spinner;
    String loket;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static MainActivity ma;


    private int panjang;
    String datanew, date;
    SimpleDateFormat sdf;

    //firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference_number;
    FirebaseRecyclerOptions<Post> options;
    FirebaseRecyclerAdapter<Post, RV_angka> adapter;

    Post selectedPost;
    String selectedkey;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        date =sdf.format(new Date());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("antrian");
        databaseReference_number = firebaseDatabase.getReference("number");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                displaycomment();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference_number.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Number number = dataSnapshot.child("-LV_XjONlTpYwKgrFr4v").getValue(Number.class);
                panjang = number.getNumber_next();
                if(Integer.toString(panjang+1).length()==1){
                    TV_next.setText("00"+Integer.toString(panjang+1));
                }else if(Integer.toString(panjang+1).length()==2){
                    TV_next.setText("0"+Integer.toString(panjang+1));
                } else {
                    TV_next.setText(Integer.toString(panjang+1));
                }
//                Toast.makeText(getBaseContext(), number.getNumber_next().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spinner =findViewById(R.id.spinner3);
        datanew = spinner.getSelectedItem().toString();

        loadData();

        BT_add = findViewById(R.id.BT_add);
        BT_reset = findViewById(R.id.BT_reset);
        TV_next = findViewById(R.id.TV_next);
        recyclerView = findViewById(R.id.RV_antrian);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ma = this;


        BT_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kosong;
                if(Integer.toString(panjang+2).length()==1){
                    kosong="00";
                }else if(Integer.toString(panjang+2).length()==2){
                    kosong="0";
                } else {
                    kosong ="";
                }
//                Calendar calendar = Calendar.getInstance(Locale.getDefault());
//                Date date = Calendar.getInstance().getTime();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//                Toast.makeText(getApplicationContext(),String.valueOf(date) ,Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(),
//                        String.valueOf(calendar.get(Calendar.YEAR)
//                        +"-"+String.valueOf(calendar.get(Calendar.MONTH)))
//                        +"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), String.valueOf(sdf.format(new Date())),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getBaseContext(), Tambah_antrian_Activity.class);
                intent.putExtra("angka", kosong+Integer.toString(panjang+1));
                startActivity(intent);
            }
        });


        BT_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                layoutManager = new LinearLayoutManager(v.getContext());
//                RV_antrian.setLayoutManager(layoutManager);

                saveData(0);
                TV_next.setText("00"+Integer.toString(panjang+1));
            }
        });

        displaycomment();
    }

    public void displaycomment(){
        options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(databaseReference.orderByChild("status").equalTo("uncall"), Post.class)
                .build();

        adapter =
                new FirebaseRecyclerAdapter<Post, RV_angka>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final RV_angka holder, final int position, @NonNull final Post model) {
                        holder.BT_angka.setText(model.getNomor());
                        holder.BT_angka.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectedPost = model;
                                selectedkey = getSnapshots().getSnapshot(position).getKey();
                                Log.d("Key Item", ""+selectedkey);

                                    Intent intent = new Intent(v.getContext(),PopActivity.class);
                                    intent.putExtra("angka", holder.BT_angka.getText().toString());
                                    intent.putExtra("loket", spinner.getSelectedItem().toString());
                                    intent.putExtra("nama", model.getNama());
                                    intent.putExtra("key", selectedkey);
                                    v.getContext().startActivity(intent);
//                                    Toast.makeText(getBaseContext(), selectedkey, Toast.LENGTH_SHORT).show();
                    //                remove(name);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public RV_angka onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_main_list, viewGroup, false);
                        return new RV_angka(v);
                    }
                };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }



    void saveData(int panjang1){
//        SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("last", panjang1);
//        editor.commit();

        databaseReference_number
                .child("-LV_XjONlTpYwKgrFr4v")
                .child("number_next").setValue(panjang1)
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
    }

    void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        panjang = sp.getInt("last", 0);





    }

    public void poscoment(String nomor, String nama, String status, String keperluan, String loket){
        panjang+=1;
        Post post = new Post(nomor, nama, status, keperluan,loket,date);
        databaseReference.push()
                .setValue(post);

        adapter.notifyDataSetChanged();
        String kosong;
        if(Integer.toString(panjang+2).length()==1){
            kosong="00";
        }else if(Integer.toString(panjang+2).length()==2){
            kosong="0";
        } else {
            kosong ="";
        }
        TV_next.setText(kosong+Integer.toString(panjang+1));

        saveData(panjang);

    }
    @Override
    protected void onStop() {
        if(adapter != null){
            adapter.stopListening();
        }
        super.onStop();
    }
}


