package hbi.core.workflow.service;

import com.hand.hap.core.IRequest;
import hbi.core.workflow.dto.WorkflowDto;

/**
 * @Author: LiMing
 * @Date: 2019/4/2 13:00
 * @Description:
 **/
public interface BillReturnService {

    /**
     * 工作开启的入口
     * @param iRequest
     * @param workflowDto
     */
    public void startBill(IRequest iRequest, WorkflowDto workflowDto);
}
