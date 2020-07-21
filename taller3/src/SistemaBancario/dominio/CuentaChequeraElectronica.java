package SistemaBancario.dominio;

public class CuentaChequeraElectronica extends Cuenta {
	private boolean limiteDinero=false;
	private int minimoMontoTrans=5000;
	private int montoGiro=500;
	public CuentaChequeraElectronica(String numeroCuenta, String rutTitular, String contraseñaCuenta, long saldo) {
		super(numeroCuenta,rutTitular,contraseñaCuenta,saldo);
	}//hola
	
	
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
		String r="";
		if(getEstado()) {
			r+="CuentaChequeraElectronica\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Activo ContraseñaCuenta:"
					+getContraseñaCuenta()+" Saldo:"+getSaldo();
		}
		else {
			r+="CuentaChequeraElectronica\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Bloqueada ContraseñaCuenta:"
					+getContraseñaCuenta()+" Saldo:"+getSaldo();
		}
		return r;
	
	}
	
		
	
	

}
