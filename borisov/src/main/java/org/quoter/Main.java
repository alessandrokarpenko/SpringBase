package org.quoter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * by https://youtu.be/BmBr5diz8WA
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("context.xml");
        classPathXmlApplicationContext.getBean(Quoter.class).sayQuote();
    }

}
