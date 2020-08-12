package br.com.capture.information.Util;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
  
import org.apache.pdfbox.pdfparser.PDFParser;  
import org.apache.pdfbox.pdmodel.PDDocument;  
import org.apache.pdfbox.util.PDFTextStripper;  
  
public class ConverterPdfInTxt {  
  
    public String CapturePdf(String pdfFile) {  
        try {  
        	
            File filePDF = new File(pdfFile);  
            FileInputStream fileInputStream = new FileInputStream(filePDF); 
            PDFTextStripper stripper = new PDFTextStripper();  
  
            PDDocument pdfDocument = null;  
            try {  
                PDFParser parser = new PDFParser(fileInputStream);  
                parser.parse();  
                pdfDocument = parser.getPDDocument();  
                pdfFile = stripper.getText(pdfDocument); 
  
                info("Arquivo PDF: ");  
                info("");  
                info(pdfFile); 
                
                return pdfFile;
            } finally {  
                if (pdfDocument != null) 
                {  
                    try 
                    {  
                        pdfDocument.close();  
                        return pdfFile;
                    } catch (IOException e) {}  
                }  
            }  
        } catch (Exception e) {  
            error(e.toString());  
        }
        
        return pdfFile;
    }  
  
    /** 
     * Log Info. 
     * @param log 
     */  
    private static void info(String log) {  
        System.out.println("INFO: " + log);  
    }  
  
    /** 
     * Log Error. 
     * @param log 
     */  
    private static void error(String log) {  
        System.out.println("ERROR: " + log);  
    }  
  
}  