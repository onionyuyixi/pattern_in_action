package com.example.pattern_in_action.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoveRecoveryItem extends BaseItem implements Item {

    private String loveRecoverySpecial1;
    private String loveRecoverySpecial2;

    @Override
    public Object accept(Visitor visitor) {
        return visitor.getExchangePageUrl(this);
    }


}
