import java.util.Random;
import java.util.ArrayList;

public class DrBsAlgorithmAnalysis {

	public static void main(String[] args) {

		timeCodeOnAMethodThatNeedsAnInt(10,25,new SimpleMethod());
	}

	public static void timeCodeOnAMethodThatNeedsAnInt(int numTimesForAverage, 
														int largestBigOhArrayIndex,
														SimpleMethod f) {

		//An integer array that gradually increases in terms of big oh times
		//sizes.length = 45 currently
		int [] sizes = new int[45];
		for(int i = 0; i < sizes.length; i++){
			sizes[i] = (int) Math.pow(2, (double) i);
		}
		//watch out for too large a size
		if(largestBigOhArrayIndex > sizes.length) {
			largestBigOhArrayIndex = sizes.length;
		}
		

		//It seems the first time doesn't work well with timing....
		//So time something to get the system warmed up;
			long startTime = System.nanoTime();
			f.runThisFuctionThatNeedsAnInt(sizes[largestBigOhArrayIndex/2]);
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println(totalTime + " Bogus Time\n");

		for(int z = 0; z < largestBigOhArrayIndex; z++) {
			Long [] times = new Long[numTimesForAverage];
			for(int i = 0; i < numTimesForAverage; i++) {
		

				System.gc();
				startTime = System.nanoTime();
				f.runThisFuctionThatNeedsAnInt(sizes[z]);

				endTime   = System.nanoTime();
				totalTime = endTime - startTime;

				addIntoSortedArray(times, totalTime);
			}
			long averageTime = times[numTimesForAverage/2];
			String s = String.format("Trial, %4d, input size, %10d, average time, %10d", z+1, sizes[z], averageTime);
			System.out.println(s);
			//Test code to see if sorting works correctly
//			if(z == largestBigOhArrayIndex-1) {
//				for(int i = 0; i < numTimesForAverage; i++) {
//					System.out.println(times[i]);
//				}
//			}
		}
	}

	public static void addIntoSortedArray(Long [] a, long x) {
		int spot = -1;
		for(int i = 0; i < a.length; i++) {
			if (a[i] == null || a[i] > x) {
				spot = i;
				break;
			}
		}
		//found a spot, so let's shift the array to put x into its spot
		if(spot > -1) {
			for(int i = a.length-1; i > spot; i--) {
				a[i] = a[i-1];
			}
			a[spot] = x;
		}
	}

}

class SimpleMethod{

	
	public void runThisFuctionThatNeedsAnInt(int a) {
		OhTimeAnalysisCode o = new OhTimeAnalysisCode();
		// o.lexicalOrder(a);
		o.searchRange(createArrayAndFillWithRandom(a), (int)(Math.random()*10000));
		// o.numJewelsInStones(createRandomString(a), createRandomString(a));
		// o.replaceWords(createFourLetterDictionaryForReplaceWords(a), createRandomSentenceOfNWords(a));
		// o.reverseWords(createRandomSentenceOfNWords(a));
		// o.shuffle(createArrayAndFillWithRandom(a), 0);
		// o.kidsWithCandies(createArrayAndFillWithRandom(a), (int)(Math.random()*100));
		// o.minOperations(createBinaryString(a));
		// o.minPartitions(createBinaryString(a));
		// o.toLowerCase(createRandomString(a));
		// o.sumEvenGrandparent(turnIntArrayIntoTree(createArrayAndFillWithRandom(a), new TreeNode(), 0));
		// o.testRectangleUpdate(a);
	}

	//Code taken from https://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
	//Transforms an int array into a binary tree, using the TreeNode method.
	// Function to insert nodes in level order
    public TreeNode turnIntArrayIntoTree(int[] arr, TreeNode root,
                                                int i)
    {
        // Base case for recursion
        if (i < arr.length) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;
  
            // insert left child
            root.left = turnIntArrayIntoTree(arr, root.left,
                                             2 * i + 1);
  
            // insert right child
            root.right = turnIntArrayIntoTree(arr, root.right,
                                               2 * i + 2);
        }
        return root;
    }

	//Code taken from https://www.programiz.com/java-programming/examples/generate-random-string
	//Generate a random string of a specified length.
	public String createRandomString(int a){
		// create a string of uppercase and lowercase characters and numbers
    	String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";

		// combine all strings
	    String alphabet = upperAlphabet + lowerAlphabet;

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // specify length of random string
	    int length = a;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }

	    return sb.toString();


	}
	
	public int[] createArrayAndFillWithRandom(int size) {
		int [] a = new int[size];
		for(int i = 0; i < a.length; i++) {
			//Default:
			//a[i] = (int)(Math.random()*Integer.MAX_VALUE/2);

			//For search Range
			a[i] = (int)(Math.random()*1000);
		}
		return a;
	}

	public ArrayList createFourLetterDictionaryForReplaceWords(int a){
		ArrayList<String> dic = new ArrayList<String>();
		for(int i = 0; i < a; i++){
			dic.add(createRandomString(4));
		}
		return dic;
	}

	public String createRandomSentenceOfNWords(int a){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < a; i++){
			s.append(createRandomString(10) + " ");
		}

		return s.toString();
	}

	public String createBinaryString(int a){
		StringBuilder sb = new StringBuilder();
		Random rn = new Random();
		for(int i = 0; i < a; i++){
			sb.append(rn.nextInt(2));
		}
		return sb.toString();
	}

}



