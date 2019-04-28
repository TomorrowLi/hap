package hbi.core.customer.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.customer.dto.ActivityCustomer;
import org.springframework.data.repository.query.Param;

import javax.management.relation.Role;
import java.util.List;

public interface ActivityCustomerMapper extends Mapper<ActivityCustomer>{


    List<ActivityCustomer> selectActivityByCustomer(ActivityCustomer dto);



    List<ActivityCustomer> selectAllCustomer(ActivityCustomer dto);

    List<ActivityCustomer> selectCustomerCount(ActivityCustomer dto);
    List<Role> queryRoleAll(Long userId);

    List<ActivityCustomer> queryApplication(Long activityId);
}