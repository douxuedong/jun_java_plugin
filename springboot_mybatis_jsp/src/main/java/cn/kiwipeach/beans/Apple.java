package cn.kiwipeach.beans;

import java.math.BigDecimal;

/**
 * Create Date: 2017/11/05
 * Description: 苹果类
 *
 * @author Wujun
 */
public class Apple extends Fruit {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
