package SistemaBancario.logica;
import SistemaBancario.dominio.Cuenta;
import SistemaBancario.dominio.CuentaCorriente;
import ucn.*;

import java.io.IOException;
import java.util.Random;

public class App {
	public static void LeerPersonas(SistemaBancario sistema) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("Personas.txt");
		
		while(!arch1.isEndFile()) {//d
			
			Registro reg = arch1.getRegistro();
	
			String rut = reg.getString();
			String nombre = reg.getString();
			String apellido = reg.getString();
			String contraseña = reg.getString();
						
			int [][] matriz = new int [5][10];
			for(int i = 0 ; i<5 ;i++ ) {
				reg = arch1.getRegistro();
				for(int j = 0 ;j <10 ;j++) {
					matriz[i][j] = reg.getInt();
					
				}	
				
			}
			if(sistema.ingresarPersona(rut, nombre, apellido, contraseña, matriz)){
				System.out.println("Persona Ingresada :)");
				
			}else {
				System.out.println("No se pudo ingresar la persona (Falta espacio)");
				
			}
				
		}		
		arch1.close();	
	}		
		
	public static void  LeerCuenta(SistemaBancario sistema) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("Cuenta.txt");
		
		while(!arch1.isEndFile()) {
			Registro reg = arch1.getRegistro();
			
			String numeroCuenta = reg.getString();
			
			String tipo = reg.getString();
			String rut = reg.getString();
			String contraseña = reg.getString();
			long saldo = reg.getLong();
			
			if (tipo.equals("CE") ) {
				try {
					if(sistema.ingresarCuentaChequeraElectronica(numeroCuenta, rut, contraseña, saldo)) {
						System.out.println("Ingrese Exitoso ");
						
					}else {
						System.out.println("No hay espacio para la persona");
					}
				}
				catch
					(NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
					
				
				
				
			}
			else {
				try {
					if(sistema.ingresarCuentaCorriente(numeroCuenta, rut, contraseña, saldo)) {
						System.out.println("Ingrese Exitoso ");
					}else {
						System.out.println("No hay espacio para la persona");
					}
					
				}
				catch
					(NullPointerException ex) {
					System.out.println(ex.getMessage());
					
				}
			}
		}
		arch1.close();
	}
	public static void Menu()throws IOException{
		System.out.println("1) Depositar");
		System.out.println("2) Girar");
		System.out.println("3) Transferencia");
		System.out.println("4) Informacion Cuenta");
		System.out.println("5) Actualizar contraseña");
		System.out.println("6) Bloquear Cuenta");
		System.out.println("7) Cerrar sesion");
	}
	
	
	
	public static void Datos(SistemaBancario sistema) throws IOException{
		StdOut.println("1) Bienvenido a Banco BCI-DEUDAS ");
		StdOut.print("ingrese rut: ");
		String rut=StdIn.readString();
		StdOut.print("Ingrese contraseña: ");
		String contraseñaI=StdIn.readString();
		try {
			if(sistema.verificarCuentaInicioSesion(rut, contraseñaI)) {
				Menu();
				System.out.print("ingrese opcion a realizar: ");
				int opcione=StdIn.readInt();
				while(opcione!=7) {
					if(opcione==1) {
						System.out.print("Ingrese numero de su cuenta: ");
						String numeroCuenta=StdIn.readString();
						System.out.print("Ingrese monto a depositar: ");
						long monto=StdIn.readLong();
						while(monto<0) {
							System.out.println("ingrese monto valido");
							System.out.print("Ingrese monto a depositar: ");
							monto=StdIn.readLong();
						}
						try {
							if(sistema.Depositar(monto, rut, numeroCuenta)) {
								System.out.println("Deposito hecho exitosamente");
							}
							else {
								System.out.println("Su cuenta Corriente alcanzo el maximo valor de saldo permitido");
							}
						}catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
						}
					}
					if(opcione==2) {
						System.out.print("ingrese su cuenta (Numero Cuenta): ");
						String cuentaP=StdIn.readString();
						System.out.print("ingrese contraseña Cuenta: ");
						String contraC=StdIn.readString();
						System.out.print("ingrese monto a girar: ");
						long montoG=StdIn.readLong();
						while(montoG<0) {
							System.out.println("monto ingresado incorrecto");
							System.out.print("ingrese monto a girar: ");
							montoG=StdIn.readLong();
						}
						try {
							if(sistema.Girar(montoG, cuentaP, contraC, rut)) {
								System.out.println("Giro completado");
								System.out.println("Saldo:$"+sistema.obtenerSaldo(cuentaP));
								
							}
							else {
								System.out.println("saldo insuficiente");
							}
						}catch(IllegalArgumentException ex) {
							System.out.println(ex.getMessage());
						}
					}
				}
			}
		}catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cuenta c=new CuentaCorriente("2222222","3333333","3dfrrff",223333);
		System.out.println(c.getEstado());
		c.setEstado(false);
		System.out.println(c.getEstado());
		String []vector= {"A","B","C","D","E","F","G","H","I","J"};
		String []vector1= {"1","2","3","4","5"};
		
		int X=(int)Math.random()*9;
		int i=(int)Math.random()*4;
		System.out.print("ingrese cordenada "+vector[X]+vector1[i]+": ");
		int numero1=StdIn.readInt();
		System.out.print("ingrese cordenada "+vector[X]+vector1[i]+": ");
		int numero2=StdIn.readInt();
		
		
	}

}
