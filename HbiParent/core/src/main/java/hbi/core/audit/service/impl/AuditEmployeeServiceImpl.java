package hbi.core.audit.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.audit.service.IAuditRecordService;
import com.hand.hap.audit.util.AuditRecordUtils;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.BaseDTO;
import hbi.core.audit.dto.EmployeeDemoA;
import hbi.core.audit.mapper.EmployeeDemoAMapper;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: LiMing
 * @Date: 2019/3/29 14:27
 * @Description:
 **/

@Service("auditEmployeeServiceImpl")//注入Service
public class AuditEmployeeServiceImpl implements IAuditRecordService {


    @Autowired
    private EmployeeDemoAMapper employeeDemoAMapper;

    /**
     * 供应商审计列表
     */
    @Override
    public List<Map<String, Object>> selectAuditRecord(IRequest iRequest, BaseDTO dto, int page, int pageSize) {
        // 分页
        PageHelper.startPage(page, pageSize);
        return AuditRecordUtils.operateAuditRecord(employeeDemoAMapper.selectAuditEmployee((EmployeeDemoA)dto));

    }
    /**
     * 供应商审计明细
     */
    @Override
    public List selectAuditRecordDetail(IRequest iRequest, BaseDTO dto, int page, int pageSize) {
        // 分页
        PageHelper.startPage(page,pageSize);
        List<Map<String,Object>> selectAuditProductDetail = employeeDemoAMapper.selectAuditEmployeeDetail((EmployeeDemoA) dto);
        return AuditRecordUtils.operateAuditRecordSingleDetail(selectAuditProductDetail);

    }
}
