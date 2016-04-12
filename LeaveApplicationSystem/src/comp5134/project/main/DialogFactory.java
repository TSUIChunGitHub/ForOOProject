package comp5134.project.main;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import javax.swing.JOptionPane;

public class DialogFactory {
	public final static int ERROR_MESSAGE = 0;
	public final static int INFORMATION_MESSAGE = 1;
	public final static int WARNING_MESSAGE = 2;
	
	public static void showMessageDialog(String message, String title, int messageCode){
		JOptionPane.showMessageDialog(null, title ,message , messageCode);  
	}
}
