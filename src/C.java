import java.util.*;
public class C {

	
   public static class C1 implements Hello
   {
	   public void SayHello()
		{
			System.out.print("hello");
		}
		public void TalkHello()
		{
			System.out.print("hello");
		}
		public void SayHello(String s)
		{
			System.out.print(s);
		}
   }
   
	
	public interface Hello{
		void SayHello();
		void TalkHello();
	}
	
	//应用程序入口
	public static void main(String []agrs)
	{
	   C1 c = new C1();
	   c.SayHello();
		
	}
	
}
