package org.example.DesginPatterns.Factory.AudioFactory;

public class AudioPlayerFactory {
    public static AudioPlayer createAudioPlayer(MediaFormat supportType, int volume, double playBackRate) {
        switch (supportType) {
            case FLAC:
                return new FLACPlayer(volume, playBackRate);
            case MP3:
                return new MP3Player(volume, playBackRate);
            case WAV:
                return new WAVPlayer(volume, playBackRate);
            default:
                return null;
        }
    }
}
