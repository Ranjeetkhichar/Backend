package org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor;

import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.MediaFormat;

public abstract class AudioProcessor {
    private final byte[] audioData;

    public AudioProcessor(byte[] audioData) {
        this.audioData = audioData;
    }

    public byte[] getAudioData() {
        return audioData;
    }

    public abstract MediaFormat supportsFormat();

    public abstract byte[] process();
}
