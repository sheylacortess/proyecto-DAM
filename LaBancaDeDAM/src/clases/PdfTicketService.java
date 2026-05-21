package clases;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class PdfTicketService {

    public static void generarPdfMovimientos(List<Movimiento> movimientos) {
        String carpeta = "tickets";
        String nombreArchivo = "movimientos.pdf";
        String ruta = carpeta + File.separator + nombreArchivo;

        try {
            File directorio = new File(carpeta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            documento.open();
            documento.add(new Paragraph("HISTORIAL DE MOVIMIENTOS"));
            documento.add(new Paragraph(" "));

            if (movimientos.isEmpty()) {
                documento.add(new Paragraph("No hay movimientos registrados."));
            } else {
                for (Movimiento mov : movimientos) {
                    documento.add(new Paragraph(mov.toString()));
                    documento.add(new Paragraph(" "));
                }
            }

            documento.close();
            System.out.println("PDF generado en: " + ruta);

        } catch (Exception e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
        }
    }
}