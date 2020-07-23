package SistemaBancario.dominio;

public class CuentaChequeraElectronica extends Cuenta {
	private boolean limiteDinero=false;
	private int minimoMontoTrans=5000;
	private int montoGiro=500;
	private static boolean estado=true;
	
	public CuentaChequeraElectronica(String numeroCuenta, String rutTitular, String contrase�aCuenta, long saldo) {
		super(numeroCuenta,rutTitular,contrase�aCuenta,saldo);
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
		if(estado) {
			r+="CuentaChequeraElectronica\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Activo Contrase�aCuenta:"
					+getContrase�aCuenta()+" Saldo:"+getSaldo();
		}
		else {
			r+="\n CuentaChequeraElectronica\n NumeroCuenta:"+getNumeroCuenta()+"Estado:Bloqueada Contrase�aCuenta:"
					+getContrase�aCuenta()+" Saldo:"+getSaldo();
		}
		return r;
	
	}
	
		
	
	

}
