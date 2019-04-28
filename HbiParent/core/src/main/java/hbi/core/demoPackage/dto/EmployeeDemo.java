package hbi.core.demoPackage.dto;

/**Auto Generated By Hap Code Generator**/
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hand.hap.core.annotation.AuditEnabled;
import com.hand.hap.mybatis.annotation.ExtensionAttribute;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Table;
import com.hand.hap.system.dto.BaseDTO;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@ExtensionAttribute(disable=true)
@Table(name = "hr_employee_demo")
@AuditEnabled
public class EmployeeDemo extends BaseDTO {

     public static final String FIELD_EMPLOYEE_ID = "employeeId";
     public static final String FIELD_EMPLOYEE_CODE = "employeeCode";
     public static final String FIELD_NAME = "name";
     public static final String FIELD_BORN_DATE = "bornDate";
     public static final String FIELD_EMAIL = "email";
     public static final String FIELD_MOBIL = "mobil";
     public static final String FIELD_JOIN_DATE = "joinDate";
     public static final String FIELD_GENDER = "gender";
     public static final String FIELD_CERTIFICATE_ID = "certificateId";
     public static final String FIELD_STATUS = "status";
     public static final String FIELD_ENABLED_FLAG = "enabledFlag";
     public static final String FIELD_CERTIFICATE_TYPE = "certificateType";
     public static final String FIELD_EFFECTIVE_START_DATE = "effectiveStartDate";
     public static final String FIELD_EFFECTIVE_END_DATE = "effectiveEndDate";


     @Id
     @GeneratedValue
     private Long employeeId;


     @Length(max = 30)
     private String employeeCode; //员工编码


     @Length(max = 50)
     private String name; //员工姓名


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bornDate; //出生日期

     @Length(max = 50)
     private String email; //电子邮件

     @Length(max = 50)
     private String mobil; //移动电话

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate; //入职日期


     private String gender; //性别


     @Length(max = 100)
     private String certificateId; //ID


     @Length(max = 50)
     private String status; //状态



     private String enabledFlag; //启用状态


     private String certificateType; //证件类型

     private Date effectiveStartDate; //有效日期从

     private Date effectiveEndDate; //有效日期至


     public void setEmployeeId(Long employeeId){
         this.employeeId = employeeId;
     }

     public Long getEmployeeId(){
         return employeeId;
     }

     public void setEmployeeCode(String employeeCode){
         this.employeeCode = employeeCode;
     }

     public String getEmployeeCode(){
         return employeeCode;
     }

     public void setName(String name){
         this.name = name;
     }

     public String getName(){
         return name;
     }

     public void setBornDate(Date bornDate){
         this.bornDate = bornDate;
     }

     public Date getBornDate(){
         return bornDate;
     }

     public void setEmail(String email){
         this.email = email;
     }

     public String getEmail(){
         return email;
     }

     public void setMobil(String mobil){
         this.mobil = mobil;
     }

     public String getMobil(){
         return mobil;
     }

     public void setJoinDate(Date joinDate){
         this.joinDate = joinDate;
     }

     public Date getJoinDate(){
         return joinDate;
     }

     public void setGender(String gender){
         this.gender = gender;
     }

     public String getGender(){
         return gender;
     }

     public void setCertificateId(String certificateId){
         this.certificateId = certificateId;
     }

     public String getCertificateId(){
         return certificateId;
     }

     public void setStatus(String status){
         this.status = status;
     }

     public String getStatus(){
         return status;
     }

     public void setEnabledFlag(String enabledFlag){
         this.enabledFlag = enabledFlag;
     }

     public String getEnabledFlag(){
         return enabledFlag;
     }

     public void setCertificateType(String certificateType){
         this.certificateType = certificateType;
     }

     public String getCertificateType(){
         return certificateType;
     }

     public void setEffectiveStartDate(Date effectiveStartDate){
         this.effectiveStartDate = effectiveStartDate;
     }

     public Date getEffectiveStartDate(){
         return effectiveStartDate;
     }

     public void setEffectiveEndDate(Date effectiveEndDate){
         this.effectiveEndDate = effectiveEndDate;
     }

     public Date getEffectiveEndDate(){
         return effectiveEndDate;
     }

     }
