import java.awt.Color;

import javax.swing.*;

public class Controller {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
				{

					@Override
					public void run() {
						myPanel panel = new myPanel();
						JFrame frame = new JFrame();
						frame.setTitle("Pranto's Meal Calculator");		
						frame.setSize(1200,600);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setResizable(false);
						frame.setVisible(true);
						frame.add(panel);
					}
			
				});
		
		
		
		
	}

}