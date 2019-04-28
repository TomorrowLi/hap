package hbi.core.activity.mapper;

import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.mybatis.common.Mapper;

import java.util.List;

public interface FileMapper extends Mapper<SysFile>{



    List<SysFile> fileDownload(Long activityId);


}