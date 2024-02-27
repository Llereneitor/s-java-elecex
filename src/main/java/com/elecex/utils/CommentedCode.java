package com.elecex.utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CommentedCode {
	
	//Fumada maxima, no se si es viable hacerlo de esta forma, ya lo hablaremos

	/*public String recogerFacturaDatos(MultipartFile pdf) {
		String result = null;

		String basePath = System.getProperty("user.dir");
		String relativePath = "src/main/resources/tessdata";
		String absolutePath = basePath + "/" + relativePath;

		try (PDDocument document = PDDocument.load(pdf.getInputStream())) {
			log.info("Se procede a tratar el PDF para sacar el contenido");
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath(absolutePath);
			tesseract.setLanguage("spa");

			// Convert each page of the PDF into an image and apply OCR
			for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); pageNumber++) {
				BufferedImage image = pdfRenderer.renderImageWithDPI(pageNumber, 300);
				File outputImage = new File(absolutePath, "pagina-" + (pageNumber + 1) + ".png");
				ImageIO.write(image, "png", outputImage);

				// Apply OCR to the image
				result = tesseract.doOCR(outputImage);
				log.info("Texto extraído del PDF: {}", result);
			}
		} catch (Exception e) {
			log.error("Error al procesar el PDF", e);
			throw new IllegalArgumentException("No se puede procesar el PDF correctamente.");
		}
		return result;
	}
	
	@Override
	public FacturaDto leerFacturaPorImagen(MultipartFile pdf) {
		
		//Fumada maxima, no se si es viable hacerlo de esta forma, ya lo hablaremos
		String resultadoFactura2 = utils.anotherWay(pdf);
		String resultadoFactura = utils.recogerFacturaDatos(pdf);
		

		FacturaDto faturaDto = new FacturaDto();

		faturaDto.setFecha(facturaFechaAquatubo(resultadoFactura));
		faturaDto.setIdFactura(numFacturaAquatubo(resultadoFactura));
		faturaDto.setImporte(totalFacturaAquatubo(resultadoFactura));
		
		faturaDto.setNumPlazosAPagar(plazos(resultadoFactura));

		return faturaDto;
	}

	private String facturaFechaAquatubo(String textoFactura) {

		// Utilizamos una expresión regular para buscar la fecha
		Pattern pattern = Pattern.compile("\\b(\\d{2}/\\d{2}/\\d{4})\\b");
		Matcher matcher = pattern.matcher(textoFactura);

		String fecha = null;
		if (matcher.find()) {
			fecha = matcher.group(1);
			log.info("factura con valor {} ", fecha);
		} else {
			log.info("Llama a sergio fechaFactura");
		}

		return fecha;
	}

	private String numFacturaAquatubo(String textoFactura) {

		Pattern pattern = Pattern.compile("\\b([0-9A-Z]{10})\\b");
		Matcher matcher = pattern.matcher(textoFactura);

		String cadena = null;
		// Si se encuentra la cadena, la imprimimos
		if (matcher.find()) {
			cadena = matcher.group(1);
			System.out.println("Cadena encontrada: " + cadena);
		} else {
			log.info("Llama a sergio numFactura");
		}
		return cadena;
	}

	private String totalFacturaAquatubo(String textoFactura) {

		Pattern patron = Pattern.compile("Total Albarán \\S+ (\\d+(?:[.,]\\d+)?)");

		// Crear un objeto Matcher
		Matcher matcher = patron.matcher(textoFactura);

		String resultado = null;
		// Buscar todas las coincidencias
		while (matcher.find()) {
			// Imprimir el resultado
			log.info("Cadena encontrada {} ", matcher.group());
			resultado = matcher.group(1); // Extraer la cantidad
		}

		if (null != resultado) {
			return resultado;
		} else {
			log.info("Llama a sergio totalFactura");
			return null;
		}
	}

	private String plazos(String texto) {

		Pattern patron = Pattern.compile("RECIBO\\s*(\\d{2}/\\d{2}/\\d{2})\\s*(\\d{2}/\\d{2}/\\d{2})");
		Matcher matcher = patron.matcher(texto);

		String resultado1 = null;
		String resultado2 = null;

		while (matcher.find()) {
			// Imprimir el resultado
			resultado1 = matcher.group(1);
			resultado2 = matcher.group(2);
		}
		
		return resultado1;
	}*/
}
