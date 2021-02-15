package org.infinity.motan.demoserver.config;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MotanConfiguration {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("org.infinity.motan.demoserver");
        return motanAnnotationBean;
    }

    @Bean(name = "demoMotan")
    public ProtocolConfigBean protocolConfig1() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setName("motan");
        config.setMaxContentLength(1048576);
        return config;
    }

    @Bean(name = "registryConfig1")
    public RegistryConfigBean registryConfig() {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setRegProtocol("zookeeper");
        config.setAddress("127.0.0.1:2181");
        config.setConnectTimeout(2000);
        return config;
    }

    @Bean
    public BasicServiceConfigBean baseServiceConfig() {
        BasicServiceConfigBean config = new BasicServiceConfigBean();
        config.setApplication(applicationName);
        config.setModule("default-module");
        config.setGroup("default-group");
        config.setRegistry("registryConfig1");
        config.setExport("demoMotan:23010");
        config.setAccessLog(false);
        config.setShareChannel(true);
        return config;
    }
}
