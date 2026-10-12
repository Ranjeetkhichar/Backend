package org.example.DesginPatterns.Factory.MultiTaskAudioFactory;

import org.example.DesginPatterns.Factory.AudioFactory.AudioPlayerFactory;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Decoder.AudioDecoder;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Decoder.MP3Decoder;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Player.AudioPlayer;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Player.MP3Player;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor.AudioProcessor;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor.MP3AudioProcessor;

public class MP3AudioFactory extends AudioFactory {

    @Override
    public MediaFormat supportsFormat() {
        return MediaFormat.MP3;
    }

    @Override
    public AudioDecoder createAudioDecorder(byte[] audioData) {
        return new MP3Decoder(audioData);
    }

    @Override
    public AudioProcessor createAudioProcessor(byte[] audioData) {
        return new MP3AudioProcessor(audioData);
    }

    @Override
    public AudioPlayer createAudioPlayer(int volume, double playbackRate) {
        return new MP3Player(volume, playbackRate);
    }
}
