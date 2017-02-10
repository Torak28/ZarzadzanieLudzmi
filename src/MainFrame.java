import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Torak28 on 10.02.2017.
 */
public class MainFrame {
	private JPanel panel1;
	private JButton zapisButton;
	private JLabel label1;

	public JLabel getLabel1(){
		return label1;
	}
	public void setLabel1(String in){
		label1.setText(in);
	}

	public MainFrame() {
		zapisButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setLabel1("Ala ma kota");
			}
		});
	}
	public void show(){
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MainFrame().panel1);
		frame.pack();
		frame.setVisible(true);
	}
}
