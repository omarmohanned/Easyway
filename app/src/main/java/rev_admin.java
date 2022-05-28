import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Retrieve_bus_data_base;
import com.example.myapplication.all_buses_adapter;
import com.example.myapplication.ret_rev;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class rev_admin extends RecyclerView.Adapter<rev_admin.imageviewholder> {

    private Context mcontext;
    private List<ret_rev> mlist;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;


    public rev_admin() {
    }

    public rev_admin(Context mcontext, List<ret_rev> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override

    public imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.all_rev_admin, parent, false);
        return new rev_admin.imageviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageviewholder holder, int position) {
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();

        final ret_rev ret=mlist.get(position);

        holder.name_user.setText(ret.getUser());
        holder.stars.setText(ret.getRev_num());

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class imageviewholder extends RecyclerView.ViewHolder {
        private TextView name_user, stars;

        public imageviewholder(@NonNull View itemView) {
            super(itemView);
            name_user=itemView.findViewById(R.id.name_user);
            stars=itemView.findViewById(R.id.stars);


        }
    }
}
