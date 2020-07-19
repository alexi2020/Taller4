package SistemaBancario.logica;

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
	public Cuenta BuscarCuenta(Cuenta  {das
		int i = 0;aaddasdasdsadsdasdjjk
		for(i = 0;i < cant ; i++) {
			if(lp[i].GetRut.equals(rut)) {
				break;
			}
		}
		if(i == cant) {
			return null;
		}
		return lp[i];
	}
}
