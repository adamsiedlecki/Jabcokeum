package pl.adamsiedlecki.jabcokeum.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileUtil {

    public static List<File> listBlockFilesForFolder(final File folder) {
        if(folder == null) {
            return null;
        }
        createDirectories(folder);

        log.info("Listing block files in directory: {}", folder.getAbsolutePath());
        var files = folder.listFiles();

        if(files != null) {
            return Arrays.stream(files).filter(f->f.isFile() && f.getName().endsWith(".jabcokeum")).collect(Collectors.toList());
        }
        return List.of();
    }

    public static void createDirectories(File directory) {
        try {
            Files.createDirectories(directory.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(File file) {
        try {
            return String.join("\n", Files.readAllLines(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(File f, String content) {
        log.info("Trying to write to file: {}", f.getAbsolutePath());
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            writer.append(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
