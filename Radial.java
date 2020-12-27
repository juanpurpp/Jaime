package Jaime;
public class Radial{
	float xcenter,ycenter, radio;
	public Radial(float xcenter, float ycenter, float radio){
		this.xcenter = xcenter;
		this.ycenter = ycenter;
		this.radio=radio;
	}
	public void update(float movx, float movy){
		this.xcenter+=movx;
		this.ycenter+=movy;
	}
}