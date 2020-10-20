import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *Client用于创建发送从键盘输入的数据的线程
 * @author Xueji
 */
public class ClientSend extends Thread {
    Socket socket;
    DataOutputStream outputStream;
    BufferedReader in= new BufferedReader((new InputStreamReader(System.in)));

    public ClientSend(Socket soc) {
        try {
            socket = soc;
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * while(true)循环,若键盘输入信息,则发送到服务器端
     */
    @Override
    public void run() {
        while (true) {
            try {
                String lrSocketString2 = in.readLine();
                outputStream.writeUTF(lrSocketString2);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }
}



