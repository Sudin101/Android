package com.sudin.jobseeker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.StrictMode;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sudin.jobseeker.Interface.jobpostinterface;
import com.sudin.jobseeker.R;
import com.sudin.jobseeker.jobpostsfinal;
import com.sudin.jobseeker.models.ltems;


import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewsHolder>{
    Context context;
    Intent intent;
    String cookie;

    Bitmap bitmap;
    ArrayList<ltems> jobposts = new ArrayList<>();
    private static final String TAG = jobpostinterface.class.getSimpleName();

    public ItemAdapter(Context context, ArrayList<ltems> jobposts, String cookie) {
        this.context = context;
        this.jobposts = jobposts;
        this.cookie = cookie;
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @NonNull
    @Override
    public MyViewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_rows, viewGroup, false);
        MyViewsHolder viewHolder = new MyViewsHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.MyViewsHolder myViewsHolder, int i) {
        Toast.makeText(context, "Ayo Binder ma ", Toast.LENGTH_SHORT).show();

        final ltems ostbindit = jobposts.get(i);

        Log.d(TAG,"==========ON BInd vitra  chiryo=============");

//        Log.d(TAG,ostbindit.get.toString());

        Log.d(TAG,"==========ON BInd vitra  chiryo=============");
        Log.d(TAG,ostbindit.toString());
        Log.d(TAG,ostbindit.toString());
        Log.d(TAG,ostbindit.toString());
             StrictMode();

myViewsHolder.postdatas.setText(ostbindit.getName());
        myViewsHolder.image.setText(ostbindit.getJobType());
//        cookie = intent.getStringExtra("cookie");
// this is for seeting the names of ser to the field

        myViewsHolder.asdas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, jobpostsfinal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(context, "aayo ki nai "+ cookie, Toast.LENGTH_SHORT).show();
                intent.putExtra("datasingle",ostbindit);
                intent.putExtra("cookie",cookie);
                context.startActivity(intent);


//                i.putExtra("Editing", details);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobposts.size();
    }
    public class MyViewsHolder extends RecyclerView.ViewHolder {

         TextView postdatas,image;
        RelativeLayout relativeLayoutforswoingcomment;
        public CardView asdas;

        public MyViewsHolder(View itemView) {
            super(itemView);
            image=(TextView) itemView.findViewById(R.id.images);
            postdatas=(TextView) itemView.findViewById(R.id.postkolagi);
            asdas=itemView.findViewById(R.id.card_view);

        }
    }
}