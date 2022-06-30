package com.mpc.anita.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: Minio配置文件类
 * @Auther: mapengcheng
 * @Date: 2022/06/29/10:25 AM
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperty {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private int partSize;
    private String bucketName;

}