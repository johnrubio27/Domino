/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino;

import java.util.ArrayList;


public class Jugador {
    
    private ArrayList<Ficha> mano;
    // se inicializa la mano como un array list
    public Jugador( ) {
        this.mano = new ArrayList<Ficha>();
    }
    
    public void AddFicha( Ficha nueva){ //agregar ficha que se va recibiendo
        mano.add(nueva);
    }
    
    public ArrayList<Ficha> getMano(){ //devuelve la mano del jugador
        return mano;
    }
    
    public void RemoveFicha (int i){ //quitar ficha de la mano cuando se juega
        mano.remove(i);
    }
    
    
}
