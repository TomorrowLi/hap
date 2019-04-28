package hbi.core.demoPackage.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.demoPackage.mapper.EmployeeDemoMapper;
import hbi.core.redis.RedisDemo;
import hbi.core.redis.service.Message;
import hbi.core.workflow.dto.WorkflowDto;
import hbi.core.workflow.service.BillReturnService;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.demoPackage.service.IEmployeeDemoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeDemoServiceImpl extends BaseServiceImpl<EmployeeDemo> implements IEmployeeDemoService{

    @Autowired
    RedisDemo redisDemo;

    @Autowired
    EmployeeDemoMapper employeeDemoMapper;

    @Autowired
    Message message;

    @Autowired
    BillReturnService billReturnService;

    public List<EmployeeDemo> queryAll(IRequest requestContext, EmployeeDemo dto, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<EmployeeDemo> demoRedis = redisDemo.getValue("demoRedis");
        //System.out.println(demoRedis);

        //发送消息
       //message.sendMessageTest();



       //流程操作
        //========================================
        /*if(null==dto.getEmployeeId()){
            WorkflowDto dto1 =new WorkflowDto();
            createParams(dto1);
            billReturnService.startBill(requestContext,dto1);
        }*/

        //========================================
       return employeeDemoMapper.queryAll(dto);
    }



    private void createParams(WorkflowDto dto){

        dto.setActivitiCode("activiteTest");
        dto.setBusinessKey("10100");//
        List<RestVariable> variables = new ArrayList<>();
        RestVariable parames1 = new RestVariable();
        parames1.setName("parames1");
        parames1.setType("string");
        parames1.setValue("parames1");



        RestVariable primarykey = new RestVariable();
        primarykey.setName("primarykey");
        primarykey.setType("long");
        primarykey.setValue(67);


        RestVariable actCode = new RestVariable();
        actCode.setName("actCode");
        actCode.setType("string");
        actCode.setValue("activiteTest");


        RestVariable amount = new RestVariable();
        amount.setName("amount");
        amount.setType("long");
        amount.setValue(10000);


        /*RestVariable gender = new RestVariable();
        amount.setName("gender");
        amount.setType("String");
        amount.setValue("F");*/

        variables.add(parames1);
        variables.add(primarykey);
        variables.add(actCode);
        variables.add(amount);
        //variables.add(gender);
        dto.setVariable(variables);

    }

}