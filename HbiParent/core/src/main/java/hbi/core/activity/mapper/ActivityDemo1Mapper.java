package hbi.core.activity.mapper;

import com.hand.hap.account.dto.Role;
import com.hand.hap.account.dto.UserRole;
import com.hand.hap.mybatis.common.Mapper;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.dto.Comment;
import hbi.core.activity.dto.Demo;
import hbi.core.demoPackage.dto.EmployeeDemo;


import java.util.List;

public interface ActivityDemo1Mapper extends Mapper<ActivityDemo1>{

    List<ActivityDemo1> queryAllActivity(ActivityDemo1 activityDemo1);

    List<EmployeeDemo> queryEmployee();

    void updateById(Comment comment);

    List<ActivityDemo1> queryHistoryActivity(ActivityDemo1 activityDemo1);


    List<Demo> queryDemo();

    List<Role> selectRole(Long activityId);

    List<ActivityDemo1> selectActivityByStatus();


}