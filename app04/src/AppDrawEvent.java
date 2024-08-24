import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.imageio.ImageIO; 
import bix.BixelMap;

class AppFrame extends JFrame { 
    public AppFrame(String title) {
	super(title);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}

class PicturePanel extends JPanel {
    BufferedImage img;
    public BixelMap bmap;

    public PicturePanel(String path) {
	super();
        File f = new File(path);
	try {
	    img = ImageIO.read(f);
	} catch (IOException e) {
	    System.out.println("bruh");
	}
	System.out.println(f.getAbsolutePath());

	if (img == null)
	    this.setPreferredSize(new Dimension(0, 0));
	else {
	    this.setPreferredSize(new Dimension(img.getWidth() * 2, img.getHeight() * 2));
	    bmap = new BixelMap(img);
	}
    }

    @Override 
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);

	// g.drawImage(img, 0, 0, this);
	g.drawImage(bmap.update(), 0, 0, this);
    }

    public void setBMode(int code) { bmap.setState(code); }
}

class Menu extends JPanel {
    JButton original;
    JButton grayscale;
    JButton pattern;

    public Menu(PicturePanel pp) {
	super();
	original  = new JButton("original");
        grayscale = new JButton("grayscale");
        pattern   = new JButton("pattern");
	this.add(original);
	this.add(grayscale);
	this.add(pattern);
	this.addImg(pp);
    }

    public void addImg(PicturePanel pp) {
	original.addActionListener(new ActionListener(){
	    public void actionPerformed(final ActionEvent e) {
		pp.setBMode(0);
		pp.repaint();
	    }
	});
	grayscale.addActionListener(new ActionListener(){
	    public void actionPerformed(final ActionEvent e) {
		pp.setBMode(1);
		pp.repaint();
	    }
	});
	pattern.addActionListener(new ActionListener(){
	    public void actionPerformed(final ActionEvent e) {
		pp.setBMode(2);
		pp.repaint();
	    }
	});
    }
}

public class AppDrawEvent
{ 
    public static void main( String[] args ) 
    { 
	System.out.println("Enter the path of your picture: ");
	System.out.print("> ");
	Scanner sc = new Scanner(System.in);
	String path = sc.next();
	
	AppFrame frame = new AppFrame("Allgemeines Programmierpraktikum"); 
	JPanel panel = new JPanel(new BorderLayout());  
	frame.add(panel);
	
	JPanel draw  = new JPanel();
	PicturePanel pp = new PicturePanel(path);
	draw.add(pp);

	Menu menu = new Menu(pp); 

	panel.add(draw, BorderLayout.CENTER);
	panel.add(menu, BorderLayout.NORTH);
	
	frame.pack();
	frame.setVisible(true); 
    } 
}
