/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacio;

import java.util.ArrayList;
import java.util.List;
import presentacio.PresentationLayer;

/**
 * Classe Singleton que permet accedir als controllers
 * per a interactuar entre pantalles
 * 
 * @author Ivan
 */
public class Manager {
    
    private static Manager single_instance = null;
    
    private final List<PresentationLayer> controllers;

    private Manager() {
        this.controllers = new ArrayList<>();
    }
    
    /***
     * Afegeix un controlador 
     * @param c 
     */
    public void addController(PresentationLayer c) {
        this.controllers.add(c);
    }
    
    /***
     * Obté un controlador via el nom de la classe
     * @param type
     * @return 
     */
    public Object getController(Class type) {
        Object ret = null;
        
        for (Object c: controllers)
        {
            if (c.getClass() == type)
                ret = c;
        }
        
        return ret;
    }
    
    /***
     * Singleton
     * @return 
     */
    public static Manager getInstance()
    {
        if (single_instance == null)
            single_instance = new Manager();
        
        return single_instance;
    }
}
