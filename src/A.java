import java.util.*;

public class A {

    public static void main(String []args) {
      
      Scanner scr = new Scanner(System.in);
      //��������
      int index,mov;
      int array[] ;
      //����ʽ��ʶ
      boolean flag = true;//true : ���� false : ����
      System.out.println("������һ��0��100����");
      // ��ȡ����  ע�� �����ֵ ���ܴ��� 100  ��Ϊ randomCommonn : max value is 101
      index  = scr.nextInt();
      //��ȡһ�����ֵ����[���ظ�]
      array= randomCommon(0,101,index);
      scr.close();
      //********����******
      //��������
      if(flag)//����ʽ��ʶ
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
      //��������
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
      //���������
      for(int i:array)
      {
    	  System.out.print(i+" ");
      }
      
    }
    
    
    	//GetRandomArray
    public static int[] randomCommon(int min, int max, int n){
    	// ��Ч���ж�
    	if (n > (max - min + 1) || max < min) {
    		return null;
    	}

    	// ��������������������
    	int[] result = new int[n];

    	// ͳ�����ɸ���
    	int count = 0;

    	// ���ɸ���С��n֮ǰ��һֱ����
    	while(count < n) {
    		// ��ָ����Χ�����������
    		int num = (int) (Math.random() * (max - min)) + min;
    		
    		// �ظ����
    		boolean flag = true;

    		// �����ɵ�������������������ɵ������Ƚ�
    		for (int j = 0; j < n; j++) {

    			// �ظ�����
    			if(num == result[j]){
    				flag = false;
    				break;
    			}
    		}

    		// ���ظ����������
    		if(flag) {
    			result[count] = num;
    			// ���ɸ����ۼ�
    			count++;
    		}
    	}

    	return result;
    }

}

