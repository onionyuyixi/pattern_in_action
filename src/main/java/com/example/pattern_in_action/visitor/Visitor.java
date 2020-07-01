package com.example.pattern_in_action.visitor;

public interface Visitor {
    Object getExchangePageUrl(LoveRecoveryItem loveRecoveryItem);

    Object getExchangePageUrl(EnoughToBuyItem enoughToBuyItem);


}
