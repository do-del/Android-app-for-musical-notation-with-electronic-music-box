package model.cqupt;

import java.io.Serializable;

public class Music_Bundle implements Serializable {
    private  String music_name_bundle;
    private int position_bundle;
    public Music_Bundle(String music_name,int position){
        this.music_name_bundle = music_name;
        this.position_bundle = position;
    }
    public String getMusicName(){
        return music_name_bundle;
    }
    public int getPosition(){
        return position_bundle;
    }
}
