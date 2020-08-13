package br.com.capture.information.application;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.capture.information.services.SaveFilesDownloadService;
import br.com.capture.information.services.ServicesFactory;

public class Main {
	
	public static void main(String[] args) throws MalformedURLException, IOException  
	{	
		String LocalFiles = "C:\\DownloadPDFs\\";
		String UrlFiles = "https://www.santamaria.rs.gov.br/secao/editais/";
		
		ServicesFactory service = new ServicesFactory(LocalFiles);
		SaveFilesDownloadService saveService = new SaveFilesDownloadService(LocalFiles, UrlFiles);
		Thread threadSaveFiles = new Thread(saveService);
		Thread threadLoadFiles = new Thread(service);
		threadSaveFiles.start();
		threadLoadFiles.start();
	}
}

