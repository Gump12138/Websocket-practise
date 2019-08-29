package web.socket.service.impl;

import web.socket.service.CxkService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ClassName CxkServiceImpl
 * Package web.socket.service.impl
 * Description
 *
 * @author gmb1995@outlook.com
 * @date 2019-08-28 17:59
 */
public class CxkServiceImpl implements CxkService {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readLineFromConsole() {
        String msg = "123";
        try {
            InputStream in = System.in;
            System.out.println(System.in);
            System.out.println("是否可用：" + in.available());
            while (in.available() == -1) {
                System.out.println("System.in中的值：" + in.read());
                System.out.println("请输入返回给客户端浏览器的消息：");
                msg = reader.readLine();
            }
        } catch (IOException e) {
            msg = "键盘输入错误";
            e.printStackTrace();
        }
        return msg;
    }
}
