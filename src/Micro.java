
import java.util.ArrayList;

public class Micro {

	private String destino;
	private FechaDMAMicro fecha;
	private int precio;
	private ArrayList<Boolean> asientos;
	private int cantAsientos;
	private int asientosLibres;
	private ArrayList<Integer> listaAsientosLibres;

	public Micro(String destino, FechaDMAMicro fecha, int precio, int cantDeAsientos) {
		if(destino == null || destino == "") {
			throw new RuntimeException("destino inválido");
		}
		if(precio <= 0) {
			throw new RuntimeException("precio debe ser mayor a 0");
		}
		if(cantDeAsientos <= 0) {
			throw new RuntimeException("cantidad de asientos debe ser mayor a 0");
		}
		this.destino = destino;
		this.fecha = fecha;
		this.precio = precio;
		this.asientos = new ArrayList<Boolean>();
		this.listaAsientosLibres = new ArrayList<Integer>();
		// true es ocupado
		for (int i = 0; i < cantDeAsientos; i++) {
			asientos.add(false);
			listaAsientosLibres.add(i);
		}
		cantAsientos = cantDeAsientos;
		asientosLibres = cantDeAsientos;
	}

	// el primer asiento es el asiento nro 0. Yo entiendo que este método implementa el IREP.
	private boolean esNumeroValidoDeAsiento(int numero) {
		return numero >= 0 && numero < asientos.size();
	}

	public int getCantAsientos() {
		return cantAsientos;
	}

	// Cuando un asiento está ocupado la posición contiene true.
	public void vaciarMicro() throws Exception {
		for (int i = 0; i < cantAsientos; i++) {
			if (estaOcupado(i)) { // asientos.get(i){
				liberarAsiento(i); // asientos.set(i, false);
			} 						// listaAsientosLibres.add(i)}
		}
	}

	// Ocupar un asiento específico:
	// dado un número de asiento establecer el mismo como ocupado. 
	public void ocuparAsiento(int numero) throws Exception {
		if (!estaOcupado(numero)) {
			asientos.set(numero, true);
			Integer num = numero;
			listaAsientosLibres.remove(num);
			asientosLibres--;
		}
	}

	// Cantidad de asientos libres:
	// en el momento que necesite debe poder consultar cantidad de asientos libres
	// de un micro.
	public int cantidadDeAsientosLibres() {
		return asientosLibres;
	}

	// Liberar un asiento específico: dado un número de asiento establecer el mismo
	// como libre. 
	public void liberarAsiento(int numero) throws Exception {
		if (estaOcupado(numero)) {
			asientos.set(numero, false);
			asientosLibres++;
			listaAsientosLibres.add(numero);
		}
	}

	// Asiento ocupado:  dado un número de asiento, saber si está ocupado o no
	public boolean estaOcupado(int numero) throws Exception {
		if (esNumeroValidoDeAsiento(numero)) {
			return asientos.get(numero);
		} else {
			throw new Exception("el asiento numero " + numero + " no existe");
		}
	}

	// Ver  asiento libre:  busca un numero de asiento que se encuentre libre. 
	public int asientoLibre() throws Exception {
		if (listaAsientosLibres.isEmpty()) {
			throw new Exception("no hay asientos vacíos");
		}
		return listaAsientosLibres.get(0);
	}

	// Cambiar micro: Se pasan los pasajeros desde otro micro.
	// No es necesario que conserven el número de asiento original.
	// Si no hay lugar suficiente para todos, el resto queda en el micro original,
	// esperando otro micro
	public void cambiarMicro(Micro microDos) throws Exception {
		if (microDos == null) {
			throw new Exception("microDos no existe");
		}
		int cantAsientosDisponibles = cantidadDeAsientosLibres();
		int cantAsientosAOcupar = microDos.asientos.size() - microDos.cantidadDeAsientosLibres();

		int cantDisponible = Math.min(cantAsientosDisponibles, cantAsientosAOcupar);

		int iM1 = 0;
		int iM2 = 0;

		while (cantDisponible > 0) {
			while (asientos.get(iM1)) {
				iM1++;
			}

			while (!microDos.estaOcupado(iM2)) {
				iM2++;
			}

			ocuparAsiento(iM1);
			microDos.liberarAsiento(iM2);
			cantDisponible--;
		}
	}
	
	@Override
	public String toString() {
		return "micro = " + asientos;
	}
}