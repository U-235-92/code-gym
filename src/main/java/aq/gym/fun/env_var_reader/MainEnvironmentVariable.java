package aq.gym.fun.env_var_reader;

public class MainEnvironmentVariable {

	public static void main(String[] args) {
		System.getenv().forEach((k, v) -> System.out.println(k + ": " + v));
	}
}
