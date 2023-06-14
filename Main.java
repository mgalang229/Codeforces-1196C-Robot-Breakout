import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

[-10^5, 10^5]

[2, 10^5]

[-10^5, 3]

------------------------

[-10^5, 1]

[2, 10^5]

[-10^5, 3]

------------------------

1
3
1 5 1 1 1 1
2 4 0 1 0 1
3 6 1 0 0 0

------------------------

1
3
1 5 1 0 1 1
2 4 0 1 0 1
3 6 1 0 0 0

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			Coord[] coords = new Coord[n];
			int limit = (int) 1e5;
			for (int i = 0; i < n; i++) {
				int x = fs.nextInt(), y = fs.nextInt();
				int l = fs.nextInt(), u = fs.nextInt(), r = fs.nextInt(), d = fs.nextInt();
				int startX = (l == 1 ? -limit : x);
				int endX = (r == 1 ? limit : x);
				int startY = (d == 1 ? -limit : y);
				int endY = (u == 1 ? limit : y);
				coords[i] = new Coord(x, y, startX, endX, startY, endY);
			}
			int fixedX = 0, fixedY = 0;
			Pair prevX = null, prevY = null;
			for (int i = 0; i < n; i++) {
//				System.out.println(coords[i]);
				//for x
				if (coords[i].startX == coords[i].endX) {
					if (prevX == null) {
						prevX = new Pair(coords[i].startX, coords[i].endX);
					} else if (prevX.first == coords[i].startX && prevX.second == coords[i].endX) {
						continue;
					}
					fixedX++;
				}
				
				//for y
				if (coords[i].startY == coords[i].endY) {
					if (prevY == null) {
						prevY = new Pair(coords[i].startY, coords[i].endY);
					} else if (prevY.first == coords[i].startY && prevY.second == coords[i].endY) {
						continue;
					}
					fixedY++;
				}
			}
			if (fixedX > 1 || fixedY > 1) {
				System.out.println(0);
				continue;
			}
			int maxStartX = -limit - 1, minEndX = limit + 1;
			int maxStartY = -limit - 1, minEndY = limit + 1;
			for (int i = 0; i < n; i++) {
				//for x
				maxStartX = Math.max(maxStartX, coords[i].startX);
				minEndX = Math.min(minEndX, coords[i].endX);
				//for y
				maxStartY = Math.max(maxStartY, coords[i].startY);
				minEndY = Math.min(minEndY, coords[i].endY);
			}
//			System.out.println(maxStartX + " " + minEndX);
//			System.out.println(maxStartY + " " + minEndY);
//			System.out.println("--------");
			if (maxStartX > minEndX || maxStartY > minEndY) {
				System.out.println(0);
				continue;
			}
			System.out.println("1 " + maxStartX + " " + maxStartY);
		}
		out.close();
	}
	
	static boolean isWithinRangeX(int num, Coord coord) {
		return coord.startX <= num && num <= coord.endX;
	}
	
	static boolean isWithinRangeY(int num, Coord coord) {
		return coord.startY <= num && num <= coord.endY;
	}
	
	static class Pair {
		int first, second;
		
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
	static class Coord {
		int x, y;
		int startX, endX;
		int startY, endY;
		
		public Coord(int x, int y, int startX, int endX, int startY, int endY) {
			this.x = x;
			this.y = y;
			this.startX = startX;
			this.endX = endX;
			this.startY = startY;
			this.endY = endY;
		}
		
		@Override
		public String toString() {
			String res = "(" + x + "," + y + "):\n";
			res = "x = " + "[" + startX + "," + endX + "]\n";
			res += "y = " + "[" + startY + "," + endY + "]\n";
			return res;
		}
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
