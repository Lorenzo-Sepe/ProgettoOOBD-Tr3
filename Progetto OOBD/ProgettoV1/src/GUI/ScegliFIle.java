package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Controller;

import java.awt.Choice;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ScegliFIle extends JFrame {

	private JPanel contentPane;
	private JTextField testoFile;
	private JLabel Foto;
	private JLabel jlabelProva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScegliFIle frame = new ScegliFIle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScegliFIle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		jlabelProva = new JLabel("TESTO PROVA");
		contentPane.add(jlabelProva);
		
		JButton btnNewButton = new JButton("Modifica Foto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
			
	            fileChooser.addChoosableFileFilter(new ImageFilter());
	            fileChooser.setAcceptAllFileFilterUsed(false);

	            int option = fileChooser.showOpenDialog(new JPanel());
	            if(option == JFileChooser.APPROVE_OPTION){
	               File file = fileChooser.getSelectedFile();
	               testoFile.setText("File Selected: " + file.getName());
	               Controller c=new Controller();
	               jlabelProva.setText(file.toPath().toString());
	               //c.setFotoContatto(file.getAbsolutePath(), 0);
	               System.out.println("file.getAbsolutepath: "+file.getAbsolutePath());
	               try {
					System.out.println("file.getCanonicalPath: "+file.getCanonicalPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	               System.out.println("file.getPath: "+file.getPath());
	               
	               System.out.println("file.toURI: "+file.toURI().toString());
	               System.out.println("file.getPath: "+file.getPath());
	               System.out.println("getresorce: "+ScegliFIle.class.getResource(".").getPath());
	               
	               //----------------------------------------------------------------------------------
	               File v = new File ("void.txt");
	               String name = v.getAbsolutePath();
	               System.out.println(name);
	               name = name.substring(0,name.lastIndexOf("\\")+1);
	               System.out.println(name);
	               name = name + "src\\Immagini\\";
	               System.out.println(name);
	               try {
	       			c.CaricaESettaFoto(file.getAbsolutePath(), name, 100, 100);
	       		} catch (IOException e1) {
	       			// TODO Auto-generated catch block
	       			e1.printStackTrace();
	       		}
	               //----------------------------------------------------------------------------------
	               

	            }else{ 
	               testoFile.setText("Open command canceled");
	            }
			}
		});
		
		testoFile = new JTextField();
		contentPane.add(testoFile);
		testoFile.setColumns(10);
		contentPane.add(btnNewButton);
		
		Foto = new JLabel("Foto profilo");
		contentPane.add(Foto);
	
	}
	
	
	class ImageFilter extends FileFilter {
		   public final static String JPEG = "jpeg";
		   public final static String JPG = "jpg";
		   public final static String GIF = "gif";
		   public final static String TIFF = "tiff";
		   public final static String TIF = "tif";
		   public final static String PNG = "png";
		   
		   @Override
		   public boolean accept(File f) {
		      if (f.isDirectory()) {
		         return true;
		      }

		      String extension = getExtension(f);
		      if (extension != null) {
		         if (extension.equals(TIFF) ||
		            extension.equals(TIF) ||
		            extension.equals(GIF) ||
		            extension.equals(JPEG) ||
		            extension.equals(JPG) ||
		            extension.equals(PNG)) {
		            return true;
		         } else {
		            return false;
		         }
		      }
		      return false;
		   }

		   @Override
		   public String getDescription() {
		      return "Image Only";
		   }

		   String getExtension(File f) {
		      String ext = null;
		      String s = f.getName();
		      int i = s.lastIndexOf('.');
		   
		      if (i > 0 &&  i < s.length() - 1) {
		         ext = s.substring(i+1).toLowerCase();
		      }
		      return ext;
		   } 
	

}}

