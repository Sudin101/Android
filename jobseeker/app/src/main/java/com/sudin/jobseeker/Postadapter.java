package com.sudin.jobseeker;

import android.content.Context;
import android.content.Intent;
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

import com.sudin.jobseeker.models.Post;

import java.util.ArrayList;

public class Postadapter extends RecyclerView.Adapter<Postadapter.MyViewHolder>{

    Context context;
    Intent intent;
    String cookie;
    ArrayList<com.sudin.jobseeker.models.Post> postako = new ArrayList<>();

    private static final String TAG = Postadapter.class.getSimpleName();

    public Postadapter(Context context, ArrayList<com.sudin.jobseeker.models.Post> postako, String cookie) {
        this.context = context;
        this.postako = postako;
        this.cookie = cookie;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postlayoutonly, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;


    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Toast.makeText(context, "Ayo Binder ma "+cookie, Toast.LENGTH_SHORT).show();

        final Post ostbindit = postako.get(i);

        Log.d(TAG,"==========ON BInd vitra  chiryo=============");

//        Log.d(TAG,ostbindit.get.toString());

        Log.d(TAG,"==========ON BInd vitra  chiryo=============");


        Log.d(TAG,ostbindit.toString());


// this is for seeting the names of ser to the field

        myViewHolder.postdata.setText(ostbindit.getDescription());
        myViewHolder.asdas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                intent = new Intent(context, showpost.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("datasingle",ostbindit);
                intent.putExtra("cookie",cookie);

                context.startActivity(intent);


//                i.putExtra("Editing", details);


            }
        });
    }

    @Override
    public int getItemCount() {
        return postako.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView postdata;
        RelativeLayout relativeLayoutforswoingcomment;
        public CardView asdas;

        public MyViewHolder(View itemView) {
            super(itemView);

            postdata=(TextView) itemView.findViewById(R.id.postkolagi);
            asdas=itemView.findViewById(R.id.card_view);

        }
    }
}
