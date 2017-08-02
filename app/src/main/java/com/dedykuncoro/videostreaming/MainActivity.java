package com.dedykuncoro.videostreaming;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final String VIDEO_URL = "http://dedykuncoro.com/childrens-song/uploads/videos/japanese_childrens_song_-_okina_kuri_no_ki_no_shita_de.mp4";

    ProgressDialog pDialog;
    VideoView videoView;
    MediaController mediaController;
    Uri video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.video_view);

        videoStream();

    }

    private void videoStream() {
        // membuat progressbar
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Buffering ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {
            // Memulai MediaController
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            // Video URL
            video = Uri.parse(VIDEO_URL);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Menutup pDialog dan play video
                public void onPrepared(MediaPlayer mp) {
                    pDialog.dismiss();
                    videoView.start();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

}
