package comp5134.project.view;
/**
 * Created by:TSUI Chun
 * Student Id:15036709g
 * Date:10/4/2016
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import comp5134.project.model.LogoutListener;

public class MaintainLeaveJFrame extends JFrame {

	private JPanel contentPane;
	
	private JButton mBtnReqeustToLeave;
	private JButton mBtnViewLeaveRequestState;
	private JButton mBtnHandleLeaveRequest;
	
	private LogoutListener mLogoutListener;

	public MaintainLeaveJFrame() {
		setResizable(false);
		setTitle("Maintain Leave");
		setBounds(100, 100, 255, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mBtnReqeustToLeave = new JButton("Request To Leave");
		mBtnReqeustToLeave.setBounds(10, 10, 219, 23);
		contentPane.add(mBtnReqeustToLeave);
		
		mBtnViewLeaveRequestState = new JButton("View Leave Reqeust State");
		mBtnViewLeaveRequestState.setBounds(10, 43, 219, 23);
		contentPane.add(mBtnViewLeaveRequestState);
		
		mBtnHandleLeaveRequest = new JButton("Handle Leave Request");
		mBtnHandleLeaveRequest.setBounds(10, 76, 219, 23);
		contentPane.add(mBtnHandleLeaveRequest);
		
		/* When this page close, It will return to login page, It is for listen the close event */
		addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
            	/* CallBack:notfiy show the login page*/
            	mLogoutListener.onLogout();
                e.getWindow().dispose();
            }
		});
	}
	
	public JButton getBtnReqeustToLeave(){
		return mBtnReqeustToLeave;
	}
	
	public JButton getBtnViewLeaveRequestState(){
		return mBtnViewLeaveRequestState;
	}
	
	public JButton getBtnHandleLeaveRequest(){
		return mBtnHandleLeaveRequest;
	}
	
	public void setOnLogOutListener(LogoutListener logoutListener){
		this.mLogoutListener = logoutListener;
	}
}
