package com.example.madfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNew extends RecyclerView.Adapter<AdapterNew.viewHolder> {


    ArrayList<AdapterNew> list;
    Context context;

    public AdapterNew(ArrayList<AdapterNew> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.row_posts,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        ModelPost modelPost=list.get(position);
//        holder.post_img.setImageResource(modelPost.getUimage());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{


        ImageView profile,post_img;
        TextView name,title,description,like,comment,time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile=itemView.findViewById(R.id.picturetv);
            post_img=itemView.findViewById(R.id.pimagetv);
            name=itemView.findViewById(R.id.unametv);
            title=itemView.findViewById(R.id.ptitletv);
            description=itemView.findViewById(R.id.descript);
            time=itemView.findViewById(R.id.utimetv);
            like=itemView.findViewById(R.id.plikeb);
            comment=itemView.findViewById(R.id.pcommentco);
        }
    }
}
