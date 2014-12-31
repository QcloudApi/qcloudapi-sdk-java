### qcloudapi-sdk-java

qcloudapi-sdk-java是为了让Java开发者能够在自己的代码里更快捷方便的使用腾讯云的API而开发的SDK工具包。

#### 资源

* [公共参数](http://wiki.qcloud.com/wiki/%E5%85%AC%E5%85%B1%E5%8F%82%E6%95%B0)
* [API列表](http://wiki.qcloud.com/wiki/API)
* [错误码](http://wiki.qcloud.com/wiki/%E9%94%99%E8%AF%AF%E7%A0%81)

#### 入门

1. 申请安全凭证。
在第一次使用云API之前，用户首先需要在腾讯云网站上申请安全凭证，安全凭证包括 SecretId 和 SecretKey, SecretId 是用于标识 API 调用者的身份，SecretKey是用于加密签名字符串和服务器端验证签名字符串的密钥。SecretKey 必须严格保管，避免泄露。

2. 下载SDK，放入到您的程序目录。
使用方法请参考下面的例子。

#### 例子

    public static void main(String[] args) {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        //config.put("SecretId", "你的secretId");
        //config.put("SecretKey", "你的secretKey");
        //config.put("RequestMethod", "GET");
        //config.put("DefaultRegion", "gz");
        //QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cvm(), config);
        //TreeMap<String, Object> params = new TreeMap<String, Object>();
        //params.put("offset", 0);
        //params.put("limit", 3);
        //System.out.println(module.generateUrl("DescribeInstances", params));
        //System.out.println(module.call("DescribeInstances", params));
        
        config.put("SecretId", "你的secretId");
        config.put("SecretKey", "你的secretKey");
        config.put("RequestMethod", "POST");
        config.put("DefaultRegion", "gz");
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cdn(), config);
        TreeMap<String, Object> params = new TreeMap<String, Object>();
        try{
            //DeleteCdnEntity
            //params.put("entityFileName", "/upload/del.bat");
            //System.out.println(module.call("DeleteCdnEntity", params));

            //UploadCdnEntity
            String fileName = "c:\\9EF077317173460C8C7F48CA4F177D2B.png";       
            params.put("entityFileName", "/upload/9EF077317173460C8C7F48CA4F177D2B.png");
            params.put("entityFileMd5", MD5.fileNameToMD5(fileName));
            params.put("entityFile", fileName);
            System.out.println(module.call("UploadCdnEntity", params));
        
            //DescribeCdnEntities
            //params.put("entityBaseDir", "/upload");
            //System.out.println(module.call("DescribeCdnEntities", params));
        
            //RenameCdnEntity
            //params.put("entityFileName", "/upload/del.bat");
            //params.put("entityNewFileName", "/upload/delete.bat");
            //System.out.println(module.call("RenameCdnEntity", params));
        
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }