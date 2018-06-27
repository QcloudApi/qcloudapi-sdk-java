import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Morphling;

public class DynamicModuleDemo {
    public static void main(String[] args) {
        // 从环境变量中读取信息，设置代理，如果没有代理则不需要设置
        System.setProperty("https.proxyHost", System.getenv("HTTPS_PROXY_HOST"));
        System.setProperty("https.proxyPort", System.getenv("HTTPS_PROXY_PORT"));
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        // 从环境变量中读取信息，设置密钥对，请不要把密钥对明文写在代码中以防泄漏
        // 如果读取环境变量失败，运行代码时将报java.lang.NullPointerException错误
        config.put("SecretId", System.getenv("QCLOUD_SECRET_ID"));
        config.put("SecretKey", System.getenv("QCLOUD_SECRET_KEY"));
        // 请求方法类型 POST、GET
        // config.put("RequestMethod", "POST");
        // 区域参数，例如 ap-guangzhou
        // 参考：https://cloud.tencent.com/document/api/213/9456
        config.put("DefaultRegion", "ap-guangzhou");
        // 在这里指定所要用的签名算法，不指定默认为 HmacSHA1
        config.put("SignatureMethod", "HmacSHA256");

        // 示例服务：CVM
        // 示例：DescribeInstances
        // API 文档地址：https://cloud.tencent.com/document/api/213/831
        Morphling morphling = new Morphling("cvm");
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(morphling, config);
        String action = "DescribeInstances";

        TreeMap<String, Object> params = new TreeMap<String, Object>();
        params.put("offset", 1);
        params.put("limit", 1);

        // generateUrl 方法生成请求串,可用于调试使用
        System.out.println(module.generateUrl(action, params));
        String result = null;
        try {
            // call 方法正式向指定的接口名发送请求，并把请求参数 params 传入，返回即是接口的请求结果。
            result = module.call(action, params);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }

        // 示例服务：ckafka
        // 示例：ListInstance
        // API 文档地址：https://cloud.tencent.com/document/product/597/10093

        // 你可以创建新的对象，此处演示使用旧对象切换不同的模块
        // module = new QcloudApiModuleCenter(new Morphling("ckafka"), config);
        morphling.morph("ckafka");
        action = "ListInstance";

        // 你应当根据接口定义重新设置params，但是此处恰好两个接口都支持offset和limit，因此可以直接使用
        System.out.println(module.generateUrl(action, params));
        try {
            result = module.call(action, params);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }
    }
}
