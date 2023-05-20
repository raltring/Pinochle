package src.gameanagement;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


@SuppressWarnings("serial")
public class GameSetup extends JFrame implements ActionListener
{
    JFrame frame;
	JTextField text;
	public GameSetup()
    {
        frame = new JFrame("Welcome to Pinochle");
        
        JPanel panel = new JPanel();
        
        JLabel label = new JLabel("How many decks?");
        text = new JTextField("1");
        
        JButton button = new JButton("Start");
        button.addActionListener(this);
        
        
        panel.add(text);
        panel.add(label);
        panel.add(button);
        panel.setBackground(Color.green);
        frame.add(panel);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	int decks = Integer.parseInt(text.getText());
    	System.out.println();
    	Game game = new Game(decks);
    	frame.dispose();
    	game.startGame();
    }
}
