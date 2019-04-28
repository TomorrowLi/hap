package hbi.core.customer.service.impl;


import com.alibaba.fastjson.JSONObject;
import hbi.core.customer.dto.CustomerVo;
import hbi.core.customer.dto.CustomerWebservice;

import net.sf.json.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/23 10:39
 * @Description:
 **/
@Service
public class TransformWsUrlUtil {

    private String DEFAULT_URL = "http://localhost:8080/ws/CustomerWeb";

    public  List<CustomerWebservice> TransformWsUrl(CustomerVo vo) {
        Document document=null;
        try {
            String transformXml = getTransformXml(DEFAULT_URL);
            document=DocumentHelper.parseText(transformXml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
//获取根元素
        Element root=document.getRootElement();
        System.out.println("root:"+root);
//获取根元素下的所有子元素
        List<Element> list=root.elements();
        CustomerWebservice customerWebservice = new CustomerWebservice();
        List<CustomerWebservice> customerWebserviceList=new ArrayList<>();
        return customerWebserviceList;


    }
    private static   String getTransformXml(String url){
        String xml = "";
        HttpClient httpClient = new HttpClient();
        //GetMethod getMethod = new GetMethod(url);

        PostMethod postMethod = new PostMethod(url);

        CustomerVo customerVo = new CustomerVo();
        customerVo.setActivityId((long)14);

       /* String transJson = JSONObject.toJSONString(customerVo);
        RequestEntity se = null;
        try {
            se = new StringRequestEntity(transJson, "application/json", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        postMethod.setRequestEntity(se);*/

        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            // 读取内容
            byte[] responseBody = postMethod.getResponseBody();
            // 处理内容
            xml = new String(responseBody);
        } catch (Exception e) {
            System.err.println("页面无法访问");
        } finally {
            postMethod.releaseConnection();
        }
        return xml;
    }

}
