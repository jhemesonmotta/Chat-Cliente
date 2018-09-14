package chat;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class Cliente extends UnicastRemoteObject implements Icliente {

    String nome;
    static IServidor server;
    ArrayList<String> msg = new ArrayList<>();
        
    public Cliente(IServidor server) throws RemoteException {
        super();
        Cliente.server = server;
    }

    @Override
    public void notificaRecebimento(String mensagem) throws RemoteException {
        //this.msg.add("\n" + nome+":" + mensagem);
        this.msg.add("\n" + mensagem);
    }

    public ArrayList<String> mensagem() {
        return msg;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String retornaNome() throws RemoteException {
        return nome;
    }

}
