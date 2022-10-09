import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private int score = 0;
    private final int DotSize = 16;
    private final int AllDots = 400;
    private Image dot;
    private Image bigApple;
    private Image apple;
    private int appleX;
    private int appleY;
    private int BigAppleX;
    private int BigAppleY;
    private int[] x = new int[AllDots];
    private int[] y = new int[AllDots];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameField() {
        setBackground(Color.BLACK);
        loadImages();
        initGame();
        addKeyListener(new KeyListener());
        setFocusable(true);
    }

    public void initGame(){
        dots = 3;
        for(int i = 0; i < dots; i++){
            x[i] = 48 - i * DotSize;
            y[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    public void createApple(){
        appleX = new Random().nextInt(20) * DotSize;
        appleY = new Random().nextInt(20) * DotSize;
    }
    public void createBigApple(){
        BigAppleX = new Random().nextInt(20) * DotSize;
        BigAppleY = new Random().nextInt(20) * DotSize;
    }

    public void loadImages() {
        ImageIcon imageIconApple = new ImageIcon("apple.png");
        apple = imageIconApple.getImage();
        ImageIcon imageIconDot = new ImageIcon("dot.png");
        dot = imageIconDot.getImage();
        ImageIcon imageIconBigApple = new ImageIcon("м€ч.png");
        bigApple = imageIconBigApple.getImage();
    }

    public void checkCollisins(){
        for(int i = dots; i > 0; i--){
            if( x[0] == x[i] && y[0] == y[i] ){
                inGame = false;
            }
        }
        if( x[0] > SIZE ){
            inGame = false;
        }
        if( x[0] < 0 ){
            inGame = false;
        }
        if( y[0] < 0 ){
            inGame = false;
        }
        if( y[0] > SIZE ){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(inGame){
             move();
             checkApple();
             checkCollisins();
         }
         repaint();
    }

    private void checkApple() {

        if(x[0] == appleX && y[0] == appleY){
            dots++;
            if(dots % 4 == 0) createBigApple();
            createApple();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            if(dots % 4 == 0) {
                g.drawImage(bigApple, appleX, appleY, this);
            }else{
                g.drawImage(apple, appleX, appleY, this);
            }
            for(int i = 0; i < dots; i++){
                g.drawImage(dot, x[i], y[i], this);
            }
        }else {
            String str = "Game Over - Ћох";
            g.setColor(Color.white);
            g.drawString(str, 125, SIZE/2);
        }
    }

    private void move() {
       for (int i = dots; i > 0; i--){
           x[i] = x[i - 1];
           y[i] = y[i - 1];
       }
       if(left){
           x[0] = x[0] - DotSize;
       }
        if(right){
            x[0] = x[0] + DotSize;
        }
        if( up ){
            y[0] = y[0] - DotSize;
        }
        if(down){
            y[0] = y[0] + DotSize;
        }
    }

    class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && (right == false)){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && (left == false)){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && (down == false)){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && (up == false)){
                right = false;
                left = false;
                down = true;
            }
        }
    }

}
