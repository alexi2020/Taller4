package SistemaBancario.dominio;

public class CuentaCorriente extends Cuenta{
	private long limiteDinero=2000000;
	private int minimoMontoTrans=5000;
	private int montoGiro=500;
	
	
	public CuentaCorriente(String numeroCuenta, String rutTitular, String contraseñaCuenta, long saldo) {
		super(numeroCuenta,rutTitular,contraseñaCuenta,saldo);
	}//hola
	
	
	

	
	
	/**
	 * @return the limiteDinero
	 */
	public long LimiteDinero() {
		return limiteDinero;
	}

	/**
	 * @param limiteDinero the limiteDinero to set
	 */
	public void setLimiteDinero(long limiteDinero) {
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
		String r="";
		if(getEstado()) {
			r+="CuentaCorriente\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Activo ContraseñaCuenta:"
					+getContraseñaCuenta()+" Saldo:"+getSaldo();
		}
		else {
			r+="CuentaCorriente\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Bloqueada ContraseñaCuenta:"
					+getContraseñaCuenta()+" Saldo:"+getSaldo();
		}
		return r;
	
	}

}
