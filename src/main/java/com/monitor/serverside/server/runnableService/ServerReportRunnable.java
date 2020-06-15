package com.monitor.serverside.server.runnableService;

import com.monitor.serverside.server.vo.ServerReportVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 七渣渣
 */
@Slf4j
public class ServerReportRunnable implements Runnable{


    @Override
    public void run() {
        ServerReportVo serverReportVo = new ServerReportVo();
        log.info("服务上报, {}", serverReportVo.toString());
    }

}
