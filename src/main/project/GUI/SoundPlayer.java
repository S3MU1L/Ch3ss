package src.main.project.GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    public static final String SOUND_FOLDER = "soundfx" + File.separator;
    public static final String CAPTURE = "capture.wav";
    public static final String CASTLE = "castle.wav";
    public static final String CHECKMATE = "game-end.wav";
    public static final String ILLEGAL = "illegal.wav";
    public static final String MOVE = "move.wav";
    public static final String TIME = "no_time.wav";
    public static final String PROMOTE = "promote.wav";
    public static final String CHECK = "move-check.wav";

    private static void playSound(String soundPath) {
        try {
            URL soundURL = SoundPlayer.class.getResource(File.separator + soundPath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playCaptureSound() {
        playSound(SOUND_FOLDER + CAPTURE);
    }

    public static void playCastleSound() {
        playSound(SOUND_FOLDER + CASTLE);
    }

    public static void playCheckmateSound() {
        playSound(SOUND_FOLDER + CHECKMATE);
    }

    public static void playIllegalSound() {
        playSound(SOUND_FOLDER + ILLEGAL);
    }

    public static void playMoveSound() {
        playSound(SOUND_FOLDER + MOVE);
    }

    public static void playTimeSound() {
        playSound(SOUND_FOLDER + TIME);
    }

    public static void playPromoteSound() {
        playSound(SOUND_FOLDER + PROMOTE);
    }

    public static void playCheckSound() {
        playSound(SOUND_FOLDER + CHECK);
    }
}