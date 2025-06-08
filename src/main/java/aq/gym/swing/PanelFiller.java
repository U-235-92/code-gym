package aq.gym.swing;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.security.KeyStore;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class PanelFiller {

	public static void main(String[] args) {
		View view = new View();
		view.runGUI();
	}

	private static class View {
		
		@SuppressWarnings("unused")
		void runGUI() {
			SwingUtilities.invokeLater(() -> {
				JFrame frame = new JFrame("PanelFiller");
				JPanel panel = new JPanel();
				JButton redButton = new JButton("Red");
				JButton greenButton = new JButton("Green");
				JButton blueButton = new JButton("Blue");
				redButton.setName("red.panel");
				greenButton.setName("green.panel");
				blueButton.setName("blue.panel");
				redButton.addActionListener(e -> changeColor(Color.RED, panel));
				greenButton.addActionListener(e -> changeColor(Color.GREEN, panel));
				blueButton.addActionListener(e -> changeColor(Color.BLUE, panel));
				AbstractAction redAction = new AbstractAction() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						changeColor(Color.RED, panel);
					}
				};
				AbstractAction greenAction = new AbstractAction() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						changeColor(Color.GREEN, panel);
					}
				};
				AbstractAction blueAction = new AbstractAction() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						changeColor(Color.BLUE, panel);
					}
				};
				KeyStroke redKeyStroke = KeyStroke.getKeyStroke("ctrl r");
				KeyStroke greenKeyStroke = KeyStroke.getKeyStroke("ctrl g");
				KeyStroke blueKeyStroke = KeyStroke.getKeyStroke("ctrl b");
				InputMap inMap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
				inMap.put(redKeyStroke, "red.panel");
				inMap.put(greenKeyStroke, "green.panel");
				inMap.put(blueKeyStroke, "blue.panel");
				ActionMap actMap = panel.getActionMap();
				actMap.put("red.panel", redAction);
				actMap.put("green.panel", greenAction);
				actMap.put("blue.panel", blueAction);
				panel.add(redButton);
				panel.add(greenButton);
				panel.add(blueButton);
				panel.requestFocus();
				int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - frame.getWidth() / 2;
				int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - frame.getHeight() / 2;
				frame.setLocation(x, y);
				frame.add(panel);
				frame.setSize(100, 400);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			});
		}
		
		private void changeColor(Color color, JPanel panel) {
			panel.setBackground(color);
			panel.repaint();
		}
	}
}
