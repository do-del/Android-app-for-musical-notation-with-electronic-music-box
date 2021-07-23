package model.cqupt;

import java.io.Serializable;

public class Create_Bundle implements Serializable {
    private  String music_name_bundle;
    private int fenzi_bundle;
    private int fenmu_bundle;
    public Create_Bundle(String music_name,int fenzi,int fenmu){
        this.music_name_bundle = music_name;
        this.fenmu_bundle = fenmu;
        this.fenzi_bundle = fenzi;
    }
    public String getMusicName(){
        return music_name_bundle;
    }
    public int getFenzi(){
        return fenzi_bundle;
    }
    public int getFenmu(){return fenmu_bundle;}
}
