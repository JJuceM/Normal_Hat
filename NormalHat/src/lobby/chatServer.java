package lobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// ������ſ� ���� �ڵ�
public class chatServer extends Thread {
    static ArrayList<Socket> list = new ArrayList<Socket>(); // ���� Ȯ�ο�
    static Socket socket = null;
//    private String nickname;
    //    private final String nickname = null;
    String name = null; // Ŭ���̾�Ʈ �̸� ������;
    public chatServer(Socket socket) {
        this.socket = socket; // ���� socket�� �Ҵ�
        list.add(socket); // ������ list�� �߰�
//        name = this.nickname;
    }
    // Thread ���� start() �޼ҵ� ��� �� �ڵ����� �ش� �޼ҵ� ���� (Thread���� ������ ����)
    public void run() {
        try {
            // ���� Ȯ�ο�
            System.out.println("���� : " + socket.getInetAddress()
                    + " IP�� Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");

            // InputStream - Ŭ���̾�Ʈ���� ���� �޼��� �б�
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // OutputStream - �������� Ŭ���̾�Ʈ�� �޼��� ������
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            // Ŭ���̾�Ʈ���� ����Ǿ��ٴ� �޼��� ������
            writer.println("������ ����Ǿ����ϴ�! ID�� �Է��� �ּ���!");

            String readValue; // Client���� ���� �� ����
//            String name = null; // Ŭ���̾�Ʈ �̸� ������
            name = lobby.getChatname();
//            boolean banner = false;
            writer.println(name + "���� �����ϼ̽��ϴ�.");
            // Ŭ���̾�Ʈ�� �޼��� �Է½ø��� ����
            while((readValue = reader.readLine()) != null ) {
//                if(!banner) { // ���� �� �ѹ��� ����
//                    name = readValue; // �̸� �Ҵ�
//                    banner = true;
//                    continue;
//                }

                // list �ȿ� Ŭ���̾�Ʈ ������ �������
                for(int i = 0; i<list.size(); i++) {
                    out = list.get(i).getOutputStream();
                    writer = new PrintWriter(out, true);
                    // Ŭ���̾�Ʈ���� �޼��� �߼�
                    writer.println(name + " : " + readValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // ����ó��
        }
    }

    public static void main(String[] args) {
        try {
            int socketPort = 2030; // ���� ��Ʈ ������
            ServerSocket serverSocket = new ServerSocket(socketPort); // ���� ���� �����
            // ���� ���� Ȯ�ο�
            System.out.println("socket : " + socketPort + "���� ������ ���Ƚ��ϴ�");

            // ���� ������ ����� ������ ���ѷ���
            while(true) {
                Socket socketUser = serverSocket.accept(); // ������ Ŭ���̾�Ʈ ���� ��
                // Thread �ȿ� Ŭ���̾�Ʈ ������ �����
                Thread thd = new chatServer(socketUser);
                thd.start(); // Thread ����
            }

        } catch (IOException e) {
            e.printStackTrace(); // ����ó��
        }

    }

}