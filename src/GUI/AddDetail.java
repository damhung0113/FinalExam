package GUI;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import B.DetailB;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class AddDetail extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfTimeIn;
	private JTextField tfDate;
	private JTextField tfAdminID;
	private JTextField tfUserID;
	private JTextField tfRoomID;
	private JTextField tfPurpose;
	private JTextField tfTimeOut;

	/**
	 * Create the frame.
	 */
	public AddDetail(DetailB b, DetailMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 688, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbAdd = new JLabel("ADD NEW DETAIL");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbAdd, "cell 1 0");

		JLabel lblId = new JLabel("ID ");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblId, "cell 0 2,alignx trailing");

		tfID = new JTextField();
		contentPane.add(tfID, "cell 1 2,growx");
		tfID.setColumns(10);

		JLabel lblLocation = new JLabel("Time in");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLocation, "cell 0 3,alignx trailing");

		tfTimeIn = new JTextField();
		contentPane.add(tfTimeIn, "cell 1 3,growx");
		tfTimeIn.setColumns(10);

		JLabel lblType = new JLabel("Time out");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, "cell 0 4,alignx trailing");

		tfTimeOut = new JTextField();
		contentPane.add(tfTimeOut, "cell 1 4,growx");
		tfTimeOut.setColumns(10);

		tfDate = new JTextField();
		contentPane.add(tfDate, "cell 1 5,growx");
		tfDate.setColumns(10);

		JLabel lblCapacity = new JLabel("Admin ID");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 6,alignx trailing");

		tfAdminID = new JTextField();
		contentPane.add(tfAdminID, "cell 1 6,growx");
		tfAdminID.setColumns(10);

		JLabel lblAirconditional = new JLabel("User ID");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 7,alignx trailing");

		tfUserID = new JTextField();
		contentPane.add(tfUserID, "cell 1 7,growx");
		tfUserID.setColumns(10);

		JLabel lbRoomID = new JLabel("Lecture Room ID");
		lbRoomID.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbRoomID, "cell 0 8,alignx trailing");

		tfRoomID = new JTextField();
		contentPane.add(tfRoomID, "cell 1 8,growx");
		tfRoomID.setColumns(10);

		JLabel lblScreen = new JLabel("Purpose");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 9,alignx trailing");

		tfPurpose = new JTextField();
		contentPane.add(tfPurpose, "cell 1 9,growx");
		tfPurpose.setColumns(10);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(tfID.getText());
					Time time_in = Time.valueOf(tfTimeIn.getText());
					Time time_out = Time.valueOf(tfTimeOut.getText());
					int date = Integer.parseInt(tfDate.getText());
					int adminID = Integer.parseInt(tfAdminID.getText());
					int userID = Integer.parseInt(tfUserID.getText());
					int roomID = Integer.parseInt(tfRoomID.getText());
					String purpose = tfPurpose.getText();

					try {
						if (!b.checkID(id)) {
							if (b.checkPK(adminID, userID, roomID)) {
								b.addDetail(id, time_in, time_out, date, adminID, roomID, userID, purpose);
								JOptionPane.showMessageDialog(rootPane, "Add UsesDetail successed");
								parent.initModel();
								AddDetail.this.dispose();
							} else
								JOptionPane.showMessageDialog(rootPane, "SQL PrimaryKey Syntax");
						} else
							JOptionPane.showMessageDialog(rootPane,
									"This ID has already been taken, please choose another");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}
			}
		});
		contentPane.add(btnAdd, "cell 1 13,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfID.setText("");
				tfTimeIn.setText("");
				tfTimeOut.setText("");
				tfDate.setText("");
				tfAdminID.setText("");
				tfUserID.setText("");
				tfRoomID.setText("");
				tfPurpose.setText("");
			}
		});
		contentPane.add(btnReset, "cell 1 13,alignx trailing");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDetail.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 13,alignx trailing");

		JLabel lbDate = new JLabel("Date");
		contentPane.add(lbDate, "cell 0 5,alignx trailing");
	}

}
