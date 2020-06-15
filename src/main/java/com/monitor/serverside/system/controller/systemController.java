package com.monitor.serverside.system.controller;

import com.monitor.serverside.api.vo.Result;
import com.monitor.serverside.system.model.MySystemInfo;
import com.monitor.serverside.system.model.SystemOperationInformation;
import com.monitor.serverside.system.service.SystemServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.software.os.OSService;
import oshi.software.os.OperatingSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/system")
@Slf4j
public class systemController {

    static List<String> oshi = new ArrayList<>();

    private final SystemServiceI systemServiceI;

    public systemController(SystemServiceI systemServiceI) {
        this.systemServiceI = systemServiceI;
    }

//    @GetMapping(value = "/cpuRatio")
//    public Result<?> getCpuUseRatio() {
//        return Result.ok((int) (osmxb.getSystemCpuLoad() * 100));
//    }

    /**
     * 获取系统 cpu memory ip
     * @return MySystemInfo
     */
    @GetMapping(value = "/systemInfo")
    public Result<?> getSystemInfo() {
        MySystemInfo systemInfo = systemServiceI.getSystemInfo();
        return Result.ok(systemInfo);
    }

    /**
     * 获取当前主机基本资源使用情况
     * @return
     */
    @GetMapping(value = "/operationRatio")
    public Result<?> getOperationRatio() {
        while (true) {
            systemServiceI.getSystemOperationInformation();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        SystemOperationInformation systemOperationInformation = systemServiceI.getSystemOperationInformation();
//        return Result.ok(systemOperationInformation);
    }

    private static void printServices(OperatingSystem os) {
        oshi.add("Services: ");
        oshi.add("   PID   State   Name");
        // DO 5 each of running and stopped
        int i = 0;
        for (OSService s : os.getServices()) {
            if (s.getState().equals(OSService.State.RUNNING) && i++ < 5) {
                oshi.add(String.format(" %5d  %7s  %s", s.getProcessID(), s.getState(), s.getName()));
            }
        }
        i = 0;
        for (OSService s : os.getServices()) {
            if (s.getState().equals(OSService.State.STOPPED) && i++ < 5) {
                oshi.add(String.format(" %5d  %7s  %s", s.getProcessID(), s.getState(), s.getName()));
            }
        }
    }

    /**
     * long类型内存大小转G
     * @param byteNumber long类型内存大小
     * @return G类型内存大小 string
     */
    private static String formatByte(long byteNumber){
        double FORMAT = 1024.0;
        double kbNumber = byteNumber/FORMAT;
        if(kbNumber<FORMAT){
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber/FORMAT;
        if(mbNumber<FORMAT){
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber/FORMAT;
        if(gbNumber<FORMAT){
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber/FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }

    /**
     * 请求腾讯的开源项目地址获取外网IP
     * @return ip地址 string
     */
    private static String getIp() {
//        String ip = "http://ip.chinaz.com/getip.aspx";
        String ip = "https://checkip.map.qq.com/";

        String inputLine = "";
        String read = "";
        try {
            URL url = new URL(ip);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((read = in.readLine()) != null) {
                inputLine += read;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputLine;
    }

}
