package renamer.server.util;

import renamer.shared.file.PathUt;
import java.io.File;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import renamer.shared.file.FileData;
import renamer.shared.file.FileSys;
import renamer.shared.logging.Logs;

/**
 * @file FileUt.java
 * @author Matt Buske
 * @version 0.2A
 * @date 12/17/2013
 */
/**
 * A utility class for renaming files.
 */
public class FileUt {

    /**
     * The Logger for the LastAccess Class.
     */
    private static final Logger logger = Logs.getLogger(FileUt.class.getName());

    public static boolean renameFile(String fileToBeRenamed, String newFileName, File folder, String tag, boolean isSubs) {
        String ext = PathUt.getFileExtension(fileToBeRenamed);
        File oldName = new File(folder.getAbsolutePath() + FileData.SEP + fileToBeRenamed);
        try {
            boolean successRN;
            //fix name in case
            String t = Pattern.quote("?");
            newFileName = newFileName.replaceAll(t, "");
            t = Pattern.quote(":");
            newFileName = newFileName.replaceAll(t, "");
            File formattedName;
            String nameNoExt;
            if (isSubs) {
                // String tag = options.getLanguageTag();
                nameNoExt = folder.getAbsolutePath() + FileData.SEP + newFileName + tag;
                formattedName = new File(nameNoExt + ext);
            } else {
                nameNoExt = folder.getAbsolutePath() + FileData.SEP + newFileName;
                formattedName = new File(nameNoExt + ext);
            }
            if (formattedName.getAbsolutePath().equals(oldName.getAbsolutePath())) {
                Logs.logInfo("No Need To Rename " + formattedName.getName() + ".", logger);
                return true;
            } else {
            }
            if (formattedName.exists()) {
                if (!renameExistingFile(formattedName, nameNoExt, ext)) {
                    System.out.println("Could not sidestep the renaming bug.");
                } else {
                    System.out.println("Success with sidestepping the renaming bug.");
                    if (!formattedName.exists()) {
                        System.out.println("Bug Sidestep Success Verified.");
                        System.out.println(formattedName.getAbsolutePath());
                    }
                }
            }
            if (oldName.exists()) {
                Logs.logInfo("Previous " + FileSys.getFileExistsMessage(oldName.getAbsolutePath()), logger);
            }
            successRN = oldName.renameTo(formattedName);
            if (successRN) {
                Logs.logInfo("Successful Rename to \"" + formattedName.getAbsolutePath() + "\"", logger);
                return true;
            } else {
                //error check, is the error because the file already exists?
                Logs.logWarning("Could Not Rename \"" + oldName.getAbsolutePath() + "\" to \"" + formattedName.getAbsolutePath() + "\"", logger);
                return false;
            }
        } catch (Exception e) {
            Logs.logException(e, "Failed To Rename " + oldName.getName() + ".", logger);
            return false;
        }
    }

    public static boolean renameExistingFile(File formattedName, String name, String ext) {
        File temp = new File(name + "_temp_" + ext);

        return formattedName.renameTo(temp);
    }
}
