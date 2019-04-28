package hbi.core.customer.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@ExtensionAttribute(disable=true)
@Table(name = "rent_activity_customer")
public class ActivityCustomer extends BaseDTO {

     public static final String FIELD_ID = "id";
     public static final String FIELD_CUSTOMER_ID = "customerId";
     public static final String FIELD_ACTIVITY_ID = "activityId";
     public static final String FIELD_APPLICATION_STATUS = "applicationStatus";
     public static final String FIELD_APPLICATION_DATE = "applicationDate";


     @Id
     @GeneratedValue
     private Long id;

     private Long customerId;

     private Long activityId;

     @Length(max = 50)
     private String applicationStatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date applicationDate;

    @Transient
    private String customer_name;

    @Transient
    private String activity_name;

    @Transient
    private Boolean status;

    @Transient
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCustomer_name() {
        return customer_name;
    }


    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public void setId(Long id){
         this.id = id;
     }

     public Long getId(){
         return id;
     }

     public void setCustomerId(Long customerId){
         this.customerId = customerId;
     }

     public Long getCustomerId(){
         return customerId;
     }

     public void setActivityId(Long activityId){
         this.activityId = activityId;
     }

     public Long getActivityId(){
         return activityId;
     }

     public void setApplicationStatus(String applicationStatus){
         this.applicationStatus = applicationStatus;
     }

     public String getApplicationStatus(){
         return applicationStatus;
     }

     public void setApplicationDate(Date applicationDate){
         this.applicationDate = applicationDate;
     }

     public Date getApplicationDate(){
         return applicationDate;
     }

     }
