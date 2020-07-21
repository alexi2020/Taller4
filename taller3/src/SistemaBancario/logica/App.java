package SistemaBancario.logica;
import SistemaBancario.dominio.Cuenta;
import SistemaBancario.dominio.CuentaCorriente;
import ucn.*;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cuenta c=new CuentaCorriente("2222222","3333333","3dfrrff",223333);
		System.out.println(c.getEstado());
		c.setEstado(false);
		System.out.println(c.getEstado());
		String []vector= {"A","B","C"};
		String []vector1= {"1","2","3"};
		
		int X=(int)Math.random()*2;
		int i=(int)Math.random()*2;
		System.out.print("ingrese cordenada "+vector[X]+vector1[i]+": ");
		int numero1=StdIn.readInt();
		
	}

}
