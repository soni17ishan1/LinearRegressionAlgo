class points{
	private int[] x;
	private int[] y;
	private int xmean = 0;
	private int ymean = 0;
	private float b0 = 0;//constant for equation of regression line.
	private float b1= 0;//constant for equation of regression line.
	
	points(int x[],int y[]){
		this.x = x;
		this.y = y;
	}
	
	public float getB1() {
		return b1;
	}

	public void setB1(float b1) {
		this.b1 = b1;
	}

	public float getB0() {
		return b0;
	}

	public void setB0(float b0) {
		this.b0 = b0;
	}
	
	public int getXmean() {
		return xmean;
	}

	public void setXmean(int xmean) {
		this.xmean = xmean;
	}

	public int getYmean() {
		return ymean;
	}

	public void setYmean(int ymean) {
		this.ymean = ymean;
	}
	
	public void xmean(){ // finding xbar i.e mean for x
		for(int i =0;i<x.length;i++){
			xmean = x[i]+xmean;
		}
		xmean = xmean/x.length;
		System.out.println("Xmean is :"+xmean);
		setXmean(xmean);
	}
	
	public void ymean(){ // finding ybar i.e. mean for y
		for(int i =0;i<y.length;i++){
			ymean = y[i]+ymean;
		}
		ymean = ymean/y.length;
		System.out.println("Ymean is :"+ymean);
		setYmean(ymean);
	}
	
	public void calculateb1(){ // calculating constant b1: sum(x-xmean)(y-ymean)/sum (x-xmean)(x-xmean)
		float xy = 0;
		float xdifference = 0;
		float b1= 0 ;
		for(int i=0;i<x.length;i++){
			xy += (x[i]-getXmean()) * (y[i]-getYmean());
			xdifference+= (x[i]-getXmean())*(x[i]-getXmean()); 
		}
		b1= xy/xdifference;
		System.out.println("(x-xmean)*(y-ymean): "+ xy);
		System.out.println("difference of x and mean is:"+ xdifference);
		System.out.println("value of constant b1 for the line y=b0+b1x is:"+b1);
		setB1(b1);
	}
	
	public void calculateb0(){ // calculating constant b0 by substituting the value of b1.
		float b0=0;
		b0 = getYmean()-(getB1()*getXmean());
		System.out.println("value of constant b0 for the line y=b0+b1x is:"+b0);
		setB0(b0);
	}
	
	public void calculateR2(){ //comparing the actual distance - mean and estimated distance - mean
							   //to know how the values are presented on the regression line.Between 0 to 1.(more is the value inclined towards 1 better it is.)	
		float estimatedy = 0;
		float estimatediff = 0;
		float actualdiff = 0;
		float r2;
		for(int i=0;i<x.length;i++){
			estimatedy = getB0() + (getB1()*x[i]);//estimating the value of y based on the equation.(y=b0+(b1*x))
			estimatediff += (estimatedy-getYmean())* (estimatedy-getYmean());//estimated distance - mean
			actualdiff+= (y[i]-getYmean())*(y[i]-getYmean());//actual distance - mean
		}
		r2 = estimatediff/actualdiff;//comparing both
		System.out.println("Actual value distance and mean difference is:"+actualdiff);
		System.out.println("Estimated value distance and mean difference is:"+estimatediff);
		System.out.println("Rsquare is:"+r2+":::more is the value inclined towards 1 better it is." );
	}
	
	public void predictY(float x){
		float result = 0;
		System.out.println("=============================================================");
		System.out.println("The value of X is:" + x);
		result = getB0() + (getB1()*x);
		System.out.println("The Estimated value of Y based on the Regression Line is:"+result);
	}
	
	public void isInDeviationRange(float x,float y,float dev){
		System.out.println("=============================================================");
		System.out.println("DEVIATION GIVEN BY THE USER FOR THE ALLOWED RANGE IS +-:"+dev);
		float m = getB1();//y=mx+c(Equation of line-slope point equation), ax+by+c - standard point equation
		float b= -1;
		float c = getB0();
		System.out.println("Equation of the line is: "+"y="+m+"x+"+c);
		double d = Math.abs((m*x)+(b*y)+c);//find perpendicular distance from line to a point
		d = d/Math.sqrt((m*m)+(b*b));		// if the distance is less then or equal to the deviation it means that the point is between the deviation range.
		System.out.println("The perpendicular distance from deviation range line to a given point is:"+d );
		if(d<=dev){
			System.out.println("True:As the distance is less then the deviation, it is inside the deviation range provided");
		}
		else{
			System.out.println("False:As the distance is more then the deviation, it is outside the deviation range provided");
		}
	}
}


public class LinearRegressionDeviation {
	public static void main(String Args[]){
		int x[]={1,2,3,4,5};
		int y[]={2,4,5,4,5};
		points p = new points(x, y);
		p.xmean();
		p.ymean();
		p.calculateb1();
		p.calculateb0();
		p.calculateR2();
		p.predictY(7); //predict the value of Y for certain X.
		p.isInDeviationRange(15,0,2);
	}
}
