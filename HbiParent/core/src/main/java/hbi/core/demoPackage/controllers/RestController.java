package hbi.core.demoPackage.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.ServiceRequest;
import com.hand.hap.intergration.annotation.HapInbound;
import com.hand.hap.system.controllers.BaseController;
import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.demoPackage.service.IEmployeeDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LiMing
 * @Date: 2019/3/28 13:10
 * @Description:
 **/

/**
 * @RequestMapping 来映射URL 到控制器类,自定义
 * 当Controller 类上没有标记@RequestMapping 注解时，方法上的@RequestMapping 都是绝对路径
 * 系统默认拦截绝对路径进行登陆校验
 * @author jun
 *
 */
@Controller
@RequestMapping(value = { "/api/public/rest" })
public class RestController extends BaseController {


    @Autowired
    private IEmployeeDemoService service;

    /**
     * 查-有效的商品
     * @HapInbound(apiName="有效商品接口")  加上@HapInbound注解可以使系统监控到此URL服务,apiName设置接口名称,自定义
     * @param dto
     * @param request
     * @return
     */
    @HapInbound(apiName="有效商品接口")
    @RequestMapping(value = "/goods/appQueryGoodList", produces = "application/json")
    @ResponseBody
    public Map<String,Object> appQueryGoodList(@RequestBody EmployeeDemo dto, HttpServletRequest request) {
         /*dto.setEnabledFlag(RentConstant.FS_ENABLED_FLAG_Y);
       Page<GoodsVo> appQueryGoodsList = (Page<GoodsVo>) goodsInfoService.appQueryGoodsList(dto);*/
        IRequest request1=new ServiceRequest();
        request1.setEmployeeCode("6768");
        List<EmployeeDemo> list= service.selectAll(null);
        Map<String,Object> map=new HashMap<>();
        map.put("list", list);
        return map;
    }
}
