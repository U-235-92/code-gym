package aq.gym.security.security_manager;

import java.security.Permission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class WordCheckPermission extends Permission {

	private static final long serialVersionUID = 5953496504540885288L;
	private String action;
	@SuppressWarnings("unused")
	private String name;
	public WordCheckPermission(String name, String action) {
		super(name);
		this.action = action;
	}

	@Override
	public boolean implies(Permission other) {
//		if(!(other instanceof WordCheckPermission)) {
//			return false;
//		}
//		var incommingPermission = (WordCheckPermission) other;
//		if(action.equals("insert")) {
//			return incommingPermission.action.equals("insert") && getName().indexOf(incommingPermission.getName()) >= 0;
//		} else if(action.equals("avoid")) {
//			if(incommingPermission.action.equals("avoid")) {
//				return incommingPermission.badWordSet().containsAll(badWordSet());
//			} else if(incommingPermission.action.equals("insert")) {
//				for(String badWord : badWordSet()) {
//					if(incommingPermission.getName().indexOf(badWord) >= 0) {
//						return false;
//					}
//				}
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			return false;
//		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!obj.getClass().equals(getClass())) {
			return false;
		}
		var permission = (WordCheckPermission) obj;
		if(!Objects.equals(action, permission.action)) {
			return false;
		}
		if("insert".equals(action)) {
			return Objects.equals(getName(), permission.getName());
		} else if("avoid".equals(action)) {
			return badWordSet().equals(permission.badWordSet());
		} else {
			return false;
		}
	}

	private Set<String> badWordSet() {
		Set<String> set = new HashSet<>();
		set.addAll(Arrays.asList(getName().split(",")));
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), action);
	}

	@Override
	public String getActions() {
		return action;
	}

}
