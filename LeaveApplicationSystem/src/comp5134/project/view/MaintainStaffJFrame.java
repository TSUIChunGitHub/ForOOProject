package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comp5134.project.model.LogoutListener;

import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MaintainStaffJFrame extends JFrame{
	private JPanel mContentPane;
	private JButton mBtnDeleteStaff;
	private JButton mBtnCreateStaff;
	private JButton mBtnAssignStaff;
	/* For call back */
	private LogoutListener mLogoutListener;

	public MaintainStaffJFrame() {
		setResizable(false);
		setTitle("Maintain Staff");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 201, 137);
		mContentPane = new JPanel();
		mContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mContentPane);
		mContentPane.setLayout(null);
		
		mBtnDeleteStaff = new JButton("Delete Staff");
		mBtnDeleteStaff.setBounds(10, 43, 166, 23);
		mContentPane.add(mBtnDeleteStaff);
		
		mBtnCreateStaff = new JButton("Create Staff");
		mBtnCreateStaff.setBounds(10, 10, 166, 23);
		mContentPane.add(mBtnCreateStaff);
		
		mBtnAssignStaff = new JButton("Assign Staff");
		mBtnAssignStaff.setBounds(10, 76, 166, 23);
		mContentPane.add(mBtnAssignStaff);
		
		/* When this page close, It will return to login page, It is for listen the close event */
		addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            	/* CallBack:notfiy show the login page */
            	mLogoutListener.onLogout();
                e.getWindow().dispose();
            }
		});
	}
	
	public JButton getCreateStaffJButton(){
		return mBtnCreateStaff;
	}
	
	public JButton getDeleteStaffJButton(){
		return mBtnDeleteStaff;
	}
	
	public JButton getAssignStaffJButton(){
		return mBtnAssignStaff;
	}
	
	public void setOnLogOutListener(LogoutListener logoutListener){
		this.mLogoutListener = logoutListener;
	}
}
