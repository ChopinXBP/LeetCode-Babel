package Problems;

/**
 * 
 * @author ChopinXBP Given n points on a 2D plane, find the maximum number of
 *         points that lie on the same straight line. ����һ���㼯���ж�����ж��ٸ�����һ��ֱ���ϡ�
 *         ע��㣺1.double���ݵıȽϣ�2.б�ʷ��������ڴ������ݵ�
 *
 */

public class MaxPointsOnALine {

	public static class Point {
		int x;
		int y;
		Point() {
			x = 0;
			y = 0;
		}
		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Point[] points = {new Point(1,2), new Point(1,3), new Point(2,4), new
		// Point(3,6), new Point(2,6)};
		// Point[] points = {new Point(3,10), new Point(0,2), new Point(0,2), new
		// Point(3,10)};
		// б�ʷ��ܹ���ţ�͵Ĳ��Ե㣬����ȴ����������LeetCode�Ĳ��Ե㣬���Կ����ñ���������(y2-y0)*(x2-x1)==(y2-y1)*(x2-x0)
		Point[] points = { new Point(0, 0), new Point(94911151, 94911150), new Point(94911152, 94911151) };
		System.out.println(maxPoints(points)); // б�ʷ�
		System.out.println(maxPoints2(points)); // ������
	}

	// б�ʷ�
	public static int maxPoints(Point[] points) {
		// �ų�����3��������
		if (points.length <= 2)
			return points.length;

		int max = 2;
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int count = 2;
				// б�ʲ�Ϊ����
				if ((points[i].x != points[j].x)) {
					double k = (double) (points[i].y - points[j].y) / (points[i].x - points[j].x);
					double b = (double) points[i].y - points[i].x * k;
					int m = 0;
					while (m < points.length) {
						if (m != i && m != j && isEqual(points[m].y, k * points[m].x + b))
							count++;
						m++;
					}
					if (count > max)
						max = count;
				}
				// б��Ϊ�����
				else {
					int m = 0;
					while (m < points.length) {
						if (m != i && m != j && points[m].x == points[i].x)
							count++;
						m++;
					}
					if (count > max)
						max = count;
				}
			}
		}
		return max;
	}

	// ������
	public static int maxPoints2(Point[] points) {
		// �ų�����3��������
		if (points.length <= 2)
			return points.length;

		int max = 2;
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int count = 2;
				// б�ʲ�Ϊ����
				if ((points[i].x != points[j].x)) {
					int m = 0;
					double y = 10000.0 * (double) (points[i].y - points[j].y);
					double x = 10000.0 * (double) (points[i].x - points[j].x);
					while (m < points.length) {
						if (m != i && m != j) {
							double y2 = 10000.0 * (double) (points[i].y - points[m].y);
							double x2 = 10000.0 * (double) (points[i].x - points[m].x);
							if (isEqual(x2 * y, y2 * x))
								count++;
						}
						m++;
					}
					if (count > max)
						max = count;
				}
				// б��Ϊ�����
				else {
					int m = 0;
					while (m < points.length) {
						if (m != i && m != j && points[m].x == points[i].x)
							count++;
						m++;
					}
					if (count > max)
						max = count;
				}
			}
		}
		return max;
	}

	// ��������ȱȽ�
	public static boolean isEqual(double x1, double x2) {
		// �ȽϷ���1
		if ((x1 - x2 <= 0.0000001) && (x1 - x2 >= -0.0000001)) {
			return true;
		} else {
			return false;
		}

		// �ȽϷ���2
		// if(Double.doubleToLongBits(x1) == Double.doubleToLongBits(x2)){
		// return true;
		// }else{
		// return false;
		// }

		// �ȽϷ���3
		// if(Double.toString(x1).equals(Double.toString(x2))){
		// return true;
		// }else{
		// return false;
		// }
	}
}
