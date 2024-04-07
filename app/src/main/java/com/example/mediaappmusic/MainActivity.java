package com.example.mediaappmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSong, txtTimeTotal, txtNguoiTheHien;
    ImageView imgDisc;
    SeekBar sbSong;
    ImageButton ibtnNext, ibtnPrev, ibtnStop, ibtnPlay;
    MediaPlayer mediaPlayer;
    ArrayList<Song> arraySong;
    int position = 0;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = (TextView) findViewById(R.id.txtTenBaiHat);
        txtTimeSong = (TextView) findViewById(R.id.txtTime);
        txtTimeTotal = (TextView) findViewById(R.id.txtTotalTime);
        txtNguoiTheHien = (TextView) findViewById(R.id.txtNguoiTheHien) ;
        sbSong = (SeekBar) findViewById(R.id.seekbarSong);
        ibtnNext = (ImageButton) findViewById(R.id.ibtnNext);
        ibtnPrev = (ImageButton) findViewById(R.id.ibtnPrev);
//        ibtnStop = (ImageButton) findViewById(R.id.ibtnStop);
        ibtnPlay = (ImageButton) findViewById(R.id.ibtnPlay);
        imgDisc = (ImageView) findViewById(R.id.imgCD);

        Intent intent = getIntent();
        Song song = (Song) intent.getSerializableExtra("song");

//        Log.d("AAAA", song.getTenBaiHat());
//        Log.d("AAAA", song.getNguoiTheHien());
//        Log.d("AAAA", song.getFile()+"");

        arraySong = new ArrayList<>();
        arraySong.add(song);

//        if (arraySong != null) {
//        } else {
//            arraySong = new ArrayList<>();
//            arraySong.add(song);
//        }

//        String tenBaiHat  = intent.getStringExtra("ten");
//        String theHien  = intent.getStringExtra("thehien");
//        int file  = intent.getIntExtra("file", 0);

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);

        KhoiTaoMediaPlayer();

        ibtnPrev.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0) {
                    position = arraySong.size() - 1;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                ibtnPlay.setImageResource(R.drawable.pause_white_32x32);
                SetTimeTotal();
                UpdateTimeSong();
            }
        }));

        ibtnNext.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if(position > arraySong.size() - 1) {
                    position = 0;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                ibtnPlay.setImageResource(R.drawable.pause_white_32x32);
                SetTimeTotal();
                UpdateTimeSong();
            }
        }));

//        ibtnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                ibtnPlay.setImageResource(R.drawable.play_white_32x32);
//                KhoiTaoMediaPlayer();
//                imgDisc.clearAnimation();
//            }
//        });

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    // đang chạy -> ngừng -> đổi hình play;
                    mediaPlayer.pause();
                    ibtnPlay.setImageResource(R.drawable.play_white_32x32);
                    imgDisc.clearAnimation();
                } else {
                    // đang ngừng -> phát -> đổi hình pause;
                    mediaPlayer.start();
                    ibtnPlay.setImageResource(R.drawable.pause_white_32x32);
                    imgDisc.startAnimation(animation);
                }
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        sbSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbSong.getProgress());
            }
        });
    }

    private void UpdateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                // update sbSong
                sbSong.setProgress(mediaPlayer.getCurrentPosition());

                // kiểm tra thời gian nếu kết thúc thì chuyển bài
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        ibtnPlay.setImageResource(R.drawable.pause_white_32x32);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this, 00);
            }
        }, 100);
    }
    private void SetTimeTotal() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));

        // gán max của sbSong = mediaPlayer.getDuration()
        sbSong.setMax(mediaPlayer.getDuration());
    }
    private void KhoiTaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());

        txtTitle.setText((arraySong.get(position).getTenBaiHat()+""));
        txtNguoiTheHien.setText((arraySong.get(position).getNguoiTheHien()));
    }
//    private void AddSong() {
//        arraySong = new ArrayList<>();
//        arraySong.add(new Song("Lan man", R.raw.lanman_ronboogz));
//        arraySong.add(new Song("Cắt đôi nỗi sầu", R.raw.cat_doi_noi_sau_ft_drum7_tang_duy_tan));
//        arraySong.add(new Song("With you", R.raw.withyou_hoaprox));
//        arraySong.add(new Song("Cứ chill thôi", R.raw.cu_chill_thoi_chillies_ft_suni_halinh_rhymastic));
//        arraySong.add(new Song("Dạ Vũ", R.raw.da_vu_tang_duy_tan));
//        arraySong.add(new Song("Gác lại âu lo", R.raw.gac_lai_au_lo_dalab_ft_miule));
//        arraySong.add(new Song("Ngây thơ", R.raw.ngay_tho_tang_duy_tan));
//        arraySong.add(new Song("Thích em hơi nhiều", R.raw.thich_em_hoi_nhieu_wrenevans));
//        arraySong.add(new Song("Phía sau một cô gái", R.raw.phia_sau_mot_co_gai));
//    }

//    private void Mapping() {
//        txtTitle = (TextView) findViewById(R.id.txtTenBaiHat);
//        txtTimeSong = (TextView) findViewById(R.id.txtTime);
//        txtTimeTotal = (TextView) findViewById(R.id.txtTotalTime);
//        txtNguoiTheHien = (TextView) findViewById(R.id.txtTheHien) ;
//        sbSong = (SeekBar) findViewById(R.id.seekbarSong);
//        ibtnNext = (ImageButton) findViewById(R.id.ibtnNext);
//        ibtnPrev = (ImageButton) findViewById(R.id.ibtnPrev);
////        ibtnStop = (ImageButton) findViewById(R.id.ibtnStop);
//        ibtnPlay = (ImageButton) findViewById(R.id.ibtnPlay);
//        imgDisc = (ImageView) findViewById(R.id.imgCD);
//    }
}