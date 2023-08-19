package src.main.project.GUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public static final String SOUND_FOLDER = "soundfx" + File.separator;
    public static final String CAPTURE = "capture.wav";
    public static final String CASTLE = "castle.wav";
    public static final String CHECKMATE = "game-end.webm";
    public static final String ILLEGAL = "illegal.wav";
    public static final String MOVE = "move.wav";
    public static final String TIME = "no_time.wav";
    public static final String PROMOTE = "promote.wav";
    public static final String CHECK = "move-check.wav";

    private static void playSound(String soundPath) {
        try {
            File soundFile = new File(soundPath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
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