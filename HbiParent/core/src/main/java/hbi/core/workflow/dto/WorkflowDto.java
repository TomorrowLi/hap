package hbi.core.workflow.dto;

/**
 * @Author: LiMing
 * @Date: 2019/4/2 12:39
 * @Description:
 **/
import org.activiti.rest.service.api.engine.variable.RestVariable;

import java.util.List;

public class WorkflowDto {

    private String activitiCode;//流程编码
    private String businessKey;//业务-编码
    private List<RestVariable> variable;//流程变量

    public String getActivitiCode() {
        return activitiCode;
    }

    public void setActivitiCode(String activitiCode) {
        this.activitiCode = activitiCode;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public List<RestVariable> getVariable() {
        return variable;
    }

    public void setVariable(List<RestVariable> variable) {
        this.variable = variable;
    }
}
