package org.example.DesginPatterns.Factory.MultiTaskAudioFactory;

import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Decoder.AudioDecoder;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Player.AudioPlayer;
import org.example.DesginPatterns.Factory.MultiTaskAudioFactory.Processor.AudioProcessor;

public abstract class AudioFactory {

    public abstract MediaFormat supportsFormat();

    public abstract AudioDecoder createAudioDecorder(byte[] audioData);

    public abstract AudioProcessor createAudioProcessor(byte[] audioData);

    public abstract AudioPlayer createAudioPlayer(int volume, double playbackRate);
}