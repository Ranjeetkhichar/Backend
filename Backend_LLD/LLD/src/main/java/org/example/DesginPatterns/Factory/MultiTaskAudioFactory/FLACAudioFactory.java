package org.example.DesginPatterns.Factory.MultiTaskAudioFactory;

import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Decoder.AudioDecoder;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Decoder.FLACDecoder;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Player.AudioPlayer;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Player.FLACPlayer;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor.AudioProcessor;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor.FLACAudioProcessor;

public class FLACAudioFactory extends AudioFactory {

    @Override
    public MediaFormat supportsFormat() {
        return MediaFormat.FLAC;
    }

    @Override
    public AudioDecoder createAudioDecorder(byte[] audioData) {
        return new FLACDecoder(audioData);
    }

    @Override
    public AudioProcessor createAudioProcessor(byte[] audioData) {
        return new FLACAudioProcessor(audioData);
    }

    @Override
    public AudioPlayer createAudioPlayer(int volume, double playbackRate) {
        return new FLACPlayer(volume, playbackRate);
    }
}
