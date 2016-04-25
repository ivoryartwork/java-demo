package demo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Yaochao on 2016/4/25.
 */
public class Client {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 9000));

            ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
            socketChannel.write(buffer);
            socketChannel.socket().shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
