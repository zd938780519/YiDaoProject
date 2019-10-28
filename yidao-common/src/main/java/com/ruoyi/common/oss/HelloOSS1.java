/**
 * 示例说明
 * 
 * HelloOSS是OSS Java SDK的示例程序，您可以修改endpoint、accessKeyId、accessKeySecret、bucketName后直接运行。
 * 运行方法请参考README。
 * 
 * 本示例中的并不包括OSS Java SDK的所有功能，详细功能及使用方法，请参看“SDK手册 > Java-SDK”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/preface.html?spm=5176.docoss/sdk/java-sdk/。
 * 
 * 调用OSS Java SDK的方法时，抛出异常表示有错误发生；没有抛出异常表示成功执行。
 * 当错误发生时，OSS Java SDK的方法会抛出异常，异常中包括错误码、错误信息，详细请参看“SDK手册 > Java-SDK > 异常处理”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/exception.html?spm=5176.docoss/api-reference/error-response。
 * 
 * OSS控制台可以直观的看到您调用OSS Java SDK的结果，OSS控制台地址是：https://oss.console.aliyun.com/index#/。
 * OSS控制台使用方法请参看文档中心的“控制台用户指南”， 指南的来链接地址是：https://help.aliyun.com/document_detail/oss/getting-started/get-started.html?spm=5176.docoss/user_guide。
 * 
 * OSS的文档中心地址是：https://help.aliyun.com/document_detail/oss/user_guide/overview.html。
 * OSS Java SDK的文档地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/install.html?spm=5176.docoss/sdk/java-sdk。
 * 
 */

package com.ruoyi.common.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class HelloOSS1 {
    private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "LTAImyrf18KHUtlP";
    private static String accessKeySecret = "kV6XSClCVQA6fYcqd6Mfh8uzhRHcAk";
    private static String bucketName = "yd-2019-test";
    private static String objectName = "image/report/9246803b-dd13-47ce-a779-6758a872cf02.png";
    public static void main(String[] args) {
/*        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //OSSClient ossClient = new OSSClient(OSSStaticPeram.ENDPOINT,OSSStaticPeram.ACCESS_KEY_ID,OSSStaticPeram.ACCESS_KEY_SECRET);
        try {
            Date expiration = new Date(new Date().getTime() + 1000l * 3600 * 24 * 365 * 100);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            System.out.println(url.toString());
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }*/


        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。


// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 1000l * 3600 * 24 * 365 * 100);
// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
System.out.println(url.toString());
// 关闭OSSClient。
        ossClient.shutdown();


    }

}
