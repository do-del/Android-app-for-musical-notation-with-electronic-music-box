package com.example.musicbox;



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Controller.cqupt.Controller;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn_lib;
    private Button btn_new;
    private Context mContext;
    private int[] melody;
    private double[] noteDurations;
    private int[] melody1;
    private double[] noteDurations1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        Controller file_save = new Controller(mContext);
//        List<String> filenames = new ArrayList<String>();
//        for(int y = 0;y<filenames.size();y++){
//            deleteFile(filenames.get(y));
//        }
        String filename = "生日歌.txt";
        melody = new int[]{
                Constants.NOTE_G4,//5
                Constants.NOTE_G4,//5
                Constants.NOTE_A4,//6
                Constants.NOTE_G4,//5
                Constants.NOTE_C5,//1.
                Constants.NOTE_B4,//7
                0,
                Constants.NOTE_G4,//5
                Constants.NOTE_G4,//5
                Constants.NOTE_A4,//6
                Constants.NOTE_G4,//5
                Constants.NOTE_D5,//2.
                Constants.NOTE_C5,//1.
                0,
                Constants.NOTE_G4,//5
                Constants.NOTE_G4,//5
                Constants.NOTE_G5,//5.
                Constants.NOTE_E5,//3.
                Constants.NOTE_C5,//1.
                Constants.NOTE_B4,//7
                Constants.NOTE_A4,//6
                0,
                Constants.NOTE_F5,//4.
                Constants.NOTE_F5,//4.
                Constants.NOTE_E5,//3.
                Constants.NOTE_C5,//1.
                Constants.NOTE_D5,//2.
                Constants.NOTE_C5,//1.
                0,
        };

        noteDurations = new double[]{
                0.5, 0.5, 1, 1, 1, 1,
                1,
                0.5, 0.5, 1, 1, 1, 1,
                1,
                0.5, 0.5, 1, 1, 1, 1, 2,
                0.5,
                0.5, 0.5, 1, 1, 1, 2,
                1,
        };

        String file_detail = "1,4,1,3"; //播放，一拍时间，AB调，一节几拍
        for (int i = 0; i < melody.length; i++) {
            file_detail =file_detail + ",";
            file_detail = file_detail + String.valueOf(melody[i]);
            file_detail = file_detail + ",";
            file_detail = file_detail + String.valueOf(noteDurations[i]);
        }

        try {
            file_save.save(filename, file_detail);
            //Toast.makeText(this, "数据写入成功", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
        }

        btn_lib = findViewById(R.id.btn_music_lib);
        btn_new = findViewById(R.id.btn_music_new);
        btn_lib.setOnClickListener(this);
        btn_new.setOnClickListener(this);

        findViewById(R.id.Vivy).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch(v.getId()){
            case R.id.btn_music_lib:
                intent.setClass(MainActivity.this,MusicLibActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_music_new:
                intent.setClass(MainActivity.this,CreateActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.Vivy:
                Toast.makeText(getApplicationContext(),"暂未收录",Toast.LENGTH_LONG).show();
                break;
        }
    }

}