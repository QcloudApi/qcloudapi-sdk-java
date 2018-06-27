=========
CHANGELOG
=========

latest (now)
============

* support dymanic module: now if module is not explicitly presented in src/main/java/com/qcloud/Module, such as the ckafka product, you can use Morphling module with the dynamic module name, it will work as it is.

2.0.6 (2018-03-02)
==================

* add tbaas module
* add emr module
* add athena module

2.0.5 (2017-01-22)
==================

* add partners module
* increase connect timeout (5s) and add read timeout (90s)

2.0.4 (2017-12-10)
==================

* add tmt module
* add apigateway module
* add scf module
* add dfw module
* add cloudaudit module
* add batch module

2.0.3 (2017-11-10)
==================

* add bgpip module

history
=======

* [2017/11/12] 增加Bgpip模块
* [2017/9/11] 增加Bmeip和Bmvpc模块
* [2017/8/7] 增加Feecenter模块
* [2017/7/31] 增加Bm和Bmlb模块
* [2017/7/12] 回滚：不默认传Version参数
* [2017/6.29] https请求支持SNI特性：访问api的域名放入server_name扩展字段中。
* [5/19] 设置接口默认Version：
  Cvm模块新版本API已经上线，通过是否传Version区分新旧版本。SDK默认调用新接口，因此需要增加Version的默认设置。
  CvmAPI接口介绍见：https://www.qcloud.com/document/api/213/569
* [3/10] 增加HmacSHA256签名算法的兼容。
* [7/15] 增加Tdsql模块。
