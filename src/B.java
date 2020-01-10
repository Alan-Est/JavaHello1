import java.util.Scanner;
/***************************
 *  *石头剪刀布游戏*       *
 *  *2019年12月11日20:33:25*
 *                         *
 *                         *
 ***************************/
public class B{
	public static void main(String []args)
	{
		//实例化一个输入获取器
		Scanner scr = new Scanner(System.in);
		//声明玩家的输入值变量
		int input=-1;
		//声明电脑的随机值变量
		int arr[];
		//声明电脑的输入变量
		int target;
		//初始化石头剪刀布的字符串数组
		String str[] = {"石头","剪刀","布"};
		System.out.println("游戏开始");
		do {
			//防止死循环
			input=-1;
			//随机一个电脑输入
			arr = randomCommon(0,4,1);
			//由于获取的随机数不能为负数 所以 这里获取了 零~三 需要减一
			target= arr[0]-1;
			//检查输入是否正确
			while(input>3||input<0)
			{
				System.out.println("请输入一个数  : 0'：石头or'1'：剪刀'or'2'：布' !or'3'：退出");
				input = scr.nextInt();
			}
			//判断胜负 三 为退出游戏 零一二为石头|剪刀|布
			if(input!=3) {
				//胜利判断
				System.out.println("玩家："+str[input]+"  "+"电脑："+str[target]);
				if(input-target ==-1||input - target == 2)
				{
					System.out.println("赢");
				}
				//平局判断
				else if(input - target == 0)
				{
					System.out.println("平");
				}
				//失败判断
				else
				{
					System.out.println("输");
				}
			}
		}while(input<3);
		System.out.println("游戏结束");
		scr.close();
	}
	//获取随机数数组 返回n个 大于min 小于 max的数
	 public static int[] randomCommon(int min, int max, int n){
	    	// 有效性判断
	    	if (n > (max - min + 1) || max < min) {
	    		return null;
	    	}

	    	// 按随机个数声明存放数组
	    	int[] result = new int[n];

	    	// 统计生成个数
	    	int count = 0;

	    	// 生成个数小于n之前，一直生成
	    	while(count < n) {
	    		// 在指定范围内生成随机数
	    		int num = (int) (Math.random() * (max - min)) + min;

	    		// 重复标记
	    		boolean flag = true;

	    		// 将生成的随机数与数组中已生成的数作比较
	    		for (int j = 0; j < n; j++) {

	    			// 重复则标记
	    			if(num == result[j]){
	    				flag = false;
	    				break;
	    			}
	    		}

	    		// 不重复则加入数组
	    		if(flag) {
	    			result[count] = num;
	    			// 生成个数累加
	    			count++;
	    		}
	    	}

	    	return result;
	    }
}