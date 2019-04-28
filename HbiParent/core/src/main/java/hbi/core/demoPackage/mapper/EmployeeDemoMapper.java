package hbi.core.demoPackage.mapper;

import com.hand.hap.core.IRequest;
import com.hand.hap.mybatis.common.Mapper;
import hbi.core.demoPackage.dto.EmployeeDemo;

import java.util.List;

public interface EmployeeDemoMapper extends Mapper<EmployeeDemo>{

    List<EmployeeDemo> queryAll(EmployeeDemo employeeDemo);


}