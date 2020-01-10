import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Test {
	public static class Form extends JFrame{
		public Form() {
			/****************
			 *  一个简单的登录窗口
			 *  账号：123456
			 *  密码：123  
			 *****************/
			//---------Set Form title
			this.setTitle("From");
			//---------Main Container-cont------
			//实例化一个控件的容器，作为登录窗体的主容器
			Container cont = new Container();
			//设置cont的位置，大小
			cont.setBounds(0, 0, this.getWidth(), this.getHeight());
			//-------Account Label-jl1------
			JLabel jl1 = new JLabel("Acc:");
			//jl1.setText("Acc:");
			//设置jl1的位置，大小
			jl1.setBounds(40, 20, 50, 20);
			//添加jl1到cont
			cont.add(jl1);
			//--------Account TextBox-jt1-----------
			JTextField jt1 = new JTextField("",20);
			jt1.setBounds(90, 20, 100, 20);
			cont.add(jt1);
			//-------Password Label-jl2------
			JLabel jl2 = new JLabel("");
			jl2.setText("Pwd:");
			jl2.setBounds(40, 40, 50, 20);
			cont.add(jl2);			
			//--------Password TextBox-jp1-----------
			JPasswordField jp1 = new JPasswordField();
			//设置位置、大小
			jp1.setBounds(90, 40, 100, 20);
			//设置密码的掩码
			jp1.setEchoChar('*');
			//为cont添加控件
			cont.add(jp1);
			//------Button Login-jb1------
			JButton jb1 = new JButton("Login");
			jb1.setBounds(90, 70, 80, 25);
			cont.add(jb1);
			//为按钮添加事件监听器
			jb1.addActionListener(new ActionListener() {
				//actionPerformed 是发生事件时会调用的函数
				//重载该函数
				@Override
				public void actionPerformed(ActionEvent e) {
					//读取并记录账号、密码
					String Acc =jt1.getText();
					String Pwd =new String(jp1.getPassword());
					//实例化一个LoginModel : lm
					LoginModel  lm = new LoginModel();
					//为lm赋值登录信息
					lm.LoginAcc = Acc;
					lm.LoginPwd = Pwd;
					//调用函数检查登录并将结果记录在LoginIsSuccess中
					CheckLogin(lm);
					//重置密码输入框
					jp1.setText("");
					//判断是否登录成功
					if(lm.LoginIsSuccess == true)
					{
						//弹出登录成功对话框
						JOptionPane.showMessageDialog(null, "登录成功");
						//登录成功，重置账号输入框
						jt1.setText("");
					}
					else
					{
						//弹出登录失败对话框
						JOptionPane.showMessageDialog(null, "登录失败");
					}
				}
				
				
			});/*
			//-------Test------------
			JTextField JTF = new JTextField();
			JTF.setBounds(90, 40, 100, 20);
			cont.add(JTF);
			JButton jb1 = new JButton("Button");
			jb1.setBounds(90, 70, 80, 25);
			cont.add(jb1);
			jb1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String Input =JTF.getText();

						JOptionPane.showMessageDialog(null, Input);
				}
				
				
			});*/
			//-------Form State-------
			//设置位置、大小
			this.setBounds(300,200,580,600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//添加控件组
			this.add(cont);
			//---------Form Show---------
			this.setVisible(true);
		}
		/**function CheckLogin take LoginModel return LoginModel
		 * 当账号与密码正确时，LoginModel.LoginIsSuccess 将为 true
		 * 否则为false
		 * 通过判断LoginIsSuccess的值来得到是否登录成功
		 * */
		public LoginModel CheckLogin(LoginModel lm)
		{
			//理应连接数据库/网站后台
			//这里仅做测试
			if(lm.LoginAcc.equals("123456"))
			{
				if(lm.LoginPwd.equals("123"))
				{
					lm.LoginIsSuccess = true;
				}
			}
			return lm;
		}
		
		//登录模型
		//账号信息：(string)LoginAcc
		//密码信息：(string)LoginPwd
		//登录信息：(boolean)LoginIsSuccess 默认为 false
	    public class LoginModel
	    {
	    	String LoginAcc;
	    	String LoginPwd;
	    	boolean LoginIsSuccess = false;
	    }
	}
	//应用程序入口
    public static void main(String []args) {
    	//调用Form的构造函数
        new Form();
    }
}
