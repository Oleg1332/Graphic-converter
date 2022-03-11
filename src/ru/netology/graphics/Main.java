package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverterImpl;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new TextGraphicsConverterImpl();

        GServer server = new GServer(converter);
        server.start();


        // String url = "https://ukrmemoria.com/images/8/83/%D0%9D%D0%B5%D0%BA%D0%BE_%D0%90%D1%80%D0%BA.png";

        //String imgTxt = converter.convert(url);
        //System.out.println(imgTxt);
    }
}



