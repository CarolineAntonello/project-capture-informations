package br.com.capture.information.application;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.capture.information.Util.ConverterPdfInTxt;
import br.com.capture.information.services.ServicesFactory;

public class Main {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		String LocalFiles = "C:\\Users\\carol\\eclipse-workspace\\projeto-jdbc-capture\\projeto-jdbc\\pdf\\in";
		String UrlFiles = "https://www.santamaria.rs.gov.br/secao/editais/pdf/teste.pdf";
		
		ServicesFactory service = new ServicesFactory();
		//service.SaveFilesDonwload(LocalFiles, UrlFiles);
		
		ConverterPdfInTxt pdf = new ConverterPdfInTxt();
		String receivePdf = pdf.CapturePdf(LocalFiles + "\\convoca-candidatos-classificados-no-processo-seletivo-do-programa-bolsaestagio.pdf");
	
		service.FillFormByPdfText(receivePdf);
	}
}

