package project;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;

public class SalesStatus extends JPanel {
	JLabel lab, dateLab, totalLab;
	DefaultTableModel firstTabModel;
	JTable firstTab;
	JScrollPane firstSc;
	private JComboBox<String> dateComboBox;
	private JButton btnSearch;

	String date[] = { "2018-09", "2018-10", "2018-11", "2018-12" };

	public SalesStatus() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 1
		JPanel p1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) p1.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(p1);
		lab = new JLabel("�Ǹ���Ȳ");
		p1.add(lab);
		lab.setFont(new Font("����", Font.PLAIN, 18));

		// 2
		JPanel p2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) p2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		add(p2);

		dateLab = new JLabel("�� ��");
		dateLab.setBounds(100, 100, 450, 300);
		p2.add(dateLab);

		dateComboBox = new JComboBox(date);
		p2.add(dateComboBox);

		btnSearch = new JButton("��ȸ");
		p2.add(btnSearch);

		// 3
		String firstTabName[] = { "����", "����", "����", "�ܰ��ݾ�", "���Ǹűݾ�", "�����ݾ�(���Ǹ�)" };
		Object firstData[][] = { { "2018-09-01", "��", "30", "748,600", "748,600", "748,600" },
				{ "2018-09-02", "��", "25", "668,500", "657,500", "1,406,100" } };
		firstTabModel = new DefaultTableModel(firstData, firstTabName) {
			public boolean isCellEditable(int row, int col) {
				return false; // ���̺� ���� ���ϰ�
			}
		};
		firstTab = new JTable(firstTabModel);
		firstTab.getTableHeader().setReorderingAllowed(false); // �� �̵� �Ұ�
		firstSc = new JScrollPane(firstTab);
		add(firstSc);

		// 4
		String te = "1,406,100";
		JPanel p3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) p3.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		add(p3);
		totalLab = new JLabel("�� �� : ");
		totalLab.setText("�� ��: " + te);
		p3.add(totalLab);

		// table center align
		DefaultTableCellRenderer tCellRenderer = new DefaultTableCellRenderer();
		tCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		TableColumnModel t1ColModel = firstTab.getColumnModel();

		for (int i = 0; i < t1ColModel.getColumnCount(); i++)
			t1ColModel.getColumn(i).setCellRenderer(tCellRenderer);
	}
}