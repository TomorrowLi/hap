package hbi.core.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.mapper.ActivityDemo1Mapper;
import hbi.core.activity.mapper.FileMapper;
import hbi.core.customer.dto.CustomerWebservice;
import hbi.core.customer.mapper.CustomerMapper;
import hbi.core.webservice.dto.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.customer.dto.Customer;
import hbi.core.customer.service.ICustomerService;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements ICustomerService{

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public List<Customer> queryAllCustomer(IRequest requestContext, Customer customer, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Customer> customers = customerMapper.queryAllCustomer(customer);
        return customers;
    }
}