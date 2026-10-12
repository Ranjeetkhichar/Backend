package org.example.DesginPatterns.Factory.AudioFactory;

public class WAVPlayer extends AudioPlayer {

    public WAVPlayer(int volume, double playBackRate) {
        super(volume, playBackRate);
    }

    @Override
    public void play() {
        // Simulate playing audio in WAV format
        // Each media format will have its own implementation
        System.out.println("Playing WAV audio");
    }

    @Override
    public void pause() {
        // Simulate pausing audio in WAV format
        // Each media format will have its own implementation
        System.out.println("Pausing WAV audio");
    }

    @Override
    public void stop() {
        // Simulate stopping audio in WAV format
        // Each media format will have its own implementation
        System.out.println("Stopping WAV audio");
    }


    @Override
    public MediaFormat supportsType() {
        return MediaFormat.WAV;
    }
}
