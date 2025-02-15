package com.tech.service.pdf;

import java.io.IOException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class PdfParagraph extends Paragraph {

    private static final long serialVersionUID = -244970043180837974L;

    public PdfParagraph(String content) {
        super(content, getChineseFont(10, false));
    }

    public PdfParagraph(String content, int fontSize, boolean isBold) {
        super(content, getChineseFont(fontSize, isBold));
    }

    public PdfParagraph(String content, int fontSize) {
        super(content, getChineseFontUnderLine(fontSize));
    }

    // 设置字体-返回中文字体
    protected static Font getChineseFont(int nfontsize, boolean isBold) {
        BaseFont bfChinese;
        Font fontChinese = null;
        try {
            bfChinese = BaseFont.createFont(PdfParagraph.class.getResource("")+ "simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            if (isBold) {
                fontChinese = new Font(bfChinese, nfontsize, Font.BOLD);
            } else {
                fontChinese = new Font(bfChinese, nfontsize, Font.NORMAL);
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fontChinese;
    }

    // 设置字体-返回中文字体
    protected static Font getChineseFontUnderLine(int nfontsize) {
        BaseFont bfChinese;
        Font fontChinese = null;
        try {
            bfChinese = BaseFont.createFont(PdfParagraph.class.getResource("")+ "simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            fontChinese = new Font(bfChinese, nfontsize, Font.UNDERLINE);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fontChinese;
    }

    // 转化中文
    protected Cell ChangeCell(String str, int nfontsize, boolean isBold) throws IOException, BadElementException,
            DocumentException {
        Phrase ph = ChangeChinese(str, nfontsize, isBold);
        Cell cell = new Cell(ph);
        cell.setBorderWidth(3);

        return cell;
    }

    // 转化中文
    protected Chunk ChangeChunk(String str, int nfontsize, boolean isBold) throws IOException, BadElementException,
            DocumentException {
        Font FontChinese = getChineseFont(nfontsize, isBold);
        Chunk chunk = new Chunk(str, FontChinese);
        return chunk;
    }

    // 转化中文
    protected Phrase ChangeChinese(String str, int nfontsize, boolean isBold) throws IOException, BadElementException,
            DocumentException {
        Font FontChinese = getChineseFont(nfontsize, isBold);
        Phrase ph = new Phrase(str, FontChinese);
        return ph;
    }

}
