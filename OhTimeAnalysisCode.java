import java.util.*;
import java.lang.*;

public class OhTimeAnalysisCode{
	
	//386. Lexicographical Numbers
	//https://leetcode.com/problems/lexicographical-numbers/

    public static List<Integer> lexicalOrder(int n) {
        ArrayList<Integer> Lexical = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++){
            Lexical.add(i);
            
            int pow = 10;
            while(i*pow <= n){
                for(int j = 0; j < pow; j++){
                    if(i*pow + j > n)
                        break;
                    Lexical.add(i*pow + j);
                }   
                pow *= 10;
            }
        }
        return Lexical;
    }

    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/
	//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
	//If target is not found in the array, return [-1, -1].

    public int[] searchRange(int[] nums, int target) {
        mergeSort(nums);
        int[] firstAndLastPosition = new int[]{-1,-1};
        int start = 0;
		int end = nums.length-1;
		int mid;
		while(start <= end){
			mid = (end + start)/2;
			if(nums[mid] < target && (mid+1 == nums.length || nums[mid+1] == target)){
				firstAndLastPosition[0] = mid + 1;
                break;
			}
			if(nums[mid] < target && nums[mid +1] != target) 
                start = mid + 1;
			if(nums[mid] >= target) 
                end = mid - 1;
        }
        start = 0;
		end = nums.length-1;
		while(start <= end){
			mid = (end + start)/2;
			if(nums[mid] > target && (mid == 0 || nums[mid-1] == target)){
				firstAndLastPosition[1] = mid - 1;
                break;
			}
			if(nums[mid] <= target) 
                start = mid + 1;
			if(nums[mid] > target  && nums[mid - 1] != target) 
                end = mid - 1;
        }
	    return firstAndLastPosition;
    }


    //https://leetcode.com/problems/jewels-and-stones/

    public int numJewelsInStones(String J, String S){
        int count = 0;   
    for(int j = 0; j < J.length(); j++){
            for(int s = 0; s < S.length(); s++){
                if(S.charAt(s) == J.charAt(j)){
                    count++;
                }
            }
        }
    return count;
   }

   //https://leetcode.com/problems/replace-words/

    public String replaceWords(List<String> dictionary, String sentence) {
        List<String> words = new ArrayList<String>();
        words = parseWords(sentence);
        String rootSentence = "";
        for(int i = 0; i < words.size(); i++){
            String word = words.get(i);
            for(int j = 0; j < dictionary.size(); j++){
                String root = dictionary.get(j);
                if(checkForRoot(root, word)){
                    word = root;   
                }
            }
            rootSentence = rootSentence + word;
            if(i < words.size() -1) rootSentence = rootSentence + " ";
        }
    return rootSentence;
    }
    
    public List<String> parseWords(String sentence){
        List<String> words = new ArrayList<String>();
        String word = "";
        for(int i = 0; i < sentence.length(); i++){
            if(sentence.charAt(i) != ' '){
               word = word + sentence.charAt(i);
            }
            else{
                words.add(word);
                word = "";
            }
        }
        words.add(word);
        return words;
    }
    public boolean checkForRoot(String root, String word){
        if(root.length() > word.length()) return false;
        for(int i = 0; i < root.length(); i++){
            if(root.charAt(i) != word.charAt(i)) return false;
        }
        return true;
    }

    //https://leetcode.com/problems/reverse-words-in-a-string/

    public String reverseWords(String s) {
        s = s.trim();
        List<String> words = parseWords2(s);
        String reverseS = "";
        for(int i = words.size() -1; i >= 0; i--){
            reverseS  = reverseS + words.get(i) + " ";
        }
        reverseS = reverseS.trim();
        return reverseS;
    }
    public List<String> parseWords2(String sentence){
        List<String> words = new ArrayList<String>();
        String word = "";
        for(int i = 0; i < sentence.length(); i++){
            if(sentence.charAt(i) != ' '){
               word = word + sentence.charAt(i);
            }
            else{
                if(word != "") words.add(word);  
                word = "";
            }
        }
        words.add(word);
        return words;
    }

    //https://leetcode.com/problems/shuffle-the-array/

    public int[] shuffle(int[] nums, int n) {
        //First, Divide the array in two 
        int [] firstHalf = new int[nums.length/2];
        int [] lastHalf = new int[nums.length/2];
        
        //Copy the larger array into tthese two.
        for(int i = 0; i < nums.length/2; i++){
            firstHalf[i] = nums[i];
        }
        for(int i = 0; i <nums.length/2; i++){
            lastHalf[i] = nums [i + nums.length/2];
        }
        //Merge the two together
        int j = 0;
         for(int i = 0; i < nums.length/2; i++){
            nums[j++] = firstHalf[i];
            nums[j++] = lastHalf[i];
        }
        return nums;

    }

    //https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
      public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        //First, find the number of current maximum candies.
        int max = candies[0];
        for(int i = 0; i < candies.length; i++){
            if(candies[i] > max)
                max = candies[i];
            //Add extra candies to the array
            candies[i]+= extraCandies;
        }
        ArrayList<Boolean> canHaveMaxCandies = new ArrayList<Boolean>();
        for(int i = 0; i < candies.length; i++){
            if(candies[i] >= max)
                canHaveMaxCandies.add(true);
            else
                canHaveMaxCandies.add(false);
        }
        return canHaveMaxCandies;
        //Finally, return boolean list for each index where the array is greater than that. 
    }

    //https://leetcode.com/submissions/detail/476091166/    

     public int[] minOperations(String boxes) {
        int[] minMovesPerBox = new int[boxes.length()]; 
        //Select each box
        for(int i = 0; i < boxes.length(); i++){
            //For every other box, if it has a ball, what is its distance from our current box.
            int minMoves = 0;
            for(int j = 0; j < boxes.length(); j++){
                if(boxes.charAt(j) == '1'){
                    minMoves += Math.abs(j-i);
                }
            }
            minMovesPerBox[i] = minMoves;
        }
        return minMovesPerBox;
    }

    // https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
    //could decrease average run by changing it to stop at 9.
    public int minPartitions(String n) {
        //The mininum number will be the largest digit in the number. 
        char largestDigit = n.charAt(0);
        for(int i = 1; i< n.length(); i++){
            if(largestDigit < n.charAt(i)){
                largestDigit = n.charAt(i);
            }
            if(largestDigit == '9')
                break;
        }
        return Character.getNumericValue(largestDigit);
    }


    // https://leetcode.com/problems/to-lower-case/
    public String toLowerCase(String str) {
    //For this one, talk about the use of Stringbuilder
    StringBuilder s = new StringBuilder();
    
    for(int i = 0; i < str.length(); i++){
        s.append(Character.toLowerCase(str.charAt(i)));
    }
        return s.toString();
    }

    public int sumEvenGrandparent(TreeNode root) {
        //Check to see if the node is null or even
        int sum = 0;
        if(root == null)
            return 0;
        if(root.val %2 == 0){
        //If even, check to see if it has grandchildren, then add their value
            if(root.left != null){                
                if(root.left.left != null)
                    sum += root.left.left.val;
                if(root.left.right != null)
                    sum += root.left.right.val;
            }
            if(root.right != null){
                if(root.right.left != null)
                    sum += root.right.left.val;
                if(root.right.right != null)
                    sum += root.right.right.val;                
            }
            
        }
        //Continue wth its children
        return sum + sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
            
    }

    public static void testRectangleUpdate(int a){
        SubrectangleQueries r = new SubrectangleQueries(new int[a][a]);
        r.updateSubrectangle(0,0,a,a, 0);
    }


    /**
     * Mergesort algorithm.
     * @param a an array of Comparable items.
     */
    public static void mergeSort( int [ ] a )
    {
        int [ ] tmpArray = new int[ a.length ];
        mergeSort( a, tmpArray, 0, a.length - 1 );
    }

    /**
     * Internal method that makes recursive calls.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static void mergeSort(int [ ] a, int [ ] tmpArray,
               int left, int right )
    {
        if( left < right )
        {
            int center = ( left + right ) / 2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center + 1, right );
            merge( a, tmpArray, left, center + 1, right );
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
    private static void merge(int [ ] a, int [ ] tmpArray,
                               int leftPos, int rightPos, int rightEnd )
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a[ leftPos ] <= a[ rightPos ])
                tmpArray[ tmpPos++ ] = a[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        while( leftPos <= leftEnd )    // Copy rest of first half
            tmpArray[ tmpPos++ ] = a[ leftPos++ ];

        while( rightPos <= rightEnd )  // Copy rest of right half
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        // Copy tmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- )
            a[ rightEnd ] = tmpArray[ rightEnd ];
    }

}

//https://leetcode.com/problems/subrectangle-queries/

class SubrectangleQueries {
    
    public int[][] rectangle;
    
    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for(int i = col1; i < col2; i++){
            for(int j = row1; j< row2; j++){
                rectangle[j][i] = newValue;
            }
        }
    }
    
    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}