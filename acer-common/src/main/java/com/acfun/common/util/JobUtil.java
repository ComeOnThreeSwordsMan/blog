package com.acfun.common.util;

import com.xiaoleilu.hutool.cron.Scheduler;
import com.xiaoleilu.hutool.cron.task.Task;
import com.xiaoleilu.hutool.exceptions.UtilException;
import com.xiaoleilu.hutool.setting.Setting;
import com.xiaoleilu.hutool.setting.SettingRuntimeException;

/**
 * @author: fqlee
 * @description:
 * @date: created in 17:17 2017/12/15
 * @modified:
 */
public final class JobUtil {

//	private final static Log log = StaticLog.get();

    /** Crontab配置文件 */
    public final static String CRONTAB_CONFIG_PATH = "config/cron.setting";

    private final static Scheduler SCHEDULER = new Scheduler();
    private static Setting crontabSetting;

    private JobUtil() {}

    /**
     * 自定义定时任务配置文件
     * @param cronSetting 定时任务配置文件
     */
    public static void setCronSetting(Setting cronSetting) {
        crontabSetting = cronSetting;
    }

    /**
     * 自定义定时任务配置文件路径
     * @param cronSettingPath 定时任务配置文件路径（相对绝对都可）
     */
    public static void setCronSetting(String cronSettingPath) {
        try {
            crontabSetting = new Setting(cronSettingPath, Setting.DEFAULT_CHARSET, false);
        } catch (SettingRuntimeException e) {
            //ignore setting file parse error
        }
    }

    /**
     * 设置是否支持秒匹配，默认不使用
     * @param isMatchSecond <code>true</code>支持，<code>false</code>不支持
     */
    public static void setMatchSecond(boolean isMatchSecond) {
        SCHEDULER.setMatchSecond(isMatchSecond);
    }

    /**
     * 设置是否支持年匹配，默认不使用
     * @param isMatchYear <code>true</code>支持，<code>false</code>不支持
     */
    public static void setMatchYear(boolean isMatchYear) {
        SCHEDULER.setMatchYear(isMatchYear);
    }

    /**
     * 加入定时任务
     * @param schedulingPattern 定时任务执行时间的crontab表达式
     * @param task 任务
     * @return 定时任务ID
     */
    public static String schedule(String schedulingPattern, Task task) {
        return SCHEDULER.schedule(schedulingPattern, task);
    }

    /**
     * 加入定时任务
     * @param schedulingPattern 定时任务执行时间的crontab表达式
     * @param task 任务
     * @return 定时任务ID
     */
    public static String schedule(String schedulingPattern, Runnable task) {
        return SCHEDULER.schedule(schedulingPattern, task);
    }

    /**
     * 批量加入配置文件中的定时任务
     * @param cronSetting 定时任务设置文件
     */
    public static void schedule(Setting cronSetting) {
        SCHEDULER.schedule(cronSetting);
    }

    /**
     * 移除任务
     * @param schedulerId 任务ID
     */
    public static void remove(String schedulerId){
        SCHEDULER.deschedule(schedulerId);
    }

    /**
     * @return 获得cron4j的Scheduler对象
     */
    public Scheduler getScheduler(){
        return SCHEDULER;
    }

    /**
     * 开始
     */
    synchronized public static void start() {
        if(null == crontabSetting) {
            setCronSetting(CRONTAB_CONFIG_PATH);
        }
        if(SCHEDULER.isStarted()) {
            throw new UtilException("Scheduler has been started, please stop it first!");
        }

        schedule(crontabSetting);
        SCHEDULER.start();
    }

    /**
     * 重新启动定时任务<br>
     * 重新启动定时任务会清除动态加载的任务
     */
    synchronized public static void restart(){
        if(null != crontabSetting){
            crontabSetting.load();
        }
        if(SCHEDULER.isStarted()){
            SCHEDULER.stop();
        }

        schedule(crontabSetting);
        SCHEDULER.start();
    }

    /**
     * 停止
     */
    synchronized public static void stop() {
        SCHEDULER.stop();
    }
}
