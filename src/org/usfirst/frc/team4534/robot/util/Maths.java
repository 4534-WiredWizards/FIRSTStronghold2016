package org.usfirst.frc.team4534.robot.util;

/**
 * Math functions not provided by {@link java.lang.Math}
 * 
 * @since Sprint 1
 * @author Brandon Dyer
 *
 */
public final class Maths {

	/**
	 * Pythagorean theorem
	 * 
	 * @return sqrt(x^2 + y^2)
	 */
	public static final double pyth(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * Linear interpolation function.
	 * 
	 * @param a
	 *            origin value
	 * @param b
	 *            target value
	 * @param factor
	 *            value between 0.0 and 1.0 where 0.0 will return the origin,
	 *            1.0 will return the target value, and 0.5 will return the
	 *            mean.
	 * @return linear interpolation
	 */
	public static final double lerp(double a, double b, double factor) {
		return (1 - factor) * a + factor * b;
	}

	public static final class Fast {

		/**
		 * Linear interpolation function. (Fast and imprecise)
		 * 
		 * @param a
		 *            origin value
		 * @param b
		 *            target value
		 * @param factor
		 *            value between 0.0 and 1.0 where 0.0 will return the
		 *            origin, 1.0 will return the target value, and 0.5 will
		 *            return the mean.
		 * @return linear interpolation
		 */
		public static final double lerp(double a, double b, double factor) {
			return a + factor * (b - a);
		}
	}

	public static class Vector {
		public double x, y;

		public Vector() {
			this(0, 0);
		}

		public Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double magnitude() {
			return pyth(x, y);
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
