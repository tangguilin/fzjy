package com.cisdi.transaction.config.file;

import io.minio.MinioClient;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/8 11:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {


    //minio服务Ip地址
    private String endpoint;

    //minio接入用户名
    private  String accessKey;

    //minio接入密码
    private  String secretKey;

    //存储桶名
    private  String bucketName;

    @Bean
    @SneakyThrows
    public MinioClient minioClient() {
      //return  new MinioClient(endpoint,accessKey,secretKey);
       return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

}
