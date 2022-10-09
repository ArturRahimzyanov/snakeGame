import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(340, 345);
        //setLocation(400, 400);
        setLocationRelativeTo(null);
        add(new GameField());
        JPanel scorePanel = new JPanel();
        
        setVisible(true);
    }

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow();
    }
}
