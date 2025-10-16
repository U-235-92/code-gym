package aq.gym.security.security_manager;

import javax.swing.JTextArea;

public class WordCheckTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8756387159656852310L;

	@Override
	public void append(String str) {
		WordCheckPermission p = new WordCheckPermission(str, "insert");
		SecurityManager manager = System.getSecurityManager();
		if(manager != null) {
			manager.checkPermission(p);
		}
		super.append(str);
	}
}
