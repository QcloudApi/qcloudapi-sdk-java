# qcloudapi-sdk-java

qcloudapi-sdk-java是为了让Java开发者能够在自己的代码里更快捷方便的使用腾讯云的API而开发的SDK工具包。

## 资源

* [公共参数](http://wiki.qcloud.com/wiki/%E5%85%AC%E5%85%B1%E5%8F%82%E6%95%B0)
* [API列表](http://wiki.qcloud.com/wiki/API)
* [错误码](http://wiki.qcloud.com/wiki/%E9%94%99%E8%AF%AF%E7%A0%81)

## 入门

1. 申请安全凭证。
在第一次使用云API之前，用户首先需要在腾讯云网站上申请安全凭证，安全凭证包括 SecretId 和 SecretKey, SecretId 是用于标识 API 调用者的身份，SecretKey是用于加密签名字符串和服务器端验证签名字符串的密钥。SecretKey 必须严格保管，避免泄露。

2. 下载SDK，放入到您的程序目录。或使用Maven。
3. SDK Maven使用示例，所有SDK版本可查看[此链接](https://mvnrepository.com/artifact/com.qcloud/qcloud-java-sdk)。

```
<dependency>
  <groupId>com.qcloud</groupId>
  <artifactId>qcloud-java-sdk</artifactId>
  <version>2.0.6</version>
</dependency>
```
使用方法请参考下面的例子。

## 例子

### DescribeInstances 接口

	public class Demo {
	public static void main(String[] args) {
		/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", "你的secretId");
		config.put("SecretKey", "你的secretKey");
		/* 请求方法类型 POST、GET */
		config.put("RequestMethod", "GET");
		/* 区域参数，可选: gz:广州; sh:上海; hk:中国香港; ca:北美;等。 */
		config.put("DefaultRegion", "gz");

		/*
		 * 你将要使用接口所在的模块，可以从 官网->云api文档->XXXX接口->接口描述->域名
		 * 中获取，比如域名：cvm.api.qcloud.com，module就是new Cvm()。
		 */
		/*
		 * DescribeInstances
		 * 的api文档地址：http://www.qcloud.com/wiki/v2/DescribeInstances
		 */
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cvm(),config);
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		// 将需要输入的参数都放入 params 里面，必选参数是必填的。
		// DescribeInstances 接口的部分可选参数如下
		params.put("offset", 0);
		params.put("limit", 3);
		// 在这里指定所要用的签名算法，不指定默认为HmacSHA1
		// params.put("SignatureMethod", "HmacSHA256");
		// generateUrl 方法生成请求串，但不发送请求。在正式请求中，可以删除下面这行代码。
        // 如果是POST方法，或者系统不支持UTF8编码，则仅会打印host+path信息。
		// System.out.println(module.generateUrl("DescribeInstances", params));

		String result = null;
		try {
			// call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。
			result = module.call("DescribeInstances", params);
            // 可以对返回的字符串进行json解析，您可以使用其他的json包进行解析，此处仅为示例
			JSONObject json_result = new JSONObject(result);
			System.out.println(json_result);
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
		}

	}
}

## 动态模块

以前SDK需要把每一个产品都建一个类文件，指定域名，这虽然加强了合法性检查，但是新产品上线后，可能没有同步到SDK，造成开发者使用不便。
2.0.7及更高的版本支持动态模块，你可以直接按模块名初始化，例如：

```
import com.qcloud.Module.Morphling;

...

        // ckafka并未有定义文件，但你仍然可以通过Morphling这个类来指定初始化
        Morphling morphling = new Morphling("ckafka");
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(morphling, config);
        String action = "ListInstance";

...
```

这里ckafka并未有对应的类文件，但是仍然可以初始化成功并执行接下来的接口调用。注意，代码中实际上是把模块名拼接API的根域名进行调用的，而极个别的产品其模块名和域名不对应，典型的如cmq的域名首部是带地域信息的，此时模块名应该遵从域名首部。
