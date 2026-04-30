package cm.enspm.studia.service;

import cm.enspm.studia.model.Evaluation;
import cm.enspm.studia.model.Eleve;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReportCardGenerator {

    public static void generateReportCard(Eleve eleve, List<Evaluation> evaluations, File outputFile) throws IOException {
        if (eleve == null) {
            throw new IllegalArgumentException("L'élève ne peut pas être null");
        }

        File parentFolder = outputFile.getParentFile();
        if (parentFolder != null && !parentFolder.exists()) {
            parentFolder.mkdirs();
        }

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 16);
                content.newLineAtOffset(50, 720);
                content.showText("Bulletin scolaire / Report Card");
                content.newLineAtOffset(0, -24);
                content.setFont(PDType1Font.HELVETICA, 12);
                content.showText("Matricule: " + eleve.getMatricule());
                content.newLineAtOffset(0, -18);
                content.showText("Nom: " + eleve.getNomComplet());
                content.newLineAtOffset(0, -18);
                content.showText("Date de naissance: " + eleve.getDateNaissance());
                content.newLineAtOffset(0, -18);
                content.showText("Lieu de naissance: " + eleve.getLieuNaissance());
                content.newLineAtOffset(0, -18);
                content.showText("Sexe: " + eleve.getSexe() + "    Nationalité: " + eleve.getNationalite());
                content.newLineAtOffset(0, -30);
                content.setFont(PDType1Font.HELVETICA_BOLD, 13);
                content.showText("Matière                    Note   Séquence   Commentaire");
                content.setFont(PDType1Font.HELVETICA, 11);
                content.newLineAtOffset(0, -18);

                if (evaluations.isEmpty()) {
                    content.showText("Aucune évaluation disponible pour cet élève.");
                    content.endText();
                } else {
                    for (Evaluation evaluation : evaluations) {
                        String matiere = evaluation.getMatiere().getLibelle();
                        String note = String.format("%.1f", evaluation.getNote());
                        String sequence = evaluation.getSequence().getLibelle();
                        String commentaire = evaluation.getCommentaire();
                        String line = String.format("%-25s %-5s %-12s %s", truncate(matiere, 24), note, truncate(sequence, 12), truncate(commentaire, 36));
                        content.showText(line);
                        content.newLineAtOffset(0, -16);
                    }
                    content.endText();
                }
            }

            document.save(outputFile);
        }
    }

    private static String truncate(String value, int length) {
        if (value == null) {
            return "";
        }
        if (value.length() <= length) {
            return value;
        }
        return value.substring(0, length - 3) + "...";
    }
}
