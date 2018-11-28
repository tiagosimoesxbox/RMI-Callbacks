/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IComunicacao extends Remote {
    String recebeUltimaMensagem() throws RemoteException;
    void adicionarListener(IListener l) throws RemoteException;
    void removerListener(IListener l) throws RemoteException;
}
