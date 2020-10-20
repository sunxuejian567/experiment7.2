import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(55536);
        /**
         * 若有连接到来,则创建一个ProcessServer线程,
         * 并把该线程加入到ProcessCommunication中的线程消息转发队列
         */
        try {

            System.out.println("等待连接到来");
            while (true) {
                Socket socket = serverSocket.accept();
                ProcessServer processServer=new ProcessServer(socket);
                ProcessCommunication.addClient(processServer);
                processServer.start();
//                Thread thread = new Thread(processServer);
//                thread.start();
                System.out.println("一位用户进入聊天室");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        finally {
            serverSocket.close();
        }
    }
}

