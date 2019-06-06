package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame(LoginFrame lf) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 960, 620);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem mntnUsername = new JMenuItem("");
		mntnUsername.setEnabled(false);
		mntnUsername.setText(lf.getName());
		mnUser.add(mntnUsername);

		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoginFrame().setVisible(true);
				MainFrame.this.setVisible(false);
			}
		});
		mnUser.add(mntmLogOut);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));

		JButton btnUser = new JButton("USER MANAGEMENT");
		btnUser.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnUser.setForeground(Color.RED);
		btnUser.setBackground(Color.CYAN);
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMgmt UM;
				try {
					UM = new UserMgmt(lf.isAdmin());
					UM.setVisible(true);
					UM.enable(lf.isAdmin());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnUser);

		JButton btnRoom = new JButton("LECTUREROOM MANAGEMENT");
		btnRoom.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnRoom.setForeground(Color.RED);
		btnRoom.setBackground(Color.CYAN);
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomMgmt RM;
				try {
					RM = new RoomMgmt();
					RM.setVisible(true);
					RM.enable(lf.isAdmin());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnRoom);

		JButton btnDetail = new JButton("USESDETAIL MANAGEMENT");
		btnDetail.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnDetail.setForeground(Color.RED);
		btnDetail.setBackground(Color.CYAN);
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailMgmt DM;
				try {
					DM = new DetailMgmt();
					DM.setVisible(true);
					DM.enable(lf.isAdmin());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnDetail);
	}
}
