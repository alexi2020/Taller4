package SistemaBancario.dominio;

public class CuentaChequeraElectronica extends Cuenta {
	private boolean limiteDinero=false;
	private int minimoMontoTrans=5000;
	private int montoGiro=500;
	public CuentaChequeraElectronica(String numeroCuenta, String rutTitular, String contraseñaCuenta, long saldo) {
		super(numeroCuenta,rutTitular,contraseñaCuenta,saldo);
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
