package com.example.madfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private ImageView logout;

    RecyclerView deleteNoticeRecycler;
    DatabaseReference reference;

    ArrayList<NoticeData> list;
    NoticeAdapter adapter;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = (ImageView) findViewById(R.id.logoutBTN);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, Login.class));
                Toast.makeText(Home.this, "Logout Successful", Toast.LENGTH_LONG).show();
            }
        });


        deleteNoticeRecycler = findViewById(R.id.deleteNoticeRecycler);
        reference = FirebaseDatabase.getInstance().getReference("Notice");
        deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecycler.setHasFixedSize(true);

        list = new ArrayList<>();
        adapter =new NoticeAdapter(this,list);
        deleteNoticeRecycler.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    NoticeData data = dataSnapshot.getValue(NoticeData.class);
                    list.add(data);


                    }
                adapter.notifyDataSetChanged();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

            }
        });





//        getNotice();

    }
//        private void getNotice() {
//            reference.addValueEventListener(new ValueEventListener() {
//                @SuppressLint("NotifyDataSetChanged")
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    list = new ArrayList<>();
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                        NoticeData data = snapshot.getValue(NoticeData.class);
//                        list.add(0,data);
//                    }
//
//                    adapter =  new NoticeAdapter(getApplicationContext(), list);
//                    adapter.notifyDataSetChanged();
//
//                    deleteNoticeRecycler.setAdapter(adapter);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    Toast.makeText(Home.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            });
//        }



    }
