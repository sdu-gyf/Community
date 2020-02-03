package top.sdugyf.community.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
public class FileProvider {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String upload(InputStream fileStream, String fileName) {
        String generatedFileName = "";
        String[] filePaths = fileName.split("\\.");
        if(filePaths.length>1) {
            generatedFileName = UUID.randomUUID().toString() + "."+filePaths[filePaths.length-1];
        } else {
            return null;
        }
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = bucketName+"/"+generatedFileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        ossClient.putObject(bucketName, objectName, fileStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        return generatedFileName;
    }
}

