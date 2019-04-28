package hbi.core.customer.controllers;

import hbi.core.customer.dto.CustomerVo;
import hbi.core.customer.service.impl.TransformWsUrlUtil;
import hbi.core.customer.ws.CustomerWeb;
import hbi.core.customer.ws.CustomerWeb_Service;
import hbi.core.customer.ws.CustomerWebservice;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.customer.dto.ActivityCustomer;
import hbi.core.customer.service.IActivityCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

    @Controller
    public class ActivityCustomerController extends BaseController{

    @Autowired
    private IActivityCustomerService service;

    @Autowired
    private TransformWsUrlUtil wsUrlUtil;

    @RequestMapping(value = "/rent/activity/customer/query")
    @ResponseBody
    public ResponseData query(ActivityCustomer dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

        return new ResponseData(service.selectAllCustomer(requestContext,dto,page,pageSize));
    }

        @RequestMapping(value = "/rent/activity/customer/queryCustomerCount")
        @ResponseBody
        public ResponseData queryCustomerCount(ActivityCustomer dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);

            return new ResponseData(service.selectCustomerCount(requestContext,dto,page,pageSize));
        }

        @RequestMapping(value = "/rent/activity/customer/queryRole")
        @ResponseBody
        public ResponseData queryRole(@RequestParam("userId") Long userId, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.queryRoleAll(requestContext,userId,page,pageSize));
        }




    @RequestMapping(value = "/rent/activity/customer/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<ActivityCustomer> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/rent/activity/customer/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<ActivityCustomer> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }


        @RequestMapping(value = "/rent/activity/customer/queryApplication")
        @ResponseBody
        public ResponseData fileDownload(@RequestParam("userId") Long userId, HttpServletRequest request,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                         @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize){
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.queryApplication(requestContext,userId,page,pageSize));
        }


        @RequestMapping(value = "/rent/activity/customer/TransformWsUrl")
        @ResponseBody
        public ResponseData TransformWsUrl(@RequestParam("activityId") Long acyivityId){

            CustomerWeb_Service customerWeb_service = new CustomerWeb_Service();
            CustomerWeb customerWebImplPort = customerWeb_service.getCustomerWebImplPort();
            hbi.core.customer.ws.CustomerVo customerVo = new hbi.core.customer.ws.CustomerVo();
            customerVo.setActivityId(acyivityId);
            CustomerWebservice customerBack = customerWebImplPort.getCustomerBack(customerVo);

            List<CustomerWebservice> customerWebservices = new ArrayList<>();
            customerWebservices.add(customerBack);
            return new  ResponseData(customerWebservices);

    }
    }