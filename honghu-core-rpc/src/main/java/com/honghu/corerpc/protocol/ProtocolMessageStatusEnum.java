package com.honghu.corerpc.protocol;

import lombok.Getter;

/**
 * 协议消息的状态枚举
 * @author hzd
 */
@Getter
public enum ProtocolMessageStatusEnum {
    //正常
    OK("ok",20),
    //错误请求
    BAD_REQUEST("badRequest",40),
    //错误响应
    BAD_RESPONSE("badResponse",50);
    private final String text;
    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 返回枚举
     * @param value
     * @return
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value){
        for (ProtocolMessageStatusEnum item : ProtocolMessageStatusEnum.values()){
            if(item.value == value){
                return item;
            }
        }
        return null;
    }
}
