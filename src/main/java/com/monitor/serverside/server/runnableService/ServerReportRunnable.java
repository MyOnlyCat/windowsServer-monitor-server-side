package com.monitor.serverside.server.runnableService;

import com.monitor.serverside.server.clientFeignService.IClientFeignService;
import com.monitor.serverside.server.vo.ServerReportVo;
import com.monitor.serverside.util.BeanContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 七渣渣
 * 定时任务类
 */
@Slf4j
public class ServerReportRunnable implements Runnable{

    private IClientFeignService clientFeignService;


    @Override
    public void run() {
        ServerReportVo serverReportVo = new ServerReportVo();
        this.clientFeignService = BeanContext.getApplicationContext().getBean(IClientFeignService.class);
        clientFeignService.saveThisServerAndStopTask(serverReportVo);
        log.info("服务上报, {}", serverReportVo.toString());
    }

}
