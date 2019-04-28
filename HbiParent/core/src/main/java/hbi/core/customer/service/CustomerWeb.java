package hbi.core.customer.service;

import hbi.core.customer.dto.CustomerVo;
import hbi.core.customer.dto.CustomerWebservice;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author: LiMing
 * @Date: 2019/4/17 17:16
 * @Description:
 **/

@WebService
public interface CustomerWeb {

    CustomerWebservice getCustomerBack(@WebParam(name = "result") CustomerVo vo);

}
