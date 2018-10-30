package util;

public class NoSuchElementsExceptions extends Exception{

	public NoSuchElementsExceptions () {
		super("No existen suficientes elementos para ejecutar la transaccion ");
	}
}
