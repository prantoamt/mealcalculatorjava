import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Date;
import java.awt.*;
import java.text.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class myPanel extends JPanel{
	
	
	static int calculate_counter = 0;
	static myTable table;
	static DefaultTableModel model;
	static JLabel label, welcome, namelabel, bazarlabel, meallabel, monthname; //Label containd "Meal_rate" & label1 contains Welcome Massege
	static JTextField mealratefield, namefield, bazarfield, mealfield, monthnamefield;
	static JButton add, delete, update, calculate, Print, Save, Load, save_as_pdf;
	ImageIcon icon;
	
	static String[] columns =  {"Name", "Total Bazar", "Total Meal", "Will Pay", "Will Get"}; //Name of Table Columns
	static String[] data;
	static double meal_rate;
	private String month_name;

	public  myPanel() // Constructor of myPanel Class which extends JPanel
	{
		setLayout(null);
		loadImage(this.getClass().getResource("/background.png"));				
		
/*........................This part controlls month name Label ............................*/
		
		monthname = new JLabel ("Month : ");
		monthname.setBounds(1010, 25, 80, 30);
		monthname.setForeground(Color.black.darker());
		monthname.setFont(new Font("Arial",1,12));
		
		monthnamefield = new JTextField();
		monthnamefield.setBounds(1080, 32, 100, 20);
		
/*.........................................................................................*/
		
/*.......................This part controlls Table.............*/	    		
		table = new myTable ();
		table.setBackground(Color.white);
		table.setForeground(Color.black);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		data = new String[5];
		table.setPreferredScrollableViewportSize(new Dimension(600,300));
		table.setFillsViewportHeight(true);
		JScrollPane scp = new JScrollPane(table);
		scp.setBounds(300, 100, 700, 400);
		table.setFont(new Font("Arial",0,13));
		
		table.addMouseListener(new MouseAdapter() // Controlls to set name, bazar, meal textfield when any row is selected
				{
					public void mouseClicked(MouseEvent e)
					{
						int  i = table.getSelectedRow();
						if(i >= 0)
						{
							namefield.setText(table.getValueAt(i, 0).toString());
							bazarfield.setText(table.getValueAt(i, 1).toString());
							mealfield.setText(table.getValueAt(i, 2).toString());
						}
					}
				});
/*...............................................................*/	    
	    
/*...........This part controlls Labels and Text Fields on the Panel ........*/
		
		namelabel = new JLabel("Name: ");
	    namelabel.setBounds(40, 120, 60, 20);
	    namelabel.setForeground(Color.black.darker());
	    
	    namefield = new JTextField();
	    namefield.setBounds(110, 120, 160, 20);
	    
	    bazarlabel = new JLabel("Bazar: ");
	    bazarlabel.setBounds(40, 150, 60, 20);
	    bazarlabel.setForeground(Color.BLACK.darker());
	    
	    bazarfield = new JTextField();
	    bazarfield.setBounds(110, 150, 160, 20);
	    
	    meallabel = new JLabel("Meal: ");
	    meallabel.setBounds(40, 180, 60, 20);
	    meallabel.setForeground(Color.BLACK.darker());
	    
	    mealfield = new JTextField();
	    mealfield.setBounds(110, 180, 160, 20);
/*...........................................................................*/

/*.................This Part Controlls Add Button......................*/	 
	    
	    add = new JButton ("Add"); // Creats a new Add Button
	    add.setBounds(70, 240, 80, 30);//Sets the Add Button on preferred place on the panel 
	    add.addActionListener(new ActionListener()
	    		{
	    	         public void actionPerformed (ActionEvent e) // This Method Controlls what to do if Add Button Clicked.
	    	         {
	    	        	
	    	        	 try
	    	        	 {
	    	        		 double value = Double.parseDouble(bazarfield.getText()) - 0;
	    	        		 double value1 = Double.parseDouble(mealfield.getText()) - 0;
	    	        		 
	    	        		 data[0] = namefield.getText();
		    	        	 data[1] = bazarfield.getText(); 
		    	        	 data[2] = mealfield.getText();        	 
		    	        	       	 
		    	        	 model.addRow(data);
		    	        	 namefield.setText("");
		    	        	 bazarfield.setText("");
		    	        	 mealfield.setText("");
	    	        	 }catch(Exception e1)
	    	        	 {
	    	        		 namefield.setText("");
		    	        	 bazarfield.setText("");
		    	        	 mealfield.setText("");
	    	        		JOptionPane.showMessageDialog(null, "Invalid Input");	
	    	        	 }
	    	        	 
    	        	}	  
	    		});
	    add.setToolTipText("Add above data to the table");
	    
/*.....................................................................*/	    

/*......This Part controlls Delete Button.........T..................................*/		 

	    delete = new JButton("Delete");// Creates update Button
	    delete.setBounds(170, 240, 90, 30);
	    delete.addActionListener(new ActionListener()
	    		{
	    	        public void actionPerformed(ActionEvent e)
	    	        {
	    	        	int i = table.getSelectedRow();
	    	        	if(i >=0)
	    	        	{
	    	        		model.removeRow(i);
	    	        	}
	    	        	else
	    	        		JOptionPane.showMessageDialog(null, "Invalid Selection");
	    	        }
	    		});
	    
	    delete.setToolTipText("Delete selected row from the table");
	    
/*..........................................................................*/	
	    
/*................................This Part controlls Delete Button...........................................*/		 

	    update = new JButton("Update");// Creates update Button
	    update.setBounds(90, 295, 150, 30);
	    update.addActionListener(new ActionListener()
	    		{
	    	        public void actionPerformed(ActionEvent e)
	    	        {
	    	        	int i = table.getSelectedRow();
	    	        		    	        	
	    	        	if(i >=0)
	    	        	{
	    	        		try
		    	        	 {
		    	        		 double value = Double.parseDouble(bazarfield.getText()) - 0;
		    	        		 double value1 = Double.parseDouble(mealfield.getText()) - 0;
		    	        		 
		    	        		 model.setValueAt(namefield.getText(), i, 0);
		    	        		 model.setValueAt(bazarfield.getText(), i, 1);
		    	        		 model.setValueAt(mealfield.getText(), i, 2);        	 
			    	        	       	 
			    	        	 namefield.setText("");
			    	        	 bazarfield.setText("");
			    	        	 mealfield.setText("");
		    	        	 }catch(Exception e1)
		    	        	 {
		    	        		 namefield.setText("");
			    	        	 bazarfield.setText("");
			    	        	 mealfield.setText("");
		    	        		JOptionPane.showMessageDialog(null, "Invalid Input");	
		    	        	 }
	    	        	}
	    	        	else
	    	        		JOptionPane.showMessageDialog(null, "Invalid Selection");
	    	        }
	    		});
	    
	    update.setToolTipText("Update selected row from the table");
	    
/*.............................................................................................*/		    
	    
	    
/*...........................This Part controlls Save as pdf Button............................*/	 

	    save_as_pdf = new JButton ("Save as PDF");
	    save_as_pdf.setBounds(90, 350, 150, 30);
	    save_as_pdf.addActionListener(new ActionListener()
	    		{

					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser fs = new JFileChooser(new File("c://"));
						fs.setDialogTitle("Save as");
						fs.setFileFilter(new FileTypeFilter(".pdf","PDF File"));
						File file = fs.getSelectedFile();
						int result = fs.showSaveDialog(null);
						FileOutputStream output = null;
						Document doc = new Document();
						if(result == JFileChooser.APPROVE_OPTION)
						{
                         try{
								output = new FileOutputStream(fs.getSelectedFile().getPath());
								PdfWriter.getInstance(doc, output );
								doc.open();
								
							}catch(Exception ex)
							{
								ex.printStackTrace();
							}						
							
                         
                         doc.addTitle("Pranto's Meal Calculator");
 						Paragraph headline = new Paragraph ("Pranto's Meal Calculator", FontFactory.getFont(FontFactory.TIMES,14,Font.BOLD,BaseColor.BLACK));
 						headline.setAlignment(Element.ALIGN_CENTER);
 						try {
 							doc.add(headline);
 							doc.add(new Paragraph ("Month: " + monthnamefield.getText(), FontFactory.getFont(FontFactory.TIMES,14,Font.BOLD,BaseColor.RED)));
 							doc.add(new Paragraph("Report Created at " + new Date().toString(),FontFactory.getFont(FontFactory.TIMES,8,Font.PLAIN,BaseColor.BLACK)));
 							doc.add(new Paragraph("------------------------------------------------------------------------------------------------------------------\n\n"));
 							doc.add(new Paragraph("\t\t\t"));
 							
 						} catch (DocumentException e2) {
 							// TODO Auto-generated catch block
 							e2.printStackTrace();
 						}
 						
 						PdfPTable table = new PdfPTable(6);
 						PdfPCell cel = new PdfPCell(new Paragraph("Meal Calculator Result"));
 						cel.setColspan(6);
 						cel.setHorizontalAlignment(Element.ALIGN_CENTER);
 						cel.setVerticalAlignment(Element.ALIGN_CENTER);
 						table.setSpacingBefore((float) 0.5);
 						table.setSpacingAfter((float) 0.5);
 						cel.setBackgroundColor(BaseColor.LIGHT_GRAY);
 						
 						PdfPCell cel_meal_rate = new PdfPCell(new Paragraph("Meal Rate: " + mealratefield.getText()));
 						cel_meal_rate.setColspan(6);
 						
 						table.addCell(cel);
 						table.addCell("Serial No");
 						table.addCell("Name");
 						table.addCell("Total Bazar");
 						table.addCell("Total Meal");
 						table.addCell("Will Pay");
 						table.addCell("Will Get");
 						
 						for(int i = 0; i < model.getRowCount(); i++)
     	        		{
 							table.addCell(String.valueOf(i+1));
     	        			table.addCell((String.valueOf(model.getValueAt(i, 0))));
     	        			table.addCell((String.valueOf(model.getValueAt(i, 1))));
     	        			table.addCell((String.valueOf(model.getValueAt(i, 2))));
     	        			table.addCell((String.valueOf(model.getValueAt(i, 3))));
     	        			table.addCell((String.valueOf(model.getValueAt(i, 4))));
     	        			
     	        		} 
 						table.addCell(cel_meal_rate);
 						
 						
 						try {
 							
 							doc.add(table);
 							
 							
 						} catch (Exception e1) {
 							// TODO Auto-generated catch block
 							e1.printStackTrace();
 						}
 						
 						doc.close();
 						JOptionPane.showMessageDialog(null, "Report Saved");
 						
 					}
						}
						
	    	
	    		});
	    save_as_pdf.setToolTipText("Save as PDF File");
	    save_as_pdf.setForeground(Color.black.darker());
	    
	    
/*........................................................................*/		    
	    
	   

/*......This Part Controlls Calculate Button............*/
	    
	    
	    calculate = new JButton ("Calculate");
	    calculate.setBounds(300, 520, 100, 30);
	    
	    calculate.addActionListener(new ActionListener()
	    		{
	    	         public void actionPerformed(ActionEvent e)
	    	         {	    
	    	        	 Do();	    	        	 
	    	         }
	    		});
	    
	    calculate.setToolTipText("Click to calculate total cost of each person");
/*........................................................................*/
	    
/*.......................This Part Controlls Print Button..................*/
	    
	    Print = new JButton("Print");
	    Print.setBounds(900, 520, 100, 30);
	    Print.addActionListener(new ActionListener()
	    		{
	    		public void actionPerformed(ActionEvent e)
	    		{
	    			MessageFormat header = new MessageFormat("Meal Calculator Result:- " + monthnamefield.getText());
	    			MessageFormat footer = new MessageFormat("page{0,number,integer}");
	    			
	    			try
	    			{
	    				table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
	    				
	    			}catch (java.awt.print.PrinterException e1)
	    			{
	    				JOptionPane.showMessageDialog(null, e1.getMessage());
	    			}
	    		}
	    		});
	    
	    Print.setToolTipText("Print The Table Data ");	    
/*........................................................................*/
	    
/*.......................This Part Controlls Save Button..................*/
	    
	    Save = new JButton("Save");
	    Save.setBounds(540, 520, 80, 30);
	    Save.addActionListener(new ActionListener()
	    		{
	    	        public void actionPerformed(ActionEvent e)
	    	        {  	        	
	    	        	    JFileChooser fc = new JFileChooser("C://");
	    	        	    fc.setDialogTitle("Save as .txt");
	    	        	    fc.setFileFilter(new FileTypeFilter(".txt","Text File"));
	    	        	    int result = fc.showSaveDialog(null);
	    	        		FileWriter fw ;        		
	    	        		
	    	        	    if(result == JFileChooser.APPROVE_OPTION)
	    	        	    {
	    	        	    	File file = fc.getSelectedFile();
	    	        	    	try {
									 fw = new FileWriter(file);
									 
									 fw.write(mealratefield.getText() + "\n");
									 fw.write(monthnamefield.getText() + "\n");
									  for(int i = 0; i < model.getRowCount(); i++)
				    	        		{
										    
				    	        			fw.write(String.valueOf(model.getValueAt(i, 0)) + "\t");
				    	        			fw.write(String.valueOf(model.getValueAt(i, 1) + "\t"));
				    	        			fw.write(String.valueOf(model.getValueAt(i, 2) + "\t"));
				    	        			fw.write(String.valueOf(model.getValueAt(i, 3) + "\t"));
				    	        			fw.write(String.valueOf(model.getValueAt(i, 4) + "\t"));
				    	        			fw.write("\n");
				    	        		}     	        		
	    	        	
								 fw.flush();
								 fw.close();
			    	        	 //bw.close();
			    	        	 
			    	        	 JOptionPane.showMessageDialog(null, "Data Saved");
								
								} catch (IOException e1) {
								
								JOptionPane.showMessageDialog(null, "Import Process Failed!!");
							     }
	    	        	    }
	    	        		
	    	        	}
	    		});
	    
	    Save.setToolTipText("Save All data to a text file");
 /*........................................................................*/
	    

	    
/*.......................This Part Controlls Load Button..................*/
	    Load = new JButton("Load");
	    Load.setBounds(430, 520, 80, 30);
	    Load.addActionListener(new ActionListener()
	    		{

					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser fs = new JFileChooser(new File ("c:\\"));
	    	        	fs.setDialogTitle("Load");
	    	           	fs.setFileFilter(new FileTypeFilter(".txt","Text File"));
	    	           
	    	        	int result = fs.showOpenDialog(null);
	    	        	
	    	        	if(result == JFileChooser.APPROVE_OPTION)
	    	        	{
	    	        		try{
	    	        			File fi = fs.getSelectedFile();
	    	        			BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
	    	        			String line;
	    	        			String [] data = null;
	    	        			String meal_rate = null;
	    	        			String month_name = null;
	    	
	    	        			meal_rate = br.readLine();
	    	        			
	    	        			Double.parseDouble(meal_rate);
	    	        			mealratefield.setText(meal_rate);    	        			
	    	        			
	    	        			month_name = br.readLine();
	    	        			
	    	        			monthnamefield.setText(month_name);
	    	        			
	    	        			
	    	        			while( (line = br.readLine()) != null)
	    	        			{
	    	           				data = line.split("\\s");
	    	        				model.addRow(data);
	    	        			}
	    	        			
	    	        			JOptionPane.showMessageDialog(null, "Data imported");
	    	        			
	    	        		}catch(Exception e1)
	    	        		{
	    	        			JOptionPane.showMessageDialog(null, "Error in opening file!");
	    	        		}
	    	        	}
						
					}
	    	
	    		});
	    
	    Load.setToolTipText("Load a archived text file");
 /*........................................................................*/
	    
		label = new JLabel("Meal Rate: ");
		label.setBounds(700, 520, 80, 30);
		label.setForeground(Color.BLACK.darker());
		
		mealratefield =new JTextField();
		mealratefield.setEditable(false);
		mealratefield.setBounds(790, 520, 100, 30);
		
		welcome = new JLabel("Welcome To Pranto's Meal Calculator");
		welcome.setBounds(490, 15, 500, 50);
		welcome.setForeground(Color.BLACK.darker());
		welcome.setFont(new Font("Arial",1,16));
		
		add(monthname);
		add(monthnamefield);
		add(namelabel);
		add(namefield);
		add(bazarlabel);
		add(bazarfield);
		add(meallabel);
		add(mealfield);
		add(add);
		add(delete);
		add(update);
		add(save_as_pdf);
		add(calculate);
		add(Save);
		add(Load);
		add(Print);
		
		add(welcome);
		add(scp);
		add(label);
		add(mealratefield); 
		
		
	}
	
	
	public static void Do()
	{	
		int total_rows = table.getRowCount();
	    double total_bazar = 0; 
		double total_meal = 0;
		
			for(int i = 0; i < total_rows; i++)
		{
			total_bazar = total_bazar + Double.parseDouble((String) table.getValueAt(i, 1));
			total_meal = total_meal + Double.parseDouble((String) table.getValueAt(i, 2));

						
		}
			
		meal_rate = getMealRate(total_bazar, total_meal); // invokes getMealRate to get meal rate
		setBill(meal_rate, total_rows);
		
		if(meal_rate >= 0)
		{
			 mealratefield.setText(String.format("%.2f", meal_rate));
		}
	   
		else
		{
			JOptionPane.showMessageDialog(null, "No Member Found!");
			mealratefield.setText("");			
		
		}
			
	}
	
	
	public static double getMealRate(double bazar, double meal) // Calculates the meal rate
	{
		double meal_rate = bazar / meal;
		
		return meal_rate;
	}
	
	
	
    public static void setBill(double meal_rate, int total_members)
    {
    	
    	for(int i = 0; i < total_members; i++)
    	{
    		double will_pay = 0, will_get = 0, total_meal_of_that_person = 0, bazar_of_that_person = 0;
    		total_meal_of_that_person =  Double.parseDouble((String) model.getValueAt(i, 2));
    		bazar_of_that_person = Double.parseDouble((String) model.getValueAt(i, 1));
    		double bill = meal_rate * total_meal_of_that_person;
    		
    		if(bill > bazar_of_that_person )
    				{
    			        will_pay = bill - bazar_of_that_person ;
    			        will_get = 0;
    			        model.setValueAt(String.format("%.2f", will_pay), i, 3);
    			        model.setValueAt(String.format("%.2f", will_get), i, 4);
    				}
    		else if(bill < bazar_of_that_person )
			{
		        will_get = bazar_of_that_person - bill;
		        will_pay = 0;
		        model.setValueAt(String.format("%.2f", will_pay), i, 3);
		        model.setValueAt(String.format("%.2f", will_get), i, 4);
			}
    	}
    }
	

/*.....This is my Table class which inherits JTable. It is mainly created to set the table rows uneditable..*/	
private class myTable extends JTable 
{
    public boolean isCellEditable(int row, int column)
		 {
			 return false;
		 } 
}
/*...............................................................................................................*/

public void loadImage(URL str)
{
	try {
		icon = new ImageIcon(str);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void paintComponent(Graphics g)
{
    super.paintComponent(g);
    g.drawImage(icon.getImage(), 0, 0, 1200, 600,this);
}

}