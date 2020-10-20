import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *ClientReceive类用于创建接受服务器发过来的数据的线程
 * @author Xueji
 */
public class ClientReceive extends Thread {
    Socket socket;
    DataInputStream inputStream;

    public ClientReceive(Socket soc) {
        try {
            socket = soc;
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 若链接后接收到"Entry the chat room successfully!"信息,则进入持续接收信息的while(true)循环
     * 否则报错:"Server Wrong!"
     */
    @Override
    public void run() {

        try {
            String lrSockeString3;
            lrSockeString3 = inputStream.readUTF();
            if ("Entry the chat room successfully!".equals(lrSockeString3)) {
                System.out.println("Entry the chat room successfully!");
                while (true) {
                    lrSockeString3 = inputStream.readUTF();
                    System.out.println("lrSockeString3:" + lrSockeString3);
                    if (lrSockeString3.equals("Quit the chat room successfully")) {
                        System.out.println("Quit the chat room successfully!");
                        System.exit(1);
                    }
                }
            } else {
                System.out.println("Server Wrong!");
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


