package aq.gym.fun.pyramid;

public class Pyramid {

	
	/*
     * 	If you replace all the chars specified in comments, 
     *  you will see result like this:
		#####$
		####$$&
		###$$$&&
		##$$$$&&&
		#$$$$$&&&&
		How you can see, the pyramid consists of 3 triangles
		3 cycles inside [while cycle] build pyramid using 3 triangles together
	 */
	public static void draw(int height) {
		if(height <= 0) 
			throw new IllegalArgumentException("Incorrect value of pyramid heigh: [" + height +  "] should be > 0" );
		int rowIndex = height;
		while(rowIndex > 0) {
//			Drawing the first pyramid (replace 'whitespace' to '#' to show result)
			for(int j = 0; j < rowIndex; j++) 
				System.out.print(" ");
//			Drawing the second pyramid (replace '*' to '$' to show result)
			for(int j = height - rowIndex; j >= 0; j--)
				System.out.print("*");
//			Drawing the third pyramid (replace '*' to '&' to show result)
			for(int j = 0; j < height - rowIndex; j++)
				System.out.print("*");
			System.out.println();
			rowIndex--;
		}
	}
}
