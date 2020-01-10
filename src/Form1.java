import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


import java.sql.*;

/****************
 *  *ѧ����Ϣ����ϵͳ
 *  *2019��12��22��00:34:43
 *  *2019��12��22��13:47:11
 *  *
 *  *
 *  *
 *  *
 *****************/
public class Form1 extends JFrame{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    static final String USER = "root";
    static final String PASS = "123456";
     
		public Form1() {
             //����ѧ����������
			//Test();Test();Test();Test();Test();
			//---------Set Form title
			this.setTitle("ѧ����Ϣ����ϵͳ");
			//---------Main Container-cont------
			//ʵ����һ���ؼ�������
			Container cont = new Container();
			//������¼
			container = cont; 
			//����cont��λ�ã���С
			cont.setBounds(0, 0, this.getWidth(), this.getHeight());
			//��ʾ���ݱ�
			AddTable(cont);
	        //
			JLabel lb1 = new JLabel("����");
			lb1.setBounds(10, 0, 60, 25);
			cont.add(lb1);
			JTextField jt1 = new JTextField();
			jt1.setBounds(70, 0, 120, 25);
			cont.add(jt1);
	        //
			JLabel lb2 = new JLabel("����");
			lb2.setBounds(10, 25, 60, 25);
			cont.add(lb2);
			JTextField jt2 = new JTextField();
			jt2.setBounds(70,25, 120, 25);
			cont.add(jt2);
	        //
			JLabel lb3 = new JLabel("java�ɼ�");
			lb3.setBounds(10, 50, 60, 25);
			cont.add(lb3);
			JTextField jt3 = new JTextField();
			jt3.setBounds(70, 50, 120, 25);
			cont.add(jt3);
	        //
			JLabel lb4 = new JLabel("C#�ɼ�");
			lb4.setBounds(10, 75, 60, 25);
			cont.add(lb4);
			JTextField jt4 = new JTextField();
			jt4.setBounds(70, 75, 120, 25);
			cont.add(jt4);
	        //
			JLabel lb5 = new JLabel("sql�ɼ�");
			lb5.setBounds(10, 100, 60, 25);
			cont.add(lb5);
			JTextField jt5 = new JTextField();
			jt5.setBounds(70, 100, 120, 25);
			cont.add(jt5);
	        //
			JLabel lb6 = new JLabel("html�ɼ�");
			lb6.setBounds(10, 125, 60, 25);
			cont.add(lb6);
			JTextField jt6 = new JTextField();
			jt6.setBounds(70, 125, 120, 25);
			cont.add(jt6);

			
			//------Button -jb1------
			JButton jb1 = new JButton("�����Ϣ");
			jb1.setBounds(10, 170, 120, 25);
			cont.add(jb1);
			//Ϊ��ť����¼�������
			jb1.addActionListener(new ActionListener() {
				//actionPerformed �Ƿ����¼�ʱ����õĺ���
				//���ظú���
				@Override
				public void actionPerformed(ActionEvent e) {
				//���ѧ����Ϣ
					try {
						String name = jt1.getText();
						int age = Integer.parseInt(jt2.getText());
						int java = Integer.parseInt(jt3.getText());
						int cs = Integer.parseInt(jt4.getText());
						int sql = Integer.parseInt(jt5.getText());
						int html = Integer.parseInt(jt6.getText());
						Student student = AddStudent(age, name, java, cs, sql, html,MaxId+1);
						jt1.setText("");jt2.setText("");jt3.setText("");
						jt4.setText("");jt5.setText("");jt6.setText("");
						UpdateData();
						DataSave_StudentWasCreated(student);
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, "�������Ϣ����");
						}
				}
			});
			//------Button -jb2------
			JButton jb2 = new JButton("ɾ����Ϣ");
			jb2.setBounds(130, 170, 120, 25);
			cont.add(jb2);
			//Ϊ��ť����¼�������
			jb2.addActionListener(new ActionListener() {
				//actionPerformed �Ƿ����¼�ʱ����õĺ���
				//���ظú���
				@Override
				public void actionPerformed(ActionEvent e) {
				//
					String s=JOptionPane.showInputDialog("��������Ҫɾ����ѧ��ID:");
					if(s!=null)
					{
						//������ȷ��
						int id = -1;
						//����ת��
						try {
							id = Integer.parseInt(s);}
						catch(Exception ex){}
						//
						if(id!=-1)
						{
							if(!RemoveStudent(id))
							{JOptionPane.showMessageDialog(null, "id������");}
						}
						else {
							{
								//����������Ч
								JOptionPane.showMessageDialog(null, "������Ч,������һ����ȷ�����֣�");
							}
						}
					}
				}
			});
			//-------Form State-------
			//����λ�á���С
			this.setBounds(300,200,580,600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//��ӿؼ���
			this.add(cont);
			//�ߴ�仯
			this.addComponentListener(new ComponentAdapter(){
				@Override public void componentResized(ComponentEvent e){
					//cont.setBounds(e.COMPONENT_RESIZED);
					//����仯��ĳߴ�
					//System.out.println(getBounds());
				}});
			
			
			//*****
			DataLoad();
			UpdateData();
			//---------Form Show---------
			this.setVisible(true);
			
		}
		
