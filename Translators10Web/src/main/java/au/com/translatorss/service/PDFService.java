package au.com.translatorss.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface PDFService {
    void createDocument(InputStream template, OutputStream os, Character pdfVersion, boolean includeAutoPrint);
}
