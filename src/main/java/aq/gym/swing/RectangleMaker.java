package aq.gym.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RectangleMaker {

	public static void main(String[] args) {
		View view = new View();
		view.runGUI();
	}

	private static class View {
		
		private static final int WIDTH = 400;
		private static final int HEIGHT = 300;
		private static final int RECTANGLE_WIDTH = 10;
		private static final int RECTANGLE_HEIGHT = 10;
		private List<Rectangle2D> rectangles;
		private JFrame frame;
		private JPanel panel;
		
		public View() {
			rectangles = new ArrayList<>();
		}
		
		void runGUI() {
			SwingUtilities.invokeLater(() -> {
				int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - WIDTH / 2;
				int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - HEIGHT / 2;
				panel = new PaintPanel();
				panel.addMouseListener(getMaouseListener());
				frame = new JFrame("Rectangle maker");
				frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocation(x, y);
				frame.add(panel);
				frame.pack();
				frame.setVisible(true);
				panel.requestFocus();
			});
		}
		
		private MouseAdapter getMaouseListener() {
			return new MouseAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					Rectangle2D currentRectangle2d = findCurrentRectangle(e.getPoint());
					if(currentRectangle2d != null) {
						currentRectangle2d.setFrame(e.getX(), e.getY(), RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
						panel.repaint();
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(findCurrentRectangle(e.getPoint()) != null && e.getClickCount() >= 2) 
						removeRectangle(e);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if(findCurrentRectangle(e.getPoint()) == null) {						
						addRectangle(e);
					}
				}
				
				private Rectangle2D findCurrentRectangle(Point2D p) {
					for(Rectangle2D rectangle2d : rectangles) {
						if(rectangle2d.contains(p))
							return rectangle2d;
					}
					return null;
				}
				
				private void removeRectangle(MouseEvent e) {
					for(int i = 0; i < rectangles.size(); i++) {
						if(rectangles.get(i).contains(e.getX(), e.getY())) {							
							rectangles.remove(i);
							panel.repaint();
						}
					}
				}
				
				private void addRectangle(MouseEvent e) {
					Graphics2D graphics2d = (Graphics2D) panel.getGraphics();
					graphics2d.setColor(Color.RED);
					Rectangle2D rectangle2d = new Rectangle2D.Double(e.getX(), e.getY(), RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
					rectangles.add(rectangle2d);
					panel.repaint();
				}
			};
		}
		
		private class PaintPanel extends JPanel {

			private static final long serialVersionUID = 3920792718895136396L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graphics2d = (Graphics2D) g;
				graphics2d.setColor(Color.RED);
				for(Rectangle2D rectangle2d : rectangles) {
					graphics2d.draw(rectangle2d);
				}
			}
		}
	}
}
