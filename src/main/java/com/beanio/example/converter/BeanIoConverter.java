package com.beanio.example.converter;

import com.beanio.example.beans.Contact;
import com.beanio.example.beans.Header;
import com.beanio.example.beans.Record;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Service
public class BeanIoConverter {

    @Value("classpath:contacts.xml")
    public Resource resource;

    @Value("classpath:output.csv")
    public Resource outputResource;

    public Resource getOutputResource() {
        return outputResource;
    }

    public void setOutputResource(Resource outputResource) {
        this.outputResource = outputResource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void convert() throws Exception {
        // create a BeanIO StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file from the working directory
        factory.load(resource.getFile());

        BeanWriter out = getBeanWriter(resource,"contacts", outputResource.getFilename());

        List<Record> recordList = getRecords();
        recordList.forEach(out::write);

        out.flush();
        out.close();
    }

    private static BeanWriter getBeanWriter(Resource resource,String mapping,String fileName) throws IOException {
        StreamFactory streamFactory = StreamFactory.newInstance();
        FileInputStream file = new FileInputStream(resource.getFilename());
        streamFactory.load(file);
        return streamFactory.createWriter(mapping,new File(fileName));
    }

    public List<Record> getRecords() {
        Record record = new Record();
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact = new Contact();
        contact.setCity("Mumbai");
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setState("MH");
        contact.setStreet("Adam Street");
        contact.setZip("421033");

        Contact contact1 = new Contact();
        contact1.setCity("Mumbai");
        contact1.setFirstName("John");
        contact1.setLastName("Doe");
        contact1.setState("MH");
        contact1.setStreet("Adam Street");
        contact1.setZip("421033");
        contactList.add(contact);
        contactList.add(contact1);
        Header header = new Header();
        header.setCity("CITY");
        header.setName("NAME");
        header.setSurname("SURNAME");
        header.setState("STATE");
        header.setStreet("STREET");
        header.setZip("ZIP");
        record.setContactList(contactList);
        record.setHeader(header);
        return  Arrays.asList(record);
    }
}
