package hbi.core.customer.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.customer.dto.ActivityCustomer;
import hbi.core.customer.dto.Customer;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerMapper extends Mapper<Customer>{


    List<Customer> queryAllCustomer(Customer customer);


}