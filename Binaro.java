
public class Binaro {
private int sizeX;
private int sizeY;
private int hMaxNumberOfZeroes;
private int hMaxNumberofOnes;
private int vMaxNumberofZeroes;
private int vMaxNumberofOnes;
private BinaroBox bb[][];

	public static void main(String args[]){
		//get SizeX & SizeY
		//SizeX=SizeY=12;
		Binaro b=new Binaro(10,10);
		
		b.setField(3, 1, true);
		b.setField(7, 1, false);
		b.setField(10, 1, false);
		b.setField(3, 2, false);
		b.setField(5, 2, true);
		b.setField(9, 2, true);
		b.setField(10, 2, true);
		b.setField(7, 3, true);
		b.setField(9, 3, true);
		b.setField(3, 4, false);
		b.setField(5, 4, true);
		b.setField(7, 4, true);
		b.setField(10, 4, false);
		b.setField(2, 6, true);
		b.setField(3, 6, true);
		b.setField(9, 6, false);
		b.setField(10, 6, false);
		b.setField(2, 8, true);
		b.setField(5, 8, false);
		b.setField(6, 8, false);
		b.setField(9, 8, true);
		b.setField(10, 8, true);
		b.setField(1, 9, false);
		b.setField(10, 9, true);
		b.setField(1, 10, true);
		b.setField(3, 10, false);
		b.setField(4, 10, false);
		b.setField(6, 10, false);
		b.setField(8, 10, true);
		b.setField(9, 10, true);
		System.out.println(b.toString()+"\n\n");
		if(b.solve(0, 0)==1) System.out.println("Solved");
		else System.out.println("couldn't solve");
		b.printSolved();
	}
	
	public Binaro(int Sizex,int Sizey){
		this.sizeX=Sizex;
		this.sizeY=Sizey;
		
		this.hMaxNumberOfZeroes=this.sizeY/2;
		this.hMaxNumberofOnes=this.sizeY-hMaxNumberOfZeroes;
		this.vMaxNumberofZeroes=this.sizeX/2;
		this.vMaxNumberofOnes=this.sizeX-vMaxNumberofZeroes;
		
		bb=new BinaroBox[this.sizeX][this.sizeY];
		for(int i=0;i<this.sizeX;i++)
			for(int j=0;j<this.sizeY;j++)
				bb[i][j]=new BinaroBox(this, i, j);
	}
	
	public void setField(int X, int Y, Boolean Value){
		bb[X-1][Y-1].setValue(Value);
		bb[X-1][Y-1].setUserDefined(true);
	}
	
