package com.example.pattern_in_action.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemTypeEnum {
    LOVE_RECOVERY(0),ENUOUGH_TO_BUY(1);
    Integer value;
}
