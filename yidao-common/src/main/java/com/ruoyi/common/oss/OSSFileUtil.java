package com.ruoyi.common.oss;

import com.aliyun.oss.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;

/**
 * oss上传图片或视频工具类
 */
public class OSSFileUtil {
    /**
     * 上传
     * @param fileKey
     * @param file
     */
    public static String upload(String fileKey, MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(OSSStaticPeram.ENDPOINT,OSSStaticPeram.ACCESS_KEY_ID,OSSStaticPeram.ACCESS_KEY_SECRET);
        try {
            ossClient.putObject(OSSStaticPeram.BUCKET_NAME, fileKey,new ByteArrayInputStream(file.getBytes()));
            // 设置URL过期时间为100年，默认这里是int型，转换为long型即可
            Date expiration = new Date(new Date().getTime() + 1000l * 3600 * 24 * 365 * 100);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(OSSStaticPeram.BUCKET_NAME, fileKey, expiration);
            String path = url.toString();
            path = path.substring(path.indexOf(".aliyuncs.com/") + 14,path.length());
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
       return "";
    }

    /**
     * 删除
     * @param fileKey
     */
    public static void remove(String fileKey) {
        OSS ossClient = new OSSClientBuilder().build(OSSStaticPeram.ENDPOINT,OSSStaticPeram.ACCESS_KEY_ID,OSSStaticPeram.ACCESS_KEY_SECRET);
        try {
            ossClient.deleteObject(OSSStaticPeram.BUCKET_NAME, fileKey);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
    }
    // 获取文 MultipartFile 文件后缀名工具
    public static String getSuffix(MultipartFile fileupload){
        String originalFilename = fileupload.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        return suffix;
    }

}
