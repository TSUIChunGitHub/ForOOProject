package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginJFrame extends JFrame {

	private JPanel mContentPane;
	private JPasswordField mTxtPassword;
	private JTextField mTxtUserId;
	private JLabel mLblUserId;
	private JLabel mLblPassword;
	private JLabel mLblWelcome;
	private JButton mBtnLogin;
	/**
	 * Create the frame.
	 */
	public LoginJFrame() {
		setResizable(false);
		setTitle("Leave Application System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 171);
		mContentPane = new JPanel();
		mContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mContentPane);
		mContentPane.setLayout(null);
		
		mBtnLogin = new JButton("Login");
		mBtnLogin.setBounds(101, 99, 84, 23);
		mContentPane.add(mBtnLogin);
		
		mLblWelcome = new JLabel("Welcome ");
		mLblWelcome.setFont(new Font("·s²Ó©úÅé", Font.PLAIN, 16));
		mLblWelcome.setBounds(109, 12, 63, 15);
		mContentPane.add(mLblWelcome);
		
		mTxtPassword = new JPasswordField();
		mTxtPassword.setBounds(120, 68, 96, 21);
		mContentPane.add(mTxtPassword);
		
		mTxtUserId = new JTextField();
		mTxtUserId.setBounds(120, 37, 96, 21);
		mContentPane.add(mTxtUserId);
		mTxtUserId.setColumns(10);
		
		mLblUserId = new JLabel("User ID:");
		mLblUserId.setBounds(41, 40, 46, 15);
		mContentPane.add(mLblUserId);
		
		mLblPassword = new JLabel("Password:");
		mLblPassword.setBounds(41, 71, 69, 15);
		mContentPane.add(mLblPassword);
	}
	
	public JPasswordField getJPasswordFiled(){
		return mTxtPassword;
	}
	
	public JTextField getJTextField(){
		return mTxtUserId;
	}
	
	public JButton getLoginJButton(){
		return mBtnLogin;
	}
	
}
