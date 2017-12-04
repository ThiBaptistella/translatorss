package au.com.translatorss.service.impl;

import au.com.translatorss.service.PDFService;
import au.com.translatorss.service.pdf.ITextRendererWithFooters;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.InputStream;
import java.io.OutputStream;

@Service
public class PDFServiceImpl implements PDFService{
        private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Override
    public void createDocument(InputStream template, OutputStream os, Character pdfVersion, boolean includeAutoPrint) {

        //1. Create flying saucer renderer
        ITextRendererWithFooters renderer = new ITextRendererWithFooters(28.6f, 20);
        renderer.setFooterHeight(2200);
//        ITextRenderer renderer = new ITextRenderer();

        //2. Create document from html template
        Document doc = XMLResource.load(template).getDocument();

        SharedContext sharedContext = renderer.getSharedContext();
        sharedContext.setPrint(true);
        sharedContext.setInteractive(false);
        sharedContext.getTextRenderer().setSmoothingThreshold(0);


        //4. Feed document to renderer
        renderer.setDocument(doc, "");

        //5. Set pdf version, if it was provided
        if (pdfVersion != null) {
            renderer.setPDFVersion(pdfVersion);
        }

        //6. Do the rendering
        renderer.layout();

        try {
            renderer.createPDF(os);
        } catch (DocumentException e) {
            _logger.warn("createDocument", e);
        }
    }
}
