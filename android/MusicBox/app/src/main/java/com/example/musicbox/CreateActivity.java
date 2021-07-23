package com.example.musicbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import Controller.cqupt.Controller;
import model.cqupt.Create_Bundle;

public class CreateActivity extends Activity implements View.OnClickListener {
    Button create_btn;
    RadioGroup rdg_yinfu;
    RadioGroup rdg_paishu;
    EditText edit_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_music_activity);

        create_btn = findViewById(R.id.btn_create);
        create_btn.setOnClickListener(this);

        rdg_paishu = findViewById(R.id.paishu_rdg);
        rdg_yinfu = findViewById(R.id.yinfu_rdg);
        edit_name = findViewById(R.id.name_edit);
    }

    @Override
    public void onClick(View v) {
        if (edit_name.getText().toString().equals("")) {
            Toast.makeText(this, "请输入歌名", Toast.LENGTH_LONG).show();
            return;
        }
        Controller file_save = new Controller(getApplicationContext());
        if(file_save.fileIsExists(edit_name.getText().toString()+".txt")){
            Toast.makeText(getApplicationContext(), "该歌曲已存在", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(CreateActivity.this, NewMusicActivity.class);
        Bundle mExtra = new Bundle();
        String music_name_bundle = edit_name.getText().toString();
        int fenzi_bundle = 4;
        int fenmu_bundle = 4;
        for (int i = 0; i < rdg_yinfu.getChildCount(); i++) {
            RadioButton rd_yinfu = (RadioButton) rdg_yinfu.getChildAt(i);
            if (rd_yinfu.isChecked()) {
                if (rd_yinfu.getText().equals("二分音符")) {
                    fenmu_bundle = 2;
                } else if (rd_yinfu.getText().equals("四分音符")) {
                    fenmu_bundle = 4;
                } else if (rd_yinfu.getText().equals("八分音符")) {
                    fenmu_bundle = 8;
                }
                break;
            }
        }

        for (int j = 0; j < rdg_paishu.getChildCount(); j++) {
            RadioButton rd_paishu = (RadioButton) rdg_paishu.getChildAt(j);
            if (rd_paishu.isChecked()) {
                if (rd_paishu.getText().equals("3拍")) {
                    fenzi_bundle = 3;
                } else if (rd_paishu.getText().equals("4拍")) {
                    fenzi_bundle = 4;
                } else if (rd_paishu.getText().equals("5拍")) {
                    fenzi_bundle = 5;
                }
                break;
            }
        }
        Create_Bundle create_bundle = new Create_Bundle(music_name_bundle,fenzi_bundle,fenmu_bundle);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mExtra.putSerializable("create_bundle",create_bundle);
        intent.putExtras(mExtra);
        CreateActivity.this.startActivity(intent);
        finish();
    }
}
