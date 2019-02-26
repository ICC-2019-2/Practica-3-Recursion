package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
            this.elemento = elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return this.anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return this.siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Estudiante get() {
            return this.elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return getLongitud(cabeza);
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Estudiante elemento) {
        if(elemento != null){
        Nodo n = new Nodo(elemento);
        longitud++;
        if(rabo == null){
          cabeza=rabo=n;
          }else {
          rabo.siguiente = n;
          n.anterior = rabo;
          rabo = n;
        }
      }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Estudiante elemento) {
        if(elemento != null){
        Nodo n = new Nodo(elemento);
        longitud++;
        if(cabeza == null){
          cabeza=rabo=n;
          }else {
          cabeza.anterior = n;
          n.siguiente = cabeza;
          cabeza = n;
        }
      }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Estudiante elemento) {
        if(elemento == null)
            return;
          if(longitud == 0) {
            agregaFinal(elemento);
            return;
          } else if(i >= longitud) {
            agregaFinal(elemento);
            return;
          } else if(i <= 0){
            agregaInicio(elemento);
            return;
          }
        longitud ++;
        Nodo ingresa = new Nodo(elemento);
        Nodo n = inserta(cabeza, i);
        Nodo antes = n.anterior;
        antes.siguiente = ingresa;
        ingresa.anterior = antes;
        n.anterior = ingresa;
        ingresa.siguiente = n;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Estudiante elemento) {
        Nodo n = buscaNodo(elemento,cabeza);
           if(n!= null){
               if(n.equals(cabeza)){
               eliminaPrimero();
               }else{
               if(n.equals(rabo)){
                 eliminaUltimo();
                 }else{
                   (n.anterior).siguiente = n.siguiente;
                   (n.siguiente).anterior = n.anterior;
             longitud--;
        }
       }
      }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
        if(cabeza == null)
          return null;
        Estudiante r = cabeza.elemento;
          if(longitud == 1){
            cabeza = rabo = null;
            longitud--;
            return r;
          }
          else{
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
            return r;
          }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaUltimo() {
        if(rabo == null)
          return null;
        Estudiante r = rabo.elemento;
        if(longitud == 1){
        cabeza = rabo = null;
        longitud--;
        return r;
       }
        else{
        rabo = rabo.anterior;
        rabo.siguiente = null;
        longitud--;
        return r;
       }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(Estudiante elemento) {
        return contiene(cabeza, elemento);
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaEstudiante reversa() {
        ListaEstudiante r = new ListaEstudiante();
        return reversa(r, rabo);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaEstudiante copia() {
        ListaEstudiante copiaLista = new ListaEstudiante();
        return copia(copiaLista, cabeza);
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = null;
        rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
        if(cabeza != null)
          return cabeza.elemento;
        return null;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
        if(cabeza != null)
          return rabo.elemento;
        return null;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
        if(i<0 || i>=longitud)
          return null;
        return get(cabeza, i, 0);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Estudiante elemento) {
        return indiceDe(cabeza, elemento, 0);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        if(cabeza == null)
          return "[]";
        String r = "[" + cabeza.elemento.toString();
        return toString(r, cabeza.siguiente);
    }

    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <tt>true</tt> si la lista es igual a la recibida;
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
        if (lista == null || longitud != lista.longitud)
             return false;
        return equals(cabeza, lista.cabeza);
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }

    //Zona de metodos auxiliares

    /*metodo auxiliar getLongitud*/
    private int getLongitud(Nodo n){
      if(n == null)
        return 0;
      return 1 + getLongitud(n.siguiente);
    }

    /*metodo auxiliar para inserta*/
    private Nodo inserta(Nodo n,int i){
      return i-- > 0? inserta(n.siguiente,i): n;
    }

    /*metodo auxiliar para buscar nodo*/
    private Nodo buscaNodo(Estudiante elemento, Nodo n){
        if(n == null)
        return null;
      return n.elemento.equals(elemento)? n:buscaNodo(elemento, n.siguiente);
    }

    private boolean contiene(Nodo n, Estudiante elemento){
      if(n == null)
        return false;
      if(n.elemento.equals(elemento))
        return true;
      return contiene(n.siguiente, elemento);
    }

    /*metodo auxiliar para reversa*/
    private ListaEstudiante reversa(ListaEstudiante r, Nodo n){
      if (n == null){
        return r;
      }
      r.agregaFinal(n.elemento);
      return reversa(r, n.anterior);
    }

    /*metodo auxiliar para copia*/
    private ListaEstudiante copia(ListaEstudiante copiaLista, Nodo n){
      if (n == null){
        return copiaLista;
      }
      copiaLista.agregaFinal(n.elemento);
      return copia(copiaLista, n.siguiente);
    }

    /*metodo auxiliar para get*/
    private Estudiante get(Nodo n, int i, int c){
        if(i == c)
          return n.elemento;
        return get(n.siguiente, i--, c+1);
    }

    /*metodo auxiliar para indiceDe*/
    private int indiceDe(Nodo n, Estudiante elemento, int c){
      if(n == null)
        return -1;
      if(n.elemento.equals(elemento))
        return c;
      return indiceDe(n.siguiente, elemento, c+1);
    }

    /*metodo auxiliar para toString*/
    private String toString(String r, Nodo n){
        if(n == null)
          return r + "]";
        r+=", "+ n.elemento.toString();
        return toString(r, n.siguiente);
    }

    /*metodo auxiliar para equals*/
    private boolean equals(Nodo n, Nodo listaDiferente){
        if(n == null)
          return true;
        return n.elemento.equals(listaDiferente.elemento)? equals(n.siguiente,listaDiferente.siguiente) :false;
    }

}
