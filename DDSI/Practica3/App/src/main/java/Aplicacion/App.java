package Aplicacion;

import Contralador.ControladorLogin;

/**
 *
 *  !TODO 
 * 
 *  In 'eliminaSocio' / 'eliminaMonitor' there is a ISocioDAO/IMonitorDao object so we need to
 *  check dataBase to instanciate the right object. Can we fix that ?
 *
 *
 * 
 *  Recive 'boolean database' as an argument for the constructor and set it. Then send 'this.dataBase'
 *  to both ControladorAddMonitor/ControladorAddSocio so they can instanciate the right DAO object.
 * 
 * 
 *  Fix both OracleDAOs object sintax so they can do requests to Oracle DB
 * 
 */


/**
 *
* @author ElPsy
 */
public class App {
    
    public static void main(String args[]){
        new ControladorLogin();
    }
}

// jdbc:mariadb://172.18.1.241:3306/DDSI_024
// jdbc:mariadb://172.18.1.241:3306/DDSI_001
// jdbc:mariadb://172.18.1.241:3306/DDSI_022






