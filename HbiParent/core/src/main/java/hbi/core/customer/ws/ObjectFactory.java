
package hbi.core.customer.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hbi.core.customer.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCustomerBackResponse_QNAME = new QName("http://service.customer.core.hbi/", "getCustomerBackResponse");
    private final static QName _GetCustomerBack_QNAME = new QName("http://service.customer.core.hbi/", "getCustomerBack");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hbi.core.customer.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomerBackResponse }
     * 
     */
    public GetCustomerBackResponse createGetCustomerBackResponse() {
        return new GetCustomerBackResponse();
    }

    /**
     * Create an instance of {@link GetCustomerBack }
     * 
     */
    public GetCustomerBack createGetCustomerBack() {
        return new GetCustomerBack();
    }

    /**
     * Create an instance of {@link CustomerWebservice }
     * 
     */
    public CustomerWebservice createCustomerWebservice() {
        return new CustomerWebservice();
    }

    /**
     * Create an instance of {@link CustomerVo }
     * 
     */
    public CustomerVo createCustomerVo() {
        return new CustomerVo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerBackResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.customer.core.hbi/", name = "getCustomerBackResponse")
    public JAXBElement<GetCustomerBackResponse> createGetCustomerBackResponse(GetCustomerBackResponse value) {
        return new JAXBElement<GetCustomerBackResponse>(_GetCustomerBackResponse_QNAME, GetCustomerBackResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerBack }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.customer.core.hbi/", name = "getCustomerBack")
    public JAXBElement<GetCustomerBack> createGetCustomerBack(GetCustomerBack value) {
        return new JAXBElement<GetCustomerBack>(_GetCustomerBack_QNAME, GetCustomerBack.class, null, value);
    }

}
