package com.example.mediaappmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListMusicActivity extends AppCompatActivity {

    ListView lvSong;
    ArrayList<Song> arraySong;
    ListMusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);

        AnhXa();
        adapter = new ListMusicAdapter(ListMusicActivity.this, R.layout.item_song, arraySong);
        lvSong.setAdapter(adapter);

        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListMusicActivity.this, MainActivity.class);

                Song song = new Song(arraySong.get(position).getTenBaiHat(), arraySong.get(position).getNguoiTheHien(), arraySong.get(position).getFile());
                intent.putExtra("song", (Serializable) song);

//                Log.d("AAA", arraySong.get(position).getTenBaiHat());
//                Log.d("AAA", arraySong.get(position).getNguoiTheHien());
//                Log.d("AAA", arraySong.get(position).getFile()+"");

//                intent.putExtra("ten", arraySong.get(position).getTenBaiHat());
//                intent.putExtra("thehien", arraySong.get(position).getNguoiTheHien());
//                intent.putExtra("file", arraySong.get(position).getFile());


                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        lvSong = (ListView) findViewById(R.id.lvSong);
        arraySong = new ArrayList<>();

        arraySong.add(new Song("Lan Man", "Roodgoz", R.raw.lanman_ronboogz));
        arraySong.add(new Song("Cắt đôi nỗi sầu", "Tăng Duy Tân",R.raw.cat_doi_noi_sau_ft_drum7_tang_duy_tan));
        arraySong.add(new Song("Dạ Vũ", "Tăng Duy Tân",R.raw.da_vu_tang_duy_tan));
//        arraySong.add(new Song("With you", R.raw.withyou_hoaprox));
//        arraySong.add(new Song("Cứ chill thôi", R.raw.cu_chill_thoi_chillies_ft_suni_halinh_rhymastic));
//        arraySong.add(new Song("Gác lại âu lo", R.raw.gac_lai_au_lo_dalab_ft_miule));
//        arraySong.add(new Song("Ngây thơ", R.raw.ngay_tho_tang_duy_tan));
//        arraySong.add(new Song("Thích em hơi nhiều", R.raw.thich_em_hoi_nhieu_wrenevans));
//        arraySong.add(new Song("Phía sau một cô gái", R.raw.phia_sau_mot_co_gai));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_music, menu);
        return true;
    }
}