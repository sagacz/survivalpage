package com.syndicate.helloServer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopicTest {

    @Test
    public void createTopicHasNoMessages(){
        Topic created = Topic.create("testowy");

        assertTrue(created.messages.isEmpty());

    }


    @Test
    public void cretedTopicHasCorrectName(){
        Topic created = Topic.create("testowy2");
        assertTrue(created.name.contentEquals("testowy2") );
    }

    @Test
    public void createdAfterAaddMessageTopicHasOneMessage(){
        Topic created = Topic.create("testowy3");
        Topic newTopic = created.addMessage(new Message("test", "Me"));
        assertTrue(newTopic.messages.length() == 1);

    }
}