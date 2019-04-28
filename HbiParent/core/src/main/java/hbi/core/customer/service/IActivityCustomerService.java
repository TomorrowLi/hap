package hbi.core.customer.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.customer.dto.ActivityCustomer;
import hbi.core.customer.dto.Customer;

import javax.management.relation.Role;
import java.util.List;

public interface IActivityCustomerService extends IBaseService<ActivityCustomer>, ProxySelf<IActivityCustomerService>{

    List<ActivityCustomer> selectAllCustomer(IRequest requestContext, ActivityCustomer dto, Integer page, Integer pageSize);

    List<Role> queryRoleAll(IRequest requestContext, Long userId, Integer page, Integer pageSize);


    List<ActivityCustomer> selectCustomerCount(IRequest requestContext, ActivityCustomer dto, Integer page, Integer pageSize);
    List<ActivityCustomer> queryApplication(IRequest requestContext, Long activityId, Integer page, Integer pageSize);
}