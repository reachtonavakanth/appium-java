package com.qa.reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class CheckReport {
	public void createReport() {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Metrics.pdf"));
			document.open();
			document.add(new Paragraph("============= CPU / Memory Utilization Report ==========="));
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void modifyReport(String fileName) {

		try {
			// Read file using PdfReader
			PdfReader pdfReader = new PdfReader(fileName + ".pdf");

			// Modify file using PdfReader
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(fileName + ".pdf"));

			Image image = Image.getInstance("temp.jpg");
			image.scaleAbsolute(100, 50);
			image.setAbsolutePosition(100f, 700f);

			for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
				PdfContentByte content = pdfStamper.getUnderContent(i);
				content.addImage(image);
			}

			pdfStamper.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void addImageToPDF() {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddImageExample.pdf"));
			document.open();
			document.add(new Paragraph("Image Example"));

			// Add Image
			Image image1 = Image.getInstance("temp.jpg");

			// Fixed Positioning
			image1.setAbsolutePosition(100f, 550f);

			// Scale to new height and new width of image
			image1.scaleAbsolute(200, 200);

			// Add to document
			document.add(image1);

			String imageUrl = "http://www.eclipse.org/xtend/images/java8_logo.png";
			Image image2 = Image.getInstance(new URL(imageUrl));
			document.add(image2);

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTable() {
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddTableExample.pdf"));
			document.open();

			PdfPTable table = new PdfPTable(3); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 1f, 1f, 1f };
			table.setWidths(columnWidths);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
			cell1.setBorderColor(BaseColor.BLUE);
			cell1.setPaddingLeft(10);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
			cell2.setBorderColor(BaseColor.GREEN);
			cell2.setPaddingLeft(10);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
			cell3.setBorderColor(BaseColor.RED);
			cell3.setPaddingLeft(10);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// To avoid having the cell border and the content overlap, if you are having
			// thick cell borders
			// cell1.setUserBorderPadding(true);
			// cell2.setUserBorderPadding(true);
			// cell3.setUserBorderPadding(true);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);

			document.add(table);

			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
