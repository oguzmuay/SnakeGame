package com.Oguz.engine.Audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundClip {
    private Clip clip;
    private FloatControl gainControl;

    public SoundClip(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
        InputStream bufferedIn = new BufferedInputStream(audioSrc);
        AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
        AudioFormat baseFormat = ais.getFormat();
        AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                    baseFormat.getSampleRate(),16, baseFormat.getChannels(),
                            baseFormat.getChannels()*2,baseFormat.getSampleRate(),false);
        AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);
        clip = AudioSystem.getClip();
        clip.open(dais);
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    public void Play()
    {
        if(clip == null)
            return;
        Stop();
        clip.setFramePosition(0);
        while (!clip.isRunning())
        {
            clip.start();
        }
    }
    public void Stop()
    {
        if(clip.isRunning())
            clip.stop();
    }
    public void Close()
    {
        Stop();
        clip.drain();
        clip.close();
    }
    public void Loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Play();
    }
    public void SetVolume(float value)
    {
        gainControl.setValue(value);
    }
    public boolean isRunning()
    {
        return clip.isRunning();
    }
}
