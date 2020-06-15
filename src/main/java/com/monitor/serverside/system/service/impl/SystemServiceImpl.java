package com.monitor.serverside.system.service.impl;

import com.monitor.serverside.system.model.MySystemInfo;
import com.monitor.serverside.system.model.SystemOperationInformation;
import com.monitor.serverside.system.service.SystemServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * @author 11609
 */
@Service
@Slf4j
public class SystemServiceImpl implements SystemServiceI {
    /**
     * 获取CPU memory ip 信息
     *
     * @return MySystemInfo 实体
     */
    @Override
    public MySystemInfo getSystemInfo() {
        log.info("Initializing System...");
        MySystemInfo mySystemInfo = new MySystemInfo();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hardware = si.getHardware();
        // 获取内存大小
        String memory = formatByte(hardware.getMemory().getTotal());
        mySystemInfo.setMemory(memory);
        // 获取CPU核心数量
        int cpuCount = hardware.getProcessor().getLogicalProcessorCount();
        mySystemInfo.setCpu(cpuCount);
        // 获取外网Ip
        String ip = getIp();
        mySystemInfo.setIp(ip);
        log.info("memory: {}, Cpu: {} core, ip: {}", memory, cpuCount, ip);
        return mySystemInfo;
    }

    /**
     * 获取信息运行时的基础信息
     *
     * @return SystemOperationInformation
     */
    @Override
    public SystemOperationInformation getSystemOperationInformation() {
        log.info("Initializing System...");
        SystemOperationInformation systemOperationInformation = new SystemOperationInformation();
//        // 计算CPU使用率
        SystemInfo si = new SystemInfo();
        CentralProcessor processor = si.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[] ticks = processor.getSystemCpuLoadTicks();
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
        String cpuUse = new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu));
        log.info("Cpu use: {}", cpuUse);

        // 计算内存使用率
        GlobalMemory memory = si.getHardware().getMemory();
        long totalByte = memory.getTotal();
        long availableByte = memory.getAvailable();
        String memoryUse = new DecimalFormat("#.##%").format((totalByte - availableByte) * 1.0 / totalByte);
        log.info("memory use: {}", memoryUse);

        systemOperationInformation.setCpuUsageTate(cpuUse);
        systemOperationInformation.setMemoryUsageTate(memoryUse);
        return systemOperationInformation;
    }

    /**
     * long类型内存大小转G
     *
     * @param byteNumber long类型内存大小
     * @return G类型内存大小 string
     */
    private static String formatByte(long byteNumber) {
        double FORMAT = 1024.0;
        double kbNumber = byteNumber / FORMAT;
        if (kbNumber < FORMAT) {
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber / FORMAT;
        if (mbNumber < FORMAT) {
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber / FORMAT;
        if (gbNumber < FORMAT) {
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber / FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }

    /**
     * 请求腾讯的开源项目地址获取外网IP
     *
     * @return ip地址 string
     */
    private static String getIp() {
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
