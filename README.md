# motan-demo


## Bug list
* motan-registry-zookeeper中使用的zkclient版本为0.3，ZkClient.java类强制引入logback而非slf4j，导致整个应用必须依赖与logback。解决办法是排除0.3版再引入0.10版的zkclient依赖，这个版本的ZkClient.java类使用了slf4j。 2021/02/15 version:1.1.9
