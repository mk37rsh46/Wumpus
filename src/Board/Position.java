	package Board;

public class Position {
	public int row;
	public int col;
	public boolean isoccupied;
	
	public Position(int x, int y) {
		this.row = x;
		this.col = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + row;
		result = prime * result + col;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (row != other.row)
			return false;
		if (col != other.col)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [row=" + row + ", col=" + col + "]";
	}

	
	

}
