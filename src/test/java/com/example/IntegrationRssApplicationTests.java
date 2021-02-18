package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import com.rometools.rome.feed.synd.SyndEntryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.io.*;

@SpringBootTest({"auto.startup=false", "feed.file.name=Test"})
class IntegrationRssApplicationTests {

    @Autowired
    private SourcePollingChannelAdapter newsAdapter;

    @Autowired
    private MessageChannel news;


    @Test
    void test() throws IOException {
        assertThat(this.newsAdapter.isRunning()).isFalse();
        SyndEntryImpl entry = new SyndEntryImpl();
        entry.setTitle("new Title");
        entry.setLink("http://test/integrationrss");
        File output = new File("./tmp/si/Test");
        output.delete();
        assertThat(output.exists()).isFalse();
        this.news.send(MessageBuilder.withPayload(entry).build());
        assertThat(output.exists()).isTrue();

        BufferedReader br = new BufferedReader(new FileReader(output));
        String line = br.readLine();
        assertThat("new Title @ http://test/integrationrss".equals(line));

        br.close();
        output.delete();


    }

}
