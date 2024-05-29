package com.honghu.corerpc.protocol;

import lombok.Getter;

/**
 * 协议消息的类型枚举
 * @author hzd
 */
@Getter
public enum ProtocolMessageTypeEnum {
    //请求
    REQUEST(1),
    //响应
    RESPONSE(2),
    //心跳
    HEART_BEAT(3),
    //其他
    OTHERS(4);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }
    /**
     * 根据 key 返回枚举
     * @param key
     * @return
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key){
        for (ProtocolMessageTypeEnum item : ProtocolMessageTypeEnum.values()){
            if(item.key == key){
                return item;
            }
        }
        return null;
    }
}
