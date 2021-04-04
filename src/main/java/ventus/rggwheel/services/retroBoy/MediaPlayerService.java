package ventus.rggwheel.services.retroBoy;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ventus.rggwheel.App;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
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

    public MediaPlayerService() {

    }

    public void test(){
        Map<String, Media> mediaMap = load("sound/clips");
        System.out.println("OK");
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
