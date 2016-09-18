import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.exceptions.COSVisitorException;

import javax.swing.JFileChooser;

public final class DrawingPanel extends JPanel{
	
	String file = "";
	JButton inputButton = new JButton("Input a file");
	JButton generate = new JButton("Generate");
	
	// make check boxes
	JCheckBox checkBox11 = new JCheckBox("Attendance");
	JCheckBox checkBox12 = new JCheckBox("Requirements Not Met");
	JCheckBox checkBox13 = new JCheckBox("Not Completed");
	JCheckBox checkBox14 = new JCheckBox("N/A");
	JCheckBox checkBox15 = new JCheckBox("Other:(Specify)");
	JTextArea os1 = new JTextArea(2, 1);
	JCheckBox checkBox21 = new JCheckBox("Unexcused Absences");
	JCheckBox checkBox22 = new JCheckBox("Excused Absences");
	JCheckBox checkBox23 = new JCheckBox("Behaviour");
	JCheckBox checkBox24 = new JCheckBox("Work Ethic");
	JCheckBox checkBox25 = new JCheckBox("N/A");
	JCheckBox checkBox26 = new JCheckBox("Other:(Specify)");
	JTextArea os2 = new JTextArea(2, 1);
	JCheckBox checkBox31 = new JCheckBox("Attend Tutorials");
	JCheckBox checkBox32 = new JCheckBox("Success Room");
	JCheckBox checkBox33 = new JCheckBox("Meet With Teacher");
	JCheckBox checkBox34 = new JCheckBox("Complete Missed Assessments");
	JCheckBox checkBox35 = new JCheckBox("Complete I-Package");
	JCheckBox checkBox36 = new JCheckBox("Other:(Specify)");
	JTextArea os3 = new JTextArea(2, 1);
	JLabel reasons1 = new JLabel("Reasons for missing assessments: ");
	JLabel reasons2 = new JLabel("Reasons for at risk: ");
	JLabel plan = new JLabel("Expected action plan: ");
	JLabel tn = new JLabel("Teacher's name: ");
	JTextArea teachername = new JTextArea();

