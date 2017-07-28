package tw.com.softleader.sample.country;

import java.io.File;
import java.io.IOException;
import org.springframework.util.FileSystemUtils;

public class FileReadWrite {

	public static void main(String[] args) throws IOException {
		// Spring API 找到更強大的FileSyStemUtils (CLASS)，直接路徑對路徑的複製。
		// 如果destination沒有apache-license文件，便會自動加並複製。
		File src = new File(
				"/Users/lacer/git/softleader-sample/src/main/java/tw/com/softleader/sample/country/LICENSE.txt");
		File des = new File(
				"/Users/lacer/git/softleader-sample/src/main/java/tw/com/softleader/sample/country/apache-license.txt");
		// File地址在Mac和Window會不同！要改址再跑。
		FileSystemUtils.copyRecursively(src, des);

		// ============================================================================

		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("Country/country.xml");
		// Resource res = context.getResource("classpath:META-INF/LICENSE.txt");
		// OutputStream os = new FileOutputStream(new File(
		// "/Users/lacer/git/softleader-sample/src/main/java/tw/com/softleader/sample/country/apache-license.txt"));
		// // File地址在Mac和Window會不同！要改址再跑。
		//
		// // 參考Spring API找到StreamUtils有複製stream的方法，收input/outputStreams。
		// // FileSystemUtils也有複製方法，收reader/writer。
		// StreamUtils.copy(res.getInputStream(), os);
		//
		// os.close();
		// if (os != null) {
		// System.out.println("Apache-License has been written");
		// }
	}

}