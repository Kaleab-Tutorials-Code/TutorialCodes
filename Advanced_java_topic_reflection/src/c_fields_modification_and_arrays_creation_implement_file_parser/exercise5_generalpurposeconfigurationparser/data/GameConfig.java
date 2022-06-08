package c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.data;

public class GameConfig {
	private int releaseYear;
	private String gameName;
	private double price;
	
	public int getReleaseYear() {
		return releaseYear;
	}
	public String getGameName() {
		return gameName;
	}
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "GameConfig [releaseYear=" + releaseYear + ", gameName=" + gameName + ", price=" + price + "]";
	}
	
}

