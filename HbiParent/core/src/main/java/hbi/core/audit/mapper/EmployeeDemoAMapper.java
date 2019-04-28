package hbi.core.audit.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.audit.dto.EmployeeDemoA;
import net.sf.ehcache.util.ProductInfo;

import java.util.List;
import java.util.Map;

public interface EmployeeDemoAMapper extends Mapper<EmployeeDemoA>{


    /**
     * 审计列表
     * @param dto
     * @return
     */
    List<Map<String, Object>> selectAuditEmployee(EmployeeDemoA dto);

    /**
     * 审计明细
     * @param dto
     * @return
     */
    List<Map<String, Object>> selectAuditEmployeeDetail(EmployeeDemoA dto);
}