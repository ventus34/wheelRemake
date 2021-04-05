package ventus.rggwheel.services.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import ventus.rggwheel.App;

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
    private MediaPlayer musicPlayer;
    private MediaPlayer soundClipPlayer;
    private MediaPlayer sfxPlayer;
    private final Map<AvailablePlayersEnum, MediaPlayer> players;

    public enum AvailablePlayersEnum{
        MUSIC, SOUND_CLIPS, SFX
    }

    Map<String, Media> soundClipsMap = load("sound/clips");
    Map<String, Media> sfxMap = load("sound/sfx");
    Map<String, Media> musicMap = load("sound/music");

    public MediaPlayerService(){
        players = new HashMap<>();
        players.put(AvailablePlayersEnum.MUSIC, musicPlayer);
        players.put(AvailablePlayersEnum.SFX, sfxPlayer);
        players.put(AvailablePlayersEnum.SOUND_CLIPS, soundClipPlayer);
    }

    public void play(AvailablePlayersEnum player, String fileName){
        switch (player) {
            case MUSIC:
                musicPlayer = new MediaPlayer(musicMap.get(fileName));
                musicPlayer.play();
                break;
            case SOUND_CLIPS:
                soundClipPlayer = new MediaPlayer(soundClipsMap.get(fileName));
                soundClipPlayer.play();
                break;
            case SFX:
                sfxPlayer = new MediaPlayer(sfxMap.get(fileName));
                sfxPlayer.play();
                break;
        }
    }

    public void setVolume(AvailablePlayersEnum player, Double volume){
        try {
            players.get(player).setVolume(volume);
        } catch (NullPointerException e){
            System.out.println("Player has not been initialized");
        }
    }

    public void seek(AvailablePlayersEnum player, Duration destination){
        try {
            players.get(player).seek(destination);
        } catch (NullPointerException e){
            System.out.println("Player has not been initialized");
        }
    }

    public void stop(AvailablePlayersEnum player){
        try {
            players.get(player).stop();
        } catch (NullPointerException e){
            System.out.println("Player has not been initialized");
        }
    }

    public void replay(AvailablePlayersEnum player){
        try {
            MediaPlayer currentPlayer = players.get(player);
            currentPlayer.seek(new Duration(0));
            currentPlayer.play();
        } catch (NullPointerException e){
            System.out.println("Player has not been initialized");
        }
    }

    public MediaPlayer getPlayer(AvailablePlayersEnum player){
        try {
            return players.get(player);
        } catch (NullPointerException e){
            System.out.println("Player has not been initialized");
        }
        return null;
    }

    private Map<String, Media> load(String directoryPath){
        Map<String, Media> mediaMap = new HashMap<>();
        List<Path> result;
        URL soundDirectory = App.class.getResource(directoryPath);
        try (Stream<Path> walk = Files.walk(Paths.get(soundDirectory.toURI()))) {
            result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
            result.forEach(currentFilePath -> {
                mediaMap.put(currentFilePath.getFileName().toString(), new Media(URIResolver(directoryPath, currentFilePath)));
            });
            return mediaMap;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private String URIResolver(String directory, Path filePath){
        return App.class.getResource(directory + "/" + filePath.getFileName().toString()).toExternalForm();
    }
}
