package SistemaBancario.dominio;

public class CuentaCorriente extends Cuenta{
	private long limiteDinero=2000000;
	
	
	
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
