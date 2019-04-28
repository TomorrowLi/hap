package hbi.core.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.customer.mapper.ActivityCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.customer.dto.ActivityCustomer;
import hbi.core.customer.service.IActivityCustomerService;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityCustomerServiceImpl extends BaseServiceImpl<ActivityCustomer> implements IActivityCustomerService{

    @Autowired
    ActivityCustomerMapper activityCustomerMapper;

    @Override
    public List<ActivityCustomer> selectAllCustomer(IRequest requestContext, ActivityCustomer dto, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ActivityCustomer> activityCustomers = activityCustomerMapper.selectAllCustomer(dto);
        Long[] allRoleId = requestContext.getAllRoleId();
        int i=0;
        for (Long aLong : allRoleId) {
            if(aLong==10004){
                i=1;
                break;
            }else {
                i=0;
            }
        }

        for (ActivityCustomer activityCustomer : activityCustomers) {

                if(i>0){
                    activityCustomer.setStatus(true);

                }else {
                    activityCustomer.setStatus(false);
                }

        }

        return activityCustomers;
    }

    @Override
    public List<Role> queryRoleAll(IRequest requestContext, Long userId, Integer page, Integer pageSize) {
        return activityCustomerMapper.queryRoleAll(userId);
    }

    @Override
    public List<ActivityCustomer> selectCustomerCount(IRequest requestContext, ActivityCustomer dto, Integer page, Integer pageSize) {
        return activityCustomerMapper.selectCustomerCount(dto);
    }

    @Override
    public List<ActivityCustomer> queryApplication(IRequest requestContext, Long activityId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ActivityCustomer> activityCustomers = activityCustomerMapper.queryApplication(activityId);
        Long[] allRoleId = requestContext.getAllRoleId();
        int i=0;
        for (Long aLong : allRoleId) {
            if(aLong==10004){
                i=1;
                break;
            }else {
                i=0;
            }
        }

        for (ActivityCustomer activityCustomer : activityCustomers) {

            if(i>0){
                activityCustomer.setStatus(true);

            }else {
                activityCustomer.setStatus(false);
            }

        }
        return activityCustomers;
    }

}