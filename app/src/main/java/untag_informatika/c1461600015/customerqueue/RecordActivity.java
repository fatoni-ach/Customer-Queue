package untag_informatika.c1461600015.customerqueue;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecordActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Post> options;
    FirebaseRecyclerAdapter<Post, RV_record_list> adapter;

//    RecyclerView RV_record;
//    RecyclerView.Adapter adapter;
    Button bt_print;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> no, nama, keperluan, status;
    RecyclerView rv_record;
    SimpleDateFormat sdf;


    DataHelper dbcenter;
    Cursor cursor;
    Post selectedPost;
    String selectedkey;
    String date;

    ArrayList<String> _nomor, _nama, _status, _keperluan, _loket, _datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        _nomor = new ArrayList<>();
        _nama = new ArrayList<>();
        _status = new ArrayList<>();
        _keperluan = new ArrayList<>();
        _loket = new ArrayList<>();
        _datetime = new ArrayList<>();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(new Date());

        bt_print = (Button)findViewById(R.id.BT_print);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("antrian");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                displaycomment();
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
//                    Log.e("Get Data", post.getHargatotal().toString());
//                    Toast.makeText(getBaseContext(), post.getHargatotal().toString(), Toast.LENGTH_SHORT).show();
                    try {
                        if (post.getDatetime().equals(date)){
                            _nomor.add(post.getNomor());
                            _nama.add(post.getNama());
                            _status.add(post.getStatus());
                            _keperluan.add(post.getKeperluan());
                            _loket.add(post.getLoket());
//                        _datetime.add(post.getDatetime());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        rv_record = (RecyclerView) findViewById(R.id.RV_record);
        rv_record.setLayoutManager(new LinearLayoutManager(this));

        no = new ArrayList<>();
        nama = new ArrayList<>();
        keperluan = new ArrayList<>();
        status = new ArrayList<>();
        dbcenter = new DataHelper(this);

//        SQLiteDatabase db = dbcenter.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM Antrian", null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToPosition(i);
//            no.add(cursor.getString(0));
//            nama.add(cursor.getString(1));
//            status.add(cursor.getString(2));
//            keperluan.add(cursor.getString(3));
////            dataset.add(Integer.toString(i+1));
//        }

//        RV_record = findViewById(R.id.RV_record);
//        RV_record.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(this);
//        RV_record.setLayoutManager(layoutManager);
//
//        adapter = new RV_record_list(no, nama, status, keperluan, this);
//        RV_record.setAdapter(adapter);
        displaycomment();
        bt_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savepdf();
            }
        });
    }

    void savepdf(){
        Document doc=new Document();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date = new String();
//        date = sdf.format(new Date());


//output file path
        File folder = new File(Environment.getExternalStorageDirectory()+"/CustomerQueue");
        folder.mkdirs();
        String outpath=folder.toString()+"/"+date+"_Record.pdf";

        try {
//create pdf writer instance
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
//open the document for writing
            doc.open();
//add paragraph to the document
            doc.add(new Paragraph("================================"));
            doc.add(new Paragraph("Laporan Record Antrian Tanggal : "+date)); //Call Tahun Bulan Tanggal
            doc.add(new Paragraph("================================\n"));
            doc.add(new Paragraph("Nomor        Nama        Status      keperluan         loket"));
            doc.add(new Paragraph("================================"));
            for(int i =0 ; i<_nama.size(); i++){
                doc.add(new Paragraph(_nomor.get(i)+"       \t\t"+_nama.get(i)+"                " +
                        "\t"+_status.get(i)+"           " +
                        "\t"+_keperluan.get(i)+"            " +
                        "\t"+_loket.get(i)));
            }
            doc.add(new Paragraph("================================"));
            doc.add(new Paragraph("================================"));
            doc.add(new Paragraph("================================\n"));
//close the document
            doc.close();
            Toast.makeText(this, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(), outpath, Toast.LENGTH_SHORT).show();
    }

    private void displaycomment(){
        options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(databaseReference.orderByChild("status"), Post.class)
                .build();

        adapter =
                new FirebaseRecyclerAdapter<Post, RV_record_list>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final RV_record_list holder, final int position, @NonNull final Post model) {
                        holder.TV_no1.setText(model.getNomor());
                        holder.TV_nama1.setText(model.getNama());
                        holder.TV_keperluan1.setText(model.getKeperluan());
                        holder.TV_status1.setText(model.getStatus());
                        holder.TV_loket.setText(model.getLoket());
                        holder.TV_date.setText(model.getDatetime());
                    }

                    @NonNull
                    @Override
                    public RV_record_list onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_record_list, viewGroup, false);
                        return new RV_record_list(v);
                    }
                };
        adapter.startListening();
        rv_record.setAdapter(adapter);
    }
}
