package SistemaBancario.dominio;

public  class Cuenta  {
	private String numeroCuenta;
	private String rutTitular;
	private String contrase�aCuenta;
	private long saldo;
	private static boolean estado=true;
	private static int minimoMontoTrans=5000;
	private static int montoGiro=500;
	
	
	public Cuenta(String numeroCuenta, String rutTitular, String contrase�aCuenta, long saldo) {
		this.numeroCuenta = numeroCuenta;
		this.rutTitular = rutTitular;
		this.contrase�aCuenta = contrase�aCuenta;
		this.saldo = saldo;
	}
	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	
	
		

	/**
	 * @return the minimoMontoTrans
	 */
	public static int getMinimoMontoTrans() {
		return minimoMontoTrans;
	}
	/**
	 * @param minimoMontoTrans the minimoMontoTrans to set
	 */
	public static void setMinimoMontoTrans(int minimoMontoTrans) {
		Cuenta.minimoMontoTrans = minimoMontoTrans;
	}
	/**
	 * @return the montoGiro
	 */
	public static int getMontoGiro() {
		return montoGiro;
	}
	/**
	 * @param montoGiro the montoGiro to set
	 */
	public static void setMontoGiro(int montoGiro) {
		Cuenta.montoGiro = montoGiro;
	}
	/**
	 * @return the estado
	 */
	public boolean getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	/**
	 * @return the rutTitular
	 */
	public String getRutTitular() {
		return rutTitular;
	}
	/**
	 * @param rutTitular the rutTitular to set
	 */
	public void setRutTitular(String rutTitular) {
		this.rutTitular = rutTitular;
	}
	/**
	 * @return the contrase�aCuenta
	 */
	public String getContrase�aCuenta() {
		return contrase�aCuenta;
	}
	/**
	 * @param contrase�aCuenta the contrase�aCuenta to set
	 */
	public void setContrase�aCuenta(String contrase�aCuenta) {
		this.contrase�aCuenta = contrase�aCuenta;
	}
	/**
	 * @return the saldo
	 */
	public long getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the estado
	 */
	
	//wena

}
