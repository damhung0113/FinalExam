package GUI;

//import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import B.DetailB;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class EditDetail extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTimeIn;
	private JTextField tfTimeOut;
	private JTextField tfDate;
	private JTextField tfCurrentID;
	private JTextField tfAdminID;
	private JTextField tfUserID;
	private JTextField tfRoomID;
	private JTextField tfPurpose;

	/**
	 * Create the frame.
	 */
	public EditDetail(DetailB b, DetailMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 540, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbEdit = new JLabel("EDIT EXITS DETAIL");
		lbEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lbEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbEdit, "cell 1 0");

		JLabel lbCurrentId = new JLabel("Current ID");
		contentPane.add(lbCurrentId, "cell 0 2,alignx trailing");

		tfCurrentID = new JTextField();
		contentPane.add(tfCurrentID, "cell 1 2,growx");
		tfCurrentID.setColumns(10);

		JLabel lbEnterNewInfor = new JLabel("ENTER NEW DETAIL");
		contentPane.add(lbEnterNewInfor, "cell 1 3");

		JLabel lbTimeIn = new JLabel("Time in");
		lbTimeIn.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbTimeIn, "cell 0 5,alignx trailing");

		tfTimeIn = new JTextField();
		contentPane.add(tfTimeIn, "cell 1 5,growx");
		tfTimeIn.setColumns(10);

		JLabel lbTimeOut = new JLabel("Time out");
		lbTimeOut.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbTimeOut, "cell 0 6,alignx trailing");

		tfTimeOut = new JTextField();
		contentPane.add(tfTimeOut, "cell 1 6,growx");
		tfTimeOut.setColumns(10);

		JLabel lbDate = new JLabel("Date");
		lbDate.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbDate, "cell 0 7,alignx trailing");

		tfDate = new JTextField();
		contentPane.add(tfDate, "cell 1 7,growx");
		tfDate.setColumns(10);

		JLabel lbAdminID = new JLabel("Admin ID");
		lbAdminID.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbAdminID, "cell 0 8,alignx trailing");

		tfAdminID = new JTextField();
		contentPane.add(tfAdminID, "cell 1 8,growx");
		tfAdminID.setColumns(10);

		JLabel lbUserID = new JLabel("User ID");
		lbUserID.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbUserID, "cell 0 9,alignx trailing");

		tfUserID = new JTextField();
		contentPane.add(tfUserID, "cell 1 9,growx");
		tfUserID.setColumns(10);

		JLabel lbRoomID = new JLabel("Lecture Room ID");
		lbRoomID.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbRoomID, "cell 0 10,alignx trailing");

		tfRoomID = new JTextField();
		contentPane.add(tfRoomID, "cell 1 10,growx");
		tfRoomID.setColumns(10);

		JLabel lbPurpose = new JLabel("Purpose");
		contentPane.add(lbPurpose, "cell 0 11,alignx trailing");

		tfPurpose = new JTextField();
		contentPane.add(tfPurpose, "cell 1 11,growx");
		tfPurpose.setColumns(10);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(tfCurrentID.getText());
					Time time_in = Time.valueOf(tfTimeIn.getText());
					Time time_out = Time.valueOf(tfTimeOut.getText());
					int date = Integer.parseInt(tfDate.getText());
					int adminID = Integer.parseInt(tfAdminID.getText());
					int userID = Integer.parseInt(tfUserID.getText());
					int roomID = Integer.parseInt(tfRoomID.getText());
					String purpose = tfPurpose.getText();

					try {
						b.updateDetail(id, time_in, time_out, date, adminID, roomID, userID, purpose);
						JOptionPane.showMessageDialog(rootPane, "Edit UsesDetail successed");
						parent.initModel();
						EditDetail.this.dispose();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}
			}
		});
		contentPane.add(btnOK, "flowx,cell 1 13,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCurrentID.setText("");
				tfTimeIn.setText("");
				tfTimeOut.setText("");
				tfDate.setText("");
				tfAdminID.setText("");
				tfUserID.setText("");
				tfRoomID.setText("");
				tfPurpose.setText("");
			}
		});
		contentPane.add(btnReset, "cell 1 13");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditDetail.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 13");
	}

}
