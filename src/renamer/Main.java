package renamer;
import renamer.shared.file.FileData;
import renamer.client.main.GUIForm;
import renamer.shared.logging.Logs;

/**
 *API KEY For TVDB: 1EEB9AB7E941F4F9
 * @author Matt
 * 
 */
public class Main {

    public static void main(String[] args) {
        if (Logs.init(FileData.LOGDIR)) {
            GUIForm.main(args);
        } else {
            System.out.println("Error loading Logs.");
        }
        
    }
}
