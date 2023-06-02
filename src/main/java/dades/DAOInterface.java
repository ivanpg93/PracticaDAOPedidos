/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dades;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ivan
 * @param <T>
 */
public interface DAOInterface<T> {
    
    /***
     * Retorna tots els elements de la taula
     * @return 
     * @throws java.sql.SQLException 
     */
    public List<T> getAll() throws SQLException;
    
    /***
     * Desa un nou element a la taula
     * @param t
     * @return 
     * @throws java.sql.SQLException si l'element ja existeix
     */
    public void save(T t) throws SQLException;
    
    /***
     * Actualitza un element existent a la taula pel seu id
     * L'element ha d'existir.
     * @param t 
     * @throws java.sql.SQLException si l'element no existeix
     */
    public void update(T t) throws SQLException;
    
     /***
     * Elimina un element de la taula pel seu id
     * L'element ha d'existir.
     * @param t 
     * @throws java.sql.SQLException si l'element no existeix
     */
    public void delete(T t) throws SQLException;
    
    /***
     * Recupera un element de la taula pel seu id o null si no existeix
     * @param t
     * @return
     * @throws SQLException 
     */
    public T get(T t) throws SQLException;
    
}
