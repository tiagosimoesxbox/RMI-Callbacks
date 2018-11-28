
package temp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;



class Comunicacao extends UnicastRemoteObject implements IComunicacao  {
    
    ArrayList<IListener> listeneres;
    volatile long ultimo = 0;
    
    public Comunicacao() throws RemoteException {
        listeneres = new ArrayList<>();
    }

    @Override
    public synchronized String recebeUltimaMensagem() throws RemoteException {
        return String.valueOf(ultimo);
    }

    @Override
    public synchronized void adicionarListener(IListener l) throws RemoteException {
        System.out.println("Adicionado listener");
        listeneres.add(l);
    }

    @Override
    public synchronized void removerListener(IListener l) throws RemoteException {
        System.out.println("Removido listener");
        listeneres.remove(l);
    }
    
    synchronized void adicionarMensagem() {
        System.out.println("Mensagem atualizada");
        ultimo++;
    }
    public synchronized void notificar() {
        for (int i = 0; i < listeneres.size(); i++) {
            try {
                listeneres.get(i).recebiNovaMensagem(this.recebeUltimaMensagem());
            } 
            catch (RemoteException ex) {
                listeneres.remove(i);
                System.out.println("Um listener terminou");
            }
        }
    }
}

public class Servidor {


    public static void main(String[] args) throws RemoteException, InterruptedException {
        
        Registry r = LocateRegistry.createRegistry(1099);
        
        Comunicacao c = new Comunicacao();
        r.rebind("Servico", c);
        
        
        while (true) {
            c.adicionarMensagem();
            c.notificar();
            Thread.sleep(2000);
        }
    }
    
}
