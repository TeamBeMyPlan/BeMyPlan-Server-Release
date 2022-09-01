package com.deploy.bemyplan.image.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @Value("${s3.profile}")
    public String profile;

    public List<String> upload(final MultipartFile[] files) {
        final List<String> fileNameList = new ArrayList<>();

        Arrays.stream(files)
                .forEach(file -> {
                    final String fileName = createFileName();
                    final ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(file.getSize());
                    metadata.setContentType(file.getContentType());

                    try(final InputStream inputStream = file.getInputStream()) {
                        s3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
                                .withCannedAcl(CannedAccessControlList.PublicRead));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    fileNameList.add(fileName);
                });
        return fileNameList;
    }

    private String createFileName() {
        return profile + "/" + UUID.randomUUID();
    }

    public List<String> get() {
        ListObjectsV2Request request = new ListObjectsV2Request()
                .withBucketName(bucket);

        return s3Client.listObjectsV2(request)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }
}
