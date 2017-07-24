
public class BinaroBox {
private Binaro b;
private int LocationX;
private int LocationY;
private Boolean UserDefined=false;
private Boolean Value;

	
	public BinaroBox(Binaro binaro,int x,int y){
		b=binaro;
		setLocationX(x);
		setLocationY(y);
		Value=new Boolean(false);
	}
	
	public void setValue(Boolean Value){
		this.Value=Value;
	}
	public Boolean getValue(){
		return this.Value;
	}
	
	public void setUserDefined(Boolean ud){
		this.UserDefined=ud;
	}
	
	public Boolean isUserDefined(){
		return this.UserDefined;
	}
	
	public int getLocationX() {
		return LocationX;
	}

	public void setLocationX(int locationX) {
		LocationX = locationX;
	}

	public int getLocationY() {
		return LocationY;
	}

	public void setLocationY(int locationY) {
		LocationY = locationY;
	}
	public String toString(){
		return isUserDefined()?(getValue()?"1":"0"):" ";
	}
}
