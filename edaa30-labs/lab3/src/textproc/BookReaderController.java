package textproc;

import java.awt.Container;
import java.util.Comparator;
import java.util.Map;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

public class BookReaderController {

    public BookReaderController(GeneralWordCounter counter) {

            SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));

        }
        private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container pane = frame.getContentPane();

            JPanel panel = new JPanel();
            JButton freq = new JButton("Frequency");
            JButton alph = new JButton("Alphabetically");
            JTextField text = new JTextField("", 18);
            JButton find = new JButton("Find");
            SortedListModel<Map.Entry<String, Integer>> slm = new SortedListModel<>(counter.getWordList());
            JList<SortedListModel> wordList = new JList(slm);
            JScrollPane scroller = new JScrollPane(wordList);


            //Comparator<Map.Entry<String, Integer>> alphComp = (e1, e2) -> e1.getKey().compareTo(e2.getKey());
            //Comparator<Map.Entry<String, Integer>> freqComp = (e1, e2) -> e2.getValue() - e1.getValue();
            
            alph.addActionListener(e -> slm.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey())));
            freq.addActionListener(e -> slm.sort((e1, e2) -> e2.getValue() - e1.getValue()));
            find.addActionListener(e -> {
                boolean found = false;
                for(int i = 0; i < slm.getSize(); i++){
                    //Nedan görs v1
                    String findWord = text.getText().toLowerCase().trim();
                    
                    if(slm.getElementAt(i).getKey().equals(findWord)){
                        wordList.ensureIndexIsVisible(i);
                        wordList.setSelectedIndex(i);
                        found = true;
                        break;
                    }

                }
                //v2
                if(!found){
                    JOptionPane errWindow = new JOptionPane();
                    errWindow.showMessageDialog(frame, "Could not find word", "Error", 0);
                }
            });

            //v3
            text.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent e) {
                find.doClick();
            }
                
           });
            panel.add(freq);
            panel.add(alph);
            panel.add(text);
            panel.add(find);


            pane.setLayout(new BorderLayout(20, 15));
            pane.add(scroller);
            pane.add(panel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        }
}
