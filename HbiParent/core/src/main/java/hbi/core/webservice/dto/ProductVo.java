package hbi.core.webservice.dto;

import java.io.Serializable;

/**
 * @Author: LiMing
 * @Date: 2019/3/28 10:36
 * @Description:
 **/
public class ProductVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;



    private String product_number;

    private String product_name;
    private String product_address;


    public String getProduct_number() {
        return product_number;
    }
    public void setProduct_number(String product_number) {
        this.product_number = product_number;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public String getProduct_address() {
        return product_address;
    }
    public void setProduct_address(String product_address) {
        this.product_address = product_address;
    }
}
