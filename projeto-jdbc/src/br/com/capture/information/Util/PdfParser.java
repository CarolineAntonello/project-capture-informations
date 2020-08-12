package br.com.capture.information.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfParser{
	
	private String enderecoRecurso;
	
	public void setEnderecoRecurso(String enderecoRecurso)
	{
		this.enderecoRecurso = enderecoRecurso;
	}
	
	public String getConteudo()	
	{
		File f = new File(this.enderecoRecurso);
		FileInputStream is = null;
		
		try
		{
			is = new FileInputStream(f);
		}
		catch(IOException e)
		{
			System.out.println("ERRO: " + e.getMessage());
			return null;
		}

		PDDocument pdfDocument = null;
		
		try
		{
			PDFParser parser = new PDFParser(is);
			parser.parse();
			pdfDocument = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			return stripper.getText(pdfDocument);
		}
		catch (IOException e)
		{
			return "ERRO: N�o � poss�vel abrir a stream" + e;
		}
		catch (Throwable e)
		{
			return "ERRO: Um erro ocorreu enquanto tentava obter o conte�do do PDF" + e;
		}
		finally
		{
			if (pdfDocument != null)
			{
				try
				{
					pdfDocument.close();
				}
				catch (IOException e)
				{
					return "ERRO: N�o foi poss�vel fechar o PDF." + e;
				}
			}
		}
	}
}