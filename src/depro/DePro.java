package depro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DePro extends JFrame {
    
    private GridLayout layout;
    
    public DePro() {
        this.setTitle(Constants.TITLE);
        this.setSize(900, 900);
        this.layout = new GridLayout(4, 4);
        this.setLayout(this.layout);
        JButton button = new JButton("Выбор архива");
        this.layout.addLayoutComponent(string, this);
        this.add(button, 0, 1);
        
    }
   
    public void selectFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            try {
                this.unzip(selectedFile,  "./");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
       
        }
    }
    

    public void unzip(File source, String out) throws IOException {
       try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source))) {

           ZipEntry entry = zis.getNextEntry();

           while (entry != null) {

               File file = new File(out, entry.getName());

               if (entry.isDirectory()) {
                   file.mkdirs();
               } else {
                   File parent = file.getParentFile();

                   if (!parent.exists()) {
                       parent.mkdirs();
                   }

                   try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {

                       int bufferSize = Math.toIntExact(entry.getSize());
                       byte[] buffer = new byte[bufferSize > 0 ? bufferSize : 1];
                       int location;

                       while ((location = zis.read(buffer)) != -1) {
                           bos.write(buffer, 0, location);
                       }
                   }
               }
               entry = zis.getNextEntry();
           }
       }
   }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DePro frame = new DePro();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
