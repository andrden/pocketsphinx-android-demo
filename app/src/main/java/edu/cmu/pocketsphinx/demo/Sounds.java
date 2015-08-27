package edu.cmu.pocketsphinx.demo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.Collection;
import java.util.HashMap;

public class Sounds {
    Context context;
    SoundPool soundPool;
    HashMap<Integer, Integer> soundPoolMap;
    AudioManager audioManager;

    private Sounds(Context context){
        this.context = context;
        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
    }

    public Sounds(Context context, int[] preloadResIds) {
        this(context);
        for( int i : preloadResIds){
            soundPoolMap.put(i, soundPool.load(context, i, 1));
        }
    }

    public Sounds(Context context, Collection<Integer> preloadResIds) {
        this(context);
        for( int i : preloadResIds){
            soundPoolMap.put(i, soundPool.load(context, i, 1));
        }
    }

    long getSoundDuration(int rawId){
        MediaPlayer player = MediaPlayer.create(context, rawId);
        int duration = player.getDuration();
        player.release();
        return duration;
    }

    void sound(int resId){
//        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = 1;//curVolume / maxVolume;
        float rightVolume = 1;//curVolume / maxVolume;
        int priority = 1;
        int no_loop = 0;
        float normal_playback_rate = 1f;
        Integer soundID = soundPoolMap.get(resId);
        soundPool.play(soundID, leftVolume, rightVolume, priority, no_loop, normal_playback_rate);
    }

}
