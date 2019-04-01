package domino;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Domino {

    private ArrayList<Jugador> jugadores; //array para guardar jugadores
    private int inicia, der, izq, cantidadjugadores; 
    private boolean finjuego;
    // inicializamos el array de jugadores

    public Domino() {   //constructor para iniciar array de jugadores
        jugadores = new ArrayList<Jugador>();  //inicia array de jugadores
        inicia = 0; 
        finjuego = false;
        cantidadjugadores = 4;
        der = 7;
        izq = 7;
    }

    public void juego() {       
        int seleccion = 0,k = 0; // seleccion captura el numero de la ficha del jugador y k es un axiliar para enumerar las fichas
        boolean finturno = false;  //booleano auxiliar para definir el fin de turno del jugador
        Scanner teclado = new Scanner(System.in); //inicia el objeto de leer por consola
        // creamos la cantidad de jugadores
        CrearJugadores(cantidadjugadores); //vairable para cantidad de jugadores
        RepartirFicha(); //crea ficha y reparte
        Quieninicia(); //inicia el doble 6

        while (!finjuego) { 

            if (der == 7) { //indica que aún no se han jugado fichas
                //indicar que inicie el juego
                System.out.println("Por favor Inicie el juego con doble 6");
            } else {
                // sino que continue con el juego
                System.out.println("\n \n \n lado izquierdo: " + izq + "\t lado derecho:" + der);
            }
            System.out.println("jugador " + (inicia+ 1) + "\n Selecione una Ficha de su mano escribiendo el numero que esta dentro del parentesis:");
            //muestra la ficha que tiene
            for (Ficha i : jugadores.get(inicia ).getMano()) { //traer mano de jugador e ir mostrando las opciones por jugador
                System.out.print(i.primer() + " | " + i.segundo() + "(" + k + ")\t"); //asignación de numero en ficha
                k++;
            }
            System.out.print("pasar (" + k + ") \n");
            finturno = true; //seleccionar ficha valida o pasar
            while (finturno) { 
                System.out.print("\t Escriba que ficha seleciona: "); 
                seleccion = teclado.nextInt(); //captura numero
                teclado.nextLine();
                if(verificarselecion(seleccion)){ 
                    // sacamos la ficha que fue valida del jugador
                    quitarficha(seleccion);
                    // pasamos al siguiente turno
                    finturno = false;
                    k = 0;
                    inicia = (inicia+1) % cantidadjugadores; //cuando se llega al limite de jugadores reinicia el ciclo
                }
            }
        }
        System.out.print("\n \n \n ---------------El ganador es el jugador  "+(inicia+1)+" Felicitaciones !!------------");
        
    }

    public void RepartirFicha() { 
        // se crea las fichas de domino y se guardan en el array
        ArrayList<Ficha> total = new ArrayList<Ficha>();
        int turno = 0, rand = 0;
        Random random = new Random();
        //creamos todas la fichas 
        for (int i = 0; i < 7; i++) { //se crean fichas del domino izquierda y derecha
            for (int j = i; j < 7; j++) {
                total.add(new Ficha(i, j));
            }
        }
        // repartimos las fichas entre los jugadores que existan
        while (!total.isEmpty()) {
            rand = random.nextInt(total.size());
            jugadores.get(turno % jugadores.size()).AddFicha(total.get(rand)); //el jugador recibe la ficha
            total.remove(rand); //resta fichas del total
            turno++;
        }
    }

    public void Quieninicia() {
        
        // se busca quien tiene el doble 6 para iniciar
        for (int i = 0; i < jugadores.size(); i++) {
            for (int j = 0; j < jugadores.get(i).getMano().size(); j++) {
                if ((jugadores.get(i).getMano().get(j).primer() == 6) && (jugadores.get(i).getMano().get(j).segundo() == 6)) { //verificar quien tiene doble 6
                    inicia = i;
                    return;
                }
            }
        }
    }

    public void CrearJugadores(int n) {
        //se crea los jugadores que van a jugar
        for (int i = 0; i < n; i++) {
            jugadores.add(new Jugador());
        }
    }

    public static void main(String[] args) {
        //creamos el juego y lo inicializamos
        Domino domino = new Domino();
        domino.juego();
    }

    private boolean verificarselecion( int i) {
        /* se verifica la seleccion
            --- si es el primero en dar
            --- si quiere pasar pero aun tiene ficha jugables
            --- si la ficha que seleciono es valida
            --- colocar la ficha en el lado continuo
        */
        Ficha fichaselecionada ;
        boolean verificar = false;
        if(i>jugadores.get(inicia ).getMano().size() || i<0){ //verficiar seleccion si es valida o no
            System.out.print("\t Selecion invalidad \n");
        }else{
            //seleciono pasar verificar si no tiene ficha para mover
            if(i == jugadores.get(inicia).getMano().size()){  //verificar si en realidad pasa o no
                if(der == 7 || izq == 7){ 
                    System.out.print("\t Seleccione el doble 6 por favor \n");
                }else{
                    
                    for(int k = 0; k < jugadores.get(inicia ).getMano().size() ; k++){ //verficar si tiene ficha para jugar
                        if(der == jugadores.get(inicia).getMano().get(k).primer() || izq == jugadores.get(inicia).getMano().get(k).primer() || der == jugadores.get(inicia).getMano().get(k).segundo()|| izq == jugadores.get(inicia).getMano().get(k).segundo()){
                            System.out.print("\t no se puede pasar con ficha para jugar \n");
                            return false;
                        }
                    }
                    return true;
                }
            }else{
                fichaselecionada  = jugadores.get(inicia).getMano().get(i); //verificar hacia que lado va la ficha der o izq
                // verificar si es la primera ficha
                if(der == 7 || izq == 7){ 
                    // verificar que halla selecionado la ficha doble 6
                    if(fichaselecionada.primer() == 6 && fichaselecionada.segundo() == 6){ //si es la primera ficha iniciar con doble
                        der = fichaselecionada.primer(); //coloca en lado derecho nuevo valor
                        izq = fichaselecionada.segundo(); //coloca en izquierda el nuevo valor
                        return true;
                    }else{
                        System.out.print("\t Seleccione el doble 6 por favor \n");
                    }
                }else{
                    // si la ficha selecionada puede ir por la derecha verificando de que lado se pone
                    if(der == fichaselecionada.primer()){
                        der = fichaselecionada.segundo();
                        return true;
                    }
                    if(der == fichaselecionada.segundo()){
                        der = fichaselecionada.primer();
                        return true;
                    }
                    // si la ficha selecionada puede ir por la izquierda
                    if(izq == fichaselecionada.primer()){
                        izq = fichaselecionada.segundo();
                        return true;
                    }
                    if(izq == fichaselecionada.segundo()){
                        izq = fichaselecionada.primer();
                        return true;
                    }
                    System.out.print("\t Selecione una ficha validad o pase \n");
                }
            }    
        }
        return false;
    }

    private void quitarficha(int seleccion) { //quitar ficha de la mano cuando se jugó
        // verificamos si seleciono pasar
        if(!(seleccion == jugadores.get(inicia).getMano().size())){
            jugadores.get(inicia).getMano().remove(seleccion);
        }
        // verificar si gano
        if(jugadores.get(inicia).getMano().size() == 0){
            finjuego = true;
        }
    }

}
