package aq.gym.fun.my_logger.exception;

public class NoSingleAnnotationException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSingleAnnotationException() {
        super();
    }

    @Override
    public String toString() {
        String clazz = this.getClass().getSimpleName();
        String msg = "No single annotation";
        return super.toString() + "\n" + String.format("%s: %s", clazz, msg);
    }
}
