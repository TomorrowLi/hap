package hbi.core.workflow.listener;

import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.demoPackage.service.IEmployeeDemoService;
import junit.framework.TestListener;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: LiMing
 * @Date: 2019/4/2 12:43
 * @Description:
 **/
@Component
public class ActivitiListener implements ExecutionListener, ApplicationContextAware {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static ApplicationContext applicationContext;

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution delegateExecution) {


        //获取业务编码
        String businessKey = delegateExecution.getProcessInstanceBusinessKey();
        Map<String, Object> variablesMap = delegateExecution.getVariables();
        // TODO 审批流结束后的逻辑处理
        String status = (String) variablesMap.get("approveResult");
        String activitiType = (String) variablesMap.get("actCode");


        log.debug("========监听到=====工作流类型是==" + activitiType + "==操作业务id是==" + businessKey + "==执行的是==" + status);
        if (null == status || "".equals(status)) {
            log.debug("=======非正常状态调用======工作流类型是==" + activitiType + "======此时status是==" + status);
            return;
        } else {

            // IEmployeeDemoService service=TestListener.getContext().getBean("iEmployeeDemoService");
            IEmployeeDemoService service= ActivitiListener.getContext().getBean(IEmployeeDemoService.class);
            log.debug("=============此时有监听到工作流, ==========status非null,也不是正常值;而是========" + status);
            List<EmployeeDemo> list= service.selectAll(null);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ActivitiListener.applicationContext = applicationContext; // NOSONAR
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }
}
