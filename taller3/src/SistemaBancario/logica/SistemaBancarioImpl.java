package SistemaBancario.logica;

import SistemaBancario.dominio.*;

public class SistemaBancarioImpl implements SistemaBancario {
	private ListaPersona listaPersonas;
	private ListaCuenta listaCuentas;
	public SistemaBancarioImpl() {
		listaPersonas=new ListaPersona(100);
		listaCuentas=new ListaCuenta(2);
		
	}
	public boolean ingresarPersona(String rut,String nombre,String apellido,String contraseña, int matriz[][]) {
		Persona persona=new Persona(rut,nombre,apellido,contraseña,matriz);
		return listaPersonas.IngresarPersona(persona);
	}
	public boolean ingresarCuentaCorriente(String numeroCuenta,String rutTitular,String contraseña,long saldo) {
		Cuenta cuentaC=new CuentaCorriente(numeroCuenta,rutTitular,contraseña,saldo);
		return listaCuentas.IngresarCuenta(cuentaC);
	}
	public boolean ingresarCuentaChequeraElectronica(String numeroCuenta,String rutTitular,String contraseña,long saldo) {
		Cuenta cuentaChequeraE=new CuentaChequeraElectronica(numeroCuenta,rutTitular,contraseña,saldo);
		return listaCuentas.IngresarCuenta(cuentaChequeraE);
	}
	public boolean verificarCuentaInicioSesion(String rut ,String contraseñaInicio) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(persona==null) {
			throw new NullPointerException("la persona no existe");
		
		}
		else {
			if(persona.getContraseñaInicio().equals(contraseñaInicio)) {
				return true;
			}
			else {
				return false;
			}
				
		}//
	}
	public boolean Depositar(long monto,String rut, String numeroCuenta) {
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		if(rut.equals(cuenta.getRutTitular())) {
			if(cuenta instanceof CuentaCorriente) {
				CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
				if((cuentaC.getSaldo()+monto)<cuentaC.LimiteDinero()) {
					cuentaC.setSaldo(monto+(cuentaC.getSaldo()));
					return true;
				}
				return false;
				
			}
			else {
				CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
				cuentaE.setSaldo(monto+(cuentaE.getSaldo()));
				return true;
				
			}
			
		}
		else {
			throw new NullPointerException("cuenta no es del propietario"); //cambiar excepcion null 
		}
		
	}
	public boolean Girar(long monto ,String numeroCuenta ,String contraseña,String rut) {
       
        Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
        String rutTitular = cuenta.getRutTitular();
        String contraseñaCuenta = cuenta.getContraseñaCuenta();

        if(cuenta==null || !cuenta.getRutTitular().equals(rutTitular)) {
            throw new NullPointerException("cuenta no exite y/o cuenta no es del propietario");
        }
        else {
        	if(contraseñaCuenta.equals(contraseña)) {
        		if(cuenta instanceof CuentaCorriente) {
        			CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
        			if(monto>0 && monto<cuentaC.getSaldo()) {
        				cuentaC.setSaldo(cuentaC.getSaldo()-monto);
        				return true;
        			}
        			return false;
        			
        		}
        		else {
        			CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
        			if(monto>0 && monto<cuentaE.getSaldo()) {
        				cuentaE.setSaldo(cuentaE.getSaldo()-monto);
        				return true;
        			}
        			return false;
        		}
        	}
        	else {
        		return false;
        	}
        }
        	
    }
	public long obtenerSaldo(String numeroCuenta) {
		 Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		 if(cuenta==null) {
			 throw new NullPointerException("cuenta no existe");
		 }
		 else {
			 return cuenta.getSaldo();
		 }
	}
	public boolean Transferir(long monto,String rut, String numeroCuentaDesti,int numero1,int numero2,int numero3) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuentaDesti);
		Persona persona=listaPersonas.BuscarPersona(rut);
		 if(cuenta==null) {
			 throw new NullPointerException("cuenta no existe");
		 }
		 else {
			persona.getTarjetaCoordenadas()
		 }
		 
	
	}
	public String ObtenerInformacionCuenta(String rut) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		return persona.getListaCuenta().toString();
	}
	public boolean BloquearCuentas(String rut,String numeroCuenta,String contraseñaInicio ,String contraseñaCuenta) {
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(cuenta==null||!cuenta.getRutTitular().equals(rut)) {
			throw new NullPointerException("cuenta no exise y/o cuenta no es de su propiedad");
		}
		else {
			if((contraseñaInicio.equals(persona.getContraseñaInicio()))&&(cuenta.getContraseñaCuenta().equals(contraseñaCuenta))) {
				cuenta.setEstado(false);
				return true;
			}
			else {
				return false;
			}
		}
	}
	public boolean ActualizarContraseñaInicioSesion(String rut,String contraseñaActual,String contraseñaNueva) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(persona.getContraseñaInicio().equals(contraseñaActual)) {
			persona.setContraseñaInicio(contraseñaNueva);
			return true;
		}
		else {
			return false;
		}	
	}
	public boolean ActualizarContraseñaCuenta(String rut,String numeroCuenta,String contraseñaActual,String contraseñaNueva) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		if(cuenta!=null&&cuenta.getRutTitular().equals(persona.getRut())) {
			if(cuenta.getEstado()) {
				if(cuenta.getContraseñaCuenta().equals(contraseñaActual)) {
					cuenta.setContraseñaCuenta(contraseñaNueva);
					return true;
				}
				return false;
			}
			return false;
		}
		else {
			throw new NullPointerException("cuenta ingresada no existe");
		}
	}
	
	


       
	

}
