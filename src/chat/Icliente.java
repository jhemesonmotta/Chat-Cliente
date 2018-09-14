
package chat;
import java.rmi.Remote;

public interface Icliente extends Remote{
    public void notificaRecebimento(String mensagem) throws java.rmi.RemoteException;
    public String retornaNome() throws java.rmi.RemoteException;
    public String getNome() throws java.rmi.RemoteException;
}
