package com.honghu.corerpc.protocol;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 协议消息的序列化器枚举
 * @author hzd
 */
@Getter
public enum ProtocolMessageSerializerEnum {
    //jdk序列化器
    JDK(0,"jdk"),
    //json序列化器
    JSON(1,"json"),
    //hassian序列化器
    HESSIAN(2,"hessian"),
    //Kryo序列化器
    KRYO(3,"kryo");

    private final int key;
    private final String value;

    ProtocolMessageSerializerEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取值列表
     * @return
     */
    public static List<String> getValues(){
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }
    /**
     * 根据 key 返回枚举
     * @param key
     * @return
     */
    public static ProtocolMessageSerializerEnum getEnumByKey(int key){
        for (ProtocolMessageSerializerEnum item : ProtocolMessageSerializerEnum.values()){
            if(item.key == key){
                return item;
            }
        }
        return null;
    }

    /**
     * 根据 value 返回枚举
     * @param value
     * @return
     */
    public static ProtocolMessageSerializerEnum getEnumByValue(String value){
        if(ObjectUtil.isEmpty(value)){
            return null;
        }
        for (ProtocolMessageSerializerEnum item : ProtocolMessageSerializerEnum.values()){
            if(item.value.equals(value)){
                return item;
            }
        }
        return null;
    }
}
