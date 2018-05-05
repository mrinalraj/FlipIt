package com.mrinalraj.flipit;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class SoundPlayer {
    Context ctx;
    public SoundPlayer(Context context){
        this.ctx = context;
    }
    MediaPlayer player = null;
    void playSound(String fileName){
        player = new MediaPlayer();
        try {
            AssetFileDescriptor afd = ctx.getAssets().openFd(fileName);
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        player.start();
    }
}
