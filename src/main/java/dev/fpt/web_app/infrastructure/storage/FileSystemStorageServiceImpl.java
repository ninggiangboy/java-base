package dev.fpt.web_app.infrastructure.storage;

import dev.fpt.web_app.application.exception.NotFoundException;
import dev.fpt.web_app.application.service.StorageService;
import dev.fpt.web_app.common.util.StringUtils;
import dev.fpt.web_app.infrastructure.config.property.StorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileSystemStorageServiceImpl implements StorageService {

    private final Path rootLocation;

    public FileSystemStorageServiceImpl(StorageProperties properties) {
        if (StringUtils.isBlank(properties.location())) {
            throw new IllegalArgumentException("Location must not be empty");
        }
        this.rootLocation = Paths.get(properties.location());
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Failed to store empty file.");
            }
            String randomFileName = generateRandomFileName(file.getOriginalFilename());
            Path destinationFile = this.rootLocation
                    .resolve(Paths.get(randomFileName))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new IllegalArgumentException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return randomFileName;
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to store file.", e);
        }
    }

    private String generateRandomFileName(String originalFilename) {
        return UUID.randomUUID() + "_" + originalFilename;
    }

    @Override
    public Path load(String filename) {
        Path file = rootLocation.resolve(filename);
        if (!Files.exists(file)) {
            throw new NotFoundException("File not found: " + filename);
        }
        return file;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IllegalArgumentException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not read file: " + filename);
        }
    }

    @Override
    public void delete(String filename) {
        FileSystemUtils.deleteRecursively(load(filename).toFile());
    }
}
