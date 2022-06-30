package com.mpc.anita;

import com.mpc.anita.minio.MinioService;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class AnitaApplicationTests {

    @Autowired
    private MinioService minioService;

    @Autowired
    private com.mpc.anita.minio.MinioProperty MinioProperty;

    @Test
    void contextLoads() throws Exception{

        minioService.getObject("20220630mpc.txt");
    }

}
