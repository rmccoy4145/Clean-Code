package com.b.simple.design.business.student;
public class StudentHelper {
    private static final int UPPER_LIMIT_GRADEB = 80;
    private static final int MATHS_UPPER_INCREASE = 10;   
    private static final int LOWER_LIMIT_GRADE_B = 51; 
    
    
    private static final String GRADE_B = "B";
    private static final String GRADE_A = "A";
    private static final String GRADE_C = "C";
    private static final int LOWER_LIMIT_GRADE_A = 91; 
    private static final int MATHS_LOWER_INSCREASE = 5; 
	/* PROBLEM 1 */	
	/*
	* You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
	*/
	public boolean isGradeB(int marks, boolean isMaths) {
            int increase = isMaths ? MATHS_UPPER_INCREASE : 0;
                    
            int upperLimter = UPPER_LIMIT_GRADEB + increase;
            
            return marks>=LOWER_LIMIT_GRADE_B && marks<=upperLimter;

	}
 



	/* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

 
   
	public String getGrade(int mark, boolean isMaths) {
		
                int mathsLowerIncrease = isMaths ? MATHS_LOWER_INSCREASE : 0;
                
		if (mark >= LOWER_LIMIT_GRADE_A + mathsLowerIncrease)
			return GRADE_A;
                
		if (mark >= LOWER_LIMIT_GRADE_B + mathsLowerIncrease)
			return GRADE_B;
		
		return GRADE_C;
	}

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     * 
     * Return value can be YES, NO or MAYBE.
     * 
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     * 
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     * 
     * marks1 - your marks
     * marks2 - your friends marks
    */
        
    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
        if ((isMaths ? marks1 <= 25 : marks1 <= 20)
                || (isMaths ? marks2 <= 25 : marks2 <= 20)) return "NO";
        if ((isMaths ? marks1 >= 85 : marks1 >= UPPER_LIMIT_GRADEB)
                || (isMaths ? marks2 >= 85 : marks2 >= UPPER_LIMIT_GRADEB)) return "YES";
        return "MAYBE";
    }	

}