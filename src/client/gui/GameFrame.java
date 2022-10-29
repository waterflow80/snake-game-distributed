package client.gui;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    //private static final long serialVersionUID = 1L;

    public GameFrame() throws NotBoundException, RemoteException {
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
