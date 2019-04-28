package hbi.core.webservice.service;

import hbi.core.webservice.dto.CallProductVo;
import hbi.core.webservice.dto.ProductVo;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author: LiMing
 * @Date: 2019/3/28 10:38
 * @Description:
 **/
@WebService
public interface ProductService {

    /**
     * 接收供应商数据
     *
     * @param vo
     * @return
     */
    CallProductVo getCallBack(@WebParam(name = "result") ProductVo vo);
}
