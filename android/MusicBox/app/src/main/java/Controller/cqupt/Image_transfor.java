package Controller.cqupt;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.example.musicbox.Constants;
import com.example.musicbox.LookMusicActivity;
import com.example.musicbox.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Image_transfor {
    public static List<ImageView> imgtrans(Context context, int[] melody, double[] noteDurations, int fenzi){
        List<ImageView> imageview_1 = new ArrayList<ImageView>();
        int j=0;
        for(int i=0;i<melody.length;i++){
            if(melody[i] == 0){
                ImageView imageview = new ImageView(context);
                imageview.setImageResource((R.mipmap.a0));
                imageview_1.add(imageview);
            }
            else{
                if(j % fenzi == 0 && j!=0){
                    ImageView imageview = new ImageView(context);
                    imageview.setImageResource(R.mipmap.aa);
                    imageview_1.add(imageview);
                }

                int note_index = Arrays.binarySearch(Constants.NOTE,melody[i]);
                if(note_index>=0){
                    if(noteDurations[i]==1) {
                        int resID = context.getResources().getIdentifier(Constants.Note_str[note_index], "mipmap","com.example.musicbox");
                        ImageView imageview = new ImageView(context);
                        imageview.setImageResource(resID);
                        imageview_1.add(imageview);
                    }
                    else if(noteDurations[i]==0.5){
                        int resID = context.getResources().getIdentifier(Constants.Note_str[note_index]+"_x", "mipmap", "com.example.musicbox");
                        ImageView imageview = new ImageView(context);
                        imageview.setImageResource(resID);
                        imageview_1.add(imageview);
                    }
                    else if(noteDurations[i]==0.25){
                        int resID = context.getResources().getIdentifier(Constants.Note_str[note_index]+"_xx", "mipmap", "com.example.musicbox");
                        ImageView imageview = new ImageView(context);
                        imageview.setImageResource(resID);
                        imageview_1.add(imageview);
                    }
                }

                j++;
            }

        }
        return imageview_1;
    }

    public static ImageView imgtran(Context context,int melody,double noteDuration){
        int note_index = Arrays.binarySearch(Constants.NOTE,melody);
        if(note_index>=0){
            if(noteDuration==1) {
                int resID = context.getResources().getIdentifier(Constants.Note_str[note_index], "mipmap","com.example.musicbox");
                ImageView imageview = new ImageView(context);
                imageview.setImageResource(resID);
                return imageview;
            }
            else if(noteDuration==0.5){
                int resID = context.getResources().getIdentifier(Constants.Note_str[note_index]+"_x", "mipmap", "com.example.musicbox");
                ImageView imageview = new ImageView(context);
                imageview.setImageResource(resID);
                return imageview;
            }
            else if(noteDuration==0.25){
                int resID = context.getResources().getIdentifier(Constants.Note_str[note_index]+"_xx", "mipmap", "com.example.musicbox");
                ImageView imageview = new ImageView(context);
                imageview.setImageResource(resID);
                return imageview;
            }
        }
        ImageView imageview = new ImageView(context);
        imageview.setImageResource(R.mipmap.aempty);
        return imageview;
    }

}
