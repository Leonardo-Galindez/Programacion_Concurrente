
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planta {
    // consultar
    // cajas y almacen
    // 1 lock para caja y almacen
    // 1 lock por caja y empaquetador
    // 1-para utilizar los await singal signalAll tengo que usar antes el lock de el
    // lock de la condition
    // 2- podemos tenes mas de un await en un metodo??
    // 3- el tema de la caja como la interpretamos??
    // 4- como resolver el problema de que si el agua manda y vino no puede
    private Lock producirVino;
    private Lock producirAgua;
    private Lock transportar;
    private Lock brazo;
    private Lock mutex;

    private Condition embotelladorVino;
    private Condition embotelladorAgua;
    private Condition empaquetador;
    private Condition transportador;

    private int contVino = 0;
    private int contAgua = 0;
    private int contCajas = 0;

    private boolean empezarEmpaquetar = false;
    private boolean empezarTransportar = false;

    private boolean empaquetarCajaVino = false;
    private boolean empaquetarCajaAgua = false;

    public Planta() {

        this.producirVino = new ReentrantLock();
        this.producirAgua = new ReentrantLock();
        this.transportar = new ReentrantLock();
        this.mutex = new ReentrantLock();
        this.brazo = new ReentrantLock();

        this.embotelladorVino = producirVino.newCondition();
        this.embotelladorAgua = producirAgua.newCondition();
        this.empaquetador = brazo.newCondition();
        this.transportador = transportar.newCondition();

    }

    public void guardarVino() {
        try {
            producirVino.lock();
            while (contVino >= 10) {
                embotelladorVino.await();
            }
            System.out.println(Thread.currentThread().getName() + " guardo un vino +++");
            brazo.lock();
            contVino++;
            if (contVino == 10) {
                System.out.println("CAJA DE VINOS LLENA !!!");
                mutex.lock();
                empezarEmpaquetar = true;
                empaquetarCajaVino = true;
                empaquetador.signal();
            } else {
                System.out.println("dsd");
                producirVino.unlock();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            brazo.unlock();
        }
    }

    public void guardarAgua() {
        try {
            producirAgua.lock();
            while (contAgua >= 10) {
                embotelladorAgua.await();
            }
            System.out.println(Thread.currentThread().getName() + " guardo un agua ---");
            brazo.lock();
            contAgua++;
            if (contAgua == 10) {
                System.out.println("CAJA DE AGUA LLENA !!!");
                mutex.lock();
                empezarEmpaquetar = true;
                empaquetarCajaAgua = true;
                empaquetador.signal();
            } else {
                System.out.println("ffff");
                producirAgua.unlock();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            brazo.unlock();
        }
    }

    public void empaquetarCaja() {
        try {
            brazo.lock();
            producirVino.lock();
            producirAgua.lock();
            while (!empezarEmpaquetar) {
                brazo.unlock();
                producirVino.unlock();
                producirAgua.unlock();

                empaquetador.await();
            }
            brazo.lock();
            producirVino.lock();
            producirAgua.lock();
            if (empaquetarCajaVino) {
                System.out.println(Thread.currentThread().getName() + " empaqueta una caja de Vino ");
                producirAgua.unlock();
            } else {
                if (empaquetarCajaAgua) {
                    System.out.println(Thread.currentThread().getName() + " empaqueta una caja de Agua ");
                    producirVino.unlock();
                }
            }
            transportar.lock();
            contCajas++;
            if (contCajas == 10) {
                empezarTransportar = true;
                transportador.signal();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            brazo.unlock();
            transportar.unlock();
        }
    }

    public void reponerCaja() {
        try {
            brazo.lock();
            producirVino.lock();
            producirAgua.lock();
            // tomar y poner antes del await y tomar devuelta
            if (empaquetarCajaVino) {
                System.out.println(Thread.currentThread().getName() + " repone una caja de Vino ");
                contVino = 0;
                empaquetarCajaVino = false;
                embotelladorVino.signalAll();
                producirVino.unlock();
            } else {
                if (empaquetarCajaAgua) {
                    System.out.println(Thread.currentThread().getName() + " repone una caja de Agua ");
                    contAgua = 0;
                    empaquetarCajaAgua = false;
                    embotelladorAgua.signalAll();
                    producirAgua.unlock();
                }
            }
        } finally {
            brazo.unlock();
        }
    }

    public void transportarCajas() {
        try {
            transportar.lock();
            while (!empezarTransportar) {
                transportador.await();
            }
            System.out.println(Thread.currentThread().getName() + " salio a repartir cajas");
            contCajas = 0;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            transportar.unlock();
        }
    }

    /*
     * private Lock vino;
     * private Lock agua;
     * private Lock almacen; // Lock para el transportador
     * private Lock caja; // Lock para el empaquetador
     * 
     * private Condition embotelladorVino;
     * private Condition embotelladorAgua;
     * private Condition empaquetador;
     * private Condition transportador;
     * 
     * private int contVino = 0;
     * private int contAgua = 0;
     * private int contCajas = 0;
     * 
     * private boolean empacarA = false;
     * private boolean empacarV = false;
     * private boolean transportar = false;
     * 
     * private String tipo = "";
     * private String cajaReponer = "";
     * 
     * public Planta() {
     * 
     * this.vino = new ReentrantLock();
     * this.agua = new ReentrantLock();
     * this.caja = new ReentrantLock();
     * this.almacen = new ReentrantLock();
     * 
     * this.embotelladorVino = vino.newCondition();
     * this.embotelladorAgua = agua.newCondition();
     * this.empaquetador = caja.newCondition();
     * this.transportador = almacen.newCondition();
     * 
     * }
     * 
     * public void guardarVino() {
     * 
     * try {
     * vino.lock();
     * while (contVino >= 10 || empacarV) {
     * embotelladorVino.await();
     * }
     * caja.lock();
     * contVino++;
     * System.out.println(Thread.currentThread().getName() +
     * " guardo una botella de vino +");
     * if (contVino == 10) {
     * System.out.println("CAJA DE VINO LLENA !!!");
     * empacarV = true;
     * tipo = "V";
     * empaquetador.signalAll(); // tengo que poner el lock de la condicion para el
     * signal??
     * }
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * caja.unlock();
     * vino.unlock();
     * }
     * }
     * 
     * public void guardarAgua() {
     * try {
     * agua.lock();
     * while (contAgua >= 10 || empacarA) {
     * embotelladorAgua.await();
     * }
     * caja.lock();
     * contAgua++;
     * System.out.println(Thread.currentThread().getName() +
     * " guardo una botella de agua -");
     * 
     * if (contAgua == 10) {
     * System.out.println("CAJA DE AGUA LLENA !!!");
     * empacarA = true;
     * tipo = "A";
     * empaquetador.signal();
     * }
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * caja.unlock();
     * agua.unlock();
     * }
     * }
     * 
     * public void empaquetarCaja() {
     * try {
     * caja.lock();
     * if (tipo.equals("V")) {
     * try {
     * vino.lock();
     * while (!empacarV && !empacarA) {
     * empaquetador.await();
     * }
     * System.out.println("El empaquetador esta preparando la caja de VINO");
     * cajaReponer = "V";
     * contCajas++;// consultar si esto puede ir afuera
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * vino.unlock();
     * }
     * } else {
     * 
     * try {
     * agua.lock();
     * while (!empacarV && !empacarA) {
     * empaquetador.await();
     * }
     * System.out.println("El empaquetador esta preparando la caja de AGUA");
     * cajaReponer = "A";
     * contCajas++;// consultar si esto puede ir afuera
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * agua.unlock();
     * }
     * }
     * if (contCajas == 10) {
     * transportar = true;
     * transportador.signal();
     * }
     * } finally {
     * caja.unlock();
     * }
     * }
     * 
     * public void reponerCaja() {
     * try {
     * caja.lock();
     * if (cajaReponer.equals("V")) {
     * System.out.println("El empaquetador repuso una caja de VINO");
     * contVino = 0;
     * embotelladorVino.signal();
     * } else {
     * System.out.println("El empaquetador repuso una caja de AGUA");
     * contAgua = 0;
     * embotelladorAgua.signal();
     * }
     * empacarV = false;
     * empacarA = false;
     * } finally {
     * caja.unlock();
     * }
     * }
     * 
     * public void transportarCajas() {
     * try {
     * almacen.lock();
     * while (!transportar) {
     * transportador.await();
     * }
     * System.out.println("El transportador esta repartiendo las CAJAS DEL ALMACEN"
     * );
     * transportar = false;
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * almacen.unlock();
     * }
     * 
     * }
     * 
     * /*
     * private Lock acceso;
     * private Lock vino;
     * private Lock agua;
     * private Lock almacen;
     * // private Lock embotellador;
     * 
     * private Condition embotelladorVino;
     * private Condition embotelladorAgua;
     * private Condition empaquetador;
     * private Condition transportador;
     * 
     * private int contVino = 0;
     * private int contAgua = 0;
     * private int cajas = 0;
     * private boolean empacar = false;
     * private boolean transportar = false;
     * private boolean empaquetando = false;
     * private String tipo;// tipo de embotellador
     * private String tipoCaja; // caja llena
     * 
     * public Planta() {
     * this.acceso = new ReentrantLock();
     * this.almacen = new ReentrantLock();
     * 
     * // this.embotellador = new ReentrantLock();
     * // se puede trabajar en concurrencia cuando un
     * // embotellador no llego la caja y otro si
     * 
     * this.embotelladorVino = vino.newCondition();
     * this.embotelladorAgua = agua.newCondition();
     * 
     * this.empaquetador = acceso.newCondition();
     * 
     * this.transportador = almacen.newCondition();
     * }
     * 
     * // Embotellador
     * 
     * public void guardarBotellaVino() {
     * try {
     * acceso.lock();
     * while (contVino >= 10 || empaquetando) {
     * embotelladorVino.await();
     * }
     * contVino++;
     * System.out.println("El embotellador " + Thread.currentThread().getName() +
     * " preparo un vino");
     * if (contVino == 10) {
     * System.out.println("CAJA DE VINO LLENA!!!");
     * empacar = true;
     * tipo = "t";
     * empaquetador.signalAll();
     * // mandar señal que es caja de agua ejemplo: mandar una "V" o algo asi
     * }
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * acceso.unlock();
     * }
     * }
     * 
     * public void guardarBotellaAgua() {
     * try {
     * acceso.lock();
     * while (contAgua >= 10) {
     * embotelladorAgua.await();
     * }
     * contAgua++;
     * System.out.println("El embotellador " + Thread.currentThread().getName() +
     * " preparo un agua");
     * if (contVino == 10) {
     * System.out.println("CAJA DE AGUA LLENA !!!");
     * empacar = true;
     * empaquetador.signalAll();
     * // mandar señal que es caja de agua ejemplo: mandar una "A" o algo asi
     * }
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * acceso.unlock();
     * }
     * }
     * 
     * // Empaquetador
     * 
     * public void empaquetarCaja() {
     * try {
     * // tomamos los 2 lock de embotelladores
     * // verificamos el tipo de caja embotellada
     * // y liberamos el contrario
     * acceso.lock();
     * while (!empacar) {
     * empaquetador.await();
     * }
     * System.out.println("El empaquetador " + Thread.currentThread().getName() +
     * " guarda la caja de vino");
     * cajas++;
     * empaquetando = true;
     * if (cajas == 10) {
     * System.out.println("Almacen Lleno !!!");
     * transportar = true;
     * transportador.signalAll();
     * }
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * acceso.unlock();
     * }
     * }
     * 
     * public void reponerCajaVino() {
     * try {
     * acceso.lock();
     * contVino = 0;
     * System.out.println("El empaquetador " + Thread.currentThread().getName() +
     * " repone caja de vinos");
     * empaquetando = false;
     * embotelladorVino.signalAll();
     * embotelladorAgua.signalAll();
     * } finally {
     * acceso.unlock();
     * }
     * }
     * 
     * public void reponerCajaAgua() {
     * try {
     * acceso.lock();
     * contAgua = 0;
     * System.out.println("El empaquetador " + Thread.currentThread().getName() +
     * " repone caja de agua");
     * empaquetando = false;
     * embotelladorAgua.signalAll();
     * embotelladorVino.signalAll();
     * } finally {
     * acceso.unlock();
     * }
     * }
     * 
     * // Transportador
     * 
     * public void repartirCajas() {
     * try {
     * acceso.lock();
     * while (!transportar) {
     * transportador.await();
     * }
     * System.out.println("El transportador " + Thread.currentThread().getName() +
     * " esta repartiendo las cajas");
     * cajas = 0;
     * } catch (InterruptedException ex) {
     * ex.printStackTrace();
     * } finally {
     * acceso.unlock();
     * }
     * }
     */
}
