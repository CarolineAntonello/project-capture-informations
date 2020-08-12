package br.com.capture.information.services;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicesFactory {
	
	public void FillFormByPdfText(String fileText) {
		
		String[] linhas = fileText.toString().split("\\r?\\n");
		System.out.println("INFO: " + linhas[0]); 
		
	}
	
	public void SaveFilesDonwload(final String filename, final String urlString) throws MalformedURLException, IOException 
	{
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }

}
