package SistemaBancario.logica;

import SistemaBancario.dominio.*;

public class SistemaBancarioImpl implements SistemaBancario {
	private ListaPersona listaPersonas;
	private ListaCuenta listaCuentas;
	public SistemaBancarioImpl() {
		listaPersonas=new ListaPersona(100);
		listaCuentas=new ListaCuenta(2);
	//	
	}
	public boolean ingresarPersona(String rut,String nombre,String apellido,String contrase�a, int matriz[][]) {
		Persona persona=new Persona(rut,nombre,apellido,contrase�a,matriz);
		return listaPersonas.IngresarPersona(persona);
	}
	public boolean ingresarCuentaCorriente(String numeroCuenta,String rutTitular,String contrase�a,long saldo) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		if(cuenta == null) {
			Cuenta cuentaC=new CuentaCorriente(numeroCuenta,rutTitular,contrase�a,saldo);
			return listaCuentas.IngresarCuenta(cuentaC);
		}
		else{
			throw new NullPointerException("Posible Hackeo");
		}
	}
	public boolean ingresarCuentaChequeraElectronica(String numeroCuenta,String rutTitular,String contrase�a,long saldo) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		if(cuenta == null) {
			Cuenta cuentaChequeraE=new CuentaChequeraElectronica(numeroCuenta,rutTitular,contrase�a,saldo);
			return listaCuentas.IngresarCuenta(cuentaChequeraE);
		}
		else {
			throw new NullPointerException("Posible Hackeo");
		}
		
	}
	public boolean verificarCuentaInicioSesion(String rut ,String contrase�aInicio) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(persona==null) {
			throw new NullPointerException("la persona no existe");
		
		}
		else {
			if(persona.getContrase�aInicio().equals(contrase�aInicio)) {
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
	public boolean Girar(long monto ,String numeroCuenta ,String contrase�a,String rut) {
       
        Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
        String rutTitular = cuenta.getRutTitular();
        String contrase�aCuenta = cuenta.getContrase�aCuenta();

        if(cuenta==null || !cuenta.getRutTitular().equals(rutTitular)) {
            throw new NullPointerException("cuenta no exite y/o cuenta no es del propietario");
        }
        else {
        	if(contrase�aCuenta.equals(contrase�a)) {
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
	public boolean Transferir(long monto,String numeroCuentaOrigen, String numeroCuentaDesti,int C1 ,int F1,int numero1,
			int C2,int F2,int numero2,int C3,int F3,int numero3) {
		 Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuentaDesti);
		 Cuenta cuentaO = listaCuentas.BuscarCuenta(numeroCuentaOrigen);
		 Persona persona=listaPersonas.BuscarPersona(cuentaO.getRutTitular());
		 if(cuenta==null||cuentaO==null) {
			 throw new NullPointerException("cuenta de origen y/o destinatario no existe");
		 }
		 else {
			 int [][]tarjetaCoordenadas=persona.getTarjetaCoordenadas();
			 if(tarjetaCoordenadas[F1][C1]==numero1&&tarjetaCoordenadas[F2][C2]==numero2&&tarjetaCoordenadas[F3][C3]==numero3) {
				if(cuenta instanceof CuentaCorriente) {
					CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
					if((cuentaC.getSaldo()+monto)<cuentaC.LimiteDinero()&&(cuentaO.getSaldo()-monto)>5000) {
						cuentaC.setSaldo(monto+(cuentaC.getSaldo()));
						cuentaO.setSaldo(cuentaO.getSaldo()-monto);
						return true;
					}
					return false;
				}//jh
				else {
					CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
					if((cuentaO.getSaldo()-monto)>0) {
						cuentaE.setSaldo(monto+(cuentaE.getSaldo()));
						cuentaO.setSaldo(cuentaO.getSaldo()-monto);
						return true;
					}
					return false;
				}
						
						
			}
			return false;
		 }
		 
	
	}
	public String ObtenerInformacionCuenta(String rut) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		return persona.getListaCuenta().toString();
	}
	public boolean BloquearCuentas(String rut,String numeroCuenta,String contrase�aInicio ,String contrase�aCuenta) {
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(cuenta==null||!cuenta.getRutTitular().equals(rut)) {
			throw new NullPointerException("cuenta no exise y/o cuenta no es de su propiedad");
		}
		else {
			if((contrase�aInicio.equals(persona.getContrase�aInicio()))&&(cuenta.getContrase�aCuenta().equals(contrase�aCuenta))) {
				cuenta.setEstado(false);
				return true;
			}
			else {
				return false;
			}
		}
	}
	public boolean ActualizarContrase�aInicioSesion(String rut,String contrase�aActual,String contrase�aNueva) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		if(persona.getContrase�aInicio().equals(contrase�aActual)) {
			persona.setContrase�aInicio(contrase�aNueva);
			return true;
		}
		else {
			return false;
		}	
	}
	public boolean ActualizarContrase�aCuenta(String rut,String numeroCuenta,String contrase�aActual,String contrase�aNueva) {
		Persona persona=listaPersonas.BuscarPersona(rut);
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		if(cuenta!=null&&cuenta.getRutTitular().equals(persona.getRut())) {
			if(cuenta.getEstado()) {
				if(cuenta.getContrase�aCuenta().equals(contrase�aActual)) {
					cuenta.setContrase�aCuenta(contrase�aNueva);
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
	//
	


       
	

}
