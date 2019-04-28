package hbi.core.customer.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.ServiceRequest;
import com.hand.hap.intergration.annotation.HapInbound;
import com.hand.hap.system.controllers.BaseController;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.mapper.ActivityDemo1Mapper;
import hbi.core.customer.dto.ActivityCustomer;
import hbi.core.customer.dto.Customer;
import hbi.core.customer.mapper.ActivityCustomerMapper;
import hbi.core.customer.mapper.CustomerMapper;
import hbi.core.demoPackage.dto.EmployeeDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.ldap.BasicControl;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LiMing
 * @Date: 2019/4/18 10:00
 * @Description:
 **/

@Controller
@RequestMapping(value = { "/api/public/rest" })
public class RestCustomerController extends BaseController {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    ActivityDemo1Mapper demo1Mapper;

    @Autowired
    ActivityCustomerMapper activityCustomerMapper;

    @HapInbound(apiName="有效申请活动接口接口")
    @RequestMapping(value = "/customer/activityApplication", produces = "application/json")
    @ResponseBody
    public Map<String,Object> activityApplication(@RequestBody ActivityCustomer dto, HttpServletRequest request) {


        Map<String,Object> map=new HashMap<>();

       /* IRequest request1=new ServiceRequest();
        request1.se*/
       // List<EmployeeDemo> list= service.selectAll(null);
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());

        ActivityDemo1 activityDemo1 = new ActivityDemo1();
        activityDemo1.setId(dto.getActivityId());
        if(customer!=null){
            if(customerMapper.queryAllCustomer(customer).size()!=0){
                if(demo1Mapper.queryAllActivity(activityDemo1).size()!=0){
                    List<ActivityCustomer> activityCustomers = activityCustomerMapper.selectActivityByCustomer(dto);
                    if(activityCustomers.size()==0){
                        dto.setApplicationStatus("NEW");
                        dto.setApplicationDate(new Date());
                        activityCustomerMapper.insert(dto);
                        map.put("message","申请成功");
                    }else {
                        map.put("message","对不起，此客户已经申请过此活动了");
                    }
                }else {
                    map.put("message","你输入的活动不存在");
                }
            }else {
                map.put("message","你的用户不存在");
            }
        }

        return map;
    }
}
