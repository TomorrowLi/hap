package hbi.core.webservice.service.impl;

import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.demoPackage.mapper.EmployeeDemoMapper;
import hbi.core.demoPackage.service.IEmployeeDemoService;
import hbi.core.webservice.dto.CallProductVo;
import hbi.core.webservice.dto.ProductVo;
import hbi.core.webservice.service.ProductService;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/3/28 10:48
 * @Description:
 **/

@WebService(endpointInterface = "hbi.core.webservice.service.ProductService", serviceName = "productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    EmployeeDemoMapper employeeDemoMapper;
    @Override
    public CallProductVo getCallBack(ProductVo vo) {
        List<EmployeeDemo> list=employeeDemoMapper.queryAll(null);
        CallProductVo vo1=new CallProductVo();
        vo1.setCode("sss");
        vo1.setMessage(list.get(0).getName());
        vo1.setResult(list.get(0).getEmail());
        return vo1;
    }
}
