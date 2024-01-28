package textproc;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class BookReaderApplication {
    

    public static void main(String[] args) throws FileNotFoundException{
        Scanner scan = new Scanner(new File("edaa30ht22-workspace/lab3/undantagsord.txt"), "UTF-8");
		Scanner s  = new Scanner(new File("edaa30ht22-workspace/lab3/nilsholg.txt"), "UTF-8");
		scan.findWithinHorizon("\uFEFF", 1);
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

        Set<String> stopwords = new HashSet<>();

		while(scan.hasNext()){
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}

        GeneralWordCounter r = new GeneralWordCounter(stopwords);
        
        
        s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
        
		while (s.hasNext()) {
            String word = s.next().toLowerCase();
            r.process(word);
		}
        BookReaderController window = new BookReaderController(r);
        
		s.close();
        scan.close();
    }
}
