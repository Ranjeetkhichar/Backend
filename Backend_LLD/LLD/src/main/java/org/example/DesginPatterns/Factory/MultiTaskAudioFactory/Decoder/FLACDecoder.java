package org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Decoder;

import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.MediaFormat;

public class FLACDecoder extends AudioDecoder {
    public FLACDecoder(byte[] audioData) {
        super(audioData);
    }

    @Override
    public MediaFormat supportsFormat() {
        return MediaFormat.FLAC;
    }

    @Override
    public byte[] decode() {
        // Implement FLAC decoding logic
        System.out.println("Decoding FLAC audio data...");
        // Decoding process
        return getAudioData();
    }
}
