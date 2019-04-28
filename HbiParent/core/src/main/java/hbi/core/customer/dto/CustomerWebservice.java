package hbi.core.customer.dto;

import com.hand.hap.account.dto.UserRole;
import com.hand.hap.attachment.dto.SysFile;
import hbi.core.activity.dto.ActivityDemo1;

import java.util.Date;
import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/17 15:07
 * @Description:
 **/
public class CustomerWebservice {

    private String activityName;

    private Date activityData;

    private String activityText;

    private String activityEmployee;
    private Double activiteMoney;

    private List<String> imageAddress;

    private List<String> activityRole;


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getActivityData() {
        return activityData;
    }

    public void setActivityData(Date activityData) {
        this.activityData = activityData;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getActivityEmployee() {
        return activityEmployee;
    }

    public void setActivityEmployee(String activityEmployee) {
        this.activityEmployee = activityEmployee;
    }

    public Double getActiviteMoney() {
        return activiteMoney;
    }

    public void setActiviteMoney(Double activiteMoney) {
        this.activiteMoney = activiteMoney;
    }

    public List<String> getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(List<String> imageAddress) {
        this.imageAddress = imageAddress;
    }

    public List<String> getActivityRole() {
        return activityRole;
    }

    public void setActivityRole(List<String> activityRole) {
        this.activityRole = activityRole;
    }
}
