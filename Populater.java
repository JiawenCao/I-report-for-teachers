import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.common.COSArrayList;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDChoiceButton;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckbox;

/** Populater
 * this is the class that used to 
 * 
 * read and change the I-Report PDF
 */

public class Populater {

	private static PDDocument pdfDocument;
	
	static String Name;
	static String Course;
	static String Teacher;
	static String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	static String Grade;
	
	// set the original file and output file
		static String originalPdf = "I-Report.pdf";				
		static String targetPdf;
		
	/**
	 *  get value of each variable
	 * @param course name
	 */
	public static void Course(String course)
	{
		Course = course;
	}
	/**
	 * get student's name and grade
	 * 
	 * @param name of the student
	 * @param grade of the student
	 */
	public static void StudentInfo(String name, String grade)
	{
		Name = name;
		Grade = grade;
	}
	/**
	 * get the teacher name
	 * 
	 * @param teacherName
	 */
	public static void teacherName(String teacherName)
	{
		Teacher = teacherName;
	}
	
	/**
	 * this method is used to open the file
	 * 
	 * @throws IOException
	 */
	public static void open() throws IOException
	{
		//output file	
		targetPdf = ""+Name+".PDF";
		File f = new File(originalPdf);
		pdfDocument = PDDocument.load(f);
		pdfDocument.getNumberOfPages();
		//printFields();  	//Uncomment to see the fields in this document in console
	}
	
	/**
	 * this method is used to save and close the file
	 * 
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public static void close() throws IOException, COSVisitorException
	{
		// save the change
		pdfDocument.save(targetPdf);
		pdfDocument.close();
	}
	
	
	public static void fill() 
	{
		try 
		{
			makeCopy(originalPdf, targetPdf);
		} catch (Exception e) {
			((Throwable) e).printStackTrace();
		}
		
		// to show that the work is done
		//System.out.println("Complete");	// uncomment for test
	}
	
	/**
	 * this method are responsible for filling the PDF file
	 */
	private static void makeCopy(String originalPdf, String targetPdf) throws IOException,COSVisitorException 
	{
		// fill in the form
		setField("STUDENT NAME", Name);
		setField("DATE OF I-REPORT", date);
		setField("COURSE", Course);
		setField("TEACHER", Teacher);
		setField("Text1", Grade);
		//setField("LIST OF MISSING LEARNING OUTCOMES", "LIST OF MISSING LEARNING OUTCOMES");
		
	}

	
	// the method that used to change the value of each block
    public static void setField(String name, String value ) throws IOException {
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        acroForm.setXFA(null);
        //System.out.println(acroForm.getFields());
        PDField field = acroForm.getField( name );
        if( field != null ) {
        	if (name.indexOf("Check Box")==0)
        	{
        		((PDCheckbox) field).check(); 
        	}
        	else{
        		field.setValue(value);
        	}
        }
        else {
            System.err.println( "No field found with name:" + name ); // print error if the block with the name cannot be find 
        }
    }
    

    @SuppressWarnings("rawtypes")
	public static void printFields() throws IOException {
        PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List fields = (List) acroForm.getFields();
        Iterator fieldsIter = ((java.util.List<PDField>) fields).iterator();

        System.out.println(new Integer(fields.size()).toString() + " top-level fields were found on the form");

        while( fieldsIter.hasNext()) {
            PDField field = (PDField)fieldsIter.next();
               processField(field, "|--", field.getPartialName());
        }
    }
    
    @SuppressWarnings("rawtypes")
	private static void processField(PDField field, String sLevel, String sParent) throws IOException
    {
        List kids = field.getKids();
        if(kids != null) {
            Iterator kidsIter = ((java.util.List<PDField>) kids).iterator();
            if(!sParent.equals(field.getPartialName())) {
               sParent = sParent + "." + field.getPartialName();
            }
            
            System.out.println(sLevel + sParent);
            
            while(kidsIter.hasNext()) {
               Object pdfObj = kidsIter.next();
               if(pdfObj instanceof PDField) {
                   PDField kid = (PDField)pdfObj;
                   processField(kid, "|  " + sLevel, sParent);
               }
            }
         }
         else {
             String outputString = sLevel + sParent + "." + field.getPartialName() + ",  type=" + field.getClass().getName();
             System.out.println(outputString);
         }
    }
   
}