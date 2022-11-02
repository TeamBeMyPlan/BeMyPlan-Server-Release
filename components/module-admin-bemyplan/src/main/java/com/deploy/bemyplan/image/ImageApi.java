package com.deploy.bemyplan.image;

import com.deploy.bemyplan.image.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ImageApi {
    private final S3Uploader s3Uploader;

    @PostMapping("upload")
    public List<String> upload(MultipartFile[] files) {
        return s3Uploader.upload(files);
    }
}
