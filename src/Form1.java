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
 *  *学生信息管理系统
 *  *2019年12月22日00:34:43
 *  *2019年12月22日13:47:11
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
             //测试学生数据生成
			//Test();Test();Test();Test();Test();
			//---------Set Form title
			this.setTitle("学生信息管理系统");
			//---------Main Container-cont------
			//实例化一个控件的容器
			Container cont = new Container();
			//变量记录
			container = cont; 
			//设置cont的位置，大小
			cont.setBounds(0, 0, this.getWidth(), this.getHeight());
			//显示数据表
			AddTable(cont);
	        //
			JLabel lb1 = new JLabel("姓名");
			lb1.setBounds(10, 0, 60, 25);
			cont.add(lb1);
			JTextField jt1 = new JTextField();
			jt1.setBounds(70, 0, 120, 25);
			cont.add(jt1);
	        //
			JLabel lb2 = new JLabel("年龄");
			lb2.setBounds(10, 25, 60, 25);
			cont.add(lb2);
			JTextField jt2 = new JTextField();
			jt2.setBounds(70,25, 120, 25);
			cont.add(jt2);
	        //
			JLabel lb3 = new JLabel("java成绩");
			lb3.setBounds(10, 50, 60, 25);
			cont.add(lb3);
			JTextField jt3 = new JTextField();
			jt3.setBounds(70, 50, 120, 25);
			cont.add(jt3);
	        //
			JLabel lb4 = new JLabel("C#成绩");
			lb4.setBounds(10, 75, 60, 25);
			cont.add(lb4);
			JTextField jt4 = new JTextField();
			jt4.setBounds(70, 75, 120, 25);
			cont.add(jt4);
	        //
			JLabel lb5 = new JLabel("sql成绩");
			lb5.setBounds(10, 100, 60, 25);
			cont.add(lb5);
			JTextField jt5 = new JTextField();
			jt5.setBounds(70, 100, 120, 25);
			cont.add(jt5);
	        //
			JLabel lb6 = new JLabel("html成绩");
			lb6.setBounds(10, 125, 60, 25);
			cont.add(lb6);
			JTextField jt6 = new JTextField();
			jt6.setBounds(70, 125, 120, 25);
			cont.add(jt6);

			
			//------Button -jb1------
			JButton jb1 = new JButton("添加信息");
			jb1.setBounds(10, 170, 120, 25);
			cont.add(jb1);
			//为按钮添加事件监听器
			jb1.addActionListener(new ActionListener() {
				//actionPerformed 是发生事件时会调用的函数
				//重载该函数
				@Override
				public void actionPerformed(ActionEvent e) {
				//添加学生信息
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
						JOptionPane.showMessageDialog(null, "输入的信息有误！");
						}
				}
			});
			//------Button -jb2------
			JButton jb2 = new JButton("删除信息");
			jb2.setBounds(130, 170, 120, 25);
			cont.add(jb2);
			//为按钮添加事件监听器
			jb2.addActionListener(new ActionListener() {
				//actionPerformed 是发生事件时会调用的函数
				//重载该函数
				@Override
				public void actionPerformed(ActionEvent e) {
				//
					String s=JOptionPane.showInputDialog("请输入需要删除的学生ID:");
					if(s!=null)
					{
						//按下了确定
						int id = -1;
						//尝试转换
						try {
							id = Integer.parseInt(s);}
						catch(Exception ex){}
						//
						if(id!=-1)
						{
							if(!RemoveStudent(id))
							{JOptionPane.showMessageDialog(null, "id不存在");}
						}
						else {
							{
								//输入内容无效
								JOptionPane.showMessageDialog(null, "输入无效,请输入一个正确的数字！");
							}
						}
					}
				}
			});
			//-------Form State-------
			//设置位置、大小
			this.setBounds(300,200,580,600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//添加控件组
			this.add(cont);
			//尺寸变化
			this.addComponentListener(new ComponentAdapter(){
				@Override public void componentResized(ComponentEvent e){
					//cont.setBounds(e.COMPONENT_RESIZED);
					//输出变化后的尺寸
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
			// 创建指定列名和数据的表格
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
		//更新数据
		private void UpdateData()
		{
			jTable.setModel(new StudentTableModel());
			
		}
		//重新排序id
		private void SortID()
		{
			int id = 0;
			for(Student s :StudentsList)
			{
				s.id = id++;
			}
		}
		
		//添加一个测试用户
		public void Test() {
			AddStudent(17,"233",59,58,57,56,MaxId+1);
		}
		
		//修改成绩
		private void ChgAAFormId(int id)
		{
			Student student = FindStudentFromID(id);
			student.all = (student.java+student.cs+student.sql+student.html);
			student.ave = student.all / 4;
		}
		//删除学生
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
		//增加学生信息
		private Student AddStudent(int age,String nam,int java,int cs,int sql,int html,int id)
		{
			//实例化学生信息类并赋值
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
            //计算总分和平均分
            ChgAAFormId(st.id);
            //用于debug
			//System.out.println("id："+st.id+"名字："+st.name+"年龄"+st.age);
			return st;
		}
		//操作成功返回指定id的学生对象，否则返回null
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
		//操作成功返回true 否则返回false
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
		//根据id 修改学生指定科目的成绩
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
		//学生枚举
		public static enum Std{
			//名字
			name,
			//编号
			id,
			//年龄
			age,
			//四项成绩
			java,
			cs,
			sql,
			html,
			//总分
			all,
			//平均分
			ave
		}
		//学生课程枚举
		public static enum StdCls{
			java,
			cs,
			sql,
			html
		}
		
		//学生类 List
		public static ArrayList<Student> StudentsList = new ArrayList<Student>();
		//最大的ID
		public static int MaxId = 0;
		//删除学生信息use ID
		public void DataDalete_FormId(int id)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // 注册 JDBC 驱动
	            Class.forName(JDBC_DRIVER);
	        
	            // 打开链接
	            //System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // 执行查询
	            //System.out.println(" 实例化Statement对象...");
	            stmt = conn.createStatement();
	            String sql;
	            
	            sql = "Delete from students where id=";
	            sql = sql + id;
	            System.out.println(sql);
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // 处理 JDBC 错误
	        	System.out.println("SQL命令错误");
	            //se.printStackTrace();
	        }catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	        }finally{
	            // 关闭资源
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// 什么都不做
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("读取完成");
	        
		}
		//在添加成员时创建学生信息
		//创建信息
		public void DataSave_StudentWasCreated(Student s)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // 注册 JDBC 驱动
	            Class.forName(JDBC_DRIVER);
	        
	            // 打开链接
	            //System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // 执行查询
	            //System.out.println(" 实例化Statement对象...");
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
	            // 处理 JDBC 错误
	        	System.out.println("SQL命令错误");
	            //se.printStackTrace();
	        }catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	        }finally{
	            // 关闭资源
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// 什么都不做
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("读取完成");
	        
		}
		//修改学生信息，参数为字符串
		public void DataSave_ChangeFormId(int id,int target,String value)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // 注册 JDBC 驱动
	            Class.forName(JDBC_DRIVER);
	        
	            // 打开链接
	            //System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // 执行查询
	            //System.out.println(" 实例化Statement对象...");
	            stmt = conn.createStatement();
	            String sql;
	            Student stu =FindStudentFromID(id);
	            String []targetStrings = {"Student","id","age","java","cs","sql","html"};
	            
	            sql = "UPDATE students" + " SET "+targetStrings[target]+ "=" +value+ " WHERE id = "+id;
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // 处理 JDBC 错误
	            se.printStackTrace();
	        }catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	        }finally{
	            // 关闭资源
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// 什么都不做
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        
		}
		//在单元格被修改时，保存指定id的学生的信息到数据库内
		//保存数据使用id
		public void DataSave_ChangeFormId(int id,int target,int value)
		{
			Connection conn = null;
	        Statement stmt = null;
	        try{
	            // 注册 JDBC 驱动
	            Class.forName(JDBC_DRIVER);
	        
	            // 打开链接
	            //System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // 执行查询
	            //System.out.println(" 实例化Statement对象...");
	            stmt = conn.createStatement();
	            String sql;
	            Student stu =FindStudentFromID(id);
	            String []targetStrings = {"Student","id","age","java","cs","sql","html"};
	            sql = "UPDATE students" + " SET "+targetStrings[target]+ "=" +value+ " WHERE id = "+id;
	            stmt.executeUpdate(sql);
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // 处理 JDBC 错误
	            se.printStackTrace();
	        }catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	        }finally{
	            // 关闭资源
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// 什么都不做
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
		}
		//开始程序 读取数据库
		public void DataLoad() {
	        Connection conn = null;
	        Statement stmt = null;
	        try{
	            // 注册 JDBC 驱动
	            Class.forName(JDBC_DRIVER);
	        
	            // 打开链接
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = conn.createStatement();
	            String sql;
	            sql = "SELECT * FROM students";
	            //执行
	            ResultSet rs = stmt.executeQuery(sql);
	            
	            // 展开结果集数据库
	            while(rs.next()){
	                // 通过字段检索
	                int id  = rs.getInt("id");
	                String name = rs.getString("Student");
	                int  age = rs.getInt("age");
	                int java = rs.getInt("java");
	                int cs = rs.getInt("cs");
	                int sql1 = rs.getInt("sql1");
	                int html = rs.getInt("html");
	                AddStudent(age, name, java, cs, sql1, html,id);
	            }
	            // 完成后关闭
	            rs.close();
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // 处理 JDBC 错误
	            se.printStackTrace();
	        }catch(Exception e){
	            // 处理 Class.forName 错误
	            e.printStackTrace();
	        }finally{
	            // 关闭资源
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// 什么都不做
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("读取完成");
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
         * *学生信息类模型
         * *
         * *
         **********************/  
	    private class StudentTableModel extends AbstractTableModel  
	    {  

	    	//JTable table = new JTable();
			String[] columnNames = { "姓名", "编号","年龄","java成绩","c#成绩","sql成绩","html成绩","总分","平均分"}; // 定义表格列名数组
			Object object[][] = new Object[StudentsList.size()][9];
	  
	        /** 
	                    * 构造方法
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
	  
	        // 以下为继承自AbstractTableModle的方法，可以自定义  
	        /** 
	         * 得到列名 
	         */  
	        @Override  
	        public String getColumnName(int column)  
	        {  
	            return columnNames[column];  
	        }  
	          
	        /** 
	         * 重写方法，得到表格列数 
	         */  
	        @Override  
	        public int getColumnCount()  
	        {  
	            return columnNames.length;  
	        }  
	  
	        /** 
	         * 得到表格行数 
	         */  
	        @Override  
	        public int getRowCount()  
	        {  
	            return object.length;  
	        }  
	  
	        /** 
	         * 得到数据所对应对象 
	         */  
	        @Override  
	        public Object getValueAt(int rowIndex, int columnIndex)  
	        {  
	            return object[rowIndex][columnIndex];  
	        }  
	  
	        /** 
	         * 得到指定列的数据类型 
	         */  
	        @Override  
	        public Class<?> getColumnClass(int columnIndex)  
	        {  
	            return object[0][columnIndex].getClass();  
	        }  
	  
	        /** 
	         * 指定设置数据单元是否可编辑.这里设置"姓名","学号"不可编辑 
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
	         * 如果数据单元为可编辑，则将编辑后的值替换原来的值 
	         */  
	        @Override  
	        public void setValueAt(Object aValue, int rowIndex, int columnIndex)  
	        {  

	            /*这里需要加个判断，如果修改的值不是数字则无效【非姓名列】*/
	            if(columnIndex!=0)
	            {
	            	//判断是否可以存到int里面
	            	try {
	            		//尝试转换为数字
	            		//失败则退出
	            		Integer.parseInt(aValue.toString());
	            	}
	            	catch(Exception ex)
	            	{
	            		//UpdateData();
	            		return;
	            	}
		        	object[rowIndex][columnIndex] = aValue;  
		            /*通知监听器数据单元数据已经改变*/  
		            fireTableCellUpdated(rowIndex, columnIndex);
		            //UpdateData();
	            }
	            else {
	            	//判断是否为一个合法的名字
		        	object[rowIndex][columnIndex] = aValue;  
		            /*通知监听器数据单元数据已经改变*/  
		            fireTableCellUpdated(rowIndex, columnIndex);
		            //UpdateData();
	            }
	              //id单元格的值
	              
	              int id = Integer.parseInt((String)getValueAt(rowIndex,1));
	              //修改StudentList中的记录值
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
		            	  //System.out.println(student.name+"修改成功");
		            	  }
		              }
	              	break;
	              case age:
		              for(Student student:StudentsList)
		              {
		            	  if(student.id == id) {
		            	  
		            	  student.age = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,2,student.age);
		            	  //System.out.println(student.name+"修改成功");
		            	  }
		              }
	            	  
	            	  break;
	              
	              case java:
		              for(Student student:StudentsList)
		              {
		            	  
		            	  if(student.id == id) {
		            	  
		            	  student.java = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  DataSave_ChangeFormId(id,3,student.java);
		            	  //System.out.println(student.java+"修改成功");
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
		            	  //System.out.println(student.cs+"修改成功");
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
		            	  //System.out.println(student.sql+"修改成功");
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
		            	  //System.out.println(student.html+"修改成功");
		            	  ChgAAFormId(id);
		            	  }
		              }
	            	  break;
	              case all:
		              for(Student student:StudentsList)
		              {
		            	  if(student.id == id) {
		            	  student.all = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  //System.out.println(student.name+"修改成功");
		            	  }
		              }
	            	  break;
	              case ave:
		              for(Student student:StudentsList)
		              {
		            	  if(student.id == id) {
		            	  student.ave = Integer.parseInt((String)getValueAt(rowIndex,columnIndex));
		            	  //System.out.println(student.name+"修改成功");
		            	  }
		              }
	                  break;
	              default:
	            	  
	            	  break;
	             
	              }
	              
	              //更新数据显示
	              UpdateData();
	        }  
	        
	        
	        
	        
	        
	        
	        //*******************************
	    }


}
