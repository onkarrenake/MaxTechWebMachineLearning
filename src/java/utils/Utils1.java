/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

//import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import core.*;
import core.Attribute;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.print.attribute.Size2DSyntax;
/**
 *
 * @author ganesh
 */
public class Utils1 {
// A small double
		public static final  double SMALL = 1E-06;
		//
		public static String Quote(String strings)
		{
			return strings;
		}
		//return the base 2 logarithm of a double value
		public static double Log2(double a)
		{
			return (Math.log(a)/Math.log(2));
		}
                
                public static double Log10(double a)
		{
			return (Math.log(a)/Math.log(10));
		}
                
		//check whether the tow values being examed are equal or not
		public static boolean  Eq(double a, double b)
		{
			return (((a - b) < SMALL) & ((b - a) < SMALL));
		}
		//check if a is greater than b
		public static boolean  Gr(double a, double b)
		{
			return (a - b > SMALL);
		}
		//return the sum of a double array
		public static double Sum(double[] doubles)
		{
			double s = 0;
			int i = 0;
			for (i = 0; i <= doubles.length - 1; i++) {
				s = s + doubles[i];
			}
			return s;
		}
		//return the sum of a integer array
		public static int Sum(int[] ints)
		{
			int s = 0;
			int i = 0;
			for (i = 0; i <= ints.length - 1; i++) {
				s = s + ints[i];
			}
			return s;
		}
		//return the index value of the max value in an integer array
		public static int MaxIndex(int[] ints)
		{
			int maximum = 0;
			int mindex = 0;
			int i = 0;
			for (i = 0; i <= ints.length - 1; i++) {
				if (((i == 0) | (ints[i] > maximum))) {
					mindex = i;
					maximum = ints[i];
				}

			}
			return mindex;
		}
		//return the index value of the max value in a double array
		public static int MaxIndex(double[] doubles)
		{
			double maximum = 0;
			int mindex = 0;
			int i = 0;
			for (i = 0; i <= doubles.length - 1; i++) {
				if (((i == 0) | (doubles[i] > maximum))) {
					mindex = i;
					maximum = doubles[i];
				}

			}
			return mindex;
		}
		//normalize a double array
             public static void Normalize(double[] doubles, double sum) throws Exception
		       {
			if (Double.isNaN(sum)) {
				throw new Exception("Can't normalize array. Sum is NaN.");
			}
			if ((sum == 0)) {
				throw new Exception("Can't normalize array. Sum is zero.");
			}
			int i = 0;
			for (i = 0; i <= doubles.length - 1; i++) {
				doubles[i] /= sum;
			}

		}
		//normalize an int array
		public static void Normalize( double[] doubles) throws Exception
		{
			double sum = 0;
			int i = 0;
			for (i = 0; i <= doubles.length - 1; i++) {
				sum += doubles[i];
			}
                        
			Normalize(doubles, sum);
		}
		//return the greater one of two int values
		public static int Max(int int1, int int2)
		{
			if ((int1 >= int2)) {
				return int1;
			} else {
				return int2;
			}
		}
		//sort an array
		public static int[] Sort(double[] array)
		{
			int[] index = null;
			index = new int[array.length];
			int i = 0;
			for (i = 0; i <= array.length - 1; i++) {
				index[i] = i;
			}
			QuickSort(array, index, 0, array.length - 1);
			return index;
		}
		//print a new line in system console box
		public static Object NEWLINE = System.getProperty("line.separator");
		private static void QuickSort(double[] array, int[] index, int lo0, int hi0)
		{
			int lo = lo0;
			int hi = hi0;
			int mid = 0;
			int help = 0;

			if ((hi0 > lo0)) {
				mid = (int)array[index[(lo0 + hi0) / 2]];
				while ((lo <= hi)) {
					while (((array[index[lo]] < mid) & (lo < hi0))) {
						lo += 1;
					}
					while (((array[index[hi]] > mid) & (hi > lo0))) {
						hi += 1;
					}

				}
				if ((lo <= hi)) {
					help = index[lo];
					index[lo] = index[hi];
					index[hi] = help;
					lo += 1;
					hi -= 1;
				}
			}

			if ((lo0 < hi)) {
				QuickSort(array, index, lo, hi0);
			}
			if ((lo < hi0)) {
				QuickSort(array, index, lo, hi0);
			}

		}
		public static String GetOptions(String flag, String[] options)
		{
			String newString = null;
			if ((options == null)) {
				return "";
			}
			try {
				int i = 0;

				for (i = 0; i <= options.length - 1; i++) {
					if (((options[i].length() > 0) & (options[i].charAt(0) == '-'))) {
						try {
                                                        Double temp_d=Double.parseDouble(options[i]);
							double dummy = (double)temp_d;

						} catch (Exception ex) {
							if ((options[i].equals("-" + flag))) {
								if ((i + 1 == options.length)) {
									throw new Exception("No value given for -" + flag + " option.");
								}
								options[i] = "";
								newString = options[i + 1].toString();
								options[i + 1] = "";
								return newString;
							}
							if ((options[i].charAt(1) == '-')) {
								return "";
							}
						}
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			return "";
		}
		public static boolean  GetFlag(String flag, char[] options)
		{
			return GetFlag("" + flag, options);
		}
		public static boolean  GetFlag(String flag, String[] options)
		{
			if ((options == null)) {
				return false;
			}
			int i = 0;
			for (i = 0; i <= options.length - 1; i++) {

				if (((options[i].length() > 1) & (options[i].charAt(0) == '-'))) {
					try {
					    	Double tmp_d=Double.parseDouble(options[i]);
                                            double dummy = (double)tmp_d;
					} catch (Exception ex) {
						if ((options[i].equals("-" + flag))) {
							options[i] = "";
							return true;
						}
						if ((options[i].charAt(1) == '-')) {
							return false;
						}

					}

				}
			}
			return false;
		}

		public static double DistanceFunction(Instances dataset, Instance first, Instance second) throws Exception
		{
			double diff = 0;
			double distance = 0;
			int i = 0;
			for (i = 0; i <= dataset.NumAttributes() - 1; i++) {
				if ((i == dataset.ClassIndex())) {
					continue;
				}
				if ((dataset.Attribute(i).IsNominal())) {
					if ((first.IsMissing(i) | second.IsMissing(i) | ((first.Value(i)) != (second.Value(i))))) {
						distance = distance + 1;
					}
				} else {
					diff = first.Value(i) - second.Value(i);
					distance = distance + diff * diff;
				}

			}

			return Math.sqrt(distance);
		}
//		public static Instance[] FNbr(Instances dataset, Instance instance, int k) throws Exception
//		{
//
//			//dataset.Delete(0)
//			Instance[] fnbrs = new Instance[k];
//			SimpleSortedList1 list = new SimpleSortedList1();
//
//			Enumeration enu = dataset.EnumerateInstances();
//			while ((enu.HasMoreElements())) {
//				Instance inst = (Instance)enu.NextElement();
//				if ((inst.ClassValue() == instance.ClassValue())) {
//					double dist = DistanceFunction(dataset, inst, instance);
//					list.Add(inst, dist);
//				}
//			}
//			list.BubbleDownSort();
//			int i = 0;
//
//			for (i = 0; i <= k - 1; i += 1) {
//				Instance fn = (Instance)list.GetElementAt(i);
//				fnbrs[i] = fn;
//			}
//
//			return fnbrs;
//		}
//		public static Instance[] NNbr(Instances dataset, Instance instance, int k) throws Exception
//		{
//
//			Instance[] nnbrs = new Instance[k];
//			SimpleSortedList1 list =new SimpleSortedList1();
//
//			Enumeration enu = dataset.EnumerateInstances();
//			while ((enu.HasMoreElements())) {
//				Instance inst = (Instance)enu.NextElement();
//
//				if ((inst.ClassValue() == instance.ClassValue())) {
//					double dist = DistanceFunction(dataset, inst, instance);
//					list.Add(inst, dist);
//				}
//
//
//			}
//			list.BubbleDownSort();
//			int i = 0;
//			for (i = 0; i <= k - 1; i += 1) {
//                Instance nn = (Instance)list.GetElementAt(((int)(list.Length()) - 1) - i);
//				nnbrs[i] = nn;
//			}
//
//			return nnbrs;
//		}
		//compute the screen width of a string
		/*public static int StringWidthInPixel(String BannerText, String FontName, float FontSize)
		{
			BufferedImage b ;
			Graphics g ;
                        
			Font f = new Font(FontName,0,(int) FontSize);

			b = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			g = new Graphics(b);
			Size2DSyntax stringSize = (BannerText, f);
			int width = (int)stringSize.Width;

			g.Dispose();
			b.Dispose();
			return width;
		}
		//compute the screen height of a string
		public static int StringHeightInPixel(String BannerText, String FontName, float FontSize)
		{
			BitMap b = default(Bitmap);
			Graphics g = default(Graphics);
			Font f = new Font(FontName, FontSize);

			b = new Bitmap(1, 1, System.Drawing.Imaging.PixelFormat.Format32bppArgb);
			g = Graphics.FromImage(b);
			SizeF stringSize = g.MeasureString(BannerText, f);

			int height = (int)stringSize.Height;
			g.Dispose();
			b.Dispose();
			
                        return height;
		}*/
		// Clears the 'tab' char in a string, then replaced with 'space' char
		public static String ReplaceTabWithSpace(String str)
		{
			//str = str.Replace(", ", ",")
			return str.replace("\t", " ");
		}
		
}
