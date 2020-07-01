package com.example.pattern_in_action.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnoughToBuyItem extends BaseItem implements Item {

    private String enoughToBuySpecial1;

    private String enoughToBuySpecial2;


    @Override
    public Object accept(Visitor visitor) {
        return visitor.getExchangePageUrl(this);
    }
}
