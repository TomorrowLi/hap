package hbi.core.activity.dto;

import java.util.Date;

/**
 * @Author: LiMing
 * @Date: 2019/4/16 23:34
 * @Description:
 **/
public class Demo {

    private Date openDate;

    private Long openPrice;

    private Long stopPrice;

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Long getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Long openPrice) {
        this.openPrice = openPrice;
    }

    public Long getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(Long stopPrice) {
        this.stopPrice = stopPrice;
    }
}
