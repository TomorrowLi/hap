package hbi.core.customer.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.customer.dto.Customer;
import hbi.core.customer.dto.CustomerWebservice;
import hbi.core.webservice.dto.CallProductVo;
import hbi.core.webservice.dto.ProductVo;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;


public interface ICustomerService extends IBaseService<Customer>, ProxySelf<ICustomerService>{


    List<Customer> queryAllCustomer(IRequest requestContext, Customer customer, Integer page, Integer pageSize);


}