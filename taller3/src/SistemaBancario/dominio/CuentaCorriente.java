package SistemaBancario.dominio;

public class CuentaCorriente extends Cuenta{
	private boolean limiteDinero=true;
	private int minimoMontoTrans=5000;
	private int montoGiro=500;
	private boolean estado=true;
	public CuentaCorriente(String numeroCuenta, String rutTitular, String contraseñaCuenta, long saldo) {
		super(numeroCuenta,rutTitular,contraseñaCuenta,saldo);
	}//hola
	
	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

	
	/**
	 * @return the limiteDinero
	 */
	public boolean isLimiteDinero() {
		return limiteDinero;
	}

	/**
	 * @param limiteDinero the limiteDinero to set
	 */
	public void setLimiteDinero(boolean limiteDinero) {
		this.limiteDinero = limiteDinero;
	}

	/**
	 * @return the minimoMontoTrans
	 */
	public int getMinimoMontoTrans() {
		return minimoMontoTrans;
	}

	/**
	 * @param minimoMontoTrans the minimoMontoTrans to set
	 */
	public void setMinimoMontoTrans(int minimoMontoTrans) {
		this.minimoMontoTrans = minimoMontoTrans;
	}

	/**
	 * @return the montoGiro
	 */
	public int getMontoGiro() {
		return montoGiro;
	}

	/**
	 * @param montoGiro the montoGiro to set
	 */
	public void setMontoGiro(int montoGiro) {
		this.montoGiro = montoGiro;
	}

	public String toString() {
		return "CuentaCorriente\n NumeroCuenta:"+getNumeroCuenta()+" RutTitular:"+getRutTitular()+" ContraseñaCuenta:"
				+getContraseñaCuenta()+" Saldo:"+getSaldo();
	
	}

}
