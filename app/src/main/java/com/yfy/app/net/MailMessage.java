package com.yfy.app.net;//package com.yfy.app.net;
//
//import org.simpleframework.xml.Attribute;
//import org.simpleframework.xml.Element;
//import org.simpleframework.xml.Root;
//import org.simpleframework.xml.core.Commit;
//import org.simpleframework.xml.core.Complete;
//import org.simpleframework.xml.core.Persist;
//
//import java.util.Base64.Encoder;
//
//@Root
//public class MailMessage {
//
//    @Attribute
//    private String format;
//
//    @Element
//    private String encoded;
//
//    private byte[] content;
//
//    private Encoder encoder;
//
//    public MailMessage() {
//        this.encoder = new Encoder();
//    }
//
//    public void setEncoding(String format) {
//        this.format = format;
//    }
//
//    public String getEncoding() {
//        return format;
//    }
//
//    public void setMessage(byte[] content) {
//        this.content = content;
//    }
//
//    public byte[] getMessage() {
//        return content;
//    }
//
//    @Commit
//    public void commit() {
//        decoded = encoder.decode(encoded, format);
//        encoded = null;
//    }
//
//    @Persist
//    public void prepare() {
//        encoded = encoder.encode(decoded, format);
//    }
//
//    @Complete
//    public void release() {
//        encoded = null;
//    }
//}