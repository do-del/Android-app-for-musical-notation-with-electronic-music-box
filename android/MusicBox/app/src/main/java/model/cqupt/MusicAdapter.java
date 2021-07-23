package model.cqupt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.musicbox.R;

import java.util.LinkedList;


public class MusicAdapter extends BaseAdapter {
    private LinkedList<Music_lib> mData;
    private Context mContext;

    public MusicAdapter(LinkedList<Music_lib> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        TextView txt_name = (TextView) convertView.findViewById(R.id.list_name);
        txt_name.setText(mData.get(position).getM_name());
        return convertView;
    }
}
