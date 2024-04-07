package com.example.mediaappmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListMusicAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Song> songList;


    public ListMusicAdapter(Context context, int layout, List<Song> songList) {
        this.context = context;
        this.layout = layout;
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTen, txtNguoiTheHien;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view== null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);
            holder = new ViewHolder();

            // ánh xạ view
            holder.txtTen =  (TextView) view.findViewById(R.id.txtTen);
            holder.txtNguoiTheHien = (TextView) view.findViewById(R.id.txtTheHien);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // gán giá trị
        Song traiCay = songList.get(position);
        holder.txtTen.setText(traiCay.getTenBaiHat());
        holder.txtNguoiTheHien.setText(traiCay.getNguoiTheHien());

        return view;
    }
}
