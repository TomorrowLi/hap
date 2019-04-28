package hbi.core.activity.dto;

import com.hand.hap.system.dto.BaseDTO;

/**
 * @Author: LiMing
 * @Date: 2019/4/8 17:59
 * @Description:
 **/
public class Comment extends BaseDTO {

    private String activitySataus;
    private  Long id;

    public String getActivitySataus() {
        return activitySataus;
    }

    public void setActivitySataus(String activitySataus) {
        this.activitySataus = activitySataus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
