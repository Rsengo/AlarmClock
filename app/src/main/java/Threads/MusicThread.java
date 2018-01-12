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
    private IRing signal;
    private Context context;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private SoundPool soundPool;

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
        float tryVolume;

        try {
            tryVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        }  catch (NullPointerException ex) {
            tryVolume = 1;
        }

        final float volume = tryVolume;

        AudioAttributes.Builder audioAttributesBuilder = new AudioAttributes.Builder();
        audioAttributesBuilder.setUsage(AudioAttributes.USAGE_ALARM)
                .setLegacyStreamType(AudioManager.STREAM_RING)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED); //воспроизв. гарант. системой
        AudioAttributes audioAttributes = audioAttributesBuilder.build();

        SoundPool.Builder soundPullBuilder = new SoundPool.Builder();
        soundPullBuilder.setMaxStreams(1)
                .setAudioAttributes(audioAttributes);
        soundPool = soundPullBuilder.build();

        soundPool.load(context, melodyID, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                int focus = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_RING, AudioManager.AUDIOFOCUS_GAIN);

                if (focus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    soundPool.play(melodyID, volume, volume, 1, -1, 1);
                }
            }
        });

        //аудио фокус
        audioFocusChangeListener = focusChange -> {
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                soundPool.pause(0);
                                break;
                            case AudioManager.AUDIOFOCUS_GAIN:
                                try {
                                    soundPool.resume(0);
                                } catch (Exception ex) {
                                    soundPool.play(melodyID, volume, volume,
                                            1, -1, 1);
                                }
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                soundPool.stop(0);
                        }
                    };
    }

    @Override
    public void interrupt() {
        super.interrupt();
        soundPool.stop(0);
        soundPool.release();
    }
}
