package store_stock_control_program;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class ProdInfoModify extends JPanel {
	private JTextField productNoField;
	private JTextField originalPriceField;
	private JTextField priceModifyField;

	private DBcon myDBcon;

	private void setDBcon(DBcon dbcon) {
		myDBcon = dbcon;
	}

	public ProdInfoModify(DBcon dbcon) {
		setDBcon(dbcon);
		setLayout(null);

		JLabel Title = new JLabel("상품단가 수정");
		Title.setFont(new Font("굴림", Font.BOLD, 20));
		Title.setBounds(12, 10, 201, 26);
		add(Title);

		TitledBorder Tb = new TitledBorder(new LineBorder(Color.black), "단가수정");
		Tb.setTitleColor(Color.black);

		JPanel priceModifyPanel = new JPanel();
		priceModifyPanel.setBounds(12, 50, 500, 300);
		add(priceModifyPanel);
		priceModifyPanel.setLayout(null);
		priceModifyPanel.setBorder(Tb);

		JLabel productNoLabel = new JLabel("품번 :");
		productNoLabel.setBounds(12, 56, 94, 30);
		productNoLabel.setFont(new Font("굴림", Font.BOLD, 13));
		productNoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceModifyPanel.add(productNoLabel);

		productNoField = new JTextField();
		productNoField.setBounds(64, 56, 163, 30);
		priceModifyPanel.add(productNoField);
		productNoField.setColumns(10);

		JLabel originalPriceLabel = new JLabel("기존 판매단가 :");
		originalPriceLabel.setBounds(12, 130, 100, 30);
		priceModifyPanel.add(originalPriceLabel);
		originalPriceLabel.setFont(new Font("굴림", Font.BOLD, 13));

		originalPriceField = new JTextField();
		originalPriceField.setBounds(118, 130, 148, 30);
		priceModifyPanel.add(originalPriceField);
		originalPriceField.setColumns(10);
		originalPriceField.setEditable(false);

		JButton searchButton = new JButton("조회");
		searchButton.setFont(new Font("굴림", Font.PLAIN, 12));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String price;
				String productNo = productNoField.getText();
				String checkProductNo = "\\d{7}"; // 품번은 7자리로 고정

				boolean isProductNo = Pattern.matches(checkProductNo, productNo);

				if (isProductNo ==false || productNo.isEmpty()) { // 품번이 숫자가 아니거나 공백일경우
					JOptionPane.showMessageDialog(null, "품번은 7자리 숫자로 입력해주세요.");
				} else { // 품번이 공백이 아니면 그 품번 조회
					myDBcon.searchProduct(productNo);
					price = myDBcon.getProductPrice().toString();
					originalPriceField.setText(price);
				}
			}

		});
		searchButton.setBounds(252, 56, 83, 30);
		priceModifyPanel.add(searchButton);

		JLabel priceModifyLabel = new JLabel("변경 판매단가 :");
		priceModifyLabel.setBounds(12, 200, 100, 30);
		priceModifyPanel.add(priceModifyLabel);
		priceModifyLabel.setFont(new Font("굴림", Font.BOLD, 13));
		priceModifyLabel.setHorizontalAlignment(SwingConstants.CENTER);

		priceModifyField = new JTextField();
		priceModifyField.setBounds(118, 200, 148, 30);
		priceModifyPanel.add(priceModifyField);
		priceModifyField.setColumns(10);

		JButton updateButton = new JButton("확인");
		updateButton.setBounds(300, 200, 83, 30);
		priceModifyPanel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String originalPrice = originalPriceField.getText();
				String priceModify = priceModifyField.getText();
				String productNo = productNoField.getText();

				String checkProductNo = "^[1-9]\\d*"; // 변경가격은 숫자만 입력가능하게!(시작숫자 0은불가)

				boolean isProductPrice = Pattern.matches(checkProductNo, priceModify);

				if (originalPrice.isEmpty()) { // 조회한 품번 가격이 공백이면
					JOptionPane.showMessageDialog(null, "변경할 품번을 먼저 조회해주세요.");
				} else { // 조회는 하였으나
					if (priceModify.isEmpty()) { // 변경할려고 하는 가격이 공백이면
						JOptionPane.showMessageDialog(null, "변경할 가격을 입력해주세요.");
					} else if (isProductPrice == false) {
						JOptionPane.showMessageDialog(null, "가격은 숫자로 입력해주세요.");
					} else {
						myDBcon.updatePrice(priceModify, productNo);
						originalPriceField.setText(null);
						priceModifyField.setText(null);
						productNoField.setText(null);
					}
				}

			}
		});
		updateButton.setFont(new Font("굴림", Font.PLAIN, 12));

	}
}