	public int solve(int locX,int locY){
		int hNumberOfZeroes=0;
		int hNumberOfOnes=0;
		int vNumberofZeroes=0;
		int vNumberofOnes=0;
		
		if(bb[locX][locY].isUserDefined()){
			//go to the next
			int newlocX=locX;
			int newlocY=locY;
			if(locX<bb.length-1) newlocX=locX+1;
			else {
				if(locY!=bb[locX].length-1){
				newlocX=0;
				newlocY=locY+1;
				}
				else return 1;
			}
			return solve(newlocX,newlocY);
		}
		//try at locX, locY 0
		bb[locX][locY].setValue(false);
		//check for mistakes
			Boolean Mistake=false;
			//count number of zeroes in row
			for(int i=0;i<bb.length;i++){
				if(bb[i][locY].isUserDefined() || i<=locX)
					if(bb[i][locY].getValue()==false) hNumberOfZeroes++;
			}
			if(hNumberOfZeroes>hMaxNumberOfZeroes) Mistake=true;
			//check number of zeroes in column
			for(int i=0;i<bb[locX].length;i++)
			{
				if(bb[locX][i].isUserDefined() || i<=locY)
					if(bb[locX][i].getValue()==false) vNumberofZeroes++; 
			}
			if(vNumberofZeroes>vMaxNumberofZeroes) Mistake=true;
			//check for max 2 the same next to each other
			int start=locX-2>0?locX-2:0;
			int end=locX+2<bb.length-1?locX+2:bb.length-1;
			for(int i=start;i+2<=end;i++){
				if((i+1>locX && !bb[i+1][locY].isUserDefined()) || (i+2>locX && !bb[i+1][locY].isUserDefined()))
					break;
				if(bb[i][locY].getValue()==bb[i+1][locY].getValue() && bb[i][locY].getValue()==bb[i+2][locY].getValue())
					Mistake=true;
			}
			
			start=locY-2>0?locY-2:0;
			end=locY+2<bb[locX].length-1?locY+2:bb[locX].length-1;
			for(int i=start;i+2<=end;i++){
				if((i+1>locY && !bb[locX][i+1].isUserDefined()) || (i+2>locY && !bb[locX][i+2].isUserDefined()))
					break;
				if(bb[locX][i].getValue()==bb[locX][i+1].getValue() && bb[locX][i].getValue()==bb[locX][i+2].getValue())
					Mistake=true;
			}
		if(Mistake==false){
			int newlocX=locX;
			int newlocY=locY;
			if(locX<bb.length-1) newlocX++;
			else {
				if(locY!=bb[locX].length-1){
				newlocX=0;
				newlocY++;
				}
				else return 1;
			}
			if(solve(newlocX,newlocY)==1)
				return 1;
		}
		
		bb[locX][locY].setValue(true);
		
		Mistake=false;
		
		//count number of ones in row
		for(int i=0;i<bb.length;i++){
			if(bb[i][locY].isUserDefined() || i<=locX)
				if(bb[i][locY].getValue()==true) hNumberOfOnes++;
		}
		if(hNumberOfOnes>hMaxNumberofOnes) Mistake=true;
		//check number of ones in column
		for(int i=0;i<bb[locX].length;i++)
		{
			if(bb[locX][i].isUserDefined() || i<=locY)
				if(bb[locX][i].getValue()==true) vNumberofOnes++; 
		}
		if(vNumberofOnes>vMaxNumberofOnes) Mistake=true;
		//check for max 2 the same next to each other
		start=locX-2>0?locX-2:0;
		end=locX+2<bb.length-1?locX+2:bb.length-1;
		for(int i=start;i+2<=end;i++){
			if((i+1>locX && !bb[i+1][locY].isUserDefined()) || (i+2>locX && !bb[i+2][locY].isUserDefined()))
				break;
			if(bb[i][locY].getValue()==bb[i+1][locY].getValue() && bb[i][locY].getValue()==bb[i+2][locY].getValue()){ 
					Mistake=true;
			}
		}
		
		start=locY-2>0?locY-2:0;
		end=locY+2<bb[locX].length-1?locY+2:bb[locX].length-1;
		for(int i=start;i+2<=end;i++){
			if((i+1>locY && !bb[locX][i+1].isUserDefined()) || (i+2>locY && !bb[locX][i+2].isUserDefined()))
				break;
			if(bb[locX][i].getValue()==bb[locX][i+1].getValue() && bb[locX][i].getValue()==bb[locX][i+2].getValue())
				Mistake=true;
		}
		
		if(Mistake==false){
			int newlocX=locX;
			int newlocY=locY;
			if(locX<bb.length-1) newlocX++;
			else {
				if(locY!=bb[locX].length-1){
				newlocX=0;
				newlocY++;
				}
				else return 1;
			}
			return solve(newlocX,newlocY);
		}
		return 0;
	}
	
	public String toString(){
		String ret="";
		
		for(int i=0;i<bb.length;i++){
			for(int j=0;j<bb[0].length;j++){
				ret+=bb[j][i].toString()+"|";
			}
			ret+="\n";
		}
		
		return ret;
	}
	
	public void printSolved(){
		for(int i=0;i<bb.length;i++){
			for(int j=0;j<bb[0].length;j++){
				String s=" ";
				if(bb[j][i].getValue()) s="1";
				if(!bb[j][i].getValue()) s="0";
				System.out.print(s+"|");
			}
			System.out.println();
		}
	}
}
