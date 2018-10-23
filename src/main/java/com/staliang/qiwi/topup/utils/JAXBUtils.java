package com.staliang.qiwi.topup.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBUtils {

    public static <T> String toXML(T object) throws JAXBException, IOException {
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(object, writer);
            return writer.toString();
        }
    }

    public static  <T> T fromXML(String xml, Class<T> clazz) throws JAXBException {
        try (StringReader reader = new StringReader(xml)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(reader);
        }
    }
}
