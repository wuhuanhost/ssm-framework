package com.geekfanfan.think.utils.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

/**
 * @Description 时间调度任务管理
 * @Date 2019/12/19 19:52
 * @Created by Jason
 */
public class QuartzManager {

    private final static Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";
    private static Scheduler scheduler;
    // 北京时间
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 创建一个定时任务并启动
     *
     * @param jobName
     * @param cls
     * @param cron
     * @param command
     */
    public static void addJob(String jobName, Class cls, String cron, String command) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();

            JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();

            JobDataMap jobDataMap = job.getJobDataMap();
            // 传递参数
            jobDataMap.put("command", command);
            // jobDataMap.put("timingExpId", timingExpId);

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

            // 获取当前系统时间 转成 北京时间
            Date currTime = new Date(System.currentTimeMillis());
            Date date = changeTimeZone(currTime, TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai"));

            // 按新的cronExpression表达式构建一个新的trigger
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
                    .withSchedule(scheduleBuilder).startAt(date)// 设置开始计时时间
                    .build();

            // 交给scheduler去调度
            sched.scheduleJob(job, trigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
                System.out.println("新任务创建完毕，并启动 ！jobName：[" + jobName + "]");
                logger.info("新任务创建完毕，并启动 ！jobName：[" + jobName + "]");
            }
        } catch (Exception e) {
            logger.error("新任务创建、并启动 失败", e);
        }
    }

    /**
     * 移除一个定时任务
     *
     * @param jobName
     */
    public static void removeJob(String jobName) {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);

        try {
            Scheduler sched = gSchedulerFactory.getScheduler();

            Trigger trigger = (Trigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            // 停止触发器
            sched.pauseTrigger(triggerKey);
            // 移除触发器
            sched.unscheduleJob(triggerKey);
            // 删除任务
            sched.deleteJob(jobKey);

            logger.info("移除任务,完毕!jobName:[" + jobName + "]");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查到当前任务的状态
     *
     * @param jobName
     * @return NONE 无， NORMAL, 正常 PAUSED, 暂停 COMPLETE, 完成 ERROR, 错误， BLOCKED 受阻；
     */
    public static String getTriggerState(String jobName) {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);

        String name = null;
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            Trigger.TriggerState triggerState = sched.getTriggerState(triggerKey);
            name = triggerState.name();
        } catch (Exception e) {
            logger.error("获取任务状态失败！jobName:[" + jobName + "]", e);
        }
        return name;
    }

    /**
     * 暂停一个任务
     *
     * @param jobName
     */
    public static void pauseJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseJob(jobKey);
        } catch (Exception e) {
            logger.error("暂停任务失败！jobName:[" + jobName + "]", e);
        }
    }

    /**
     * 恢复一个任务
     *
     * @param jobName
     */
    public static void resumeJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.resumeJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("恢复任务失败！jobName:[" + jobName + "]", e);

        }
    }

    /**
     * 获取定时任务调度中全部任务
     */
    public static void getAllJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            List<String> triggerGroupNames = sched.getTriggerGroupNames();
            for (String group : triggerGroupNames) {
                Set<TriggerKey> triggerKeys = sched.getTriggerKeys(GroupMatcher.triggerGroupEquals(group));
                for (TriggerKey triggerKey : triggerKeys) {
                    String jobName = triggerKey.getName();
                    String triggerState = getTriggerState(jobName);
                }
            }
        } catch (Exception e) {
            logger.info("获取任务调度管理器中全部任务失败", e);
        }
    }

    /**
     * 开启全部任务
     */
    public static void startJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭全部任务
     */
    public void shutdownJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            logger.error("关闭全部任务失败", e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void startJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建启动定时任务
     * 
     * @param jobName          任务名称
     * @param triggerName      定时任务名称
     * @param triggerGroupName 定时任务组名称
     * @param jobGroupName     任务组名称
     * @param cron             cron表达式
     * @param jobClass         jobClass
     */
    public void createTrgger(String jobName, String triggerName, String triggerGroupName, String jobGroupName,
            String cron, Class jobClass) {
        try {

            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            jobDetail.getJobDataMap().put("messageId", "1");
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 获取更改时区后的日期
     * 
     * @param date    日期
     * @param oldZone 旧时区对象
     * @param newZone 新时区对象
     * @return 日期
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }
}