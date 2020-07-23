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
			String contrase�a = reg.getString();		
			int [][] matriz = new int [5][10];
			for(int i = 0 ; i<5 ;i++ ) {
				reg = arch1.getRegistro();
				for(int j = 0 ;j <10 ;j++) {
					matriz[i][j] = reg.getInt();
				}	
				
			}
			if(sistema.ingresarPersona(rut, nombre, apellido, contrase�a, matriz)){
				System.out.println("Persona Ingresada :)");
				
			}else {
				System.out.println("No se pudo ingresar la persona (Falta espacio)");
				
			}
				
		}		
		arch1.close();	
	}		
		
	public static void  LeerCuenta(SistemaBancario sistema) throws IOException {
		ArchivoEntrada arch2 = new ArchivoEntrada("Cuentas.txt");
		
		while(!arch2.isEndFile()) {
			Registro reg = arch2.getRegistro();
			
			String numeroCuenta = reg.getString();
			
			String tipo = reg.getString();
			String rut = reg.getString();
			String contrase�a = reg.getString();
			long saldo = reg.getLong();
			
			if (tipo.equals("CE") ) {
				try {
					if(sistema.ingresarCuentaChequeraElectronica(numeroCuenta, rut, contrase�a, saldo)) {
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
					if(sistema.ingresarCuentaCorriente(numeroCuenta, rut, contrase�a, saldo)) {
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
		arch2.close();
	}
	public static void MenuInicio() {
		System.out.println("1)Iniciar Sesion");
		System.out.println("2)Cerrar Sistema");
	}
	public static void Menu(){
		System.out.println("1) Depositar");
		System.out.println("2) Girar");
		System.out.println("3) Transferencia");
		System.out.println("4) Informacion Cuenta");
		System.out.println("5) Actualizar contrase�a");
		System.out.println("6) Bloquear Cuenta");
		System.out.println("7) Cerrar sesion");
	}
	public static void posicionesAleatoria(int[]vector2,int[]vector3)throws IOException{
		for(int j=0;j<3;j++) {
			double X=Math.random()*9;
			double i= Math.random()*4;
			int f=(int)i;
			int C=(int)X;
			vector2[j]=f;
			vector3[j]=C;
		}
	}
	public static String MenuSecundario() {
		System.out.print("Volver a menu principal(si/no): ");
		String eleccion=StdIn.readString();
		while(!eleccion.equalsIgnoreCase("si")) {
			System.out.print("Volver a menu principal(si/no): ");
			eleccion=StdIn.readString();
		}
		return eleccion;
	}
	
	
	
	public static void Datos(SistemaBancario sistema) throws IOException{
		StdOut.println("Bienvenido a Banco BCI-DEUDAS ");
		MenuInicio();
		System.out.print("Ingrese opcion: ");
		int eleccion=StdIn.readInt();
		while(eleccion!=2) {
			StdOut.print("ingrese rut: ");
			String rut=StdIn.readString();
			StdOut.print("Ingrese contrase�a: ");
			String contrase�aI=StdIn.readString();
			try {
				if(sistema.verificarCuentaInicioSesion(rut, contrase�aI)) {
					Menu();
					System.out.print("ingrese opcion a realizar: ");
					int opcione=StdIn.readInt();
					while(opcione!=7) {
						while(true) {
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
								String volverMenuP=MenuSecundario();
								if(volverMenuP.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==2) {
								System.out.print("ingrese su cuenta (Numero Cuenta): ");
								String cuentaP=StdIn.readString();
								System.out.print("ingrese contrase�a Cuenta: ");
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
								String volverMenuP1=MenuSecundario();
								if(volverMenuP1.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==3) {
								System.out.print("ingrese su cuenta (Numero Cuenta): ");
								String cuentaT=StdIn.readString();
								System.out.print("ingrese Monto: ");
								int montoT=StdIn.readInt();
								while(montoT<0) {
									System.out.println("monto invalido");
									System.out.println("ingrese Monto: ");
									montoT=StdIn.readInt();
								}
								String []vector= {"A","B","C","D","E","F","G","H","I","J"};
								String []vector1= {"1","2","3","4","5"};
								int []vector2=new int[5];//filas
								int []vector3=new int[10];//columnas
								
								System.out.print("ingrese cuenta Destinatario: ");
								String cuentaD=StdIn.readString();
								posicionesAleatoria(vector2,vector3);
								int p=0;
								System.out.print("ingrese cordenada "+vector[vector3[p]]+vector1[vector2[p]]+": ");
								int numero1=StdIn.readInt();
								p++;
								System.out.print("ingrese cordenada "+vector[vector3[p]]+vector1[vector2[p]]+": ");
								int numero2=StdIn.readInt();
								p++;
								System.out.print("ingrese cordenada "+vector[vector3[p]]+vector1[vector2[p]]+": ");
								int numero3=StdIn.readInt();
								try {
									if(sistema.Transferir(montoT, cuentaT, cuentaD, vector3[0],vector2[0], numero1,vector3[1],vector2[1], numero2, vector3[2], 
											vector2[2],numero3)){
										System.out.println("transferencia exitosa");
									}
									else {
										System.out.println("saldo insuficiente o bien cuenta destinatario no tiene cupo");
				
									}
								}catch(NullPointerException ex) {
									System.out.println(ex.getMessage());
								}catch(IllegalArgumentException ex1) {
									System.out.println(ex1.getMessage());
								}
								String volverMenuP2=MenuSecundario();
								if(volverMenuP2.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==4) {
								System.out.println(sistema.ObtenerInformacionCuenta(rut));
								String volverMenuP3=MenuSecundario();
								if(volverMenuP3.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==5) {
								System.out.println("Indique contrase�a a actualizar");
								System.out.println("1)Contrase�a Inicio Sesion");
								System.out.println("2)Contrase�a Cuenta");
								System.out.println("ingrese opcion: ");
								int opcion=StdIn.readInt();
								if(opcion==1) {
									System.out.print("ingrese contrase�a actual: ");
									String contrase�aAA=StdIn.readString();
									System.out.print("Ingrese contrase�a nueva: ");
									String contrase�aNew0=StdIn.readString();
									System.out.print("Vuelva ingresar contrase�a nueva: ");
									String contrase�aNew=StdIn.readString();
									while(!contrase�aNew.equals(contrase�aNew0)) {
										System.out.println("las contrase�as no coinciden");
										System.out.print("Ingrese contrase�a nueva: ");
										contrase�aNew0=StdIn.readString();
										System.out.print("Vuelva ingresar contrase�a nueva: ");
										contrase�aNew=StdIn.readString();
									}
									if(sistema.ActualizarContrase�aInicioSesion(rut, contrase�aAA, contrase�aNew)) {
										System.out.println("Contrase�a de Inicio Actualizada");
									}
									else {
										System.out.println("error contrase�a actual incorrecta");
									}
								}
								if(opcion==2) {
									System.out.print("ingrese su numero de cuenta: ");
									String numeroCuenta23=StdIn.readString();
									System.out.print("ingrese contrase�a Actual: ");
									String contraActual=StdIn.readString();
									System.out.print("ingrese contrase�a Nueva: ");
									String contraNueva0=StdIn.readString();
									System.out.print("vuelva ingresar contrase�a Nueva: ");
									String contraNueva=StdIn.readString();
									while(!contraNueva.equals(contraNueva0)) {
										System.out.println("contrase�as no coinciden");
										System.out.print("ingrese contrase�a Nueva: ");
										contraNueva0=StdIn.readString();
										System.out.print("vuelva ingresar contrase�a Nueva: ");
										contraNueva=StdIn.readString();
									}
									try {
										if(sistema.ActualizarContrase�aCuenta(rut, numeroCuenta23, contraActual, contraNueva)) {
											System.out.println("contrase�a actualizada");
										}
										else {
											System.out.println("error contrase�a actual incorrecta");
										}
									}catch (IllegalArgumentException ex) {
										System.out.println(ex.getMessage());
									}catch (NullPointerException ex1) {
										System.out.println(ex1.getMessage());
									}
									
								}
								String volverMenuP4=MenuSecundario();
								if(volverMenuP4.equalsIgnoreCase("si")) {
									break;
								}
							}
							if(opcione==6) {
								System.out.print("ingrese numero cuenta:");
								String numeroCB=StdIn.readString();
								System.out.print("ingrese contrase�a Inicio Sesion: ");
								String contraInicio=StdIn.readString();
								System.out.print("ingrese contrase�a Cuenta: ");
								String contraBanco=StdIn.readString();
								try {
									if(sistema.BloquearCuentas(rut, numeroCB, contraInicio, contraBanco)) {
										System.out.println("Cuenta Bloqueada Exitosamente");
									}
									else {
										System.out.println("contrase�a de inicio de sesion y/o contrase�a de cuenta Incorrecta");
									}
								}catch (NullPointerException ex) {
									System.out.println(ex.getMessage());
								}
								String volverMenuP5=MenuSecundario();
								if(volverMenuP5.equalsIgnoreCase("si")) {
									break;
								}
							}
					    }
						Menu();
						System.out.print("ingrese opcion a realizar: ");
						opcione=StdIn.readInt();
					}
				}
				
			}catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}catch (IllegalArgumentException ex1) {
				System.out.println(ex1.getMessage());
			}
			MenuInicio();
			System.out.print("Ingrese opcion: ");
			eleccion=StdIn.readInt();
		}
		
	}
	

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		SistemaBancario sistema=new SistemaBancarioImpl();
		LeerPersonas(sistema);
		LeerCuenta(sistema);
		Datos(sistema);
	}

}
