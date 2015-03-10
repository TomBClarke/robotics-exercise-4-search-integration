package robotSearches;

/**Coordinate class allows for coordinates to be created as a type.
 * @author Kyle Allen-Taylor
 */
public class Coordinate {
	public int x, y;

	/**Constructs the coordinate, the x and y coordinate are required.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		Coordinate c = (Coordinate) o;
		return x == c.x && y == c.y;
	}
	
	/**Returns the x coordinate of the coordinates.
	 * @return The x coordinate
	 */
	public int x(){
		return this.x;
	}
	
	/**Returns the y coordinate of the coordinates.
	 * @return The y coordinate
	 */
	public int y(){
		return this.y;
	}
}
