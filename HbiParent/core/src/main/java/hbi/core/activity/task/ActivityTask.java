package hbi.core.activity.task;

import com.hand.hap.job.AbstractJob;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.service.IActivityDemo1Service;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/10 15:56
 * @Description:
 **/

@DisallowConcurrentExecution
public class ActivityTask extends AbstractJob{

    @Autowired
    private IActivityDemo1Service activityDemo1Service;
    @Override
    public void safeExecute(JobExecutionContext jobExecutionContext) throws Exception {

        //获取job参数(这个参数是在页面新建任务的时候定义的
        String prefix = jobExecutionContext.getMergedJobDataMap().getString("PREFIX");
        //核心逻辑处理
        List<ActivityDemo1> activityDemo1s = activityDemo1Service.selectAll(null);
        for (ActivityDemo1 activityDemo1 : activityDemo1s) {
            activityDemo1Service.updateByIdAndDate(prefix,activityDemo1);
        }
        setExecutionSummary("Got param " + prefix);
    }
}
