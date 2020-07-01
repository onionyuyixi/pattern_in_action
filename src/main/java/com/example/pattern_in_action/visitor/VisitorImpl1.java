package com.example.pattern_in_action.visitor;

import org.springframework.stereotype.Component;

@Component("visitorImpl1")
public class VisitorImpl1 implements Visitor {
    @Override
    public Object getExchangePageUrl(LoveRecoveryItem loveRecoveryItem) {
        //todo 爱回收的以旧换新页面处理获取处理
        String s = loveRecoveryItem.toString();
        System.err.println(s);
        return s;
    }

    @Override
    public Object getExchangePageUrl(EnoughToBuyItem enoughToBuyItem) {
        //todo 有得卖的以旧换新页面处理获取处理
        String s = enoughToBuyItem.toString();
        System.err.println(s);
        return s;
    }


}
