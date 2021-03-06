package org.wikimedia.analytics.kraken.eventlogging;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Enumeration;

public class ParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws IOException {
        Parser parser = new Parser();
        Enumeration<URL> urls;
        urls = getClass().getClassLoader().getResources("funnel/src/test/resources/");
        FileInputStream stream = null;
        String jsonSchema;
        URL url;
        while (urls.hasMoreElements()) {
            url = urls.nextElement();
            System.out.println("Reading file: " + url.toString());
            stream = new FileInputStream(url.getFile());
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			/* Instead of using default, pass in a decoder. */
            jsonSchema = Charset.defaultCharset().decode(bb).toString();
            System.out.println(jsonSchema);
            parser.parseEventLoggingJsonSchem(jsonSchema);
        }
        if (stream != null) {
            stream.close();
        }
    }
}
