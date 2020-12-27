package Jaime;
public class Cuadro{
	public float intx,inty,w,h;
	public Cuadro(float intx, float inty, float w, float h){
		this.intx = intx;
		this.inty = inty;
		this.w = w;
		this.h = h;
	}
	public void update(float movx, float movy){
		this.intx+=movx;
		this.inty+=movy;
	}
}