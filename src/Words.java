
public class Words {
	private String[] words;
	private int randomInt;
	private boolean check, win;
	private String answer, displayWord;
	
	public Words(){
		words = new String[20];
		words[0] = "DATA MEMBER";
		words[1] = "MODULARITY";
		words[2] = "COLLECTION"; 
		words[3] = "UTILITY";
		words[4] = "REGISTER";
		words[5] = "PREFIX NOTATION";
		words[6] = "ENCAPSULATION";
		words[7] = "TRUTH TABLE";
		words[8] = "TRANSLATOR";
		words[9] = "BASE";
		words[10] = "CLASS";
		words[11] = "CASE";
		words[12] = "BLOCK";
		words[13] = "NOR";
		words[14] = "AND";
		words[15] = "SEMANTICS";
		words[16] = "SEEK TIME";
		words[17] = "ADDRESS BUS";
		words[18] = "BASE";
		words[19] = "NAND";
		
		answer = returnRandomString();
		displayWord = "";
		setDisplayWord();
		check = false;
		win = false;
	}
	
	public int returnRandomInt(int min, int max){
		return randomInt = (int)(Math.random()*(max-min+1)+min);
	}
	
	public String returnRandomString(){
		return words[returnRandomInt(0, words.length-1)];
	}
	
	public void setDisplayWord(){
		for (int i =0; i<answer.length(); i++){
			if (answer.charAt(i) == ' '){
				displayWord += " ";
			}
			else displayWord += "-";
		}
	}
	
	public String returnDisplayWord(){
		return displayWord;
	}
	
	public String returnAnswer(){
		return answer;
	}
	
	public boolean checkChar(char c){
		check = false;
		for (int i = 0; i < answer.length(); i++){
			if (c == answer.charAt(i)){
				displayWord = displayWord.substring(0, i) + answer.charAt(i) + displayWord.substring(i+1);	
				check  = true;
			}
		}
		return check;
	}
	
	public boolean checkWin(){
		if (displayWord.equals(answer)){
			win = true;
		}
		return win;
	}
	
	public void reset(){
		answer = returnRandomString();
		displayWord = "";
		setDisplayWord();
		check = false;
		win = false;
	}
}
