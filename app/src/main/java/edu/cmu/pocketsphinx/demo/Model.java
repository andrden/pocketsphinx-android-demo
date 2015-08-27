package edu.cmu.pocketsphinx.demo;

import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by denny on 8/27/15.
 */
public class Model {
    TextToSpeech tts;
    Sounds sounds;

    String day = Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);

    Map<String,String> ttsResponses = new HashMap<String,String>(){{
       put("who are you", "I am a gnome");
       put("how old are you", "I am two");
       put("today is", "Today is "+day);
    }};

    static Map<String,Integer> soundResponses = new HashMap<String,Integer>(){{
        put("make a crow call", R.raw.crow6);
        put("crow like a rooster", R.raw.rooster);
        put("say something in russian", R.raw.iamgnome);
    }};

    List<String> allPhrases(){
        List<String> ret = new ArrayList<>();
        ret.addAll(ttsResponses.keySet());
        ret.addAll(soundResponses.keySet());
        return ret;
    }


    public Model(TextToSpeech tts, Sounds sounds) {
        this.tts = tts;
        this.sounds = sounds;
    }

    void wordSaid(String text){
        if (ttsResponses.containsKey(text)) {
            tts.speak(ttsResponses.get(text), TextToSpeech.QUEUE_FLUSH, null);
            while(tts.isSpeaking()){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (soundResponses.containsKey(text)) {
            sounds.sound(soundResponses.get(text));
        }
    }

}
