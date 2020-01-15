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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sudin.jobseeker.Post;
import com.sudin.jobseeker.R;
import com.sudin.jobseeker.models.ltems;

import java.util.List;

public class publisheradapter extends RecyclerView.Adapter<publisheradapter.ItemViewHolder> {

    private static final String TAG = Post.class.getSimpleName();
    List<ltems> itemList;
    Context context;
    Bitmap bitmap;
    Intent intent;
    String cookie;

    public static final String BASE_URL = "http://192.168.137.1:3000/";
    public publisheradapter(List<ltems> itemList, Context context,String cookie) {
        this.itemList = itemList;
        this.context = context;
        this.cookie = cookie;
    }
    @NonNull
    @Override
    public publisheradapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_rows,
                viewGroup, false);

        return new publisheradapter.ItemViewHolder(itemView);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull publisheradapter.ItemViewHolder itemViewHolder, int i) {
        final ltems item = itemList.get(i);


        itemViewHolder.name.setText(item.getName());
//        itemViewHolder.price.setText(item.getDescription());

        StrictMode();





        itemViewHolder.asdas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                intent = new Intent(context, publisherfulljob.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("datasingle",item);
                intent.putExtra("cookie",cookie);
                Toast.makeText(context, "clicked" , Toast.LENGTH_LONG).show();
                Log.d(TAG,cookie);
                context.startActivity(intent);


//                i.putExtra("Editing", details);


            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name, price;

        public CardView asdas;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

//            image = itemView.findViewById(R.id.photo);
//            name = itemView.findViewById(R.id.tv_itemName);
            price = itemView.findViewById(R.id.tv_price);
            asdas=itemView.findViewById(R.id.card_view);


        }


    }
}
