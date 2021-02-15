package org.infinity.motan.demoserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.infinity.motan.democommon.service.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "测试")
@Slf4j
public class TestController {
    private final ApplicationContext applicationContext;
    private final Environment        env;

    public TestController(
            ApplicationContext applicationContext,
            Environment env) {
        this.applicationContext = applicationContext;
        this.env = env;
    }


    @ApiOperation("测试获取AppService provider")
    @GetMapping("/api/test/app-service-provider")
    public void testGetAppServiceProvider() {
        Object bean = applicationContext.getBean(AppService.class);
        log.info(bean.toString());
    }
}
