package org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor;

import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.MediaFormat;

public class FLACAudioProcessor extends AudioProcessor {
    public FLACAudioProcessor(byte[] audioData) {
        super(audioData);
    }

    @Override
    public MediaFormat supportsFormat() {
        return MediaFormat.FLAC;
    }

    @Override
    public byte[] process() {
        // Implement FLAC audio processing logic
        System.out.println("Processing FLAC audio data...");
        // Processing process
        return getAudioData();
    }
}
