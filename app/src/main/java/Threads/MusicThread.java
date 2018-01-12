package Threads;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.media.SoundPool;

import Library.Signals.IRing;
import Library.Signals.Notification;
import Library.Signals.Ring;

/**
 * Created by ytgv8b on 12.01.2018.
 */

public class MusicThread extends Thread {
    IRing signal;
    Context context;

    public MusicThread(Context context, IRing signal) {
        super("Music thread");
        this.signal = signal;
        this.context = context;
    }

    @Override
    public void run() {
        super.run();

        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        int melodyID = signal.getMelody();
        float volume = audioManager.getStreamVolume(AudioManager.STREAM_RING);

        AudioAttributes.Builder audioAttributesBuilder = new AudioAttributes.Builder();
        audioAttributesBuilder.setUsage(AudioAttributes.USAGE_ALARM)
                .setLegacyStreamType(AudioManager.STREAM_RING)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED); //воспроизв. гарант. системой
        AudioAttributes audioAttributes = audioAttributesBuilder.build();

        SoundPool.Builder soundPullBuilder = new SoundPool.Builder();
        soundPullBuilder.setMaxStreams(1)
                .setAudioAttributes(audioAttributes);
        SoundPool soundPool = soundPullBuilder.build();

        // TODO: 13.01.2018 загрузка мелодии
        // TODO: 13.01.2018 audio focus

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(0, volume, volume, 1, -1, 1);
            }
        });
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }
}
