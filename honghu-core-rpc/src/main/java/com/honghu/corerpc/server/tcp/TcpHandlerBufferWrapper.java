package com.honghu.corerpc.server.tcp;

import com.honghu.corerpc.protocol.ProtocolConstant;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;


/**
 * 装饰着模式（使用 recordParser 对原有的 Buffer 处理能力增强
 * @author hzd
 */
public class TcpHandlerBufferWrapper implements Handler<Buffer> {
    private final RecordParser recordParser;

    public TcpHandlerBufferWrapper(Handler<Buffer> bufferHandler) {
        recordParser = initRecordParser(bufferHandler);
    }

    @Override
    public void handle(Buffer buffer) {
        recordParser.handle(buffer);
    }

    private RecordParser initRecordParser(Handler<Buffer> bufferHandler){
        //构造 parser
        RecordParser parser = RecordParser.newFixed(ProtocolConstant.MESSAGE_HEADER_LENGTH);
        parser.setOutput(new Handler<Buffer>() {
            //初始化
            int size = -1;
            //一次完整的读取（头+体）
            Buffer resultbuffer = Buffer.buffer();

            @Override
            public void handle(Buffer buffer) {
                if(size == -1){
                    //读取消息体长度
                    size = buffer.getInt(13);
                    parser.fixedSizeMode(size);
                    //写入头信息到结果
                    resultbuffer.appendBuffer(buffer);
                }else {
                    //写入体信息到结果
                    resultbuffer.appendBuffer(buffer);
                    //已拼接为完整 Buffer，执行处理
                    bufferHandler.handle(resultbuffer);
                    //重置
                    parser.fixedSizeMode(ProtocolConstant.MESSAGE_HEADER_LENGTH);
                    size = -1;
                    resultbuffer = Buffer.buffer();
                }
            }
        });
        return parser;
    }
}
