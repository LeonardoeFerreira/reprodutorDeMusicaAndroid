package com.playxcodes.mediaplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolum;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //carregando a musica
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        inicializarSeekBar();
    }


    private void inicializarSeekBar(){
        seekVolum = findViewById(R.id.seekVolume);

        //configurar o audiomanger
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //recupera os Valores de volume maximo e o volume atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //configurar seekbar
        seekVolum.setMax(volumeMaximo);

        //configurar o proguesso atual
        seekVolum.setProgress( volumeAtual);

        seekVolum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });









    }


    public void executarSom(View view){
      if(mediaPlayer != null){
          mediaPlayer.start();
      }
    }

    public void pausarSom(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }


    public void pararSom(View view){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            //carregando a musica
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release(); // liberar a musica da memoria
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}