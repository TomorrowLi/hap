package hbi.core.redis.service;

import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.QueueMonitor;
import hbi.core.demoPackage.dto.EmployeeDemo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: LiMing
 * @Date: 2019/4/1 17:51
 * @Description:
 **/
@Component
@QueueMonitor(queue="queue2")
public class MessageReceiver1 implements IMessageConsumer<List<EmployeeDemo>> {

    @Override
    public void onMessage(List<EmployeeDemo> list, String s) {
        System.out.println(list);
    }
}
