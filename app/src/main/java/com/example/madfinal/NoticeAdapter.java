package com.example.madfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    Context context;

    ArrayList<NoticeData> list;


    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NoticeData currentItem = list.get(position);
        holder.deleteNoticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getData());
        holder.time.setText(currentItem.getTime());

        Picasso.get().load(currentItem.getImage()).into(holder.deleteNoticeImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView deleteNoticeTitle;
        TextView date;
        TextView time;
        ImageView deleteNoticeImage;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            deleteNoticeTitle = itemView.findViewById(R.id.deleteNoticeTitle);
            deleteNoticeImage = itemView.findViewById(R.id.deleteNoticeImage);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}





//    private final Context context;
//    private final ArrayList<NoticeData> list;
//
//    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//
//
//    @NonNull
//    @Override
//    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout, parent, false);
//        return new NoticeViewAdapter(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {
//
//        final NoticeData currentItem = list.get(position);
//        holder.deleteNoticeTitle.setText(currentItem.getTitle());
//        holder.date.setText(currentItem.getData());
//        holder.time.setText(currentItem.getTime());
//
//        Picasso.get().load(currentItem.getImage()).into(holder.deleteNoticeImage);
//
////        try {
////            if (currentItem.getImage() != null)
////                Picasso.get().load(currentItem.getImage()).into(holder.deleteNoticeImage);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
////        holder.deleteNoticeImage.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent =  new Intent(context, Home.class);
////                intent.putExtra("image",currentItem.getImage());
////                context.startActivity(intent);
////            }
////        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class NoticeViewAdapter extends RecyclerView.ViewHolder {
//
//        private final TextView deleteNoticeTitle;
//        private final TextView date;
//        private final TextView time;
//        private final ImageView deleteNoticeImage;
//
//        public NoticeViewAdapter(@NonNull View itemView) {
//            super(itemView);
//            deleteNoticeTitle = itemView.findViewById(R.id.deleteNoticeTitle);
//            deleteNoticeImage = itemView.findViewById(R.id.deleteNoticeImage);
//            date = itemView.findViewById(R.id.date);
//            time = itemView.findViewById(R.id.time);
//
//        }
//    }
//}
