package com.qcloud.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

import com.qcloud.Utilities.MD5;

public class Cdn extends Base {
	
	public Cdn(){
		serverHost = "cdn.api.qcloud.com";
	}
	
	public String UploadCdnEntity(TreeMap<String, Object> params) throws NoSuchAlgorithmException, IOException {
		String actionName = "UploadCdnEntity";

        String entityFile = params.get("entityFile").toString();
        params.remove("entityFile");
        File file = new File(entityFile);        
        if (!file.exists()) {
        	throw new FileNotFoundException();
        }
        
        if (!params.containsKey("entityFileMd5")) {
            params.put("entityFileMd5", MD5.fileNameToMD5(entityFile));
        }
        
        return call(actionName, params, entityFile);
	}
}