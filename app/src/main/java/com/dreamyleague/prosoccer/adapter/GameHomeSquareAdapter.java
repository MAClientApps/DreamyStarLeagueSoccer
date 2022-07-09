package com.dreamyleague.prosoccer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.dreamyleague.prosoccer.R;
import com.dreamyleague.prosoccer.activities.GamePlayActivity;
import com.dreamyleague.prosoccer.model.ContentItemClass;
import com.dreamyleague.prosoccer.utility.ViewAdMob;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class GameHomeSquareAdapter extends RecyclerView.Adapter<GameHomeSquareAdapter.MyView>{
    private ArrayList<ContentItemClass> contentItemArrayList;
    Activity context;

    public GameHomeSquareAdapter(ArrayList<ContentItemClass> contentArrayList, Activity context) {
        this.contentItemArrayList = contentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.td_game_cview, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        try {
            final String imageUrl = contentItemArrayList.get(position).getThumbnail();
            Glide.with(context)
                    .load(new GlideUrl(imageUrl))
                    .placeholder(R.drawable.img_transparent)
                    .into(holder.gameImg);
            holder.tvGame.setText(contentItemArrayList.get(position).getTitle());
        } catch (Exception e) {
        }

        holder.playgame.setVisibility(View.VISIBLE);
        holder.playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GamePlayActivity.class);
                intent.putExtra("link", contentItemArrayList.get(position).getContent());
                ((Activity) context).startActivity(intent);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GamePlayActivity.class);
                intent.putExtra("link", contentItemArrayList.get(position).getContent());
                ((Activity) context).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentItemArrayList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        public ImageView  playgame;
        public ImageView gameImg;
        public TextView tvGame;
        public MyView(View view) {
            super(view);
            gameImg = view.findViewById(R.id.game_img);
            playgame =view.findViewById(R.id.game_img_playbtn);
            tvGame=view.findViewById(R.id.tvGame_title);
        }
    }
}
