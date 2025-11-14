package aq.gym.fun.my_logger.exception;

public class PriorityTestLevelException extends Exception {

	private static final long serialVersionUID = 1L;

	public PriorityTestLevelException() {
        super();
    }

    @Override
    public String toString() {
        String clazz = this.getClass().getSimpleName();
        String msg = "Incorrect test priority level";
        return super.toString() + "\n" + String.format("%s: %s", clazz, msg);
    }
}
