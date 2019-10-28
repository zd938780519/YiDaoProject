package com.ruoyi.common.oss;

/**
 * 阿里云OSS配置
 */
public class OSSStaticPeram {
    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    // 如果您还没有创建Bucket，endpoint选择请参看文档中心的“开发人员指南 > 基本概念 > 访问域名”，
    // 链接地址是：https://help.aliyun.com/document_detail/oss/user_guide/oss_concept/endpoint.html?spm=5176.docoss/user_guide/endpoint_region
    // endpoint的格式形如“http://oss-cn-hangzhou.aliyuncs.com/”，注意http://后不带bucket名称，
    // 比如“http://bucket-name.oss-cn-hangzhou.aliyuncs.com”，是错误的endpoint，请去掉其中的“bucket-name”。
    public static final String  ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    //public static final String  ACCESS_KEY_ID = "LTAIvDgTLhFSx1mB";
    //public static final String  ACCESS_KEY_SECRET = "QUUNxrV0jDy0VyX2n2W1vYNDRB09NZ";
    public static final String  ACCESS_KEY_ID = "LTAImyrf18KHUtlP";
    public static final String  ACCESS_KEY_SECRET = "kV6XSClCVQA6fYcqd6Mfh8uzhRHcAk";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    public static final String BUCKET_NAME = "yd-2019-test";

}
