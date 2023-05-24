package com.banking.sys.service;

import com.banking.sys.model.Account;
import com.banking.sys.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class ExtrasService {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    TransactionServiceImpl transactionService;
    public void createPdf( String iban) {
        Document document = new Document();
        try {
            Account cont = accountService.getAccountByIBAN(iban);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Extras.pdf"));
            document.open();
            document.addTitle("Extras de cont");
            document.add(new Paragraph("IBAN: " + iban));
            document.add(new Paragraph( "Sold = "+ cont.getSold().toString()));
            document.add(new Paragraph("---------------------------------------------------------------------------"));
            if(cont.getSentTransactions()!=null){
                List<Transaction> aux = transactionService.findAllCompletedTransactionsByAccount(cont);
                for(int i = 0 ; i < aux.size(); i++){
                    document.add(new Paragraph("Tip tranzactie: " + aux.get(i).getTip()) );
                    document.add(new Paragraph("De la "  + aux.get(i).getDeLa().getIBAN()));
                    document.add(new Paragraph("Catre "  + aux.get(i).getCatre().getIBAN()));
                    document.add(new Paragraph("Valoare "  + aux.get(i).getValoare()));
                    document.add(new Paragraph("Data: " + aux.get(i).getData()));
                    document.add(new Paragraph("---------------------------------------------------------------------------"));
                }
            }
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
