package org.usfirst.frc.team4534.robot.util;

public class Maths {

	/**
	 * Pythagorean theorem
	 * 
	 * @return
	 */
	public static final double pyth(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public static class Vector {
		public double x, y;
		public Vector() {
			this(0,0);
		}
		public Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public double magnitude() {
			return pyth(x,y);
		}
	}
	
	public static class Fluid {
		
		public static class Vector<T> {
			public T x, y;
			public Vector(T x, T y) {
				this.x = x;
				this.y = y;
			}
		}
		
	}
	
}
