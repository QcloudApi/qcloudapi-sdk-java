qcloudapi-sdk-java 是为了让 Java 开发者能够在自己的代码里更快捷方便的使用腾讯云的 API 而开发的 SDK 工具包。

#### 更新
[3/10] 增加HmacSHA256签名算法的兼容
[7/15] 增加Tdsql模块。

#### 资源

* [公共参数](http://wiki.qcloud.com/wiki/%E5%85%AC%E5%85%B1%E5%8F%82%E6%95%B0)
* [API列表](http://wiki.qcloud.com/wiki/API)
* [错误码](http://wiki.qcloud.com/wiki/%E9%94%99%E8%AF%AF%E7%A0%81)

#### 入门

1. 申请安全凭证。
在第一次使用云API之前，用户首先需要在腾讯云网站上申请安全凭证，安全凭证包括 SecretId 和 SecretKey, SecretId 是用于标识 API 调用者的身份，SecretKey是用于加密签名字符串和服务器端验证签名字符串的密钥。SecretKey 必须严格保管，避免泄露。

2. 下载SDK，放入到您的程序目录。
[在 github 查看 >>](https://github.com/QcloudApi/qcloudapi-sdk-java)
[点击下载 JAVA SDK >>](https://mc.qcloudimg.com/static/archive/72dbc1a82ad8e18dead2e6dc07acd5d7/qcloudapi-sdk-java-master.zip)

使用方法请参考下面的例子。

#### 例子
#### DescribeInstances 接口
	public class Demo {
	public static void main(String[] args) {
		/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", "你的secretId");
		config.put("SecretKey", "你的secretKey");
		/* 请求方法类型 POST、GET */
		config.put("RequestMethod", "GET");
		/* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
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
		/* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
		/* DescribeInstances 接口的部分可选参数如下 */
		params.put("offset", 0);
		params.put("limit", 3);
		/*在这里指定所要用的签名算法，不指定默认为HmacSHA1*/
		//params.put("SignatureMethod", "HmacSHA256");
		/* generateUrl 方法生成请求串，但不发送请求。在正式请求中，可以删除下面这行代码。 */
		// System.out.println(module.generateUrl("DescribeInstances", params));

		String result = null;
		try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
			result = module.call("DescribeInstances", params);
			JSONObject json_result = new JSONObject(result);
			System.out.println(json_result);
		} catch (Exception e) {
			System.out.println("error..." + e.getMessage());
		}

	}
}
