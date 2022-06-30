package com.mpc.anita.minio;

import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;

/**
 * @author mapengcheng
 * @Description("Minio相关接口")
 * @Date 6/28/2022 5:24 PM
 * @return
 **/
@Component
public class MinioService {

    @Autowired
    private MinioProperty property;


    /**
     * @author mapengcheng
     * @Description("get bucket name from application.yml")
     * @Date 6/30/2022 10:50 AM
     * @return bucket name
     **/
    private String getBucketName(){
        return property.getBucketName();
    }

    /**
     * @author mapengcheng
     * @Description("Create Minio Client")
     * @Date 6/28/2022 5:25 PM
     * @return io.minio.MinioClient
     **/
    public MinioClient getMinioClient(){
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(property.getEndpoint())
                        .credentials(property.getAccessKey(), property.getSecretKey())
                        .build();
        return minioClient;

    }

    /**
     * @author mapengcheng
     * @Description("check whether "anita-data" exists or not")
     * @Date 6/28/2022 5:26 PM
     * @param bucketName
     * @return boolean if bucket exist, return true
     **/
    public boolean bucketExist(String bucketName) throws Exception{
        return getMinioClient().bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    
    /**
     * @author mapengcheng
     * @Description("Lists bucket information of all buckets.")
     * @Date 6/29/2022 3:03 PM
     * @param  
     * @return java.util.List<io.minio.messages.Bucket>
     **/
    public List<Bucket> listBuckets() throws Exception{
        return getMinioClient().listBuckets();
    }

    /**
     * @author mapengcheng
     * @Description("Creates a new bucket")
     * @Date 6/29/2022 3:07 PM
     * @param bucketName  new bucket name
     * @return void
     **/
    public void makeBucket(String bucketName) throws Exception{
        getMinioClient().makeBucket(
                MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build()
        );
    }


    /**
     * @author mapengcheng
     * @Description("download file(objectName) from minio, and save as a file(fileName)")
     * @Date 6/29/2022 5:00 PM
     * @param objectName minio file name
     * @param fileName  download file as fileName
     * @return void
     **/
    public void downloadObject(String objectName,String fileName) throws Exception{
        getMinioClient().downloadObject(
                DownloadObjectArgs.builder()
                        .bucket(getBucketName())
                        .object(objectName)
                        .filename(fileName)
                        .build());
    }

    public InputStream getObject(String objectName) throws Exception {
        try (InputStream stream = getMinioClient().getObject(
                GetObjectArgs.builder()
                        .bucket(getBucketName())
                        .object(objectName)
                        .build())) {
            return stream;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}

