package com.monitor.serverside.system.sysInfoModel;


import lombok.Data;

/**
 * CPU相关信息
 * 
 * @author ruoyi
 */
@Data
public class Cpu
{
    /**
     * 核心
     */
    private int cpuNum;

    /**
     * CPU总的使用
     */
    private double total;

    /**
     * CPU系统使用
     */
    private double sys;

    /**
     * CPU用户使用
     */
    private double used;

    /**
     * CPU当前等待
     */
    private double wait;

    /**
     * CPU当前空闲
     */
    private double free;
}
