package com.weibo.api.motan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

@Slf4j
public class StatisticsUtilsTests {

    @Test
    public void test() throws InterruptedException, JsonProcessingException {
        StatsUtil.accessStatistic("requestProcess", "rpc-demo-client", "rpc-demo-client",
                System.currentTimeMillis(), 7, 2, 200, StatsUtil.AccessStatus.NORMAL);
        StatsUtil.accessStatistic("requestProcess", "rpc-demo-client", "rpc-demo-client",
                System.currentTimeMillis(), 250, 210, 200, StatsUtil.AccessStatus.NORMAL);
        StatsUtil.accessStatistic("responseProcess", "rpc-demo-server", "rpc-demo-server",
                System.currentTimeMillis(), 80, 70, 200, StatsUtil.AccessStatus.BIZ_EXCEPTION);

        // It must sleep for a while
        Thread.sleep(1000L);
        ConcurrentMap<String, AccessStatisticResult> totalAccessStatistic = StatsUtil.getTotalAccessStatistic();
        log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(totalAccessStatistic));
    }
}