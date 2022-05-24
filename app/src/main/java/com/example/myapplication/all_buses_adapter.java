package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class all_buses_adapter extends RecyclerView.Adapter<all_buses_adapter.imageviewholder> {

    private Context mcontext;
    private List<Retrieve_bus_data_base> mlist;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    public all_buses_adapter() {
    }

    public all_buses_adapter(Context mcontext, List<Retrieve_bus_data_base> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(mcontext).inflate(R.layout.recy_view_content,parent,false);
       return new imageviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageviewholder holder, int position) {

        databaseReference= FirebaseDatabase.getInstance().getReference();


        final Retrieve_bus_data_base ret_bin=mlist.get(position);

        holder.stop_name.setText(ret_bin.getBusStopName());
        holder.address.setText(ret_bin.getADDRESS());
        holder.price.setText(ret_bin.getPrice()+"JD");
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



  public class imageviewholder extends RecyclerView.ViewHolder{
        private TextView stop_name,address,price;

      public imageviewholder(@NonNull View itemView) {
          super(itemView);

          stop_name=itemView.findViewById(R.id.stop_name);
          address=itemView.findViewById(R.id.address);
          price=itemView.findViewById(R.id.price);

      }
  }
}
