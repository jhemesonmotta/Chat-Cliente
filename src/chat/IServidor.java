package chat;

import java.rmi.Remote;
import java.util.ArrayList;

public interface IServidor extends Remote {
     public void enviaMensagem(String mensagem, String dest) throws java.rmi.RemoteException;   
     public void contatoCliente(Icliente cli) throws java.rmi.RemoteException;
     public ArrayList<String> enviaClientes() throws java.rmi.RemoteException;
     public boolean Atualizar() throws java.rmi.RemoteException;
}
