package ACTIVIDAD2;

import java.util.concurrent.Semaphore;

public class Tren {
	private final int cantAsientos;

	private Asiento semAsientos[]; // Asientos del Tren
	private Semaphore semViajar; // Tren viaja cuando ya se lleno
	private Semaphore subirTren;
	private int ocupados;
	private Semaphore semBajar;

	public Tren(int cantAsientos) {
		this.cantAsientos = cantAsientos;
		this.semAsientos = new Asiento[cantAsientos];
		for (int i = 0; i < cantAsientos; i++) {
			semAsientos[i] = new Asiento();
		}
		this.semViajar = new Semaphore(0);
		this.ocupados = 0;
		this.semBajar = new Semaphore(0);
		this.subirTren = new Semaphore(1);
	}

	public int subirTren() {
		boolean finalizar = false;
		int i = 0, numeroAsiento = -1;
		try {
			subirTren.acquire();
			while (i < cantAsientos && !finalizar) {
				if (!semAsientos[i].ocupar()) {
					finalizar = true;
					System.out.println(Thread.currentThread().getName() + " subio al tren");
					numeroAsiento = i;
					ocupados++;
					if (ocupados == cantAsientos - 1) {
						System.out.println(" se libero el viaje");
						semViajar.release();
					}
				} else {
					i++;
				}
			}
			subirTren.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return numeroAsiento;
	}

	/**
	 * Espera para poder bajarse del Tren.
	 *
	 * public void dejarAsiento() {
	 * try {
	 * bajaTren.acquire();
	 * } catch (InterruptedException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 */
	/**
	 * Se baja y libera su asiento en el Tren.
	 */
	public void bajarTren(int num) {
		try {
			semBajar.acquire();
			System.out.println(Thread.currentThread().getName() + " SE BAJO  DEL TREN");
			semAsientos[num].liberar();
			this.ocupados--;
			semBajar.release();
			if (ocupados == 0) {
				semViajar.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// ------------- ControlTren -------------

	/**
	 * Comienza el viaje cuando ya se lleno el Tren de Pasajeros.
	 */
	public void iniciarViaje() {
		try {
			semViajar.acquire();
			System.out.println(" TREN LLENO");
			System.out.println(Thread.currentThread().getName() + " INICIO EL VIAJE");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finaliza el viaje cuando ya se bajaron todos los Pasajeros.
	 */
	public void finalizarViaje() {
		System.out.println(Thread.currentThread().getName() + " SE DETUVO EL TREN");
		semBajar.release();
	}

}
