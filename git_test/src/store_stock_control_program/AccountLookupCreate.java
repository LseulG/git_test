package store_stock_control_program;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AccountLookupCreate extends JPanel implements ActionListener {
	private JTable searchAccountTable;

	private DBcon myDBcon;
	private JTextField idField;
	private JTextField passwordField;
	private JTextField storeNameField;
	private JTextField personNameField;
	private JTextField phoneField;
	private JTextField managerField;
	private JButton createButton;
	private JRadioButton[] radioButton1 = new JRadioButton[2];
	private JRadioButton[] radioButton2 = new JRadioButton[3];
	String[] radioText1 = { "����", "����" };
	String[] radioText2 = { "����+����", "����", "����" };
	String radio1 = "����";
	String radio2 = "����+����";

	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public AccountLookupCreate(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		JLabel createAccountLabel = new JLabel("���� ����");
		createAccountLabel.setFont(new Font("����", Font.BOLD, 20));
		createAccountLabel.setBounds(12, 10, 99, 32);
		add(createAccountLabel);

		JPanel radiobuttonPanel1 = new JPanel();
		radiobuttonPanel1.setBounds(323, 10, 183, 32);
		add(radiobuttonPanel1);

		ButtonGroup radioGroup1 = new ButtonGroup();
		for (int i = 0; i < radioButton1.length; i++) {
			radioButton1[i] = new JRadioButton(radioText1[i]);
			radioGroup1.add(radioButton1[i]);
			radiobuttonPanel1.add(radioButton1[i]);
			radioButton1[i].addActionListener(this);
		}
		radioButton1[0].setSelected(true);

		radiobuttonPanel1.setVisible(true);

		createButton = new JButton("����");
		createButton.addActionListener(this);
		createButton.setBounds(518, 17, 70, 25);
		add(createButton);

		JPanel createAccountPanel = new JPanel();
		createAccountPanel.setBounds(12, 52, 600, 58);
		add(createAccountPanel);
		createAccountPanel.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel idLabel = new JLabel("ID *");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(idLabel);

		JLabel passwardLabel = new JLabel("PW *");
		passwardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(passwardLabel);

		JLabel storeNameLabel = new JLabel("����� *");
		storeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(storeNameLabel);

		JLabel personNameLabel = new JLabel("�̸� *");
		personNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(personNameLabel);

		JLabel phoneLabel = new JLabel("��ȭ��ȣ");
		phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(phoneLabel);

		JLabel managerLabel = new JLabel("����� ID(����)*");
		managerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountPanel.add(managerLabel);

		idLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		passwardLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		storeNameLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		personNameLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		phoneLabel.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		managerLabel.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

		idField = new JTextField();
		createAccountPanel.add(idField);
		idField.setColumns(10);

		passwordField = new JTextField();
		createAccountPanel.add(passwordField);
		passwordField.setColumns(10);

		storeNameField = new JTextField();
		createAccountPanel.add(storeNameField);
		storeNameField.setColumns(10);

		personNameField = new JTextField();
		createAccountPanel.add(personNameField);
		personNameField.setColumns(10);

		phoneField = new JTextField();
		createAccountPanel.add(phoneField);
		phoneField.setColumns(10);

		managerField = new JTextField();
		createAccountPanel.add(managerField);
		managerField.setColumns(10);

		JLabel searchAccountLabel = new JLabel("���� ��ȸ");
		searchAccountLabel.setFont(new Font("����", Font.BOLD, 20));
		searchAccountLabel.setBounds(12, 120, 99, 32);
		add(searchAccountLabel);

		JScrollPane searchAccountscrollPane = new JScrollPane();
		searchAccountscrollPane.setBounds(12, 162, 600, 216);
		add(searchAccountscrollPane);

		searchAccountTable = new JTable() {
			public boolean isCellEditable(int row, int col) {
				return false; // ���̺� ���� ���ϰ�
			}
		};
//		table_1.setEnabled(false); // �����Ұ�
		searchAccountTable.getTableHeader().setReorderingAllowed(false); // �̵��Ұ�
		searchAccountTable.getTableHeader().setResizingAllowed(false); // ũ������ �Ұ�

		 
		searchAccountTable.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "����", "ID", "�����", "����� �̸�", "��ȭ��ȣ" }));
		searchAccountscrollPane.setViewportView(searchAccountTable);
		searchAccountTable.setRowHeight(30);

		JButton searchButton = new JButton("��ȸ");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDBcon.clear(searchAccountTable); // ���̺� �ʱ�ȭ
				myDBcon.searchAccount(searchAccountTable, radio2); // ��ȸ
				System.out.println(radio2);
			}
		});
		searchButton.setBounds(413, 126, 70, 25);
		add(searchButton);

		JPanel radiobuttonPanel2 = new JPanel();
		radiobuttonPanel2.setBounds(155, 120, 246, 32);
		add(radiobuttonPanel2);
		

		
		ButtonGroup radioGroup2 = new ButtonGroup();
		for (int i = 0; i < radioButton2.length; i++) {
			radioButton2[i] = new JRadioButton(radioText2[i]);
			radioGroup2.add(radioButton2[i]);
			radiobuttonPanel2.add(radioButton2[i]);
			radioButton2[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ���� ��ȸ�κ� ������ư Action
					String es1 = e.getActionCommand();
					if (es1.equals(radioButton2[0].getText())) { //���� ��ư ����+����
						radio2 = es1;
					} else if (es1.equals(radioButton2[1].getText())) { // ������ư ����
						radio2 = es1;
					} else if (es1.equals(radioButton2[2].getText())) { // ������ư ����
						radio2 = es1;
					}

				}
			});
		}
		radioButton2[0].setSelected(true);
		radiobuttonPanel2.setVisible(true);

		searchAccountTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		searchAccountTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		searchAccountTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		DefaultTableModel tableModel = (DefaultTableModel) searchAccountTable.getModel();

		JButton deleteButton = new JButton("���� ����");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(searchAccountTable.getSelectedRow() >= 0) {
					int row = searchAccountTable.getSelectedRow();
					String storeGroup = (String) searchAccountTable.getValueAt(row, 0);
					String id = (String) searchAccountTable.getValueAt(row, 1);
					String user = myDBcon.getLoginUser();
					
					myDBcon.checkCode(id);
					String checkStoreCode = myDBcon.getStoreCode(); 
					if(user.equals(checkStoreCode)) {
						JOptionPane.showMessageDialog(null, "���� �������� ������ ���� �� �����ϴ�.");
					}else {
						if(storeGroup.equals("����")) {
							myDBcon.deleteManagerAccount(id, storeGroup);
							tableModel.removeRow(searchAccountTable.getSelectedRow());
							JOptionPane.showMessageDialog(null, "�����Ͽ����ϴ�.");							
						}else {
							JOptionPane.showMessageDialog(null, "��������� ������ �Ұ����մϴ�.");
						}
					}					
				}else {
					JOptionPane.showMessageDialog(null, "���� �������ּ���.");
				}
				
			}
		});
		deleteButton.setBounds(508, 126, 104, 25);
		add(deleteButton);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) { // ������ư ����
		// ���� �����κ� ������ư Action
		String es = e.getActionCommand();
		if (es.equals(radioButton1[0].getText())) { // ������ư���� ���� ���ý�
			this.radio1 = es;
			managerField.setEditable(true); // ����� �ʵ� �Է°���
		} else if (es.equals(radioButton1[1].getText())) { // ������ư���� ���� ���ý�
			this.radio1 = es;
			managerField.setText(null); // ����� �ʵ� �ʱ�ȭ
			managerField.setEditable(false); // ����� �ʵ� �Է� �Ұ���
		}

		if (e.getSource() == createButton) {

			
			String id = idField.getText();
			String password = passwordField.getText();
			String storeName = storeNameField.getText();
			String personName = personNameField.getText();
			String phone = phoneField.getText();
			String manage = managerField.getText();
			


			if (radio1.equals("����")) { // ����
				if(id.isEmpty() || storeName.isEmpty() || personName.isEmpty()) { // �ʼ������� �����̸�
					JOptionPane.showMessageDialog(null, "�ʼ�����(*)�� �Է����ּ���.");
				}else { // �ʼ������� ������ �ƴϸ�
					myDBcon.createAccount(id, password, personName, phone, storeName, id, radio1);					
				}

			} else { // ����
				if(id.isEmpty() || storeName.isEmpty() || personName.isEmpty() || manage.isEmpty()) { // �ʼ������� �����̸�
					JOptionPane.showMessageDialog(null, "�ʼ�����(*)�� �Է����ּ���.");
				}else { // �ʼ������� �����̾ƴϸ�
					myDBcon.createAccount(id, password, personName, phone, storeName, manage, radio1);					
				}
			}

		}

	}
	
}