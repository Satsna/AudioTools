package com.example.satsna;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.satsna.R;
import com.maxi.audiotools.IMAudioManager;
import com.maxi.audiotools.apis.DeleteListener;

public class MainActivity extends Activity {
    private String audioUrl = "http://yf.wp.zp68.com:811/sub/filestores/2018/03/28/7a1aee22131734edcc5dfbb93afba445.mp3?lx=xzwj&k=26779dd14e2643e7bb7c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IMAudioManager.instance().init(this);

        ((Button) findViewById(R.id.start_audio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String audioUrl = "http://yf.wp.zp68.com:811/sub/filestores/2018/03/28/7a1aee22131734edcc5dfbb93afba445.mp3?lx=xzwj&k=26779dd14e2643e7bb7c";
                IMAudioManager.instance().playSound(audioUrl, false, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(MainActivity.this, "播放结束", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ((Button) findViewById(R.id.pause_audio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IMAudioManager.instance().pause();
            }
        });

        ((Button) findViewById(R.id.resume_audio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IMAudioManager.instance().resume();
            }
        });

        ((Button) findViewById(R.id.release_audio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IMAudioManager.instance().release();
            }
        });

        ((Button) findViewById(R.id.delete_audio_cache)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IMAudioManager.instance().delete(new DeleteListener() {
                    @Override
                    public void success() {
                        Toast.makeText(MainActivity.this, "清除成功！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(String error) {
                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
