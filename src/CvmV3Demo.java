import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Cvm;
import com.qcloud.Utilities.Json.JSONObject;

public class CvmV3Demo {
    public static void main(String[] args) {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("SecretId", "你的SecretId");
        config.put("SecretKey", "你的SecretKey");
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
            JSONObject json_result = new JSONObject(result);
            System.out.println(json_result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }

    }
}
