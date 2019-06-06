package GUI;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import B.LectureroomB;
import Object.Room;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddRoom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfLocation;
	private JTextField tfType;
	private JTextField tfCapacity;
	private JTextField tfNote;
	private Room room;

	public Room getRoom() {
		return room;
	}

	/**
	 * Create the frame.
	 */
	public AddRoom(LectureroomB b, RoomMgmt parent) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 650, 527, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][]"));
		setContentPane(contentPane);

		JLabel lbAdd = new JLabel("ADD NEW LECTURE ROOM");
		lbAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lbAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lbAdd, "cell 1 0");

		JLabel lblId = new JLabel("ID ");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblId, "cell 0 2,alignx trailing");

		tfID = new JTextField();
		contentPane.add(tfID, "cell 1 2,growx");
		tfID.setColumns(10);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLocation, "cell 0 3,alignx trailing");

		tfLocation = new JTextField();
		contentPane.add(tfLocation, "cell 1 3,growx");
		tfLocation.setColumns(10);

		JLabel lblType = new JLabel("Type");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblType, "cell 0 4,alignx trailing");

		tfType = new JTextField();
		contentPane.add(tfType, "cell 1 4,growx");
		tfType.setColumns(10);

		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCapacity, "cell 0 5,alignx trailing");

		tfCapacity = new JTextField();
		contentPane.add(tfCapacity, "cell 1 5,growx");
		tfCapacity.setColumns(10);

		JLabel lblAirconditional = new JLabel("Air-Conditional");
		lblAirconditional.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAirconditional, "cell 0 6");

		JCheckBox chbAirC = new JCheckBox("");
		chbAirC.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(chbAirC, "cell 1 6");

		JLabel lblNewLabel = new JLabel("Projector");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 0 7");

		JCheckBox chbProjector = new JCheckBox("");
		contentPane.add(chbProjector, "cell 1 7");

		JLabel lblScreen = new JLabel("Screen");
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScreen, "cell 0 8");

		JCheckBox chbScreen = new JCheckBox("");
		contentPane.add(chbScreen, "cell 1 8");

		JLabel lblFan = new JLabel("Fan");
		contentPane.add(lblFan, "cell 0 9");

		JCheckBox chbFan = new JCheckBox("");
		chbFan.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(chbFan, "cell 1 9");

		JLabel lblNote = new JLabel("Note");
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNote, "cell 0 10,alignx trailing");

		tfNote = new JTextField();
		contentPane.add(tfNote, "cell 1 10,growx");
		tfNote.setColumns(10);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int id = Integer.parseInt(tfID.getText());
					String location = tfLocation.getText();
					int capacity = Integer.parseInt(tfCapacity.getText());
					String type = tfType.getText();
					boolean airConditioner = chbAirC.isSelected();
					boolean projector = chbProjector.isSelected();
					boolean screen = chbScreen.isSelected();
					boolean fan = chbFan.isSelected();
					String note = tfNote.getText();

					try {
						if (!b.checkID(id)) {
						b.addRoom(id, location, type, capacity, airConditioner, projector, fan, screen, note);
						JOptionPane.showMessageDialog(rootPane, "Add room successed");
						parent.initModel();
						AddRoom.this.dispose();
						} else JOptionPane.showMessageDialog(rootPane, "This ID has already been taken, please choose another");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(rootPane, "Invalid input type");
				}
			}
		});
		contentPane.add(btnAdd, "cell 1 12,alignx trailing");

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfID.setText("");
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
		contentPane.add(btnReset, "cell 1 12,alignx trailing");

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoom.this.dispose();
			}
		});
		contentPane.add(btnCancel, "cell 1 12,alignx trailing");
	}

}
