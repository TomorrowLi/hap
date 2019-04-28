package hbi.core.activity.controllers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hand.hap.excel.dto.ColumnInfo;
import com.hand.hap.excel.dto.ExportConfig;
import com.hand.hap.excel.service.IExportService;
import hbi.core.activity.dto.Comment;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.service.IActivityDemo1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class ActivityDemo1Controller extends BaseController{

@Autowired
private IActivityDemo1Service service;


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IExportService excelService;
@RequestMapping(value = "/rent/activity/demo1/query")
@ResponseBody
public ResponseData query(ActivityDemo1 dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.queryAllActivity(requestContext,dto,page,pageSize));
}


    @RequestMapping(value = "/rent/activity/demo1/queryhistory")
    @ResponseBody
    public ResponseData queryHistory(ActivityDemo1 dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

        return new ResponseData(service.queryHistoryActivity(requestContext,dto,page,pageSize));
    }

@RequestMapping(value = "/rent/activity/demo1/submit")
@ResponseBody
public ResponseData update(@RequestBody List<ActivityDemo1> dto, BindingResult result, HttpServletRequest request){
    getValidator().validate(dto, result);
    if (result.hasErrors()) {
    ResponseData responseData = new ResponseData(false);
    responseData.setMessage(getErrorMessage(result, request));
    return responseData;
    }
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}


    @RequestMapping(value = "/rent/activity/demo1/submitAndAct")
    @ResponseBody
    public ResponseData submitAndAct(@RequestBody List<ActivityDemo1> dto, HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);
        service.submitAndAct(requestContext,dto.get(0));
        return new ResponseData();
    }

    @RequestMapping(value = "/rent/activity/demo1/fileDowmload")
    @ResponseBody
    public ResponseData fileDownload(@RequestParam("userId") Long userId, HttpServletRequest request,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                     @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize){
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.fileDownload(requestContext,userId,page,pageSize));
    }

@RequestMapping(value = "/rent/activity/demo1/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request,@RequestBody List<ActivityDemo1> dto){
    service.batchDelete(dto);
    return new ResponseData();
}


    /***自定义导入*****/
    @RequestMapping(
            value = {"/rent/activity/demo1/excel/import/custom"},
            method = {RequestMethod.POST}
    )
    public ResponseData uploadExcel(HttpServletRequest request, Locale locale, String
            contextPath) throws Exception {
        ResponseData rd = new ResponseData();
        IRequest requestCtx = createRequestContext(request);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
                request;
        MultipartFile file = multipartRequest.getFile("upfile");
        if (file == null || file.isEmpty()) {
            rd.setSuccess(false);
            rd.setMessage("File is empty!");
            return rd;
        }
        InputStream in = file.getInputStream();
        //importExcel()方法在下面的IOra20796OrgAccessService定义
        return service.importExcel(requestCtx,in, file.getOriginalFilename());
    }


    @InitBinder
protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(simpleDateFormat, true);
        binder.registerCustomEditor(Date.class, dateEditor);
 }


}