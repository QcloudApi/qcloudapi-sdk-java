package com.qcloud;

import java.lang.reflect.Method;
import java.util.TreeMap;

import com.qcloud.Module.Base;

/**
 * 模块调用类
 * @author robinslsun
 *
 */
public class QcloudApiModuleCenter {

	private Base module;

	/**
	 * 构造模块调用类
	 * @param module 实际模块实例
	 * @param config 模块配置参数
	 */
	public QcloudApiModuleCenter(Base module, TreeMap<String, Object> config){
		this.module = module;
		this.module.setConfig(config);
	}

	/**
	 * 生成Api调用地址。
     * 仅支持GET方法，POST方法仅返回host+path信息，不支持utf8编码的机器上仅返回host+path信息。
	 * @param actionName 模块动作名称
	 * @param params 模块请求参数
	 * @return Api调用地址
	 */
	public String generateUrl(String actionName, TreeMap<String, Object> params){
		return module.generateUrl(actionName, params);
	}

	/**
	 * Api调用
	 * @param actionName 模块动作名称
	 * @param params 模块请求参数
	 * @return json字符串
	 * @throws Exception
	 */
	public String call(String actionName, TreeMap<String, Object> params) throws Exception
	{
		for(Method method : module.getClass().getMethods()){
			if(method.getName().equals(actionName)){
				try {
					return (String) method.invoke(module, params);
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return module.call(actionName, params);
	}

	public void setConfigSecretId(String secretId) {
		module.setConfigSecretId(secretId);
	}

	public void setConfigSecretKey(String secretKey) {
		module.setConfigSecretKey(secretKey);
	}

	public void setConfigDefaultRegion(String region) {
		module.setConfigDefaultRegion(region);
	}

	public void setConfigRequestMethod(String method) {
		module.setConfigRequestMethod(method);
	}
}
