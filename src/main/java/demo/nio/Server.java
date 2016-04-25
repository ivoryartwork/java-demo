package demo.nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * Created by Yaochao on 2016/4/25.
 */
public class Server {

    public static void main(String[] args) {

        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;
        try {
            selector = SelectorProvider.provider().openSelector();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 9000));

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey readKey = keys.next();
                    keys.remove();
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) readKey.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bytes;
                    int size = 0;
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while ((size = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        bytes = new byte[size];
                        buffer.get(bytes);
                        byteArrayOutputStream.write(bytes);
                        buffer.clear();
                    }
                    bytes = byteArrayOutputStream.toByteArray();
                    System.out.println(new String(bytes));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
