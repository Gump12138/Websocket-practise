package web.socket.service;

import org.springframework.stereotype.Service;

/**
 * ClassName CxkService
 * Package web.socket.service
 * Description
 *
 * @author gmb1995@outlook.com
 * @date 2019-08-28 17:56
 */

public interface CxkService {

    /**
     * 键盘输入:模拟用户消息
     * @return 从控制台收到的信息
     */
    String readLineFromConsole();

}
