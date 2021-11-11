package lobby;

import java.io.IOException;
import java.net.Socket;

// 소켓통신용 클라이언트 부분
public class chatClient {

    public static void main(String[] args) {
        try {
            Socket socket = null;
            // 소켓 서버에 접속
            socket = new Socket("127.0.0.1", 2030);
            System.out.println("서버에 접속 성공!"); // 접속 확인용

            // 서버에서 보낸 메세지 읽는 Thread
            ListenThread t1 = new ListenThread(socket);
            writeThread t2 = new writeThread(socket); // 서버로 메세지 보내는 Thread

            t1.start(); // ListeningThread Start
            t2.start(); // WritingThread Start

        } catch (IOException e) {
            e.printStackTrace(); // 예외처리
        }
    }
}