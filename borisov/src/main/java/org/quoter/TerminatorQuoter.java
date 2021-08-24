package org.quoter;

import javax.annotation.PostConstruct;

@Profiling
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    @PostConstruct
    public void init() {
        System.out.println("PHASE 2: init method");
        System.out.println(repeat);
    }

    public TerminatorQuoter() {
        System.out.println("PHASE 1: constructor");
    }

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("PHASE 3: post proxy");
        for (int i = 0; i < repeat; i++) {
            System.out.println("Message: " + message);
        }

        String a = new String("jsdf");
        a.intern();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
