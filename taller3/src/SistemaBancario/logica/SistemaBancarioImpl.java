package SistemaBancario.logica;

import SistemaBancario.dominio.*;

public class SistemaBancarioImpl implements SistemaBancario {
	private ListaPersona listaPersonas;
	private ListaCuenta listaCuentas;
	public SistemaBancarioImpl() {
		listaPersonas=new ListaPersona(100);
		listaCuentas=new ListaCuenta(100);
	//	hhh
	}
	public boolean ingresarPersona(String rut,String nombre,String apellido,String contraseña, int matriz[][]) {
		Persona persona=new Persona(rut,nombre,apellido,contraseña,matriz);
		return listaPersonas.IngresarPersona(persona);
	}
	public boolean ingresarCuentaCorriente(String numeroCuenta,String rutTitular,String contraseña,long saldo) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rutTitular);
		if(cuenta == null) {
			Cuenta cuentaC=new CuentaCorriente(numeroCuenta,rutTitular,contraseña,saldo);
			persona.getListaCuenta().IngresarCuenta(cuentaC);
			return listaCuentas.IngresarCuenta(cuentaC);
		}
		else{
			throw new NullPointerException("Posible Hackeo");
		}
	}
	public boolean ingresarCuentaChequeraElectronica(String numeroCuenta,String rutTitular,String contraseña,long saldo) {
		Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
		Persona persona=listaPersonas.BuscarPersona(rutTitular);
		if(cuenta == null) {
			Cuenta cuentaChequeraE=new CuentaChequeraElectronica(numeroCuenta,rutTitular,contraseña,saldo);
			persona.getListaCuenta().IngresarCuenta(cuentaChequeraE);
			return listaCuentas.IngresarCuenta(cuentaChequeraE);
		}
		else {
			throw new NullPointerException("Posible Hackeo");
		}
		
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
				throw new IllegalArgumentException("contraseña incorrecta");
			}
				
		}//
	}
	public boolean Depositar(long monto,String rut, String numeroCuenta) {
		Cuenta cuenta=listaCuentas.BuscarCuenta(numeroCuenta);
		if(rut.equals(cuenta.getRutTitular())) {
			if(cuenta instanceof CuentaCorriente) {
				CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
				if(cuentaC.getEstado()) {
					if((cuentaC.getSaldo()+monto)<cuentaC.LimiteDinero()) {
						cuentaC.setSaldo(monto+(cuentaC.getSaldo()));
						return true;
					}
					return false;
				}
				else {
					throw new IllegalArgumentException("tarjeta bloqueada");
					
				}
				
			}
			else {
				CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
				if(cuentaE.getEstado()) {
					cuentaE.setSaldo(monto+(cuentaE.getSaldo()));
					return true;
				}
				else {
					throw new IllegalArgumentException("Tarjeta Bloqueada");
				}
			}
			
		}
		else {
			throw new IllegalArgumentException("cuenta no es del propietario"); 
		}
		
	}
	public boolean Girar(long monto ,String numeroCuenta ,String contraseña,String rut) {
       
        Cuenta cuenta = listaCuentas.BuscarCuenta(numeroCuenta);
        String rutTitular = cuenta.getRutTitular();
        String contraseñaCuenta = cuenta.getContraseñaCuenta();

        if(cuenta==null || !cuenta.getRutTitular().equals(rutTitular)) {
            throw new IllegalArgumentException("cuenta no exite y/o cuenta no es del propietario");
        }
        else {
        	if(cuenta instanceof CuentaCorriente) {
        		CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
        		if(cuentaC.getEstado()) {
		        	if(contraseñaCuenta.equals(contraseña)) {
		        		if(monto<cuenta.getSaldo()) {
		        			cuenta.setSaldo(cuenta.getSaldo()-(monto+(cuenta.getMontoGiro())));
		        			return true;
		        		}
		        		return false;
		        	}
		        	else {
		        		throw new IllegalArgumentException("contraseña de cuenta incorrecta");
		        	}
        		}
        		else {
        			throw new IllegalArgumentException("Cuenta Bloqueada");
        		}
        	}
        	else {
        		CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
        		if(cuentaE.getEstado()) {
        			if(contraseñaCuenta.equals(contraseña)) {
		        		if(monto<cuenta.getSaldo()) {
		        			cuenta.setSaldo(cuenta.getSaldo()-(monto+(cuenta.getMontoGiro())));
		        			return true;
		        		}
		        		return false;
		        	}
		        	else {
		        		throw new IllegalArgumentException("contraseña de cuenta incorrecta");
		        	}
        		}
        		else {
        			throw new IllegalArgumentException("Cuenta Bloqueada");
        		}
        		
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
					if(cuentaC.getEstado()) {
						if((cuentaC.getSaldo()+monto)<cuentaC.LimiteDinero()&&(cuentaO.getSaldo()-monto)>5000) {
							cuentaC.setSaldo(monto+(cuentaC.getSaldo()));
							cuentaO.setSaldo(cuentaO.getSaldo()-monto);
							return true;
						}
						return false;
					}
					else {
						throw new IllegalArgumentException("cuenta Bloqueada");
					}
				}//jh
				else {
					CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
					if(cuentaE.getEstado()) {
						if((cuentaO.getSaldo()-monto)>0) {
							cuentaE.setSaldo(monto+(cuentaE.getSaldo()));
							cuentaO.setSaldo(cuentaO.getSaldo()-monto);
							return true;
						}
						else {
							throw new IllegalArgumentException("saldo insuficiente");
						}
					}
					else {
						throw new IllegalArgumentException("Cuenta Bloqueada");
					}
					
				}
						
						
			}
			else {
				throw new IllegalArgumentException("coordenadas incorrectas");
				 
			 }
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
				if(cuenta instanceof CuentaCorriente) {
					CuentaCorriente cuentaC=(CuentaCorriente)cuenta;
					cuentaC.setEstado(false);
					return true;
				}
				else {
					CuentaChequeraElectronica cuentaE=(CuentaChequeraElectronica)cuenta;
					cuentaE.setEstado(false);
					return true;
				}
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
			if(cuenta instanceof CuentaCorriente) {
				CuentaCorriente cuentaCC=(CuentaCorriente)cuenta;
				if(cuentaCC.getEstado()) { 
					if(cuenta.getContraseñaCuenta().equals(contraseñaActual)) {
						cuenta.setContraseñaCuenta(contraseñaNueva);
						return true;
					}
					return false;
				}
				else {
					throw new IllegalArgumentException("la cuenta esta bloqueada");
				}
			}
			else {
				CuentaChequeraElectronica cuentaEE=(CuentaChequeraElectronica)cuenta;
				if(cuentaEE.getEstado()) { 
					if(cuenta.getContraseñaCuenta().equals(contraseñaActual)) {
						cuenta.setContraseñaCuenta(contraseñaNueva);
						return true;
					}
					return false;
				}
				else {
					throw new IllegalArgumentException("la cuenta esta bloqueada");
				}
				
			}
		}
		else {
			throw new NullPointerException("cuenta ingresada no existe y/o cuenta no es propia");
		}
	}
	//
	


       
	

}
