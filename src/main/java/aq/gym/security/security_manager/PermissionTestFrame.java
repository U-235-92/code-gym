package aq.gym.security.security_manager;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PermissionTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7646509978193378563L;
	
	private JTextField textField;
	private WordCheckTextArea textArea;
	private static final int TEXT_ROWS = 20;
	private static final int TEXT_COLUMNS = 60;
	
	public PermissionTestFrame() {
		textField = new JTextField(20);
		var panel = new JPanel();
		panel.add(textField);
		var openButton = new JButton("Insert");
		panel.add(openButton);
		openButton.addActionListener(event -> {
			insertWords(textField.getText());
		});
		add(panel, BorderLayout.NORTH);
		textArea = new WordCheckTextArea();
		textArea.setRows(TEXT_ROWS);
		textArea.setColumns(TEXT_COLUMNS);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		pack();
	}

	private void insertWords(String text) {
		try {
			textArea.append(text + "\n");
		} catch(SecurityException e) {
			JOptionPane.showMessageDialog(this, "I'm sorry, but I cannot do that");
			e.printStackTrace();
		}
	}
}
