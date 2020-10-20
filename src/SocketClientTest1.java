import java.net.Socket;

/**
 * @author Xueji
 */
public class SocketClientTest1 {
    public static void main(String args[]) throws Exception {
        /**
         * 建立连接,并创建发送和接收的线程,启动线程
         */
        try {
            Socket socket = new Socket("127.0.0.1", 55536);
            Thread cliReceive = new ClientReceive(socket);
            Thread cliSend = new ClientSend(socket);
            cliSend.start();
            cliReceive.start();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

