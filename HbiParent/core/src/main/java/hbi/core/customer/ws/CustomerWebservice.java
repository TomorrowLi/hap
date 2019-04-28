
package hbi.core.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>customerWebservice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="customerWebservice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activiteMoney" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="activityData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="activityEmployee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activityRole" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="activityText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageAddress" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerWebservice", propOrder = {
    "activiteMoney",
    "activityData",
    "activityEmployee",
    "activityName",
    "activityRole",
    "activityText",
    "imageAddress"
})
public class CustomerWebservice {

    protected Double activiteMoney;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activityData;
    protected String activityEmployee;
    protected String activityName;
    @XmlElement(nillable = true)
    protected List<String> activityRole;
    protected String activityText;
    @XmlElement(nillable = true)
    protected List<String> imageAddress;

    /**
     * 获取activiteMoney属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getActiviteMoney() {
        return activiteMoney;
    }

    /**
     * 设置activiteMoney属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setActiviteMoney(Double value) {
        this.activiteMoney = value;
    }

    /**
     * 获取activityData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivityData() {
        return activityData;
    }

    /**
     * 设置activityData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivityData(XMLGregorianCalendar value) {
        this.activityData = value;
    }

    /**
     * 获取activityEmployee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityEmployee() {
        return activityEmployee;
    }

    /**
     * 设置activityEmployee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityEmployee(String value) {
        this.activityEmployee = value;
    }

    /**
     * 获取activityName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 设置activityName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityName(String value) {
        this.activityName = value;
    }

    /**
     * Gets the value of the activityRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activityRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivityRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActivityRole() {
        if (activityRole == null) {
            activityRole = new ArrayList<String>();
        }
        return this.activityRole;
    }

    /**
     * 获取activityText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityText() {
        return activityText;
    }

    /**
     * 设置activityText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityText(String value) {
        this.activityText = value;
    }

    /**
     * Gets the value of the imageAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getImageAddress() {
        if (imageAddress == null) {
            imageAddress = new ArrayList<String>();
        }
        return this.imageAddress;
    }

}
