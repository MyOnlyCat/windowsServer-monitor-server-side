package com.monitor.serverside.system.service;

import com.monitor.serverside.system.model.MySystemInfo;
import com.monitor.serverside.system.model.SystemOperationInformation;

/**
 * @author 11609
 */
public interface SystemServiceI {

    /**
     *  获取CPU memory ip 信息
     * @return MySystemInfo 实体
     */
    MySystemInfo getSystemInfo();

    /**
     * 获取信息运行时的基础信息
     * @return SystemOperationInformation
     */
    SystemOperationInformation getSystemOperationInformation();


}
