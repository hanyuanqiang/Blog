package com.hyq.util;


import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;

/**
 * Created by Administrator on 2016/7/20.
 */
public class HtmlParser {

    public static String getHtmlParser(String string)throws Exception{
        Parser parser = new Parser(string);
        TextExtractingVisitor visitor = new TextExtractingVisitor();
        parser.visitAllNodesWith(visitor);
        return visitor.getExtractedText();
    }

}
