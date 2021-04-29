package ventus.rggwheel.services.audio;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MediaPlayerService {
    private final Map<AudioPlayerEnum, MediaPlayer> players;
    private int musicTime = 10;

    public enum AudioPlayerEnum {
        MUSIC, SOUND_CLIPS, SFX
    }

    private final Double GLOBAL_VOLUME = 0.7;

    Map<String, Media> soundClipsMap = load("sound/clips/");
    Map<String, Media> sfxMap = load("sound/sfx/");
    Map<String, Media> lessThanFiveSecondsMap = load("sound/music/5s");
    Map<String, Media> lessThanTenSecondsMap = load("sound/music/10s");
    Map<String, Media> lessThanThirtySecondsMap = load("sound/music/30s");
    Map<String, Media> lessThanSixtySecondsMap = load("sound/music/60s");
    Map<String, Media> longerMusicMap = load("sound/music/90s");

    public MediaPlayerService() {
        players = new HashMap<>();
        players.put(AudioPlayerEnum.MUSIC, null);
        players.put(AudioPlayerEnum.SFX, null);
        players.put(AudioPlayerEnum.SOUND_CLIPS, null);
    }

    Random rng = new Random();

    public void play(AudioPlayerEnum player, String fileName) {
        switch (player) {
            case MUSIC:
                ArrayList<Media> music;
                System.out.println(musicTime);
                if(musicTime <= 5){
                    music = new ArrayList<>(lessThanFiveSecondsMap.values());
                } else if(musicTime <= 10){
                    music = new ArrayList<>(lessThanTenSecondsMap.values());
                } else if(musicTime <= 20){
                    music = new ArrayList<>(lessThanThirtySecondsMap.values());
                } else if(musicTime <= 30){
                    music = new ArrayList<>(lessThanThirtySecondsMap.values());
                    music.addAll(lessThanSixtySecondsMap.values());
                } else if(musicTime <= 45){
                    music = new ArrayList<>(lessThanSixtySecondsMap.values());
                } else if(musicTime <= 60){
                    music = new ArrayList<>(lessThanSixtySecondsMap.values());
                    music.addAll(longerMusicMap.values());
                } else {
                    music = new ArrayList<>(longerMusicMap.values());
                }
                Media randomMusic = music.get(rng.nextInt(music.size()));
                System.out.println(randomMusic.getSource());
                players.replace(AudioPlayerEnum.MUSIC, getAudioPlayer(randomMusic));
                players.get(AudioPlayerEnum.MUSIC).play();
                break;
            case SOUND_CLIPS:
                players.replace(AudioPlayerEnum.SOUND_CLIPS, getAudioPlayer(soundClipsMap.get(fileName)));
                players.get(AudioPlayerEnum.SOUND_CLIPS).play();
                break;
            case SFX:
                players.replace(AudioPlayerEnum.SFX, getAudioPlayer(sfxMap.get(fileName)));
                players.get(AudioPlayerEnum.SFX).play();
                break;
        }
    }


    public void fadeout(AudioPlayerEnum player, double fadeoutTime) {
        MediaPlayer currentPlayer = null;
        switch (player) {
            case MUSIC:
                currentPlayer = players.get(AudioPlayerEnum.MUSIC);
                break;
            case SOUND_CLIPS:
                currentPlayer = players.get(AudioPlayerEnum.SOUND_CLIPS);
                break;
            case SFX:
                currentPlayer = players.get(AudioPlayerEnum.SFX);
                break;
        }
        new Timeline(
                new KeyFrame(Duration.seconds(fadeoutTime),
                        new KeyValue(currentPlayer.volumeProperty(), 0))).play();
    }


    public void setVolume(AudioPlayerEnum player, Double volume) {
        try {
            players.get(player).setVolume(volume);
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    public void seek(AudioPlayerEnum player, Duration destination) {
        try {
            players.get(player).seek(destination);
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    public void stop(AudioPlayerEnum player) {
        try {
            players.get(player).stop();
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    public void replay(AudioPlayerEnum player) {
        try {
            MediaPlayer currentPlayer = players.get(player);
            currentPlayer.seek(new Duration(0));
            currentPlayer.play();
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
    }

    private Map<String, Media> load(String directoryPath) {
        File mainDir = new File (System.getProperty("user.dir"));
        Map<String, Media> mediaMap = new HashMap<>();
        List<Path> result;
        try (Stream<Path> walk = Files.walk(Paths.get(mainDir.toURI().resolve(directoryPath)))) {
            result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
            result.forEach(currentFilePath -> mediaMap.put(currentFilePath.getFileName().toString(), new Media(currentFilePath.toUri().toString())));
            return mediaMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private MediaPlayer getAudioPlayer(Media media) {
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(GLOBAL_VOLUME);
        return player;
    }

    public void setMusicTime(int musicTime) {
        this.musicTime = musicTime;
    }
}