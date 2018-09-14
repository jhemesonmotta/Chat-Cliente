package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaChat extends javax.swing.JFrame {

    static IServidor server;
    static Cliente cli;

    public TelaChat(){
        initComponents();
        IniciarRMI();
        try{
            addComboBox();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void IniciarRMI() {
        try {
            server = (IServidor) Naming.lookup("//localhost/ChatSrv");
            cli = new Cliente(server);
            cli.setNome(JOptionPane.showInputDialog("Nome: "));
            server.contatoCliente(cli);

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    public void addComboBox() throws RemoteException{
       ArrayList<String> clientes = new ArrayList<>();
       clientes = server.enviaClientes();
       for(String c: clientes){
           ComboBox_User.addItem(c);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taSaida = new javax.swing.JTextArea();
        tfnome = new javax.swing.JTextField();
        btnenviar = new javax.swing.JButton();
        ComboBox_User = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taSaida.setColumns(20);
        taSaida.setRows(5);
        jScrollPane1.setViewportView(taSaida);

        tfnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnomeActionPerformed(evt);
            }
        });

        btnenviar.setText("Enviar");
        btnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenviarActionPerformed(evt);
            }
        });

        ComboBox_User.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnenviar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ComboBox_User, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfnome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenviar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBox_User, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenviarActionPerformed
        try {
            enviar();
        } catch (RemoteException ex) {
            Logger.getLogger(TelaChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnenviarActionPerformed

    private void tfnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnomeActionPerformed
        try {
            enviar();
        } catch (RemoteException ex) {
            Logger.getLogger(TelaChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfnomeActionPerformed

    public static void main(String args[]) {
        new TelaChat().setVisible(true);
    }

    public void enviar() throws RemoteException {
        if (!tfnome.getText().equals("")) {
            server.enviaMensagem(cli.nome + ": " + tfnome.getText(),ComboBox_User.getSelectedItem().toString());
            tfnome.setText("");
            taSaida.setText("");
            taSaida.setText(cli.mensagem().toString());
        } else {
            JOptionPane.showMessageDialog(null, "Digite algo para enviar!!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboBox_User;
    private javax.swing.JButton btnenviar;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea taSaida;
    private javax.swing.JTextField tfnome;
    // End of variables declaration//GEN-END:variables
}
