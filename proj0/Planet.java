import java.lang.Math;
public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public double G = 6.67e-11;
	public Planet(double xP, double yP, double xV,
		      double yV, double m, String img){
			      xxPos = xP;
			      yyPos = yP;
			      xxVel = xV;
			      yyVel = yV;
			      mass = m;
			      imgFileName = img;	
		      }
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		return (G*mass*p.mass)/(calcDistance(p)*calcDistance(p));
	}

	public double calcForceExertedByX(Planet p){
		double dx = xxPos - p.xxPos;
		double uFx = calcForceExertedBy(p)*dx/calcDistance(p);
		return uFx>0?uFx:-uFx;
	}
	public double calcForceExertedByY(Planet p){
		double dy = yyPos - p.yyPos;
		double uFy = calcForceExertedBy(p)*dy/calcDistance(p);
		return uFy>=0?uFy:-uFy;
	}

	public double calcNetForceExertedByX(Planet[] p){
		double FnetX = 0;
		for(int i=0; i<p.length; i++){
			if(this==p[i])
				continue;
			FnetX +=calcForceExertedByX(p[i]);
		}
		return FnetX;
	}
	public double calcNetForceExertedByY(Planet[] p){
		double FnetY = 0;
		for(int i=0; i<p.length; i++){
			if(this==p[i])
				continue;
			FnetY +=calcForceExertedByY(p[i]);
		}
		return FnetY;
	}

	public void update(double dt, double fX, double fY){
		double a_x = fX/mass;
		double a_y = fY/mass;
		xxVel += dt * a_x;
		yyVel += dt * a_y;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}


}