		private void AddTable(Container cont)
		{
			try {
			// ����ָ�����������ݵı��
			JTable table = new JTable(new StudentTableModel());
			table.setBounds(200, 0,500, 300);
			JScrollPane jScrollPane1 = new JScrollPane();
	        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        jScrollPane1.setBounds(new Rectangle(10, 200,500, 300));
	        this.getContentPane().add(jScrollPane1, null);
	        jScrollPane1.getViewport().add(table, null);
	        cont.add(jScrollPane1);
			jTable = table;
			jScrollPane = jScrollPane1;
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null,e.toString());
			}

		}
		private JTable jTable = null;
		private JScrollPane jScrollPane = null;
		private Container container = null;
		//��������
		private void UpdateData()
		{
			jTable.setModel(new StudentTableModel());
			
		}
		//��������id
		private void SortID()
		{
			int id = 0;
			for(Student s :StudentsList)
			{
				s.id = id++;
			}
		}
		
		//���һ�������û�
		public void Test() {
			AddStudent(17,"233",59,58,57,56,MaxId+1);
		}
		
		//�޸ĳɼ�
		private void ChgAAFormId(int id)
		{
			Student student = FindStudentFromID(id);
			student.all = (student.java+student.cs+student.sql+student.html);
			student.ave = student.all / 4;
		}
		//ɾ��ѧ��
		private boolean RemoveStudent(int id)
		{
			Student student = FindStudentFromID(id);
			if(student !=null)
			{
				StudentsList.remove(FindStudentFromID(id));
				DataDalete_FormId(id);
				//SortID();
				UpdateData();
				return true;
			}
			return false;
		}
		//����ѧ����Ϣ
		private Student AddStudent(int age,String nam,int java,int cs,int sql,int html,int id)
		{
			//ʵ����ѧ����Ϣ�ಢ��ֵ
			Student st = new Student();
			st.age=age;
			st.name = nam;
			st.id = id;
			st.html = html;
			st.cs = cs;
			st.java = java;
			st.sql = sql;
            StudentsList.add(st);
			Update_SetMaxId();
			//System.out.println(MaxId);
            //�����ֺܷ�ƽ����
            ChgAAFormId(st.id);
            //����debug
			//System.out.println("id��"+st.id+"���֣�"+st.name+"����"+st.age);
			return st;
		}
		//�����ɹ�����ָ��id��ѧ�����󣬷��򷵻�null
		private Student FindStudentFromID(int id)
		{
			for(Student s :StudentsList)
			{
				if(s.id == id)
				{
					return s;
				}
			}
			return null;
		}
		//�����ɹ�����true ���򷵻�false
		private boolean DeleteStudentFromID(int id)
		{
			for(Student s :StudentsList)
			{
				if(s.id == id)
				{
					StudentsList.remove(id);
					DataDalete_FormId(id);
					return true;
				}
			}
			return false;
		}
		//����id �޸�ѧ��ָ����Ŀ�ĳɼ�
		private void ChangeStudentInfoFromId(int id , StdCls sc,int score)
		{
			Student st = null;
			for(Student s :StudentsList)
			{
				if(s.id == id)
				{
					st = s;
					break;
				}
			}
			//= StudentsList.get(id);
			switch (sc) {
			case java:
				st.java = score;
				break;
			case cs:
				st.cs = score;
				break;
			case sql:
				st.sql = score;
				break;
			case html:
				st.html = score;
				break;

			default:
				break;
			}
			
		}
		//ѧ��ö��
		public static enum Std{
			//����
			name,
			//���
			id,
			//����
			age,
			//����ɼ�
			java,
			cs,
			sql,
			html,
			//�ܷ�
			all,
			//ƽ����
			ave
		}
		//ѧ���γ�ö��
		public static enum StdCls{
			java,
			cs,
			sql,
			html
		}
		
		//ѧ���� List
		public static ArrayList<Student> StudentsList = new ArrayList<Student>();
		//����ID
		public static int MaxId = 0;
		//ɾ��ѧ����Ϣuse ID
		public void DataDalete_FormId(int id)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // ע�� JDBC ����
	            Class.forName(JDBC_DRIVER);
	        
	            // ������
	            //System.out.println("�������ݿ�...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // ִ�в�ѯ
	            //System.out.println(" ʵ����Statement����...");
	            stmt = conn.createStatement();
	            String sql;
	            
	            sql = "Delete from students where id=";
	            sql = sql + id;
	            System.out.println(sql);
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // ���� JDBC ����
	        	System.out.println("SQL�������");
	            //se.printStackTrace();
	        }catch(Exception e){
	            // ���� Class.forName ����
	            e.printStackTrace();
	        }finally{
	            // �ر���Դ
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// ʲô������
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("��ȡ���");
	        
		}
		//����ӳ�Աʱ����ѧ����Ϣ
		//������Ϣ
		public void DataSave_StudentWasCreated(Student s)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // ע�� JDBC ����
	            Class.forName(JDBC_DRIVER);
	        
	            // ������
	            //System.out.println("�������ݿ�...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // ִ�в�ѯ
	            //System.out.println(" ʵ����Statement����...");
	            stmt = conn.createStatement();
	            String sql;
	            Student stu =s;
	            
	            sql = "insert into students(Student,id,age,java,cs,sql1,html) values(";
	            sql = sql+"'"+stu.name+"',";
	            sql = sql+"'"+stu.id+"',";
	            sql = sql+"'"+stu.age+"',";
	            sql = sql+"'"+stu.java+"',";
	            sql = sql+"'"+stu.cs+"',";
	            sql = sql+"'"+stu.sql+"',";
	            sql = sql+"'"+stu.html+"')";
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // ���� JDBC ����
	        	System.out.println("SQL�������");
	            //se.printStackTrace();
	        }catch(Exception e){
	            // ���� Class.forName ����
	            e.printStackTrace();
	        }finally{
	            // �ر���Դ
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// ʲô������
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("��ȡ���");
	        
		}
		//�޸�ѧ����Ϣ������Ϊ�ַ���
		public void DataSave_ChangeFormId(int id,int target,String value)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // ע�� JDBC ����
	            Class.forName(JDBC_DRIVER);
	        
	            // ������
	            //System.out.println("�������ݿ�...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // ִ�в�ѯ
	            //System.out.println(" ʵ����Statement����...");
	            stmt = conn.createStatement();
	            String sql;
	            Student stu =FindStudentFromID(id);
	            String []targetStrings = {"Student","id","age","java","cs","sql","html"};
	            
	            sql = "UPDATE students" + " SET "+targetStrings[target]+ "=" +value+ " WHERE id = "+id;
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // ���� JDBC ����
	            se.printStackTrace();
	        }catch(Exception e){
	            // ���� Class.forName ����
	            e.printStackTrace();
	        }finally{
	            // �ر���Դ
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// ʲô������
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        
		}
		//�ڵ�Ԫ���޸�ʱ������ָ��id��ѧ������Ϣ�����ݿ���
		//��������ʹ��id
		public void DataSave_ChangeFormId(int id,int target,int value)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // ע�� JDBC ����
	            Class.forName(JDBC_DRIVER);
	        
	            // ������
	            //System.out.println("�������ݿ�...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // ִ�в�ѯ
	            //System.out.println(" ʵ����Statement����...");
	            stmt = conn.createStatement();
	            String sql;
	            Student stu =FindStudentFromID(id);
	            String []targetStrings = {"Student","id","age","java","cs","sql","html"};
	            sql = "UPDATE students" + " SET "+targetStrings[target]+ "=" +value+ " WHERE id = "+id;
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // ���� JDBC ����
	            se.printStackTrace();
	        }catch(Exception e){
	            // ���� Class.forName ����
	            e.printStackTrace();
	        }finally{
	            // �ر���Դ
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// ʲô������
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
		}
		//��ʼ���� ��ȡ���ݿ�
		public void DataLoad() {
	        Connection conn = null;
	        Statement stmt = null;
	        try{
	            // ע�� JDBC ����
	            Class.forName(JDBC_DRIVER);
	        
	            // ������
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = conn.createStatement();
	            String sql;
	            sql = "SELECT * FROM students";
	            //ִ��
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            // չ����������ݿ�
	            while(rs.next()){
	                // ͨ���ֶμ���
	                int id  = rs.getInt("id");
	                String name = rs.getString("Student");
	                int  age = rs.getInt("age");
	                int java = rs.getInt("java");
	                int cs = rs.getInt("cs");
	                int sql1 = rs.getInt("sql1");
	                int html = rs.getInt("html");
	                AddStudent(age, name, java, cs, sql1, html,id);
	            }
	            // ��ɺ�ر�
	            rs.close();
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // ���� JDBC ����
	            se.printStackTrace();
	        }catch(Exception e){
	            // ���� Class.forName ����
	            e.printStackTrace();
	        }finally{
	            // �ر���Դ
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// ʲô������
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("��ȡ���");
	        } 
		public void Update_SetMaxId()
		{
			for (Student student : StudentsList) {
				if(student.id >=MaxId)
				{
					MaxId = student.id;
				}
			}
			System.out.println(MaxId);
		}
        /**********************
         * *ѧ����Ϣ��ģ��
         * *
         * *
         **********************/  
	    private class StudentTableModel extends AbstractTableModel  
	    {  

	    	//JTable table = new JTable();
			String[] columnNames = { "����", "���","����","java�ɼ�","c#�ɼ�","sql�ɼ�","html�ɼ�","�ܷ�","ƽ����"}; // ��������������
			Object object[][] = new Object[StudentsList.size()][9];
	  
	        /** 
	                    * ���췽��
	         */  
	        public StudentTableModel()  
	        {  
	        	
				for (int i = 0 ; i < StudentsList.size();i++)
				{
					for(int j = 0;j<9;j++)
					{
						
						//object[i][j];
						
						switch (Std.values()[j]) {
						case name:
						{
							//System.out.println(StudentsList.get(i).name);
							//object[i][j] = "1";
							//if(StudentsList.get(i)!=null)
							object[i][j] = StudentsList.get(i).name;
							
							break;
						}
						case id:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).id);
							break;
						}	
						case age:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).age);
							break;
						}
						case java:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).java);
							break;
						}
						case cs:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).cs);
							break;
						}
						case sql:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).sql);
							break;
						}
						case html:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).html);
							break;
						}
						case all:
						{
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).all);
							break;
						}
						case ave:
						{
							
							//object[i][j] = "1";
							object[i][j] = Integer.toString(StudentsList.get(i).ave);
							break;
						}

						default:
							break;
						}
					}
				}
				
	        }  
	  
	        // ����Ϊ�̳���AbstractTableModle�ķ����������Զ���  
	        /** 
	         * �õ����� 
	         */  
	        @Override  
	        public String getColumnName(int column)  
	        {  
	            return columnNames[column];  
	        }  
	          
	        /** 
	         * ��д�������õ�������� 
	         */  
	        @Override  
	        public int getColumnCount()  
	        {  
	            return columnNames.length;  
	        }  
	  
	        /** 
	         * �õ�������� 
	         */  
	        @Override  
	        public int getRowCount()  
	        {  
	            return object.length;  
	        }  
	  
	        /** 
	         * �õ���������Ӧ���� 
	         */  
	        @Override  
	        public Object getValueAt(int rowIndex, int columnIndex)  
	        {  
	            return object[rowIndex][columnIndex];  
	        }  
	  
	        /** 
	         * �õ�ָ���е��������� 
	         */  
	        @Override  
	        public Class<?> getColumnClass(int columnIndex)  
	        {  
	            return object[0][columnIndex].getClass();  
	        }  
	  
	        /** 
	         * ָ���������ݵ�Ԫ�Ƿ�ɱ༭.��������"����","ѧ��"���ɱ༭ 
	         */  
	        @Override  
	        public boolean isCellEditable(int rowIndex, int columnIndex)  
	        {  
	            if (columnIndex ==1||columnIndex>=7)  
	                return false;  
	            else  
	                return true;  
	        }  
	          
	        /** 
	         * ������ݵ�ԪΪ�ɱ༭���򽫱༭���ֵ�滻ԭ����ֵ 
	         */  
	        @Override  
	        public void setValueAt(Object aValue, int rowIndex, int columnIndex)  
	        {  

	            /*������Ҫ�Ӹ��жϣ�����޸ĵ�ֵ������������Ч���������С�*/
	            if(columnIndex!=0)
	            {
	            	//�ж��Ƿ���Դ浽int����
	            	try {
	            		//����ת��Ϊ����
	            		//ʧ�����˳�
	            		Integer.parseInt(aValue.toString());
	            	}
	            	catch(Exception ex)
	            	{
	            		//UpdateData();
	            		return;
	            	}
		        	object[rowIndex][columnIndex] = aValue;  
		            /*֪ͨ���������ݵ�Ԫ�����Ѿ��ı�*/  
		            fireTableCellUpdated(rowIndex, columnIndex);
		            //UpdateData();
	            }
	            else {
	            	//�ж��Ƿ�Ϊһ���Ϸ�������
		        	object[rowIndex][columnIndex] = aValue;  
		            /*֪ͨ���������ݵ�Ԫ�����Ѿ��ı�*/  
		            fireTableCellUpdated(rowIndex, columnIndex);
		            //UpdateData();
	            }
	              //id��Ԫ���ֵ
	              
	              int id = Integer.parseInt((String)getValueAt(rowIndex,1));
	              //�޸�StudentList�еļ�¼ֵ
	              //System.out.println("rowIndex:"+columnIndex);
	              switch (Std.values()[columnIndex]) {
	              case name:
	            	  //System.out.println("name");
		              for(Student student:StudentsList)
		              {
		            	  //System.out.println("id:"+id);
		            	  if(student.id == id) {  
		            	  student.name = (String)getValueAt(rowIndex,columnIndex);
		            	  DataSave_ChangeFormId(id,0,student.name);
		            	  //System.out.println(student.name+"�޸ĳɹ�");
		            	  }
		              }
	              	break;
	              case age:
		              for(Student student:StudentsList)
		              {
		            	  if(student.id == id) {
		            	  
		            	  student.age = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,2,student.age);
		            	  //System.out.println(student.name+"�޸ĳɹ�");
		            	  }
		              }
	            	  
	            	  break;
	              
	              case java:
		              for(Student student:StudentsList)
		              {
		            	  
		            	  if(student.id == id) {
		            	  
		            	  student.java = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,3,student.java);
		            	  //System.out.println(student.java+"�޸ĳɹ�");
		            	  ChgAAFormId(id);
		            	  }
		              }
	            	  break;
	              case cs:
		              for(Student student:StudentsList)
		              {
		            	  
		            	  if(student.id == id) {
		            	  
		            	  student.cs = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,4,student.cs);
		            	  //System.out.println(student.cs+"�޸ĳɹ�");
		            	  ChgAAFormId(id);
		            	  }
		              }
	            	  break;
	              case sql:
		              for(Student student:StudentsList)
		              {
		            	  
		            	  if(student.id == id) {
		            	  
		            	  student.sql = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,5,student.sql);
		            	  //System.out.println(student.sql+"�޸ĳɹ�");
		            	  ChgAAFormId(id);
		            	  }
		              }
	            	  break;
	              case html:
		              for(Student student:StudentsList)
		              {
		            	  
		            	  if(student.id == id) {
		            	  
		            	  student.html = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,6,student.html);
		            	  //System.out.println(student.html+"�޸ĳɹ�");
		            	  ChgAAFormId(id);
		            	  }
		              }
	            	  break;
	              case all:
		              for(Student student:StudentsList)
		              {
		            	  if(student.id == id) {
		            	  student.all = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  //System.out.println(student.name+"�޸ĳɹ�");
		            	  }
		              }
	            	  break;
	              case ave:
		              for(Student student:StudentsList)
		              {
		            	  if(student.id == id) {
		            	  student.ave = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  //System.out.println(student.name+"�޸ĳɹ�");
		            	  }
		              }
	                  break;
	              default:
	            	  
	            	  break;
	             
	              }
	              
	              //����������ʾ
	              UpdateData();
	        }  
	        
	        
	        
	        
	        
	        
	        //*******************************
	    }


}
