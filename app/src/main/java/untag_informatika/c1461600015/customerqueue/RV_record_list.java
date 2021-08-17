package untag_informatika.c1461600015.customerqueue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RV_record_list extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView TV_no1, TV_nama1,TV_keperluan1, TV_status1, TV_loket, TV_date;
    public RV_record_list( View itemView) {
        super(itemView);
        TV_no1 = (TextView) itemView.findViewById(R.id.TV_no1);
            TV_nama1 = (TextView) itemView.findViewById(R.id.TV_nama1);
            TV_keperluan1 = (TextView) itemView.findViewById(R.id.TV_keperluan1);
            TV_status1 = (TextView) itemView.findViewById(R.id.TV_status1);
            TV_loket=(TextView)itemView.findViewById(R.id.TV_loket1);
            TV_date=(TextView)itemView.findViewById(R.id.TV_record_date);
    }

    @Override
    public void onClick(View v) {

    }

//    ArrayList<String> no,nama,keperluan,status;
//    Con


//
//    public RV_record_list(ArrayList<String> no, ArrayList<String> nama, ArrayList<String> status, ArrayList<String> keperluan, Context mContext) {
//        this.no = no;
//        this.nama = nama;
//        this.keperluan = keperluan;
//        this.status = status;
//        this.context = mContext;
//    }
//
//    @Override
//    public RV_record_list.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).
//                inflate(R.layout.activity_record_list, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(RV_record_list.ViewHolder holder, int position) {
//        holder.TV_no1.setText(no.get(position));
//        holder.TV_nama1.setText(nama.get(position));
//        holder.TV_keperluan1.setText(keperluan.get(position));
//        holder.TV_status1.setText(status.get(position));
//    }
//
//    @Override
//    public int getItemCount() { return no.size();
//
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView TV_no1, TV_nama1,TV_keperluan1, TV_status1;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            TV_no1 = (TextView) itemView.findViewById(R.id.TV_no1);
//            TV_nama1 = (TextView) itemView.findViewById(R.id.TV_nama1);
//            TV_keperluan1 = (TextView) itemView.findViewById(R.id.TV_keperluan1);
//            TV_status1 = (TextView) itemView.findViewById(R.id.TV_status1);
//        }
//    }
}
