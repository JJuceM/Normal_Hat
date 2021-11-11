package startmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class StartMenu_backupjjambbong extends JFrame  {

	JPanel panel;
	JButton login;
	JButton join;
	JButton end;
	JTextField login_id;
	JPasswordField login_pw;
	JLabel msg;
	Font font = new Font("Neo둥근모",Font.BOLD,24);


	public StartMenu_backupjjambbong() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1100, 800);
		this.setLocationRelativeTo(null); // 창 중앙에뜨기
		this.setTitle("Nomal Hat");
		this.setResizable(false);	//	창 크기 고정
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				ImageIcon img = new ImageIcon("images/title/title.png");
				Dimension d = getSize();
				g.drawImage(img.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel msg = new JLabel();
		msg.setBackground(Color.white);
		msg.setOpaque(true);		// 레이블 백그라운드 색 허용하겠다는 뜻
		msg.setBounds(225,520,625,50);
		msg.setHorizontalAlignment(JLabel.CENTER);
		msg.setFont(font);
		panel.add(msg);
		
		ImageIcon login_i = new ImageIcon("images/title/login.png");
		ImageIcon login_i_mouse = new ImageIcon("images/title/login_mouse.png");
		ImageIcon login_i_click = new ImageIcon("images/title/login_click.png");
		login = new JButton(login_i); // 로그인에 iogin_i 넣을 예정
		login.setBorderPainted(false);		//로그인 박스 테두리 없애는 기능
		login.setContentAreaFilled(false);	//로그인 박스 색깔 없애는 기능
		login.setBounds(720, 340, login_i.getIconWidth(), login_i.getIconHeight());
		login.setFocusPainted(false);		//로그인 글자 테두리 없애는 기능
		login.setCursor(new Cursor(Cursor.HAND_CURSOR));	// 로그인쪽에 커서 갖다대면 손모양으로 바뀜
		panel.add(login);
		login.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				login.setPressedIcon(login_i_click);
				msg.setText("로그인 성공!");
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				login.setIcon(login_i_mouse);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				login.setIcon(login_i);
				
			}
			
		});
		
		
		ImageIcon join_i = new ImageIcon("images/title/join.png");
		ImageIcon join_i_mouse = new ImageIcon("images/title/join_mouse.png");
		ImageIcon join_i_click = new ImageIcon("images/title/join_click.png");
		join = new JButton(join_i); 
		join.setBounds(720,415,join_i.getIconWidth(), join_i.getIconHeight());
		join.setBorderPainted(false);		//로그인 박스 테두리 없애는 기능
		join.setContentAreaFilled(false);	//로그인 박스 색깔 없애는 기능
		join.setFocusPainted(false);
		join.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(join);
		join.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				join.setPressedIcon(join_i_click);
				msg.setText("회원가입 페이지로 이동합니다.");
				StartMenu_backupjjambbong.this.setVisible(false);
				new SignupFrame();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				join.setIcon(join_i_mouse);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				join.setIcon(join_i);
				
			}
			
		});
		
		ImageIcon end_i = new ImageIcon("images/title/end.png");
		ImageIcon end_i_mouse = new ImageIcon("images/title/end_mouse.png");
		ImageIcon end_i_click = new ImageIcon("images/title/end_click.png");
		end = new JButton(end_i);
		end.setBounds(450,595,end_i.getIconWidth(),end_i.getIconHeight());
		end.setBorderPainted(false);
		end.setContentAreaFilled(false);
		end.setFocusPainted(false);
		end.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(end);
		end.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				end.setPressedIcon(end_i_click);
				System.exit(0);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				end.setIcon(end_i_mouse);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				end.setIcon(end_i);
				
			}
			
		});
		
		
		JTextField login_id = new JTextField();
		login_id.setBackground(Color.white);
		login_id.setBounds(335,355,390,50);
		panel.add(login_id);
		
		JPasswordField login_pw = new JPasswordField(10);
		login_pw.setBackground(Color.white);
		login_pw.setBounds(335,430,390,50);
		login_pw.setEchoChar('*'); // 비밀번호 입력시 별모양 출력
		panel.add(login_pw);
		 
//		login_pw.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				isLoginCheck();
//				
//			}
//
//			private void isLoginCheck() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			
//		});
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new StartMenu_backupjjambbong();
	}
	
}




