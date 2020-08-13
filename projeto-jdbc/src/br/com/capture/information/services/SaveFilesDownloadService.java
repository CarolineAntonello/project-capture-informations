package br.com.capture.information.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SaveFilesDownloadService implements Runnable {
	
	String localFiles;
	String urlString;
	
	public SaveFilesDownloadService(String _localFiles, String _urlString) {
		this.localFiles = _localFiles;
		this.urlString = _urlString;
	}

	@Override
	public void run() {
		File newDirectory = new File(localFiles);
		if(!newDirectory.isDirectory())
		{
			newDirectory.mkdirs();
		}
		
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            try 
            {
				fout = new FileOutputStream(localFiles);
			} catch (FileNotFoundException e) 
            {
				e.printStackTrace();
			}

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) 
            {
                fout.write(data, 0, count);
            }
        } catch (MalformedURLException e1) 
        {
			e1.printStackTrace();
		} catch (IOException e1) 
        {
			e1.printStackTrace();
		} finally 
        {
            if (in != null) 
            {
                try 
                {
					in.close();
				} catch (IOException e) 
                {
					e.printStackTrace();
				}
            }
            if (fout != null) 
            {
                try 
                {
					fout.close();
				} catch (IOException e) 
                {
					e.printStackTrace();
				}
            }
        }
	}
}
