package Problems;

/**
 * 
 * @author ChopinXBP
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 * ���� n ���Ǹ����� a1��a2��...��an��ÿ�������������е�һ���� (i, ai) ���������ڻ� n ����ֱ�ߣ���ֱ�� i �������˵�ֱ�Ϊ (i, ai) �� (i, 0)��
 * �ҳ����е������ߣ�ʹ�������� x �Ṳͬ���ɵ�����������������ˮ��
 * ˵�����㲻����б�������� n��ֵ����Ϊ 2��
 *
 */

public class ContainerWithMostWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] height = {1,8,6,2,5,4,8,3,7};
		int[] height2 = {1,2,4,3};
		System.out.println(maxArea(height));
		System.out.println(maxArea(height2));
		System.out.println(maxArea2(height));
		System.out.println(maxArea2(height2));
	}

	//��ÿһ���߶�i�����������˾���i��Ĳ�С��i�ĳ��ȵ��߶�j��j��Ϊ����i��Ϊ��ij���ɵľ����ǰ����߶�i��������ľ��Ρ�
    public static int maxArea(int[] height) {
        int result = 0;
        
        for(int i = 0; i < height.length; i++) {
        	int num = height[i];
        	int longest = 0;
        	
        	int idx = 0;
        	while(idx < i) {
        		if(height[idx] >= num) {
        			longest = i - idx;
        			break;
        		}
        		idx++;
        	}
        	idx = height.length - 1;
        	while(idx > i && idx - i > longest) {
        		if(height[idx] >= num) {
        			longest = idx - i;
        			break;
        		}
        		idx--;
        	}
        	
        	if(num * longest > result) {
        		result = num * longest;
        	}
        }
        
        return result;
    }
    
    //����left��right�����˱ƽ���ÿ�μ�����ͽ϶̱�Χ�ɵľ��ε������֮���ƶ��϶̱ߡ��ƽ����������ľ��������Ϊ����
    public static int maxArea2(int[] height) {
        int result = 0;
        
        int right = height.length - 1;
        int left = 0;
        
        while(left < right) {
        	int shorter = height[right] < height[left] ? height[right] : height[left];
        	if((right - left) * shorter > result) {
        		result = (right - left) * shorter;
        	}
        	//ÿ���ƶ��϶̱ߣ��ƶ��ϳ��߿��ܻᵼ����һ��������Ϊ�̱�����ȡ�������ţ�
        	if(height[right] < height[left]) {
        		right--;
        	}else {
        		left++;
        	}
        }
        
        return result;
    }
}
