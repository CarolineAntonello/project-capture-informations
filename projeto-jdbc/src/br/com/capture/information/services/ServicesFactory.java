package br.com.capture.information.services;

import java.io.File;

import br.com.capture.information.Util.ConverterPdfInTxt;
import br.com.capture.informations.vo.AddressVO;
import br.com.capture.informations.vo.CandidateVO;
import br.com.capture.informations.vo.FormVO;

public class ServicesFactory implements Runnable {
	
	String directory;
	
	public ServicesFactory(String _directory) {
		this.directory = _directory;
	}
	
	@Override
	public void run() {
		ConverterPdfInTxt convertPdf = new ConverterPdfInTxt();
		
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();

		if(listOfFiles != null) {
			for (File file : listOfFiles) 
			{
			    if (file.isFile()) 
			    {
			    	String receivePdf = convertPdf.CapturePdf(directory + file.getName());
			    	FillFormByPdfText(receivePdf);
			    }
			}
		}
	}
	
	public void FillFormByPdfText(String fileText) 
	{
		FormVO form = new FormVO();
		form.Address = new AddressVO();
		form.Candidate = new CandidateVO();
		
		String[] linhas = fileText.toString().split("\\r?\\n");
		
		form.Title = linhas[5];
		form.Address.City = linhas[1];
		form.Address.State = linhas[0];
		form.Email = FindStringInArray(linhas[9], "@");
		form.Candidate.Documents = linhas[12];
		form.Notice = FindStringInArray(linhas[13], "–");
		form.Candidate.Name = FindStringInArray(linhas[15], " ");
		form.Candidate.Classification = FindStringInArray(linhas[15], "º");
		
		info(fileText);
		info("");
	}
	
	private String FindStringInArray(String string, String caracter) 
	{
		String[] listString = string.split(" ");
		String out = null;
		for (String stringReceived : listString) {
            if(stringReceived.contains(caracter)) {
                out = stringReceived;
            }
        }
		return out;
	}
	
    private static void info(String log) {  
        System.out.println("INFO: " + log);  
    }
}
