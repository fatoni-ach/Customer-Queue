package untag_informatika.c1461600015.customerqueue;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class RV_angka extends RecyclerView.ViewHolder implements View.OnClickListener{
    public Button BT_angka;
    Context context;
    ItemClickListener itemClickListener;
    public RV_angka(View itemView) {
        super(itemView);
        BT_angka =(Button)itemView.findViewById(R.id.BT_angka);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }

//    private ArrayList<String> rvData; int temp; String tempS;
//    Context context;
//    String datanew;
//    Spinner spinner;
//    public RV_angka(ArrayList<String> data, Context mcontext, Spinner spinner){
//        this.rvData = data;
//        this.context = mcontext;
//        this.spinner = spinner;
//    }
//
//    void add(int position, String item){
//        rvData.add(position,item);
//        notifyItemInserted(position);
//
//    }
//
//    public void remove(String item){
//        int position = rvData.indexOf(item);
//        rvData.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public Button BT_angka;
//        Context context;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            context = itemView.getContext();
//            BT_angka = (Button)itemView.findViewById(R.id.BT_angka);
////            BT_angka.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
//////                    translateAngka(BT_angka.getText().toString());
////                    Intent intent = new Intent(context, PopActivity.class);
////                    intent.putExtra("angka", BT_angka.getText().toString());
////                    context.startActivity(intent);
////                }
////            });
//        }
//
//        void translateAngka(String angka){
//            BT_angka.setText("");
//            int panjang = angka.length();
//            char[] satuan = angka.toCharArray();
//            for (int i =0; i< panjang; i++){
//                if(panjang == 1 || i+1 == panjang) {
//                    fsatuan(angka.charAt(i));
//                }else if(panjang == 2 || i+2 == panjang){
//                    if(satuan[i] == '1' && satuan[i+1] == '0'){
//                        belasan(satuan[i+1]);
//                        i+=1;
//                    } else if(satuan[i] == '1'){
//                        belasan(satuan[i+1]);
//                        i+=1;
//                    } else if(i+1 != panjang){ puluhan(angka.charAt(i));}
//                }else if(panjang == 3 ){
//                    if(satuan[i] == '1' && satuan[i+1] == '0' && satuan [i+2] == 0 ){
//                        ratusan(satuan[i]);
//                        i+=2;
//                    }else if(satuan[i] == '1' && satuan[i+1] == '0'){
//                        ratusan(satuan[i]);
//                        i+=1;
//                    } else ratusan(satuan[i]);
//                }
//                else {
//                }
//            }
//        }
//
//        void fsatuan(char angka){
//            if( angka == '1'){
//                BT_angka.setText(BT_angka.getText()+"satu ");
//            }else if(angka == '2' ){
//                BT_angka.setText(BT_angka.getText()+"dua ");
//            }else if(angka == '3'){
//                BT_angka.setText(BT_angka.getText()+"tiga ");
//            }else if(angka == '4'){
//                BT_angka.setText(BT_angka.getText()+"empat ");
//            }else if(angka == '5'){
//                BT_angka.setText(BT_angka.getText()+"lima ");
//            }else if(angka == '6'){
//                BT_angka.setText(BT_angka.getText()+"enam ");
//            }else if(angka == '7'){
//                BT_angka.setText(BT_angka.getText()+"tujuh ");
//            }else if(angka == '8'){
//                BT_angka.setText(BT_angka.getText()+"delapan ");
//            }else if(angka == '9'){
//                BT_angka.setText(BT_angka.getText()+"sembilan ");
//            }
//        }
//
//        void belasan(char angka){
//            if( angka == '0' ) {
//                BT_angka.setText(BT_angka.getText()+"sepuluh ");
//            }else if( angka == '1'){
//                BT_angka.setText(BT_angka.getText()+"sebelas ");
//            }else if(angka == '2'){
//                BT_angka.setText(BT_angka.getText()+"dua belas ");
//            }else if(angka == '3'){
//                BT_angka.setText(BT_angka.getText()+"tiga belas ");
//            }else if(angka == '4'){
//                BT_angka.setText(BT_angka.getText()+"empat belas ");
//            }else if(angka == '5'){
//                BT_angka.setText(BT_angka.getText()+"lima belas ");
//            }else if(angka == '6'){
//                BT_angka.setText(BT_angka.getText()+"enam belas ");
//            }else if(angka == '7'){
//                BT_angka.setText(BT_angka.getText()+"tujuh belas ");
//            }else if(angka == '8'){
//                BT_angka.setText(BT_angka.getText()+"delapan belas ");
//            }else if(angka == '9'){
//                BT_angka.setText(BT_angka.getText()+"sembilan belas ");
//            }
//        }
//
//        void puluhan(char angka){
//            if( angka == '2'){
//                BT_angka.setText(BT_angka.getText()+"dua puluh ");
//            }else if(angka == '3'){
//                BT_angka.setText(BT_angka.getText()+"tiga puluh ");
//            }else if(angka == '4'){
//                BT_angka.setText(BT_angka.getText()+"empat puluh ");
//            }else if(angka == '5'){
//                BT_angka.setText(BT_angka.getText()+"lima puluh ");
//            }else if(angka == '6'){
//                BT_angka.setText(BT_angka.getText()+"enam puluh ");
//            }else if(angka == '7'){
//                BT_angka.setText(BT_angka.getText()+"tujuh puluh ");
//            }else if(angka == '8'){
//                BT_angka.setText(BT_angka.getText()+"delapan puluh ");
//            }else if(angka == '9'){
//                BT_angka.setText(BT_angka.getText()+"sembilan puluh ");
//            }
//        }
//
//        private void ratusan(char angka){
//            if( angka == '1'){
//                BT_angka.setText(BT_angka.getText()+"seratus ");
//            }else if( angka == '2'){
//                BT_angka.setText(BT_angka.getText()+"dua ratus ");
//            }else if(angka == '3'){
//                BT_angka.setText(BT_angka.getText()+"tiga ratus ");
//            }else if(angka == '4'){
//                BT_angka.setText(BT_angka.getText()+"empat ratus ");
//            }else if(angka == '5'){
//                BT_angka.setText(BT_angka.getText()+"lima ratus ");
//            }else if(angka == '6'){
//                BT_angka.setText(BT_angka.getText()+"enam ratus ");
//            }else if(angka == '7'){
//                BT_angka.setText(BT_angka.getText()+"tujuh ratus ");
//            }else if(angka == '8'){
//                BT_angka.setText(BT_angka.getText()+"delapan ratus ");
//            }else if(angka == '9'){
//                BT_angka.setText(BT_angka.getText()+"sembilan ratus ");
//            }
//        }
//    }
//    @Override
//    public RV_angka.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v;
//        v = LayoutInflater.from(parent.getContext()).
//                inflate(R.layout.activity_main_list, parent, false);
//        ViewHolder vh =new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(final RV_angka.ViewHolder holder, final int position) {
//        final String kosong;
//        final MediaPlayer player = null;
//        final String name = rvData.get(position);
//
//        if(name.length() == 1){
//            kosong = "00";
//        }else if(name.length() == 2){
//            kosong ="0";
//        }else kosong = "";
//
//        holder.BT_angka.setText(kosong+rvData.get(position));
////        holder.BT_angka.append(kosong);
////        holder.BT_angka.append(rvData.get(position));
//        holder.BT_angka.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(v.getContext(),PopActivity.class);
//                intent.putExtra("angka", holder.BT_angka.getText().toString());
//                intent.putExtra("loket", spinner.getSelectedItem().toString());
////                Toast.makeText(v.getContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
////                intent.putExtra("loket", );
////                intent.putExtra("kosong", kosong);
//
//                remove(name);
//                v.getContext().startActivity(intent);
////                remove(name);
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return rvData.size();
//    }
}
