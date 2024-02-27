package com.elecex.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elecex.model.facturas.FacturaDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Utils {
	
	String regexIdFactura = "\\*([^\\*]+)\\*"; // Para extraer el ID de la factura entre asteriscos
	String regexImporteTotal = "Total Albarán\\s+(\\d{1,3},\\d{2})";
    String regexPlazos = "\\b\\d{2}/\\d{2}/\\d{2}\\b";
    String regexProveedor = "www\\.(\\S+)\\.com"; // Para extraer el nombre del proveedor
    String regexFecha = "Fecha (\\d{2}/\\d{2}/\\d{4})"; // Para extraer la fecha de la factura


	public FacturaDto anotherWay(MultipartFile pdf) {
		String texto = null;
        try {
            File file = File.createTempFile("factura", ".pdf");
            pdf.transferTo(file);
            
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(1);
            texto = pdfStripper.getText(document);

            document.close();

            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extraerFactura(texto);
	}
	
	public FacturaDto extraerFactura (String textoFactura) {
		
		 // Extraer el ID de la factura
        String idFactura = extractField(textoFactura, regexIdFactura);

        // Extraer el importe total
        String importeTotal = extractField(textoFactura, regexImporteTotal);

        // Extraer las fechas de los plazos
        List<String> plazos = extractFields(textoFactura, regexPlazos);

        // Extraer el nombre del proveedor
        String proveedor = extractField(textoFactura, regexProveedor);

        // Extraer la fecha de la factura
        String fecha = extractField(textoFactura, regexFecha);
		
        return null;
	}
	
	// Método para extraer un campo usando una expresión regular
    private String extractField(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
    
    // Método para extraer múltiples campos usando una expresión regular
    private static List<String> extractFields(String texto, String regex) {
        List<String> resultados = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            resultados.add(matcher.group(1));
        }
        return resultados;
    }
}
