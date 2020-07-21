package SistemaBancario.logica;
import SistemaBancario.dominio.Cuenta;
import SistemaBancario.dominio.CuentaCorriente;
import ucn.*;

import java.io.IOException;
import java.util.Random;

public class App {
	public static void LeerPersonas(SistemaBancario sistema) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("Personas.txt");
		
		while(!arch1.isEndFile()) {
			
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
	
	
	
	public static void Menu(SistemaBancario sistema) {
		StdOut.println("1) ");
		StdOut.println("2) 2:");
		StdOut.println("3) 2");
		StdOut.println("4) 3has:");
		StdOut.println("5) 4:");
		StdOut.println("6) R4::");
		StdOut.println("7)4S:");
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
