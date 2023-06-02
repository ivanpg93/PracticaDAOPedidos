/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Client;

/**
 * Capa lógica de clients
 *
 * @author Ivan
 */
public class ClientLogic extends LogicLayer {
    
    static String emailClient;

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        ClientLogic.emailClient = emailClient;
    }

    public ClientLogic() throws LogicLayerException {
        super();
    }
    
    public Client getClient(String email) throws LogicLayerException
    {
        try {
            Client ret = null;
            
            ret = this.getClientDAO().get(new Client(email));
            
            return ret;
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant oficina " + email + " : " + ex.toString());
        }
    }

    /**
     * *
     * Recupera tots els clients de la taula
     *
     * @return
     * @throws LogicLayerException
     */
    public List<Client> getAll() throws LogicLayerException {
        List<Client> ret = new ArrayList<>();

        try {
            ret = this.getClientDAO().getAll();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant tots els clients: " + ex);
        }

        return ret;
    }
    
    public Double getDefaultCreditLimit() throws LogicLayerException {
        Double ret = null;

        try {
            ret = this.getClientDAO().getDefaultCreditLimit();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant el límit de credit per defecte dels clients: " + ex);
        }

        return ret;
    }
    
    public int getMinCustomerAge() throws LogicLayerException {
        int ret = 0;

        try {
            ret = this.getClientDAO().getMinCustomerAge();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant el límit de credit per defecte dels clients: " + ex);
        }

        return ret;
    }

    public void afegirClient(Client c) throws LogicLayerException, ParseException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("El client no es pot afegir a la BBDD");
            }

            // continuem amb el procès
            this.getClientDAO().afegirClient(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades afegint el client: " + c + " : " + ex);
        }
    }
    
    public void deleteClient(Client c) throws LogicLayerException, ParseException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("El client no es pot esborrar de la BBDD");
            }

            // continuem amb el procès
            this.getClientDAO().delete(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades esborrant el client: " + c + " : " + ex);
        }
    }
    
    public void editarClient(Client c) throws LogicLayerException, ParseException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("El client no es pot editar en la BBDD");
            }

            // continuem amb el procès
            this.getClientDAO().editarClient(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades modificant el client: " + c + " : " + ex);
        }
    }

    @Override
    public void close() throws LogicLayerException {
        try {

            if (this.getClientDAO() != null) {
                System.out.println("Tancant clientDAO...");
                this.getClientDAO().close();
            }

        } catch (SQLException ex) {
            throw new LogicLayerException("Error tancant capa logica: " + formatSQLException(ex));
        }
    }
}
