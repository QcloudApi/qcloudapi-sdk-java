package com.qcloud.Module;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import com.qcloud.Utilities.SHA1;

public class Vod extends Base {
	public Vod(){
		serverHost = "vod.api.qcloud.com";
	}
	
	public String MultipartUploadVodFile(TreeMap<String, Object> params) throws NoSuchAlgorithmException, IOException {
		serverHost = "vod.qcloud.com";
		
		String actionName = "MultipartUploadVodFile";

        String fileName = params.get("file").toString();
        params.remove("file");
        File f= new File(fileName);  
        
        if (!params.containsKey("fileSize")){
        	params.put("fileSize", f.length());
        }
        if (!params.containsKey("fileSha")){
        	params.put("fileSha", SHA1.fileNameToSHA(fileName));
        }
        
        return call(actionName, params, fileName);
	}
}
