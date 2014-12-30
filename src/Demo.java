import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.*;
import com.qcloud.Utilities.MD5;


public class Demo {
	public static void main(String[] args) {
		TreeMap<String, Object> config = new TreeMap<String, Object>();
//		config.put("SecretId", "你的secretId");
//		config.put("SecretKey", "你的secretKey");
//		config.put("RequestMethod", "GET");
//		config.put("DefaultRegion", "gz");
//		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cvm(), config);
//		TreeMap<String, Object> params = new TreeMap<String, Object>();
//		params.put("offset", 0);
//		params.put("limit", 3);
//		System.out.println(module.generateUrl("DescribeInstances", params));
//		System.out.println(module.call("DescribeInstances", params));
		
		config.put("SecretId", "你的secretId");
		config.put("SecretKey", "你的secretKey");
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Cdn(), config);
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		try{
//DeleteCdnEntity
//			params.put("entityFileName", "/upload/del.bat");
//			System.out.println(module.call("DeleteCdnEntity", params));

//UploadCdnEntity
			String fileName = "c:\\9EF077317173460C8C7F48CA4F177D2B.png";		
			params.put("entityFileName", "/upload/9EF077317173460C8C7F48CA4F177D2B.png");
			params.put("entityFileMd5", MD5.fileNameToMD5(fileName));
			params.put("entityFile", fileName);
			System.out.println(module.call("UploadCdnEntity", params));
		
//DescribeCdnEntities
//			params.put("entityBaseDir", "/upload");
//			System.out.println(module.call("DescribeCdnEntities", params));
		
//RenameCdnEntity
//			params.put("entityFileName", "/upload/del.bat");
//			params.put("entityNewFileName", "/upload/delete.bat");
//			System.out.println(module.call("RenameCdnEntity", params));
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
