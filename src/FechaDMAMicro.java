

public class FechaDMAMicro {
	/*
		FechaDMA maneja fechas entre 1/1/1 y 31/12/9999
	*/
	private int dia;
	private int mes;
	private int anio;
	
	public FechaDMAMicro(int dia, int mes, int anio) {
		if (anio<1 || anio>9999) {
			throw new RuntimeException("El a�o debe estar entre 1 y 9999");
		}
		if (mes<1 || mes>12) {
			throw new RuntimeException("El mes debe estar entre 1 y 12");
		}
		if (dia<1 || dia>diasMes(mes,anio)) {
			throw new RuntimeException("El dia no esta dentro del rango permitido para ese mes y a�o");
		}
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	public int dia() {
		return dia;
	}
	public int mes() {
		return mes;
	}
	public int anio() {
		return anio;
	}
	
	
	private int diasMes(int mes, int anio) {
		if (mes==4 || mes==6 || mes==9 || mes==11)
			return 30;
		if (mes==2)
			return (anioBisiesto(anio)) ? 29 : 28;
		return 31;
	}

	private boolean anioBisiesto(int anio) {
		return ((anio%4==0 && anio%100!=0) || (anio%400==0));
	}

	@Override
	public String toString() {
		return dia+"/"+ mes+"/"+ anio;
	}
	
}
	