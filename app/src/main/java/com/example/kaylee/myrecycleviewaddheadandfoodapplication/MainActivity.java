package com.example.kaylee.myrecycleviewaddheadandfoodapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = Arrays.asList(new String[]{"das", "da", "das", "da", "das", "da", "das", "da", "das", "da"});
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        RecycleAdapter adapter = new RecycleAdapter(list);
        TextView foot = new TextView(MainActivity.this);
        foot.setText("foot");
        TextView head = new TextView(MainActivity.this);
        head.setText("head");
        recyclerView.setAdapter(adapter);
        adapter.setFoot(foot);
        adapter.setHead(head);
    }
}
