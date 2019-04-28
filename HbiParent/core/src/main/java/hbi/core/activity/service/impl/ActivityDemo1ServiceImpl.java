package hbi.core.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.activity.dto.Comment;
import hbi.core.activity.dto.Demo;
import hbi.core.activity.mapper.ActivityDemo1Mapper;
import hbi.core.activity.mapper.FileMapper;
import hbi.core.activity.redis.RedisAcitivity;
import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.workflow.dto.WorkflowDto;
import hbi.core.workflow.service.BillReturnService;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.service.IActivityDemo1Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityDemo1ServiceImpl extends BaseServiceImpl<ActivityDemo1> implements IActivityDemo1Service{


    @Autowired
    BillReturnService billReturnService;

    @Autowired
    ActivityDemo1Mapper demo1Mapper;


    @Autowired
    FileMapper fileMapper;

    @Autowired
    RedisAcitivity redisAcitivity;


    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel


    private final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;
    @Override
    public List<ActivityDemo1> queryAllActivity(IRequest requestContext, ActivityDemo1 dto, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);

        return demo1Mapper.queryAllActivity(dto);
    }

    @Override
    public List<ActivityDemo1> queryHistoryActivity(IRequest requestContext, ActivityDemo1 dto, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        return demo1Mapper.queryHistoryActivity(dto);
    }

    @Override
    public List<EmployeeDemo> queryEmployee(IRequest requestContext, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return demo1Mapper.queryEmployee();

    }


    /**
     * 开启工作流修改状态
     * @param requestContext
     * @param activityDemo1
     */
    @Override
    public void submitAndAct(IRequest requestContext,ActivityDemo1 activityDemo1) {


        if(null!=activityDemo1.getId()){


            //修改状态
            Comment comment=new Comment();
            comment.setId(activityDemo1.getId());
            comment.setActivitySataus("IN_AUDIT");
            demo1Mapper.updateById(comment);
            //开启工作流
            WorkflowDto dto1 =new WorkflowDto();
            createParams(activityDemo1,dto1);
            billReturnService.startBill(requestContext,dto1);
        }

    }

    @Override
    public void updateById(Comment comment) {
        demo1Mapper.updateById(comment);
    }

    /**
     * 通过开始日期和结束日期来修改状态
     * @param prefix
     * @param activityDemo1
     */
    @Override
    public void updateByIdAndDate(String prefix, ActivityDemo1 activityDemo1) {
        Comment comment=new Comment();
        if(activityDemo1.getActivitySataus().equals("AUDIT_PASS")){
            if( activityDemo1.getActivityStartData().getTime()<=new Date().getTime()){
                comment.setId(activityDemo1.getId());
                comment.setActivitySataus("IN_RELEASE");
                demo1Mapper.updateById(comment);
                /*List<ActivityDemo1> activityRedis = redisAcitivity.getValue("activityRedis");
                activityRedis.add(activityDemo1);
                //List<ActivityDemo1> activityDemo1s = redisAcitivity.addRedisActivity(activityDemo1);
                redisAcitivity.setValue("activityRedis",activityRedis,EXPIRE_TIME);*/
                redisAcitivity.init();
            }
        }
        else if(activityDemo1.getActivitySataus().equals("IN_RELEASE")){
            if(new Date().getTime()-activityDemo1.getActivityStopData().getTime()>=1){
                comment.setId(activityDemo1.getId());
                comment.setActivitySataus("STOP");
                demo1Mapper.updateById(comment);
                /*List<ActivityDemo1> activityRedis = redisAcitivity.getValue("activityRedis");
                //activityRedis.re
                for (int i = 0; i < activityRedis.size(); i++) {
                    ActivityDemo1 activityDemo11 = activityRedis.get(i);
                    if(activityDemo11.getId()==activityDemo1.getId()){
                        activityRedis.remove(activityDemo11);
                    }
                }
                redisAcitivity.setValue("activityRedis",activityRedis,EXPIRE_TIME);*/
                redisAcitivity.init();
            }
        }else {
            return;
        }

    }

    @Override
    public List<SysFile> fileDownload(IRequest requestContext,Long activityId,Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<SysFile> sysFiles = fileMapper.fileDownload(activityId);
        //System.out.println(sysFiles);
        return sysFiles;
    }

    private void createParams( ActivityDemo1 act,WorkflowDto dto){

        dto.setActivitiCode("activityActivite");
        dto.setBusinessKey(act.getId()+"");//
        List<RestVariable> variables = new ArrayList<>();

        RestVariable actCode = new RestVariable();
        actCode.setName("actCode");
        actCode.setType("string");
        actCode.setValue("activityActivite");

        RestVariable activityId = new RestVariable();
        activityId.setName("activityId");
        activityId.setType("long");
        activityId.setValue(act.getId());

        RestVariable amount = new RestVariable();
        amount.setName("amount");
        amount.setType("double");
        amount.setValue(act.getActiviteMoney());

        RestVariable actRole = new RestVariable();
        if(act.getActiviteMoney()>10000){
            actRole.setName("actRole");
            actRole.setType("string");
            actRole.setValue("经理");
        }else {
            actRole.setName("actRole");
            actRole.setType("string");
            actRole.setValue("组长");
        }


        variables.add(actCode);
        variables.add(amount);
        variables.add(activityId);
        variables.add(actRole);

        dto.setVariable(variables);

    }


    /***自定义导入****/

    public ResponseData importExcel(IRequest requestContext,InputStream is, String fileName) throws Exception {
        ResponseData rd = new ResponseData();
        List<List<Object>> list = new ArrayList<List<Object>>();
// Create a new workbook
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if (excel2003L.equals(fileType)) {
            workbook = new HSSFWorkbook(is); //2003-


        } else if (excel2007U.equals(fileType)) {
            workbook = new XSSFWorkbook(is); //2007+
        } else {
            throw new Exception("解析的⽂文件格式有误！");
        }


        /*CellStyle lockstyle = workbook.createCellStyle();
        lockstyle.setLocked(true);
        lockstyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        lockstyle.setFillForegroundColor(IndexedColors.RED.getIndex());*/

        Sheet worksheet = null;
        Row row = null;
        Cell cell = null;
//遍历Excel中所有的sheet
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            worksheet = workbook.getSheetAt(i);

            if (worksheet == null) {
                continue;
            }
//遍历当前sheet中的所有⾏行行
            for (int j = worksheet.getFirstRowNum(); j < worksheet.getLastRowNum() + 1; j++) {
                row = worksheet.getRow(j);
// 跳过空⾏行行和标题⾏行行（第⼀一⾏行行）
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
//遍历所有的列列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        workbook.close();
        // 从第⼆二⾏行行开始遍历
        DateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat format1  = new SimpleDateFormat("yyyy-MM-dd");
        String message="";
        if(list.size()==0){
            message="活动名称不能为空";
        }
        for (int i = 0; i <list.size(); i++) {

            List<Object> excelRow = list.get(i);
            ActivityDemo1 dto = new ActivityDemo1();
            dto.setActivityName(excelRow.get(0).toString());

            dto.setActivityData(format1.parse(excelRow.get(1).toString()));
            dto.setActivityStartData(format1.parse(excelRow.get(2).toString()));
            dto.setActivityStopData(format1.parse(excelRow.get(3).toString()));
            dto.setActivityText(excelRow.get(5).toString());
            dto.setActivityEmployee(excelRow.get(6).toString());
             dto.setActiviteMoney(Double.parseDouble(excelRow.get(7).toString()));
            dto.setCreatedBy(requestContext.getUserId());
            dto.setCreationDate(format.parse(format.format(new Date())));
            /*dto.setDescription(excelRow.get(2).toString());
            dto.setOrgType(excelRow.get(3).toString());
            dto.setOrgId(Long.parseLong(excelRow.get(4).toString()));
            dto.setRoleId(1L);
            dto.setUserId(1L);*/
            demo1Mapper.insertSelective(dto);
        }
        rd.setSuccess(true);
        rd.setMessage(message);
        return rd;
    }


    private Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); //⽇日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00"); //格式化数字
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        value = df.format(cell.getNumericCellValue());
                    } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    } else if ("m-d-yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    }
                    else {
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
        } else {
            value = "";
        }
        return value;
    }


    public  void main(String[] args) {

        List<Demo> demos = demo1Mapper.queryDemo();

        for (int i = 2; i < demos.size()+2; i++) {

            //if(demos.get(i).getStopPrice()>)
        }
    }

}