	/**
	 * this is the method that used to draw everything
	 */
	public DrawingPanel()
	{
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		os1.setLineWrap(true); 
		os1.setWrapStyleWord(true); 
		os2.setLineWrap(true); 
		os2.setWrapStyleWord(true); 
		os3.setLineWrap(true); 
		os3.setWrapStyleWord(true); 
		
		// input files
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter loadfilter = new FileNameExtensionFilter("CSV Files", "csv");
				JFileChooser load = new JFileChooser();
				load.changeToParentDirectory();
				load.setAcceptAllFileFilterUsed(false);
				load.addChoosableFileFilter(loadfilter);
				int returnval=load.showOpenDialog(null);
				if(returnval == JFileChooser.APPROVE_OPTION) {
					file = load.getSelectedFile().getPath();
				}
			}
		});
		
		checkBox15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			os1.setVisible(true);
			}
		});
		checkBox26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			os2.setVisible(true);
			}
		});
		checkBox36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			os3.setVisible(true);
			}
		});

		// generate
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				/**
				FileNameExtensionFilter savefilter = new FileNameExtensionFilter("PDF Files", "pdf");
				JFileChooser save = new JFileChooser();
				save.showSaveDialog(null);
				save.changeToParentDirectory();
				save.setAcceptAllFileFilterUsed(false);
				save.addChoosableFileFilter(savefilter);
				 */
				String otherSpecify1 = os1.getText();
				String otherSpecify2 = os2.getText();
				String otherSpecify3 = os3.getText();
				boolean cs11 = checkBox11.isSelected();
				boolean cs12 = checkBox12.isSelected();
				boolean cs13 = checkBox13.isSelected();
				boolean cs14 = checkBox14.isSelected();
				boolean cs15 = checkBox15.isSelected();
				boolean cs21 = checkBox21.isSelected();
				boolean cs22 = checkBox22.isSelected();
				boolean cs23 = checkBox23.isSelected();
				boolean cs24 = checkBox24.isSelected();
				boolean cs25 = checkBox25.isSelected();
				boolean cs26 = checkBox26.isSelected();
				boolean cs31 = checkBox31.isSelected();
				boolean cs32 = checkBox32.isSelected();
				boolean cs33 = checkBox33.isSelected();
				boolean cs34 = checkBox34.isSelected();
				boolean cs35 = checkBox35.isSelected();
				boolean cs36 = checkBox36.isSelected();
				
				// read the csv file
				try {
						readFile.read(file);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				

				
				
				
				for (int i=0; i< readFile.students.size(); i++)
				{
					StudentInfo s = readFile.students.get(i);
					
					// test 
					//System.out.println(s.getStudentName());
					//System.out.println(s.getGrade());
					
					if (s.Fail() == true)
					{
						Populater.StudentInfo(s.getStudentName(), ""+s.getGrade());
						Populater.teacherName(""+teachername.getText());
						
						String failAssignments = "";
						for (int n=0; n< readFile.assignments.size(); n++)
						{
							AssignmentInfo a = readFile.assignments.get(n);
							if ((double)(s.getAssignmentGrade().get(n))/a.getTotalMark() < 0.5)
							{
								failAssignments += ""+ a.getassignmentName() + "   " + (double)(s.getAssignmentGrade().get(n)) 
										+"/"+(int)a.getTotalMark() +"\n";
							}
						}
							try {
								Populater.open();
								Populater.setField("LIST OF MISSING LEARNING OUTCOMES", failAssignments);
								// Check Boxes.....
								if(cs11 == true)
								{
									Populater.setField("Check Box2", " ");
								}
								if(cs12 == true)
								{
									Populater.setField("Check Box3", " ");
								}
								if(cs13 == true)
								{
									Populater.setField("Check Box4", " ");
								}
								if(cs14 == true)
								{
									Populater.setField("Check Box5", " ");
								}
								if(cs15 == true)
								{
									Populater.setField("Check Box6", " ");
									Populater.setField("Other1", otherSpecify1);
								}
								
								if(cs21 == true)
								{
									Populater.setField("Check Box7", " ");
								}
								if(cs22 == true)
								{
									Populater.setField("Check Box8", " ");
								}
								if(cs23 == true)
								{
									Populater.setField("Check Box9", " ");
								}
								if(cs24 == true)
								{
									Populater.setField("Check Box10", " ");
								}
								if(cs25 == true)
								{
									Populater.setField("Check Box11", " ");
								}
								if(cs26 == true)
								{
									Populater.setField("Check Box12", " ");
									Populater.setField("Other2", otherSpecify2);
								}
								
								if(cs31 == true)
								{
									Populater.setField("Check Box13", " ");
								}
								if(cs32 == true)
								{
									Populater.setField("Check Box14", " ");
								}
								if(cs33 == true)
								{
									Populater.setField("Check Box15", " ");
								}
								if(cs34 == true)
								{
									Populater.setField("Check Box16", " ");
								}
								if(cs35 == true)
								{
									Populater.setField("Check Box17", " ");
								}
								if(cs36 == true)
								{
									Populater.setField("Check Box18", " ");
									Populater.setField("Other3", otherSpecify3);
								}
								Populater.fill();
								Populater.close();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						
					}
				}
				
				
				
				
				
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		add(inputButton,c);
		c.gridx = 0;
		c.gridy = 2;
		add(tn,c);
		c.gridx = 0;
		c.gridy = 3;
		add(reasons1,c);
		c.gridx = 0;
		c.gridy = 4;
		add(checkBox11,c);
		c.gridx = 0;
		c.gridy = 5;
		add(checkBox12,c);
		c.gridx = 0;
		c.gridy = 6;
		add(checkBox13,c);
		c.gridx = 0;
		c.gridy = 7;
		add(checkBox14,c);
		c.gridx = 0;
		c.gridy = 8;
		add(checkBox15,c);
		c.gridx = 0;
		c.gridy = 9;
		os1.setVisible(false);
		add(os1,c);
		c.gridx = 0;
		c.gridy = 12;
		add(generate,c);
		c.gridx = 1;
		c.gridy = 2;
		add(teachername,c);
		c.gridx = 1;
		c.gridy = 3;
		add(reasons2,c);
		c.gridx = 1;
		c.gridy = 4;
		add(checkBox21,c);
		c.gridx = 1;
		c.gridy = 5;
		add(checkBox22,c);
		c.gridx = 1;
		c.gridy = 6;
		add(checkBox23,c);
		c.gridx = 1;
		c.gridy = 7;
		add(checkBox24,c);
		c.gridx = 1;
		c.gridy = 8;
		add(checkBox25,c);
		c.gridx = 1;
		c.gridy = 9;
		add(checkBox26,c);
		c.gridx = 1;
		c.gridy = 10;
		os2.setVisible(false);
		add(os2,c);
		c.gridx = 2;
		c.gridy = 3;
		add(plan,c);
		c.gridx = 2;
		c.gridy = 4;
		add(checkBox31,c);
		c.gridx = 2;
		c.gridy = 5;
		add(checkBox32,c);
		c.gridx = 2;
		c.gridy = 6;
		add(checkBox33,c);
		c.gridx = 2;
		c.gridy = 7;
		add(checkBox34,c);
		c.gridx = 2;
		c.gridy = 8;
		add(checkBox35,c);
		c.gridx = 2;
		c.gridy = 9;
		add(checkBox36,c);
		c.gridx = 2;
		c.gridy = 10;
		os3.setVisible(false);
		add(os3,c);
	}
	
	
	
  }
