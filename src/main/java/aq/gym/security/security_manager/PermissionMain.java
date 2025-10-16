package aq.gym.security.security_manager;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PermissionMain {

	public static void main(String[] args) {
		System.setProperty("java.security.policy", "src/main/java/aq/gym/security/security_manager/permission_test.policy");
		System.setSecurityManager(new SecurityManager());		
		EventQueue.invokeLater(() -> {
			var frame = new PermissionTestFrame();
			frame.setTitle("Permission test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
