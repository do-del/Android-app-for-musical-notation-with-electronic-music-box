package com.example.musicbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.cqupt.MusicAdapter;
import model.cqupt.Music_Bundle;
import model.cqupt.Music_lib;

public class MusicLibActivity extends Activity implements AdapterView.OnItemClickListener {

    private Context mContext;
    private List<Music_lib> mData;
    private MusicAdapter mAdapter;
    private ListView listview_music;
    private ArrayList<String> music_names;
    private boolean preed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musiclibs);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(preed) {
            Intent intent = new Intent(MusicLibActivity.this, LookMusicActivity.class);
            Bundle mExtra = new Bundle();
            int mPosition = position;
            String mName = music_names.get(position);
            Music_Bundle music_bundle = new Music_Bundle(mName, mPosition);
            mExtra.putSerializable("music_bundle", music_bundle);
            intent.putExtras(mExtra);
            MusicLibActivity.this.startActivity(intent);
        }
        else{
            preed = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String[] list_file = fileList();

        mContext = MusicLibActivity.this;
        listview_music = findViewById(R.id.list_test);
        mData = new LinkedList<Music_lib>();
        music_names = new ArrayList<String>();
        for(int y = 0;y<list_file.length;y++){
            if(list_file[y].split("\\.",2)[1].equals("txt")) {
                music_names.add(list_file[y].split("\\.", 2)[0]);
            }
        }

        for (int x = 0;x<music_names.size();x++){
            mData.add(new Music_lib(music_names.get(x)));
        }

        mAdapter = new MusicAdapter((LinkedList<Music_lib>) mData, mContext);
        listview_music.setAdapter(mAdapter);
        listview_music.setOnItemClickListener(this);
        listview_music.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
//                mData.remove(position);
//                mAdapter.notifyDataSetChanged();
                preed = false;
                return false;
            }
        });

    }
}
