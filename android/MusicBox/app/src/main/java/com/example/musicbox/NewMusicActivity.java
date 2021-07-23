package com.example.musicbox;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controller.cqupt.Controller;
import Controller.cqupt.Image_transfor;
import model.cqupt.Create_Bundle;

public class NewMusicActivity extends Activity implements View.OnClickListener {
    private UDPBuild udpBuild;
    private Button btn;
    private GridLayout layout_newgrid;
    private TextView text_name;
    private TextView text_nfenzi;
    private TextView text_nfenmu;
    private List<Integer> melodys = new ArrayList<Integer>();
    private List<Double> noteDurations = new ArrayList<Double>();
    private int clicked_index=0;
    private int fenzi;
    private int fenmu;
    private int ind_ddd=0;
    private String music_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmusic);

        Intent intent = getIntent();
        Create_Bundle create_bundle = (Create_Bundle) intent.getSerializableExtra("create_bundle");
        music_name = create_bundle.getMusicName();
        fenzi = create_bundle.getFenzi();
        fenmu = create_bundle.getFenmu();
        text_name = findViewById(R.id.new_music_name);
        text_nfenzi = findViewById(R.id.nfenzi);
        text_nfenmu = findViewById(R.id.nfenmu);
        text_nfenmu.setText(String.valueOf(fenmu));
        text_nfenzi.setText(String.valueOf(fenzi));
        text_name.setText(music_name);

        findViewById(R.id.btn_d).setOnClickListener(this);
        findViewById(R.id.btn_dd).setOnClickListener(this);
        findViewById(R.id.btn_ddd).setOnClickListener(this);
        findViewById(R.id.btn_x).setOnClickListener(this);
        findViewById(R.id.btn_xx).setOnClickListener(this);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);

        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_fadd).setOnClickListener(this);
        findViewById(R.id.btn_badd).setOnClickListener(this);

        findViewById(R.id.btn_qd).setOnClickListener(this);
        findViewById(R.id.btn_qdd).setOnClickListener(this);
        findViewById(R.id.btn_qddd).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);


        btn = findViewById(R.id.send_play);
        btn.setOnClickListener(this);
        udpBuild = UDPBuild.getUdpBuild();

        layout_newgrid = findViewById(R.id.xml_gridlayout);
        layout_newgrid.setColumnCount(9);

        melodys.add(0);
        noteDurations.add(1.0);
        grid_freshview();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_play:
                btn.setText("Changed");
                int[] melody = new int[melodys.size()];
                double[] noteDuration = new double[noteDurations.size()];
                for(int md = 0;md<melody.length;md++){
                    melody[md] = melodys.get(md);
                    noteDuration[md] = noteDurations.get(md);
                }
                String message = "1,"+String.valueOf(fenmu)+",1,"+String.valueOf(fenzi);
                for(int i= 0;i<melody.length;i++){
                    message = message + ",";
                    message = message + String.valueOf(melody[i]);
                    message = message + ",";
                    message = message + String.valueOf(noteDuration[i]);
                }
                Controller file_save = new Controller(getApplicationContext());
                try {
                    file_save.save(music_name+".txt", message);
                    Toast.makeText(this, "数据写入成功", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }

                udpBuild.sendMessage(message);
                break;

            case R.id.btn_d:
                if(melodys.get(clicked_index) != 0) {
                    melodys.set(clicked_index,Constants.NOTE[ind_ddd-12]);
                    grid_freshview();
                }
                break;
            case R.id.btn_dd:
                if(melodys.get(clicked_index) != 0) {
                    melodys.set(clicked_index,Constants.NOTE[ind_ddd-24]);
                    grid_freshview();
                }
                break;
            case R.id.btn_ddd:
                if(melodys.get(clicked_index) != 0) {
                    melodys.set(clicked_index,Constants.NOTE[ind_ddd-36]);
                    grid_freshview();
                }
                break;
            case R.id.btn_x:
                if(melodys.get(clicked_index) != 0) {
                    noteDurations.set(clicked_index, 0.5);
                    grid_freshview();
                }
                break;
            case R.id.btn_xx:
                if(melodys.get(clicked_index) != 0) {
                    noteDurations.set(clicked_index, 0.25);
                    grid_freshview();
                }
                break;

            case R.id.btn_0:
                melodys.set(clicked_index,0);
                noteDurations.set(clicked_index,1.0);
                grid_freshview();
                break;
            case R.id.btn_1:
                melodys.set(clicked_index,Constants.NOTE_C4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_C4);
                grid_freshview();
                break;
            case R.id.btn_2:
                melodys.set(clicked_index,Constants.NOTE_D4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_D4);
                grid_freshview();
                break;
            case R.id.btn_3:
                melodys.set(clicked_index,Constants.NOTE_E4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_E4);
                grid_freshview();
                break;
            case R.id.btn_4:
                melodys.set(clicked_index,Constants.NOTE_F4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_F4);
                grid_freshview();
                break;

            case R.id.btn_5:
                melodys.set(clicked_index,Constants.NOTE_G4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_G4);
                grid_freshview();
                break;
            case R.id.btn_6:
                melodys.set(clicked_index,Constants.NOTE_A4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_A4);
                grid_freshview();
                break;
            case R.id.btn_7:
                melodys.set(clicked_index,Constants.NOTE_B4);
                noteDurations.set(clicked_index,1.0);
                ind_ddd = find_ind(Constants.NOTE_B4);
                grid_freshview();
                break;
            case R.id.btn_badd:
                clicked_index++;
                melodys.add(clicked_index,0);
                noteDurations.add(clicked_index,1.0);
                grid_freshview();
                break;
            case R.id.btn_fadd:
                melodys.add(clicked_index,0);
                noteDurations.add(clicked_index,1.0);
                //clicked_index++;
                grid_freshview();
                break;

            case R.id.btn_qd:
                if(melodys.get(clicked_index) != 0) {
                    melodys.set(clicked_index,Constants.NOTE[ind_ddd+12]);
                    grid_freshview();
                }
                break;
            case R.id.btn_qdd:
                if(melodys.get(clicked_index) != 0) {
                    melodys.set(clicked_index,Constants.NOTE[ind_ddd+24]);
                    grid_freshview();
                }
                break;
            case R.id.btn_qddd:
                if(melodys.get(clicked_index) != 0) {
                    melodys.set(clicked_index,Constants.NOTE[ind_ddd+36]);
                    grid_freshview();
                }
                break;
            case R.id.btn_del:
                melodys.remove(clicked_index);
                noteDurations.remove(clicked_index);
                clicked_index--;
                grid_freshview();
                break;

            default:
                break;
        }
    }


    public void grid_freshview(){
        layout_newgrid.removeAllViews();
        int[] melody_yes = new int[melodys.size()];
        double[] noteDuration_yes = new double[noteDurations.size()];
        for(int x=0;x<melody_yes.length;x++){
            melody_yes[x] = melodys.get(x);
            noteDuration_yes[x] = noteDurations.get(x);
        }

        addgridview(melody_yes,noteDuration_yes);
    }

    public void addgridview(int[] melody, double[] noteDurations){
        int j=0;
        for(int i=0;i<melody.length;i++){
            if(melody[i] == 0){
                ImageView imageview = new ImageView(getApplicationContext());
                imageview.setImageResource((R.mipmap.a0));
                imageview.setId(i);
                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackgroundResource(R.color.grey);
                        clicked_index = v.getId();
                        grid_freshview();
                    }
                });
                if (i == clicked_index) {
                    imageview.setBackgroundResource(R.color.grey);
                }
                layout_newgrid.addView(imageview);
            }
            else{
                if(j % fenzi == 0 && j!=0){
                    ImageView imageview = new ImageView(getApplicationContext());
                    imageview.setImageResource(R.mipmap.aa);
                    layout_newgrid.addView(imageview);
                }

                int note_index = Arrays.binarySearch(Constants.NOTE,melody[i]);
                if(note_index>=0){
                    if(noteDurations[i]==1) {
                        int resID = getResources().getIdentifier(Constants.Note_str[note_index], "mipmap","com.example.musicbox");
                        ImageView imageview = new ImageView(getApplicationContext());
                        imageview.setImageResource(resID);
                        imageview.setId(i);
                        imageview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                v.setBackgroundResource(R.color.grey);
                                clicked_index = v.getId();
                                grid_freshview();
                            }
                        });
                        if (i == clicked_index) {
                            imageview.setBackgroundResource(R.color.grey);
                        }
                        layout_newgrid.addView(imageview);
                    }
                    else if(noteDurations[i]==0.5){
                        int resID = getResources().getIdentifier(Constants.Note_str[note_index]+"_x", "mipmap", "com.example.musicbox");
                        ImageView imageview = new ImageView(getApplicationContext());
                        imageview.setImageResource(resID);
                        imageview.setId(i);
                        imageview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                v.setBackgroundResource(R.color.grey);
                                clicked_index = v.getId();
                                grid_freshview();
                            }
                        });
                        if (i == clicked_index) {
                            imageview.setBackgroundResource(R.color.grey);
                        }
                        layout_newgrid.addView(imageview);
                    }
                    else if(noteDurations[i]==0.25){
                        int resID = getResources().getIdentifier(Constants.Note_str[note_index]+"_xx", "mipmap", "com.example.musicbox");
                        ImageView imageview = new ImageView(getApplicationContext());
                        imageview.setImageResource(resID);
                        imageview.setId(i);
                        imageview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                v.setBackgroundResource(R.color.grey);
                                clicked_index = v.getId();
                                grid_freshview();
                            }
                        });
                        if (i == clicked_index) {
                            imageview.setBackgroundResource(R.color.grey);
                        }
                        layout_newgrid.addView(imageview);
                    }
                }

                j++;
            }

        }
    }

    public int find_ind(int melody){
        int ind = Arrays.binarySearch(Constants.NOTE,melody);
        return ind;
    }

}
