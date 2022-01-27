package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity  {
    private ImageView logout, addpost;
    FirebaseAuth firebaseAuth;
    String myuid;
    RecyclerView recyclerView;
    List<ModelPost> posts;
    AdapterPosts adapterPosts;
    private Object View;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        logout = (ImageView) findViewById(R.id.logoutBTN);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(Home.this, Login.class));
//                Toast.makeText(Home.this, "Logout Successful", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        addpost = (ImageView) findViewById(R.id.add_postBTN);
//        addpost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(Home.this, addpost.class));
//
//            }
//        });
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        recyclerView = findViewById(R.id.postrecyclerview);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(Home.this);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(layoutManager);
//        posts = new ArrayList<>();
//        loadPosts();
//        return;
//
//
//    }
//
//    private void loadPosts() {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                posts.clear();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    ModelPost modelPost = dataSnapshot1.getValue(ModelPost.class);
//                    posts.add(modelPost);
//                    adapterPosts = new AdapterPosts(Home.this, posts);
//                    recyclerView.setAdapter(adapterPosts);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(Home.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//
//        });
//    }
//}
public void onCreateView(Bundle savedInstanceState) {
    // Inflate the layout for this fragment
//    View view = inflater.inflate(R.layout.activity_home, container, false);
    firebaseAuth = FirebaseAuth.getInstance();
    recyclerView = (RecyclerView) findViewById(R.id.postRV);
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    layoutManager.setReverseLayout(true);
    layoutManager.setStackFromEnd(true);
    recyclerView.setLayoutManager(layoutManager);
    posts = new ArrayList<>();
    loadPosts();
//
    posts = findViewById(R.id.postRV);
    posts = new ArrayList<>();
    posts.add(new ModelPost(R.drawable.nature,R.drawable.new_hope,"New Hope","Traveler, life Lover","247","57","33"));
    posts.add(new ModelPost(R.drawable.man,R.drawable.nature1,"Dennis Kane","Photographer","247","57","33"));
    posts.add(new ModelPost(R.drawable.nature1,R.drawable.nature,"Alicia ","Traveler, life Lover","247","57","33"));
    posts.add(new ModelPost(R.drawable.man1,R.drawable.nature_dordogne,"Alicia ","Traveler, life Lover","247","57","33"));
//    AdapterPosts adapterPosts = new AdapterPosts(,Home.this);
    AdapterPosts adapterPosts=new AdapterPosts((ArrayList<ModelPost>) posts,this);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    recyclerView.setNestedScrollingEnabled(false);
    recyclerView.setAdapter(adapterPosts);
}

    private void loadPosts() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ModelPost modelPost = dataSnapshot1.getValue(ModelPost.class);
                    posts.add(modelPost);
//                    adapterPosts = new AdapterPosts(this, posts);
                    adapterPosts = new AdapterPosts(posts,Home.this);
                    recyclerView.setAdapter(adapterPosts);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Home.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

        addpost = (ImageView) findViewById(R.id.add_postBTN);
        addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, addpost.class));

            }
        });
    }
}