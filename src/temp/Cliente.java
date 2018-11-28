
package temp;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class Cliente extends UnicastRemoteObject implements IListener {
    
    public Cliente() throws RemoteException {}

    @Override
    public void recebiNovaMensagem(String msg) throws RemoteException {
        System.out.println("Recebi nova mensagem: '" + msg + "'");
    }
    
    @Override
    public void novoClienteInscrito() throws RemoteException {
        System.out.println("Novo cliente...");
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        IComunicacao cc = (IComunicacao) LocateRegistry.getRegistry(1099).lookup("Servico");
        Cliente c = new Cliente();
        
        cc.adicionarListener(c);
    }
}
