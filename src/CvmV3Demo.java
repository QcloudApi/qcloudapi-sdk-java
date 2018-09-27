import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Cvm;

public class CvmV3Demo {
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
        //config.put("RequestMethod", "POST");
        // 区域参数，例如 ap-guangzhou
        // 参考：https://cloud.tencent.com/document/api/213/9456
        config.put("DefaultRegion", "ap-guangzhou");
        // 在这里指定所要用的签名算法，不指定默认为 HmacSHA1
        config.put("SignatureMethod", "HmacSHA256");

        // 示例服务：CVM
        // 示例：DescribeInstances
        // API 文档地址：https://cloud.tencent.com/document/api/213/9388
        QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cvm(), config);
        String action = "DescribeInstances";

        TreeMap<String, Object> params = new TreeMap<String, Object>();
        // 必须设置 Version = 2017-03-12 才能使用对应版本的 CVM API
        params.put("Version", "2017-03-12");
        // 将需要输入的参数都放入 params 里面，必选参数是必填的。
        // DescribeInstances 接口的部分可选参数如下
        params.put("Offset", 0);
        params.put("Limit", 1);

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

    }
}
