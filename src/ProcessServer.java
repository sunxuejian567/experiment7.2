import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * ProcessServer用于创建服务器端处理客户端消息的线程
 *
 * @author Xueji
 */
public class ProcessServer extends Thread {
    /**
     * @param name 线程名
     * @param num 线程数
     * @param inputStream socket的输入流
     * @param outputStream socket的输出流
     */
    Socket socket;
    String name;
    static int num = 0;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    /**
     * ProcessServer构造函数
     *
     * @param soc 创建对象传递进来的socket
     */

    public ProcessServer(Socket soc) {
        try {
            socket = soc;
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            this.name = "user" + (++num);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 消息接收,通过ProcessCommunication.castMsg()转发给其他线程,并通过消息最后一位判断是否退出
     */
    @Override
    public void run() {
        try {
            String entryTip = "Entry the chat room successfully!";
            String quitTip = "Quit the chat room successfully!";
            outputStream.writeUTF(entryTip);
            for (char flag = 'a'; flag != 'q'; ) {
//                outputStream.writeUTF(shareMessage);
                String initialMessage = inputStream.readUTF();
                String message = initialMessage.substring(0, initialMessage.length() - 1);
                flag = initialMessage.substring(initialMessage.length() - 1).toCharArray()[0];
                System.out.println("initialMessage:" + initialMessage + " message:" + message + " flag:" + flag);
                ProcessCommunication.castMsg(message, this.name);
            }
            outputStream.writeUTF(quitTip);
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 将接收到的转发的消息的内容发给客户端
     *
     * @param msg:转发到的消息
     * @param name:服务器端根据服务及建立的顺序生成的的服务名
     */
    public void sendMsg2Me(String msg, String name) {
        try {

            outputStream.writeUTF(this.name+":" + msg);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}


