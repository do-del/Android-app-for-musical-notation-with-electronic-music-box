package com.example.musicbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controller.cqupt.Controller;
import model.cqupt.Music_Bundle;

public class LookMusicActivity extends Activity {

    private RelativeLayout bc;
    private UDPBuild udpBuild;
    private String mName;
    private String detail;
    private GridLayout look_grid;
    private String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_music);
        Intent intent = getIntent();
        Music_Bundle music_bundle = (Music_Bundle) intent.getSerializableExtra("music_bundle");
        mName = music_bundle.getMusicName();
        detail = "";
        Controller file_read = new Controller(getApplication());
        try{
            file_name = mName +".txt";
            detail = file_read.read(file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split_details = detail.split(",");
        int mPosition = music_bundle.getPosition();
        TextView view_look_music_name = findViewById(R.id.look_music_name);
        TextView view_fenzi = findViewById(R.id.fenzi);
        TextView view_fenmu = findViewById(R.id.fenmu);
        view_look_music_name.setText(mName);
        view_fenzi.setText(split_details[3]);
        view_fenmu.setText(split_details[1]);


        look_grid = findViewById(R.id.xml_grid);
        look_grid.setColumnCount(9);
        List<ImageView> imageview_1 = new ArrayList<ImageView>();
        int j = 4;
        for(int i =4;i<split_details.length;) {
            int split_detail =Integer.parseInt(split_details[i]);

            if(split_detail == 0){
                ImageView imageview = new ImageView(LookMusicActivity.this);
                imageview.setImageResource((R.mipmap.a0));
                imageview_1.add(imageview);
                look_grid.addView(imageview);
            }
            else{
                int note_index = Arrays.binarySearch(Constants.NOTE,split_detail);
                if(note_index>=0){
                    if(split_details[i+1].equals("1.0")) {
                        int resID = getResources().getIdentifier(Constants.Note_str[note_index], "mipmap", getPackageName());
                        ImageView imageview = new ImageView(LookMusicActivity.this);
                        imageview.setImageResource(resID);
                        imageview_1.add(imageview);
                        look_grid.addView(imageview);
                    }
                    else if(split_details[i+1].equals("0.5")){
                        int resID = getResources().getIdentifier(Constants.Note_str[note_index]+"_x", "mipmap", getPackageName());
                        ImageView imageview = new ImageView(LookMusicActivity.this);
                        imageview.setImageResource(resID);
                        imageview_1.add(imageview);
                        look_grid.addView(imageview);
                    }
                    else if(split_details[i+1].equals("0.25")){
                        int resID = getResources().getIdentifier(Constants.Note_str[note_index]+"_xx", "mipmap", getPackageName());
                        ImageView imageview = new ImageView(LookMusicActivity.this);
                        imageview.setImageResource(resID);
                        imageview_1.add(imageview);
                        look_grid.addView(imageview);
                    }
                }
                j=j+2;
            }
            if((j-2) % (Integer.parseInt(split_details[3])*2)==0){
                ImageView imageview = new ImageView(LookMusicActivity.this);
                imageview.setImageResource(R.mipmap.aa);
                imageview_1.add(imageview);
                look_grid.addView(imageview);
            }
            i = i+2;
        }


        bc = findViewById(R.id.layout_look);
        this.registerForContextMenu(bc);

        udpBuild = UDPBuild.getUdpBuild();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        setTitle("Menu");
        menu.add(0,2,0,"播放");
        menu.add(0,3,0,"删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 2:
                udpBuild.sendMessage(detail);
                Toast.makeText(this, "已发送", Toast.LENGTH_LONG).show();
                break;
            case 3:
                deleteFile(file_name);
                finish();
            default:
                break;
        }
        return true;
    }
}
