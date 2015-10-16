import java.io.File;
import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Vod;
import com.qcloud.Utilities.SHA1;
import com.qcloud.Utilities.Json.JSONObject;

public class VodDemo {
	public static void main(String[] args) {
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		
		config.put("SecretId", "你的secretId");
		config.put("SecretKey", "你的secretKey");
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try{
			System.out.println("starting...");
			String fileName = "d:\\test.rmvb";
			long fileSize = new File(fileName).length();
			String fileSHA1 = SHA1.fileNameToSHA(fileName);
			
			int fixDataSize = 1024*1024*50;  //每次上传字节数，可自定义
			int firstDataSize = 1024*10;    //切片上传：最小片字节数（默认不变）,如果：dataSize + offset > fileSize,把这个值变小即可
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;
			String fileId;
			String result = null;
			
			if(remainderSize<=0){
				System.out.println("wrong file path...");
			}
			while (remainderSize>0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				/*
				 * 亲，输入参数的类型，记得参考wiki详细说明
				 */
				params.put("fileSha", fileSHA1);
				params.put("fileType", "rmvb");
				params.put("fileName", "Test");
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", fileName);
				params.put("isTranscode", 0);
				params.put("isScreenshot", 0);
				params.put("isWatermark", 0);
				
				
				result = module.call("MultipartUploadVodFile", params);
				System.out.println(result);
				JSONObject json_result = new JSONObject(result);
				code = json_result.getInt("code");
				if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=10K,满足大多数视频的上传)
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					return;
				}
				flag = json_result.getInt("flag");
				if (flag == 1) {
					fileId = json_result.getString("fileId");
					break;
				} else {
					tmpOffset = Integer.parseInt(json_result.getString("offset"));
				}
				remainderSize = fileSize - tmpOffset;
				if (fixDataSize < remainderSize) {
					tmpDataSize = fixDataSize;
				} else {
					tmpDataSize = (int) remainderSize;
				}
			}
			System.out.println("end...");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error..."+e.toString());
		}
	}
}
