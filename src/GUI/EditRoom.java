package GUI;

//import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import B.LectureroomB;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EditRoom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tfLocation;
	private JTextField tfType;
	private JTextField tfCapacity;
	private JTextField tfNote;
	private JTextField tfCurrentID;

	/**
	 * Create the frame.
	 */
	public EditRoom(LectureroomB b, RoomMgmt parent) {
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

		JLabel lbEdit = new JLabel("EDIT EXITS LECTURE ROOM");
		lbEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lbEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbEdit, "cell 1 0");

		JLabel lbCurrentId = new JLabel("Current ID");
		contentPane.add(lbCurrentId, "cell 0 2,alignx trailing");

		tfCurrentID = new JTextField();
		contentPane.add(tfCurrentID, "cell 1 2,growx");
		tfCurrentID.setColumns(10);

		JLabel lbEnterNewInfor = new JLabel("ENTER NEW LECTURE ROOM' INFORMATIONS");
		contentPane.add(lbEnterNewInfor, "cell 1 3");

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLocation, "cell 0 5,alignx trailing");

		tfLocation = new JTextField();
		contentPane.add(tfLocation, "cell 1 5,growx");
		tfLocation.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, "cell 0 6,alignx trailing");

		tfType = new JTextField();
		contentPane.add(tfType, "cell 1 6,growx");
		tfType.setColumns(10);

		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 7,alignx trailing");

		tfCapacity = new JTextField();
		contentPane.add(tfCapacity, "cell 1 7,growx");
		tfCapacity.setColumns(10);

		JLabel lblAirconditional = new JLabel("Air-Conditional");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 8");

		JCheckBox chbAirC = new JCheckBox("");
		chbAirC.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(chbAirC, "cell 1 8");

		JLabel lblNewLabel = new JLabel("Projector");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 0 9");

		JCheckBox chbProjector = new JCheckBox("");
		contentPane.add(chbProjector, "cell 1 9");

		JLabel lblScreen = new JLabel("Screen");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 10");

		JCheckBox chbScreen = new JCheckBox("");
		contentPane.add(chbScreen, "cell 1 10");

		JLabel lblFan = new JLabel("Fan");
		contentPane.add(lblFan, "cell 0 11");

		JCheckBox chbFan = new JCheckBox("");
		chbFan.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(chbFan, "cell 1 11");

		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNote, "cell 0 12,alignx trailing");

		tfNote = new JTextField();
		contentPane.add(tfNote, "cell 1 12,growx");
		tfNote.setColumns(10);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(tfCurrentID.getText());
					String location = tfLocation.getText();
					int capacity = Integer.parseInt(tfCapacity.getText());
					String type = tfType.getText();
					boolean airConditioner = chbAirC.isSelected();
					boolean projector = chbProjector.isSelected();
					boolean screen = chbScreen.isSelected();
					boolean fan = chbFan.isSelected();
					String note = tfNote.getText();
					try {
						b.editRoom(id, location, type, capacity, airConditioner, projector, fan, screen, note);
						JOptionPane.showMessageDialog(rootPane, "Edit room successed");
						parent.initModel();
						EditRoom.this.dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}

			}
		});
		contentPane.add(btnOK, "flowx,cell 1 13,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCapacity.setText("");
				tfLocation.setText("");
				tfType.setText("");
				tfNote.setText("");
				chbAirC.setSelected(false);
				chbProjector.setSelected(false);
				chbScreen.setSelected(false);
				chbFan.setSelected(false);
			}
		});
		contentPane.add(btnReset, "cell 1 13");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditRoom.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 13");
	}

}
