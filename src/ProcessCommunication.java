import java.io.IOException;
import java.util.ArrayList;

/**
 * 用于线程交流的类,只提供函数调用,不需要实例化,所以构造函数私有
 *
 * @author Xueji
 */
public class ProcessCommunication {

    private static ArrayList<ProcessServer> stList=new ArrayList<>();
    //存储线程的队列

    private ProcessCommunication() {}

    /**
     * 将创建的线程加入队列
     * @param st 需要加入队列的线程名
     * @throws IOException
     */
    public static void addClient(ProcessServer st) throws IOException {
        stList.add(st);
    }

    /**
     * 转发消息的函数castMsg
     * @param msg 要转发的消息
     * @param name 发出需要转发的消息的线程名
     * @throws IOException
     */
    public static void castMsg(String msg,String name) throws IOException {
        for(int i=0;i<stList.size();i++) {
            ProcessServer st=stList.get(i);
            st.sendMsg2Me(msg,name);
        }
    }
}
