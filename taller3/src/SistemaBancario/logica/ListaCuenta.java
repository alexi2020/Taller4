package SistemaBancario.logica;

import SistemaBancario.dominio.CuentaChequeraElectronica;
import SistemaBancario.dominio.CuentaCorriente;
import SistemaBancario.logica.*;
public class ListaCuenta {
	private Cuenta [] lc;
	private int max;
	private int cant;
	
	
	public ListaCuenta(int max) {
		lc = new Cuenta[max];
		cant = 0;
		this.max = max;
		
	}
	public boolean IngresarPersona(Cuenta cuenta) {
		if (cant < max) {
			lc[cant] = cuenta;
			cant++;
			return true;
		}
		else{
			return false;
		}
	}
	public Cuenta getCuenta(int i) {
		if(i >=0 && i < cant) {
			return lc[i];
		}
		else {
			return null;
		}
	}
	public int GetCantidadCuentas() {
		return cant;
	}
	public Cuenta BuscarCuenta(Cuenta numero) {
		int i = 0;
		for(i = 0;i < cant ; i++) {
			if(lc[i].GetNumerocodigo.equals(rut)) {
				break;
			}
		}
		if(i == cant) {
			return null;
		}
		return lc[i];
	}
	@Override
	public String toString() {
		String salida = "";
		int i = 0;
		for(i = 0; i < cant ; i++) {
			Cuenta cuenta = getCuenta(i);
			
			if (cuenta instanceof CuentaCorriente ) {
				CuentaCorriente cuentacorriente = (CuentaCorriente) cuenta;
				salida += cuentacorriente.toString();
			}
			else{
				CuentaChequeraElectronica chequera = (CuentaChequeraElectronica) cuenta;
				salida += chequera.toString();
			}
		return salida;	
		}
		
	}

	



}
