import java.util.Scanner;
/***************************
 *  *ʯͷ��������Ϸ*       *
 *  *2019��12��11��20:33:25*
 *                         *
 *                         *
 ***************************/
public class B{
	public static void main(String []args)
	{
		//ʵ����һ�������ȡ��
		Scanner scr = new Scanner(System.in);
		//������ҵ�����ֵ����
		int input=-1;
		//�������Ե����ֵ����
		int arr[];
		//�������Ե��������
		int target;
		//��ʼ��ʯͷ���������ַ�������
		String str[] = {"ʯͷ","����","��"};
		System.out.println("��Ϸ��ʼ");
		do {
			//��ֹ��ѭ��
			input=-1;
			//���һ����������
			arr = randomCommon(0,4,1);
			//���ڻ�ȡ�����������Ϊ���� ���� �����ȡ�� ��~�� ��Ҫ��һ
			target= arr[0]-1;
			//��������Ƿ���ȷ
			while(input>3||input<0)
			{
				System.out.println("������һ����  : 0'��ʯͷor'1'������'or'2'����' !or'3'���˳�");
				input = scr.nextInt();
			}
			//�ж�ʤ�� �� Ϊ�˳���Ϸ ��һ��Ϊʯͷ|����|��
			if(input!=3) {
				//ʤ���ж�
				System.out.println("��ң�"+str[input]+"  "+"���ԣ�"+str[target]);
				if(input-target ==-1||input - target == 2)
				{
					System.out.println("Ӯ");
				}
				//ƽ���ж�
				else if(input - target == 0)
				{
					System.out.println("ƽ");
				}
				//ʧ���ж�
				else
				{
					System.out.println("��");
				}
			}
		}while(input<3);
		System.out.println("��Ϸ����");
		scr.close();
	}
	//��ȡ��������� ����n�� ����min С�� max����
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