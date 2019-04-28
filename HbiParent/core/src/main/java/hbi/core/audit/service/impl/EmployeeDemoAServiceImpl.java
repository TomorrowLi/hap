package hbi.core.audit.service.impl;

import com.hand.hap.audit.service.IAuditRecordService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.BaseDTO;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.audit.dto.EmployeeDemoA;
import hbi.core.audit.service.IEmployeeDemoAService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeDemoAServiceImpl extends BaseServiceImpl<EmployeeDemoA> implements IEmployeeDemoAService {


}