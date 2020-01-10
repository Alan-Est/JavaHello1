import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Test {
	public static class Form extends JFrame{
		public Form() {
			/****************
			 *  һ���򵥵ĵ�¼����
			 *  �˺ţ�123456
			 *  ���룺123  
			 *****************/
			//---------Set Form title
			this.setTitle("From");
			//---------Main Container-cont------
			//ʵ����һ���ؼ�����������Ϊ��¼�����������
			Container cont = new Container();
			//����cont��λ�ã���С
			cont.setBounds(0, 0, this.getWidth(), this.getHeight());
			//-------Account Label-jl1------
			JLabel jl1 = new JLabel("Acc:");
			//jl1.setText("Acc:");
			//����jl1��λ�ã���С
			jl1.setBounds(40, 20, 50, 20);
			//���jl1��cont
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
			//����λ�á���С
			jp1.setBounds(90, 40, 100, 20);
			//�������������
			jp1.setEchoChar('*');
			//Ϊcont��ӿؼ�
			cont.add(jp1);
			//------Button Login-jb1------
			JButton jb1 = new JButton("Login");
			jb1.setBounds(90, 70, 80, 25);
			cont.add(jb1);
			//Ϊ��ť����¼�������
			jb1.addActionListener(new ActionListener() {
				//actionPerformed �Ƿ����¼�ʱ����õĺ���
				//���ظú���
				@Override
				public void actionPerformed(ActionEvent e) {
					//��ȡ����¼�˺š�����
					String Acc =jt1.getText();
					String Pwd =new String(jp1.getPassword());
					//ʵ����һ��LoginModel : lm
					LoginModel  lm = new LoginModel();
					//Ϊlm��ֵ��¼��Ϣ
					lm.LoginAcc = Acc;
					lm.LoginPwd = Pwd;
					//���ú�������¼���������¼��LoginIsSuccess��
					CheckLogin(lm);
					//�������������
					jp1.setText("");
					//�ж��Ƿ��¼�ɹ�
					if(lm.LoginIsSuccess == true)
					{
						//������¼�ɹ��Ի���
						JOptionPane.showMessageDialog(null, "��¼�ɹ�");
						//��¼�ɹ��������˺������
						jt1.setText("");
					}
					else
					{
						//������¼ʧ�ܶԻ���
						JOptionPane.showMessageDialog(null, "��¼ʧ��");
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
			//����λ�á���С
			this.setBounds(300,200,580,600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//��ӿؼ���
			this.add(cont);
			//---------Form Show---------
			this.setVisible(true);
		}
		/**function CheckLogin take LoginModel return LoginModel
		 * ���˺���������ȷʱ��LoginModel.LoginIsSuccess ��Ϊ true
		 * ����Ϊfalse
		 * ͨ���ж�LoginIsSuccess��ֵ���õ��Ƿ��¼�ɹ�
		 * */
		public LoginModel CheckLogin(LoginModel lm)
		{
			//��Ӧ�������ݿ�/��վ��̨
			//�����������
			if(lm.LoginAcc.equals("123456"))
			{
				if(lm.LoginPwd.equals("123"))
				{
					lm.LoginIsSuccess = true;
				}
			}
			return lm;
		}
		
		//��¼ģ��
		//�˺���Ϣ��(string)LoginAcc
		//������Ϣ��(string)LoginPwd
		//��¼��Ϣ��(boolean)LoginIsSuccess Ĭ��Ϊ false
	    public class LoginModel
	    {
	    	String LoginAcc;
	    	String LoginPwd;
	    	boolean LoginIsSuccess = false;
	    }
	}
	//Ӧ�ó������
    public static void main(String []args) {
    	//����Form�Ĺ��캯��
        new Form();
    }
}
