package hbi.core.activity.service;

import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.IBaseService;
import hbi.core.activity.dto.ActivityDemo1;
import hbi.core.activity.dto.Comment;
import hbi.core.demoPackage.dto.EmployeeDemo;

import java.io.InputStream;
import java.util.List;

public interface IActivityDemo1Service extends IBaseService<ActivityDemo1>, ProxySelf<IActivityDemo1Service>{


    abstract List<ActivityDemo1> queryAllActivity(IRequest requestContext, ActivityDemo1 dto, Integer page, Integer pageSize);

    abstract List<ActivityDemo1> queryHistoryActivity(IRequest requestContext, ActivityDemo1 dto, Integer page, Integer pageSize);

    List<EmployeeDemo> queryEmployee(IRequest requestContext, Integer page, Integer pageSize);


    void submitAndAct(IRequest requestContext,ActivityDemo1 activityDemo1);

    void updateById(Comment comment);

    void updateByIdAndDate(String prefix,ActivityDemo1 activityDemo1);

    List<SysFile> fileDownload(IRequest requestContext, Long id, Integer page, Integer pageSize);


    ResponseData importExcel(IRequest requestContext,InputStream is, String fileName) throws Exception;


}