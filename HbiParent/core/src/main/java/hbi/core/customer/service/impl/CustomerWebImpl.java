package hbi.core.customer.service.impl;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.UserRole;
import com.hand.hap.attachment.dto.SysFile;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.mapper.ActivityDemo1Mapper;
import hbi.core.activity.mapper.FileMapper;
import hbi.core.customer.dto.CustomerVo;
import hbi.core.customer.dto.CustomerWebservice;
import hbi.core.customer.service.CustomerWeb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/17 17:17
 * @Description:
 **/
@WebService(endpointInterface = "hbi.core.customer.service.CustomerWeb", serviceName = "customerWeb")
public class CustomerWebImpl implements CustomerWeb {
    @Autowired
    ActivityDemo1Mapper activityDemo1Mapper;

    @Autowired
    FileMapper fileMapper;
    @Override
    public CustomerWebservice getCustomerBack(CustomerVo vo) {

        ActivityDemo1 activityDemo1 = new ActivityDemo1();
        activityDemo1.setId(vo.getActivityId());
        List<ActivityDemo1> activityDemo1s1 = activityDemo1Mapper.queryAllActivity(activityDemo1);
        CustomerWebservice customerWebservice = new CustomerWebservice();
        if("IN_RELEASE".equals(activityDemo1s1.get(0).getActivitySataus())){

            customerWebservice.setActivityName(activityDemo1s1.get(0).getActivityName());
            customerWebservice.setActivityData(activityDemo1s1.get(0).getActivityData());
            customerWebservice.setActivityText(activityDemo1s1.get(0).getActivityText());
            customerWebservice.setActivityEmployee(activityDemo1s1.get(0).getActivityEmployee());
            List<SysFile> sysFiles1 = fileMapper.fileDownload(vo.getActivityId());

            List<String> list=new ArrayList<>();
            for (SysFile sysFile : sysFiles1) {
                list.add(sysFile.getFilePath());
            }
            customerWebservice.setImageAddress(list);

            List<String> list1=new ArrayList<>();

            List<Role> roles = activityDemo1Mapper.selectRole(vo.getActivityId());

            for (Role role : roles) {
                list1.add(role.getRoleName());
            }

            customerWebservice.setActivityRole(list1);
        }else {
            customerWebservice=null;
        }
        return customerWebservice;

    }
}
