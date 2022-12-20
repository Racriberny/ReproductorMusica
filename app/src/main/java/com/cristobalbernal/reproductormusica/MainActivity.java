package com.cristobalbernal.reproductormusica;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play,siguiente,atras;
    ImageView imagen;
    MediaPlayer mp;
    int posicion = 0;

    MediaPlayer cancion[] = new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.playstop);
        siguiente = findViewById(R.id.siguiente);
        atras = findViewById(R.id.atras);
        imagen = findViewById(R.id.imageView);
        cancion[0] = MediaPlayer.create(this,R.raw.race);
        cancion[1] = MediaPlayer.create(this,R.raw.tea);
        cancion[2] = MediaPlayer.create(this,R.raw.sound);
        imagen.setImageResource(R.drawable.eloy);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPause(view);
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente(view);
            }
        });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anterior(view);
            }
        });

    }
    public void playPause(View view){
        if (cancion[posicion].isPlaying()){
            cancion[posicion].pause();
            int tiempo = cancion[posicion].getDuration();
            play.setBackgroundResource(R.drawable.reproducir);
            System.out.println(tiempo);
            Toast.makeText(this,"Pausa",Toast.LENGTH_SHORT).show();
        }else {
            cancion[posicion].start();
            play.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();
        }
    }

    public void siguiente(View view){
        if (posicion < cancion.length -1){

            if (cancion[posicion].isPlaying()){
                cancion[posicion].stop();
                posicion++;
                cancion[posicion].start();
            }else {
                posicion++;
            }
            if(posicion == 0){
                imagen.setImageResource(R.drawable.eloy);
            }else if (posicion == 1){
                imagen.setImageResource(R.drawable.fermin);
            }else if (posicion == 2){
                imagen.setImageResource(R.drawable.steven);
            }

        }else {
            Toast.makeText(this,"No hay mas canciones!!!",Toast.LENGTH_SHORT).show();

        }
    }

    public void anterior(View view){
        if (posicion >=1){

            if (cancion[posicion].isPlaying()){
                cancion[posicion].stop();
                cancion[0] = MediaPlayer.create(this,R.raw.race);
                cancion[1] = MediaPlayer.create(this,R.raw.tea);
                cancion[2] = MediaPlayer.create(this,R.raw.sound);
                posicion--;

                if(posicion == 0){
                    imagen.setImageResource(R.drawable.eloy);
                }else if (posicion == 1){
                    imagen.setImageResource(R.drawable.fermin);
                }else if (posicion == 2){
                    imagen.setImageResource(R.drawable.steven);
                }

                cancion[posicion].start();
            }else {
                posicion--;
            }

        }else {
            Toast.makeText(this,"No hay canciones",Toast.LENGTH_SHORT).show();
        }
    }

}