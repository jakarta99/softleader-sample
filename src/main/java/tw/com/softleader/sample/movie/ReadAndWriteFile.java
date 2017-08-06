package tw.com.softleader.sample.movie;

public class ReadAndWriteFile {

	private static String copyFilefrom = "/META-INF/LICENSE.txt";
	private static String copyfileto = "src/main/java/tw/com/softleader/sample/movie/apache-LICENSE.txt";

	public static void setCopyFilefrom(String copyFilefrom) {
		ReadAndWriteFile.copyFilefrom = copyFilefrom;
	}

	public static void setCopyfileto(String copyfileto) {
		ReadAndWriteFile.copyfileto = copyfileto;
	}

	public String getCopyFilefrom() {
		return copyFilefrom;
	}

	public String getCopyfileto() {
		return copyfileto;
	}
}