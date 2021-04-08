package lab03;

public class Animacao {
	AquarioLombriga lombriga;
	String codigo;
	
	Animacao(String codigo) {
		int a = Integer.parseInt(codigo.substring(0, 2));
		int l = Integer.parseInt(codigo.substring(2, 4));
		int p = Integer.parseInt(codigo.substring(4, 6));
		lombriga = new AquarioLombriga(a, l, p);
		this.codigo = codigo.substring(6);
	}
	
	public void apresenta() {
		System.out.println(lombriga.apresenta());
	}
	
	public void passo() {
		if (codigo.length() > 0) {
			if (codigo.charAt(0) == 'C') {
				lombriga.crescer();
			}
			else if (codigo.charAt(0) == 'M') {
				lombriga.mover();
			}
			else if (codigo.charAt(0) == 'V') {
				lombriga.virar();
			}
			codigo = codigo.substring(1);
		}
	}
}
