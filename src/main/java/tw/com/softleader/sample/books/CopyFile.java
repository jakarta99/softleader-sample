package tw.com.softleader.sample.books;

public class CopyFile {
	private static String configFile = "/META-INF/LICENSE.txt";
	private static String copyfileto = "src/main/java/tw/com/softleader/sample/books/apache-LICENSE.txt";

	public static void setConfigFile(String configFile) {
		CopyFile.configFile = configFile;
	}

	public static void setCopyfileto(String copyfileto) {
		CopyFile.copyfileto = copyfileto;
	}

	public String toStringfrom() {
		return configFile;
	}

	public String toStringto() {
		return copyfileto;
	}

}
