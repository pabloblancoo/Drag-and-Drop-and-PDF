package xyz.pabloblanco.business.write.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import xyz.pabloblanco.business.Writer;

public class PDFWriter implements Writer{

	@Override
	public void writeFiles(List<File> files) throws IOException {
		for (File file : files) {
			PDDocument document = PDDocument.load(file);
			
			Splitter splitter = new Splitter();
			
			splitter.setStartPage(1);
			
			List<PDDocument> pages = splitter.split(document);
			String path = file.getAbsolutePath().substring(0, (int) (file.getAbsolutePath().length() - 4));
			savePages(pages, path);
			document.close();
		}
		
	}

	/**
	 * Save the pdf pages on the specified path
	 * @param pdf pages
	 * @throws IOException 
	 */
	private void savePages(List<PDDocument> pages,String path) throws IOException{
		File folder = new File(path);
		folder.mkdir();
		for (int i = 0; i < pages.size(); i++) {
			PDDocument doc = pages.get(i);
			String pagePath = folder.getAbsolutePath() +System.getProperty("file.separator") + i + ".pdf";
			doc.save(pagePath);
			doc.close();
			
		}
	}
}
