package hbi.core.demoPackage.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.demoPackage.dto.EmployeeDemo;

import java.util.List;

public interface IEmployeeDemoService extends IBaseService<EmployeeDemo>, ProxySelf<IEmployeeDemoService>{
    abstract List<EmployeeDemo> queryAll(IRequest requestContext, EmployeeDemo dto, Integer page, Integer pageSize);

}