# motan-demo


## Bug list
* motan-registry-zookeeper中使用的zkclient版本为0.3，ZkClient.java类强制引入logback而非slf4j，导致整个应用必须依赖与logback。解决办法是排除0.3版再引入0.10版的zkclient依赖，这个版本的ZkClient.java类使用了slf4j。2021/02/15 version:1.1.9
* 当使用motan-transport-netty4(motan-transport-netty则没有这个问题)、retries=0、采用直连方式并且首先启动client端应用，然后启动server端应用时，第一次调用会失败，第二次调用才会成功。2021/02/16 version:1.1.9
