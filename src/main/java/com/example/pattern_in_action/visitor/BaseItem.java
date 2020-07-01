package com.example.pattern_in_action.visitor;

import com.example.pattern_in_action.enums.ItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseItem implements Serializable {

    /**
     * 发生时间
     */
    private LocalDateTime occurTime = LocalDateTime.now();

    /**
     * 回收类型
     */
    private ItemTypeEnum itemTypeEnum;

    /**
     * 注释
     */
    private String comment;








}
