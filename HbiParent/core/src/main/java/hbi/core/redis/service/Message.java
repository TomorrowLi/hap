package hbi.core.redis.service;

import com.hand.hap.message.IMessagePublisher;
import hbi.core.demoPackage.dto.EmployeeDemo;
import hbi.core.demoPackage.mapper.EmployeeDemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/1 14:17
 * @Description:
 **/

public class Message {


    @Autowired
    private IMessagePublisher messagePublisher;

    @Autowired
    EmployeeDemoMapper employeeDemoMapper;

    public void sendMessageTest (){
        // 向 队列：queue1 中发送消息 ： message1（字符串）
        messagePublisher.rPush("queue1","message1");

        List<EmployeeDemo> employeeDemos = employeeDemoMapper.selectAll();
        // 向队列：queue2 中发送消息：bean（对象）
        messagePublisher.rPush("queue2",employeeDemos);
    }

}
