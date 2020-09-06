package swing;

import java.awt.*; 
import javax.swing.*; 
import java.io.*; 
import java.awt.event.*; 
import javax.swing.plaf.metal.*; 
import javax.swing.text.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class editor extends JFrame implements ActionListener { 
    // Text component 
    JTextArea t; 
  
    // Frame 
    JFrame f; 
    String v = null;
    String v1=null;

  JPanel contentPane;
  private final Action action = new SwingAction();
     
    editor() 
    {  
    	
        // Create a frame 
        f = new JFrame("editor"); 
        
        // Text component 
        t = new JTextArea(10,10); 
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        t.setFont(font1);
    
        JScrollPane sp= new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
  
        // Create a menubar 
        JMenuBar mb = new JMenuBar();
  
        // Create amenu for menu 
        JMenu m1 = new JMenu("File"); 
        m1.setPreferredSize(new Dimension(144, 24));
        m1.setMnemonic(KeyEvent.VK_F);

        // Create menu items 
         
        JMenuItem mi1 = new JMenuItem("Open"); 
        
        KeyStroke keyStrokeToOpen= KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
    mi1.setAccelerator(keyStrokeToOpen);
    
        JMenuItem mi2 = new JMenuItem("Save"); 
        KeyStroke keyStrokeToSave
        = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
    mi2.setAccelerator(keyStrokeToSave);

         
  
        // Add action listener 
        mi1.addActionListener(this); 
        mi2.addActionListener(this); 
         
        m1.add(mi1); 
        m1.add(mi2);  
        JMenuItem run = new JMenuItem("RUN");
        run.setPreferredSize(new Dimension(0, 24));
        run.setBounds(new Rectangle(10, 10, 10, 10));
        JMenuItem mc = new JMenuItem("close"); 
        mc.setAlignmentX(Component.LEFT_ALIGNMENT);
        mc.setPreferredSize(new Dimension(7, 24));
       
        mc.addActionListener(this); 
        run.addActionListener(this); 
        mb.add(m1);
        mb.add(run);
        mb.add(mc); 
  
        f.setJMenuBar(mb);
        f.getContentPane().add(sp);
        f.setSize(500, 500); 
        f.show(); 
    } 
  
    // If a button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
       
      
         if (s.equals("Save")) { 
            // Create an object of JFileChooser class 
            JFileChooser j = new JFileChooser("f:"); 
  
            // Invoke the SaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            if (r == JFileChooser.APPROVE_OPTION) { 
  
                // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
                
  
                try { 
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fi, false); 
                    
                    // Create buffered writer to write 
                    BufferedWriter w = new BufferedWriter(wr); 
  
                    // Write 
                    w.write(t.getText()); 
                    w.flush(); 
                    w.close(); 
                    v=fi.getName();
                    v1 = fi.getName().substring(0, fi.getName().lastIndexOf("."));
  
                   
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(f, evt.getMessage()); 
                } 
            } 
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation"); 
        } 
                else if (s.equals("Open")) { 
            // Create an object of JFileChooser class 
            JFileChooser j = new JFileChooser("f:"); 
            j.setDialogTitle("Select a file");
    		j.setAcceptAllFileFilterUsed(false);
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java File", "java");
    		j.addChoosableFileFilter(filter);


            // Invoke the showsOpenDialog function to show the save dialog 
            int r = j.showOpenDialog(null); 
  
            // If the user selects a file 
            if (r == JFileChooser.APPROVE_OPTION) { 
                // Set the label to the path of the selected directory 
                File fi = new File(j.getSelectedFile().getAbsolutePath()); 
               
  
                try { 
                    // String 
                    String s1 = "", sl = ""; 
  
                    // File reader 
                    FileReader fr = new FileReader(fi); 
  
                    // Buffered reader 
                    BufferedReader br = new BufferedReader(fr); 
  
                    // Initilize sl 
                    sl = br.readLine(); 
  
                    // Take the input from the file 
                    while ((s1 = br.readLine()) != null) { 
                        sl = sl + "\n" + s1; 
                    } 
  
                    // Set the text 
                    t.setText(sl); 
                    v=fi.getName();
                    v1 = fi.getName().substring(0, fi.getName().lastIndexOf("."));
                    
                   
                    	
                    
                    
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(f, evt.getMessage()); 
                } 
            } 
            // If the user cancelled the operation 
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation"); 
        } 
        else if (s.equals("New")) { 
            t.setText(""); 
        } 
        else if (s.equals("RUN")) {
        	try
            { 
                
        		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"javac "+ v +" &&  java " + v1 +" \"");  
      
            } 
            catch (Exception evt) 
            { 
                System.out.println("HEY Buddy ! U r Doing Something Wrong "); 
                evt.printStackTrace(); 
            } 
        }
        
        else if (s.equals("close")) { 
            f.setVisible(false); 
        } 
    } 
  
    // Main class 
    public static void main(String args[]) 
    { 
        editor e = new editor();
      }
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}