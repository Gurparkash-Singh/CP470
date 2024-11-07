package com.example.androidassignments;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChatWindowTest {

    @Mock
    private ChatWindow chatWindow;

    @Mock
    private MenuItem mockMenuItem;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        chatWindow = spy(new ChatWindow());
    }

    @After
    public void tearDown() throws Exception {
        chatWindow = null;
    }

    @Test
    public void testOnCreate() {
        doNothing().when(chatWindow).setContentView(anyInt());
        chatWindow.onCreate(null);
        verify(chatWindow).setContentView(anyInt());
    }

    @Test
    public void testOnOptionsItemSelected_HomeButton() {
        when(mockMenuItem.getItemId()).thenReturn(android.R.id.home);
        boolean result = chatWindow.onOptionsItemSelected(mockMenuItem);
        assertTrue(result);
    }

    @Test
    public void testOnOptionsItemSelected_Other() {
        when(mockMenuItem.getItemId()).thenReturn(123); // Any other menu item
        boolean result = chatWindow.onOptionsItemSelected(mockMenuItem);
        assertFalse(result);
    }

    @Test
    public void testSendMessage_ValidMessage() {
        String validMessage = "Hello, World!";
        boolean result = chatWindow.sendMessage(validMessage);
        assertTrue(result);
        assertEquals(validMessage, chatWindow.getLastMessage());
    }

    @Test
    public void testSendMessage_EmptyMessage() {
        String emptyMessage = "";
        boolean result = chatWindow.sendMessage(emptyMessage);
        assertFalse(result);
    }

    @Test
    public void testSendMessage_NullMessage() {
        boolean result = chatWindow.sendMessage(null);
        assertFalse(result);
    }

    @Test
    public void testReceiveMessage() {
        String receivedMessage = "Received message";
        chatWindow.receiveMessage(receivedMessage);
        assertEquals(receivedMessage, chatWindow.getLastMessage());
    }

    @Test
    public void testGetLastMessage() {
        String message = "Last message test";
        chatWindow.sendMessage(message);
        assertEquals(message, chatWindow.getLastMessage());
    }
}
