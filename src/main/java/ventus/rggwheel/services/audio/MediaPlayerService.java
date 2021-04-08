package ventus.rggwheel.services.audio;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MediaPlayerService {
    private final Map<AudioPlayerEnum, MediaPlayer> players;

    public enum AudioPlayerEnum {
        MUSIC, SOUND_CLIPS, SFX
    }

    private Double GLOBAL_VOLUME = 0.2;

    Map<String, Media> soundClipsMap = load("sound/clips/");
    Map<String, Media> sfxMap = load("sound/sfx/");
    Map<String, Media> musicMap = load("sound/music/");

    public MediaPlayerService() {
        players = new HashMap<>();
        players.put(AudioPlayerEnum.MUSIC, null);
        players.put(AudioPlayerEnum.SFX, null);
        players.put(AudioPlayerEnum.SOUND_CLIPS, null);
    }

    public void play(AudioPlayerEnum player, String fileName) {
        switch (player) {
            case MUSIC:
                players.replace(AudioPlayerEnum.MUSIC, getAudioPlayer(musicMap.get(fileName)));
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

    public MediaPlayer getPlayer(AudioPlayerEnum player) {
        try {
            return players.get(player);
        } catch (NullPointerException e) {
            System.out.println("Player has not been initialized");
        }
        return null;
    }

    private Map<String, Media> load(String directoryPath) {
        File mainDir = new File (System.getProperty("user.dir"));
        Map<String, Media> mediaMap = new HashMap<>();
        List<Path> result;
        try (Stream<Path> walk = Files.walk(Paths.get(mainDir.toURI().resolve(directoryPath)))) {
            result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
            result.forEach(currentFilePath -> {
                mediaMap.put(currentFilePath.getFileName().toString(), new Media(currentFilePath.toUri().toString()));
            });
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
}
