package xyz.sadiulhakim.file;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

public class FileAccessor {
    private FileAccessor() {
    }

    public static File getFile(String path) {
        var classLoader = FileAccessor.class.getClassLoader();
        try {
            return new File(Objects.requireNonNull(classLoader.getResource(path)).toURI());
        } catch (Exception ex) {
            return null;
        }
    }

    public static void write(File filePath, String text) {
        try {
            Files.writeString(filePath.toPath(), text);
        } catch (Exception ignore) {

        }
    }

    public static boolean delete(File filePath) {
        try {
            if (filePath.exists()) {
                return filePath.delete();
            }

        } catch (Exception ignore) {
        }
        return false;
    }
}
