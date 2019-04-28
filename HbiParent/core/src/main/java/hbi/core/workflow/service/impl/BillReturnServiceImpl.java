package hbi.core.workflow.service.impl;

import com.hand.hap.activiti.service.IActivitiService;
import com.hand.hap.core.IRequest;
import hbi.core.activity.service.IActivityDemo1Service;
import hbi.core.workflow.dto.WorkflowDto;
import hbi.core.workflow.listener.ActivitiListener;
import hbi.core.workflow.service.BillReturnService;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: LiMing
 * @Date: 2019/4/2 13:02
 * @Description:对工作流开启入口接口的实现
 **/

@Service
@Transactional
public class BillReturnServiceImpl implements BillReturnService{


    private Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private IActivitiService activitiService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startBill(IRequest iRequest, WorkflowDto workflowDto) {


        log.debug(workflowDto.getActivitiCode()+"====================的id是=="+workflowDto.getBusinessKey());
        ProcessInstanceCreateRequest instanceCreateRequest = new ProcessInstanceCreateRequest();
        instanceCreateRequest.setBusinessKey(workflowDto.getBusinessKey());//设置流程主键
        instanceCreateRequest.setProcessDefinitionKey(workflowDto.getActivitiCode());//设置流程编码

        instanceCreateRequest.setVariables(workflowDto.getVariable());
        activitiService.startProcess(iRequest, instanceCreateRequest);
    }
}
