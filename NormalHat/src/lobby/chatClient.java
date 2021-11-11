package lobby;

import java.io.IOException;
import java.net.Socket;

// ������ſ� Ŭ���̾�Ʈ �κ�
public class chatClient {

    public static void main(String[] args) {
        try {
            Socket socket = null;
            // ���� ������ ����
            socket = new Socket("127.0.0.1", 2030);
            System.out.println("������ ���� ����!"); // ���� Ȯ�ο�

            // �������� ���� �޼��� �д� Thread
            ListenThread t1 = new ListenThread(socket);
            writeThread t2 = new writeThread(socket); // ������ �޼��� ������ Thread

            t1.start(); // ListeningThread Start
            t2.start(); // WritingThread Start

        } catch (IOException e) {
            e.printStackTrace(); // ����ó��
        }
    }
}