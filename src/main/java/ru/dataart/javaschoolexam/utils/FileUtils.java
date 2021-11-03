package ru.dataart.javaschoolexam.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Component
public class FileUtils {
    @Value("${upload.path}")
    private String uploadPath;

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        if (multipartFile != null && !multipartFile.isEmpty()) {
            File file = new File(uploadPath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            return file;
        }
        throw new FileNotFoundException("File is empty");
    }

    public List<File> unpackZipFileToDirectory(File zip, String directory) {
        List<File> filesFromZip = new ArrayList<>();

        if (directory == null || directory.isEmpty()) {
            throw new IllegalStateException("Incorrect file path");
        }

        String directoryToUnpack = uploadPath + directory + File.separator;
        File dir = new File(directoryToUnpack);
        if (!dir.exists()) {
            dir.mkdir();
        }

        try (ZipFile zf = new ZipFile(zip.getPath(), StandardCharsets.UTF_8)) {
            Enumeration<? extends ZipEntry> zipEntries = zf.entries();
            while (zipEntries.hasMoreElements()) {
                ZipEntry zipEntry = zipEntries.nextElement();

                String pathName = directoryToUnpack + zipEntry.getName();
                Files.copy(zf.getInputStream(zipEntry), Paths.get(pathName), StandardCopyOption.REPLACE_EXISTING);
                File file = new File(pathName);
                if (Files.exists(file.toPath())) {
                    filesFromZip.add(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filesFromZip;
    }
}
