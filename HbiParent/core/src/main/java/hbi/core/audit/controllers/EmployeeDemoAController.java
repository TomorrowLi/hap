package hbi.core.audit.controllers;

import com.hand.hap.audit.service.IAuditRecordService;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.audit.dto.EmployeeDemoA;
import hbi.core.audit.service.IEmployeeDemoAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class EmployeeDemoAController extends BaseController{

    @Autowired
    private IEmployeeDemoAService service;


        @Autowired
        @Qualifier("auditEmployeeServiceImpl") // 注入Service
        private IAuditRecordService auditUserService;


    @RequestMapping(value = "/hr/employee/demo/a/query")
    @ResponseBody
    public ResponseData query(EmployeeDemoA dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(auditUserService.selectAuditRecord(requestContext,dto,page,pageSize));
    }


        /**
         *  审计供应商的详细审计记录
         * @return
         */
        @PostMapping("/hr/employee/demo/a/queryProAuditDetail")
        @ResponseBody
        public ResponseData queryAuditUserDetail(HttpServletRequest request,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
                                                 @ModelAttribute EmployeeDemoA dto){
            IRequest iRequest = createRequestContext(request);
            //封装为DTO 可条件查询
            return new ResponseData(auditUserService.selectAuditRecordDetail(iRequest, dto, page, pageSize));
        }

    @RequestMapping(value = "/hr/employee/demo/a/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<EmployeeDemoA> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hr/employee/demo/a/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<EmployeeDemoA> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }