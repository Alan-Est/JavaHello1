import java.util.*;

public class A {

    public static void main(String []args) {
      
      Scanner scr = new Scanner(System.in);
      //声明变量
      int index,mov;
      int array[] ;
      //排序方式标识
      boolean flag = true;//true : 正序 false : 逆序
      System.out.println("请输入一个0至100的数");
      // 获取输入  注意 输入的值 不能大于 100  因为 randomCommonn : max value is 101
      index  = scr.nextInt();
      //获取一个随机值数组[不重复]
      array= randomCommon(0,101,index);
      scr.close();
      //********排序******
      //升序排序
      if(flag)//排序方式标识
      for(int a = 0;a<index;a++)
      {
    	  for(int b = a + 1; b<index; b++)
    	  {
    		 if(array[a]>array[b])
    		 {
    			 mov = array[b];
    			 array[b] = array[a];
    			 array[a] = mov;
    		 }
    	  }
      }
      else
      //降序排序
      for(int a = 0;a<index;a++)
      {
    	  for(int b = a + 1; b<index; b++)
    	  {
    		 if(array[a]<array[b])
    		 {
    			 mov = array[a];
    			 array[a] = array[b];
    			 array[b] = mov;
    		 }
    	  }
      }
      //逐数组输出
      for(int i:array)
      {
    	  System.out.print(i+" ");
      }
      
    }
    
    
    	//GetRandomArray
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

