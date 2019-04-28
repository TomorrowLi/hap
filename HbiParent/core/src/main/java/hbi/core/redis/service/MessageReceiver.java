package hbi.core.redis.service;

import com.hand.hap.message.IMessageConsumer;
import com.hand.hap.message.QueueMonitor;
import org.springframework.stereotype.Component;

/**
 * @Author: LiMing
 * @Date: 2019/4/1 14:29
 * @Description:
 **/
@Component
@QueueMonitor(queue="queue1")
public class MessageReceiver implements IMessageConsumer<String> {
    @Override
    public void onMessage(String message, String queue) {
        System.out.println("消息 :"+message);
    }
}
