
package hbi.core.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getCustomerBack complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getCustomerBack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://service.customer.core.hbi/}customerVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCustomerBack", propOrder = {
    "result"
})
public class GetCustomerBack {

    protected CustomerVo result;

    /**
     * 获取result属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerVo }
     *     
     */
    public CustomerVo getResult() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerVo }
     *     
     */
    public void setResult(CustomerVo value) {
        this.result = value;
    }

}
